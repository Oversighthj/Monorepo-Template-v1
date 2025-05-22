import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:dio/dio.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart' as gen;

import 'package:admin_app/features/booking/pages/booking_list_page.dart';

class _MockBookingApi extends Mock implements gen.BookingApi {}

void main() {
  setUpAll(() {
    registerFallbackValue(RequestOptions(path: '/'));
  });

  testWidgets('BookingListPage renders returned bookings', (tester) async {
    final api = _MockBookingApi();

    final booking = gen.BookingDTO((b) => b
      ..id = 1
      ..propertyId = 1
      ..userId = 2
      ..startAt = DateTime(2025, 6, 1).toUtc()
      ..endAt = DateTime(2025, 6, 5).toUtc()
      ..status = gen.BookingStatus.PENDING);

    when(() => api.bookingsGet()).thenAnswer(
      (_) async => Response(
        data: BuiltList<gen.BookingDTO>([booking]),
        statusCode: 200,
        requestOptions: RequestOptions(path: '/bookings'),
      ),
    );

    await tester.pumpWidget(
      MaterialApp(home: BookingListPage(apiOverride: api)),
    );

    await tester.pumpAndSettle();

    expect(find.text('Booking #1'), findsOneWidget);
    expect(find.textContaining('Property: 1'), findsOneWidget);
  });
}
