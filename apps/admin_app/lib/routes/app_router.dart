import 'package:go_router/go_router.dart';
import '../features/booking/pages/booking_list_page.dart';

/// ضبط مسارات التطبيق (حالياً مسار واحد للوحة الحجوزات)
final router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (_, __) => const BookingListPage(),
    ),
  ],
);
