import 'package:flutter/services.dart';

const platform = MethodChannel('com.example.download_manager_method_channel/download');

Future<void> downloadFile({
  required String url,
  required String filename,
}) async {
  await platform.invokeMethod(
    "download",
    {
      'url': url,
      'filename': filename,
    },
  );
}