// guest_app/lib/routes/app_router.dart
import 'package:go_router/go_router.dart';
import 'package:guest_app/features/property/pages/property_list_page.dart';

final router = GoRouter(   // ← غيّرنا هنا من routes → router
  routes: [
    GoRoute(
      path: '/',
      builder: (_, __) => const PropertyListPage(),
    ),
  ],
);
