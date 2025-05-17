// integration_test/app_test.dart

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';

// استورد الملف الذي يشغّل تطبيقك عادةً
// عادةً نضعه بهذا الشكل إن كان لديك ملف main.dart
// تحتوي على void main() => runApp(MyApp());
import 'package:pilot_app/main.dart' as app;

void main() {
  // Binding لاختبارات التكامل
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  testWidgets('full CRUD flow', (WidgetTester tester) async {
    // 1. شغّل التطبيق
    app.main(); 
    await tester.pumpAndSettle();

    // 2. تأكد الصفحة الأساسية تحتوي على العنوان "Users"
    expect(find.text('Users'), findsOneWidget);

    // 3. اضغط زر الإضافة
    await tester.tap(find.byIcon(Icons.add));
    await tester.pumpAndSettle();

    // 4. املأ النموذج وأضف مستخدم جديد
    await tester.enterText(find.byType(TextField).at(0), 'Bob');
    await tester.enterText(find.byType(TextField).at(1), 'bob@example.com');
    await tester.tap(find.text('إضافة'));
    await tester.pumpAndSettle();

    // 5. تأكد من ظهور السطر الجديد
    expect(find.text('Bob'), findsOneWidget);

    // 6. عدّل المستخدم
    await tester.tap(find.byIcon(Icons.edit).first);
    await tester.pumpAndSettle();
    await tester.enterText(find.byType(TextField).at(0), 'Bobby');
    await tester.tap(find.text('حفظ التعديلات'));
    await tester.pumpAndSettle();
    expect(find.text('Bobby'), findsOneWidget);

    // 7. احذف المستخدم
    await tester.tap(find.byIcon(Icons.delete).first);
    await tester.pumpAndSettle();
    expect(find.text('Bobby'), findsNothing);
  });
}
