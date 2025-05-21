import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:dio/dio.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart' as gen;

import 'package:guest_app/features/property/pages/property_list_page.dart';

class _MockApi extends Mock implements gen.DefaultApi {}

void main() {
  // لتجنّب تحذير Mocktail حول RequestOptions
  setUpAll(() {
    registerFallbackValue(RequestOptions(path: '/'));
  });

  testWidgets('PropertyListPage displays properties from API',
      (WidgetTester tester) async {
    final api = _MockApi();

    // نموذج ملكيّة مزيّفة
    final property = gen.PropertyDTO((b) => b
      ..id = 1
      ..name = 'Sample Property'
      ..address = '123 Test Street'
      ..ownerId = 1);

    // تهيئة استجابة API مزيّفة
    when(() => api.propertiesGet()).thenAnswer(
      (_) async => Response<BuiltList<gen.PropertyDTO>>(
        data: BuiltList<gen.PropertyDTO>([property]),
        statusCode: 200,
        requestOptions: RequestOptions(path: '/properties'),
      ),
    );

    // ضخّ الـ API المزيّف في الصفحة
    await tester.pumpWidget(
      MaterialApp(home: PropertyListPage(apiOverride: api)),
    );

    // انتظر اكتمال Future ثم إعادة بناء الواجهة
    await tester.pumpAndSettle();

    // تحقّق من ظهور البيانات
    expect(find.text('Sample Property'), findsOneWidget);
    expect(find.text('123 Test Street'), findsOneWidget);
  });
}
