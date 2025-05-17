// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility in the flutter_test package. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:dio/dio.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart';

import 'package:admin_app/features/feature/feature_page.dart';

class _MockApi extends Mock implements DefaultApi {}

void main() {
  testWidgets('FeaturePage renders list', (WidgetTester tester) async {
    final mock = _MockApi();

    // جهّز استجابة وهميّة
    when(() => mock.featureGet()).thenAnswer((_) async => Response(
          data: BuiltList<FeatureDTO>([
            FeatureDTO((b) => b
              ..id = 1
              ..name = 'Mock'),
          ]),
          statusCode: 200,
          requestOptions: RequestOptions(path: ''),
        ));

    await tester.pumpWidget(
      MaterialApp(
        home: FeaturePage(apiOverride: mock),
      ),
    );

    // امنح الإطار وقتًا لبناء الواجهة
    await tester.pump();

    expect(find.text('Mock'), findsOneWidget);
  });
}
