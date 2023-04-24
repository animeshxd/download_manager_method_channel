import 'package:flutter/material.dart';

import 'downloader.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'DownloadManager MethodChannel',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const DownloadPage(),
    );
  }
}

class DownloadPage extends StatefulWidget {
  const DownloadPage({Key? key}) : super(key: key);

  @override
  State<DownloadPage> createState() => _DownloadPageState();
}

class _DownloadPageState extends State<DownloadPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ElevatedButton.icon(
          onPressed: () async {
            await downloadFile(
              url: 'http://192.168.0.154:8975/file/0',
              filename: 'file.txt',
            );
          },
          icon: const Icon(Icons.download),
          label: const Text('download'),
        ),
      ),
    );
  }
}
