import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'core/di/service_locator.dart' as di;
import 'routes/app_router.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await di.setupLocator();

  runApp(
    const ProviderScope(child: CleanerApp()),
  );
}

class CleanerApp extends StatelessWidget {
  const CleanerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'Cleaner App',
      theme: ThemeData(useMaterial3: true),
      routerConfig: router,
    );
  }
}
