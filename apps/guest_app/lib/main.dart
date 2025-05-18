// guest_app/lib/main.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'core/di/service_locator.dart' as di;
import 'routes/app_router.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await di.initServiceLocator(); // تهيئة GetIt أو أي DI آخر

  runApp(
    const ProviderScope(
      child: GuestApp(), // يمكنك لاحقاً تغيير الاسم إلى TemplateApp
    ),
  );
}

class GuestApp extends StatelessWidget {
  const GuestApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'Guest App', // عدّل العنوان إذا أردت
      theme: ThemeData(useMaterial3: true),
      routerConfig: router, // يأتي من app_router.dart
    );
  }
}
