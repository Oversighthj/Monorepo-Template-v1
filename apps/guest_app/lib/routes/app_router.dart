// guest_app/lib/routes/app_router.dart
import 'package:go_router/go_router.dart';
import 'package:guest_app/features/property/pages/property_list_page.dart';
import 'package:guest_app/features/booking/pages/booking_form_page.dart';

final router = GoRouter(
  routes: [
    // الصفحة الرئيسة للملكيّات
    GoRoute(
      path: '/',
      builder: (_, __) => const PropertyListPage(),
    ),
    // صفحة الحجز
    GoRoute(
      path: '/booking/:propertyId',
      builder: (context, state) {
        final id = int.parse(state.pathParameters['propertyId']!);
        return BookingFormPage(propertyId: id);
      },
    ),
  ],
);