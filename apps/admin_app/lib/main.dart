// admin_app/lib/main.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'core/di/service_locator.dart' as di;
import 'routes/app_router.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await di.initServiceLocator(); // تهيئة GetIt (أو أي DI) قبل التشغيل

  runApp(
    const ProviderScope(
      child: AdminApp(), // الاسم قابـل للتغيير لاحقًا إن رغبت
    ),
  );
}

class AdminApp extends StatelessWidget {
  const AdminApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'Admin App',
      theme: ThemeData(useMaterial3: true),
      routerConfig: router, // يحمِّل مسارات GoRouter من app_router.dart
    );
  }
}
