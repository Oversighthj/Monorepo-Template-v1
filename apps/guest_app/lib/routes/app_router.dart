// guest_app/lib/routes/app_router.dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

import '../features/feature/feature_page.dart'; // تأكّد من مسار الصفحة

final GoRouter router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (BuildContext context, GoRouterState state) =>
          const FeaturePage(),
    ),
  ],
);
