package com.example.download_manager_method_channel

import android.app.DownloadManager
import android.app.DownloadManager.Request
import android.os.Environment
import androidx.core.net.toUri
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val TAG = "MainActivity"
    private val CHANNEL = "com.example.download_manager_method_channel/download"

    private fun downloadFile(url: String, filename: String){
        Log.d(TAG, "downloadFile called for url: $url, filename: $filename")
        val downloadManager = getSystemService(DownloadManager::class.java)
        downloadManager.enqueue(Request(url.toUri()).apply {
            setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
        })
    }


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            when(call.method) {
                "download" -> {
//                    Log.d("main", call.arguments.toString())
                    val url = call.argument<String>("url")!!
                    val filename = call.argument<String>("filename")!!
                    downloadFile(url, filename)
                    result.success(true)
                }
               else -> result.notImplemented()
            }

        }

    }
}
