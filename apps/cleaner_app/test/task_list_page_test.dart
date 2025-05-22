import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';
import 'package:dio/dio.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart' as gen;

import 'package:cleaner_app/features/task/pages/task_list_page.dart';

class _MockTaskApi extends Mock implements gen.TaskApi {}

void main() {
  setUpAll(() {
    registerFallbackValue(RequestOptions(path: '/'));
  });

  testWidgets('TaskListPage renders tasks', (tester) async {
    final api = _MockTaskApi();

    final task = gen.TaskDTO((b) => b
      ..id = 1
      ..bookingId = 1
      ..cleanerId = 3
      ..type = 'CLEANING'
      ..due = DateTime(2025, 6, 3, 9).toUtc()
      ..status = gen.TaskStatus.PENDING);

    when(() => api.tasksGet()).thenAnswer(
      (_) async => Response(
        data: BuiltList<gen.TaskDTO>([task]),
        statusCode: 200,
        requestOptions: RequestOptions(path: '/tasks'),
      ),
    );

    await tester.pumpWidget(
      MaterialApp(home: TaskListPage(apiOverride: api)),
    );

    await tester.pumpAndSettle();

    expect(find.textContaining('Task #1'), findsOneWidget);
    expect(find.textContaining('Booking: 1'), findsOneWidget);
  });
}
