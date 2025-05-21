import 'package:dio/dio.dart';
import 'package:built_collection/built_collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:guest_app/features/property/pages/property_list_page.dart';
import 'package:mocktail/mocktail.dart';
import 'package:template_api/template_api.dart';

class _MockApi extends Mock implements DefaultApi {}

void main() {
  testWidgets('renders list of properties', (tester) async {
    final api = _MockApi();
    final property = PropertyDTO((b) => b
      ..id = 1
      ..name = 'Sample Property'
      ..address = 'Test Address'
      ..ownerId = 99        
      );

    when(() => api.propertiesGet()).thenAnswer((_) async =>
        Response(
          data: BuiltList([property]),
          statusCode: 200,
          requestOptions: RequestOptions(path: '/properties'),
        ));

    await tester.pumpWidget(MaterialApp(
      home: PropertyListPage(apiOverride: api),
    ));

    await tester.pumpAndSettle();

    expect(find.text('Sample Property'), findsOneWidget);
    expect(find.text('Test Address'), findsOneWidget);
  });
}
