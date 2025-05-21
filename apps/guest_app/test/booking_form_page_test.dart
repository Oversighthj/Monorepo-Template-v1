import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:dio/dio.dart';
import 'package:template_api/template_api.dart' as gen;

import 'package:guest_app/features/booking/pages/booking_form_page.dart';

class _MockApi extends Mock implements gen.BookingApi {}

void main() {
  setUpAll(() {
    registerFallbackValue(RequestOptions(path: '/'));

    // كائن BookingDTO كامل الحقول الإلزاميّة
    registerFallbackValue(
      gen.BookingDTO((b) => b
        ..propertyId = 0
        ..userId = 0
        ..startAt = DateTime.now().toUtc()
        ..endAt = DateTime.now().add(const Duration(days: 1)).toUtc()
        ..status = gen.BookingStatus.PENDING),
    );
  });

  testWidgets('BookingFormPage posts booking', (tester) async {
    final api = _MockApi();

    // محاكاة نجاح POST /bookings
    when(() => api.bookingsPost(bookingDTO: any(named: 'bookingDTO')))
        .thenAnswer((_) async => Response(
              requestOptions: RequestOptions(path: '/bookings'),
              statusCode: 201,
            ));

    await tester.pumpWidget(
      MaterialApp(
        home: BookingFormPage(propertyId: 1, apiOverride: api),
      ),
    );

    // اختر تواريخ البدء والنهاية
    await tester.tap(find.text('Start date'));
    await tester.pumpAndSettle();
    await tester.tap(find.byType(CalendarDatePicker).first);
    await tester.tap(find.text('OK'));
    await tester.pumpAndSettle();

    await tester.tap(find.text('End date'));
    await tester.pumpAndSettle();
    await tester.tap(find.byType(CalendarDatePicker).first);
    await tester.tap(find.text('OK'));
    await tester.pumpAndSettle();

    // اضغط زر Book
    await tester.tap(find.text('Book'));
    await tester.pump(); // يسمح بتنفيذ _submit

    // تأكد من استدعاء bookingsPost مرة واحدة
    verify(() => api.bookingsPost(bookingDTO: any(named: 'bookingDTO')))
        .called(1);
  });
}
