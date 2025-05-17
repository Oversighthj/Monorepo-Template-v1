# Routing Module

هذا المجلد يحتوي على تعاريف التنقل (Routing) داخل تطبيق Flutter باستخدام GoRouter.

## الهيكل

```text
pilot_app/lib/routes/
├── app_router.dart   # تكوين GoRouter مع قائمة المسارات
└── README.md         # هذا الملف
```

## app_router.dart

```dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import '../features/user/presentation/pages/user_page.dart';
import '../features/user/presentation/pages/user_form_page.dart';

final GoRouter router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) => const UserPage(),
    ),
    GoRoute(
      path: '/user-form',
      builder: (context, state) {
        final editingUser = state.extra as UserModel?;
        return UserFormPage(editingUser: editingUser);
      },
    ),
    // إضافة المزيد من المسارات حسب الحاجة
  ],
);
```

## التعليمات

1. **استخدام `router`** في `main.dart`:
   ```dart
   void main() {
     setupDependencies();
     runApp(
       MaterialApp.router(
         routerConfig: router,
         title: 'Pilot App',
       ),
     );
   }
   ```

2. **التنقل** إلى صفحة النموذج وإرسال بيانات:
   ```dart
   // للانتقال دون تعديل
   GoRouter.of(context).push('/user-form');

   // للانتقال مع كائن المستخدم للتحرير
   GoRouter.of(context).push(
     '/user-form',
     extra: existingUser,
   );
   ```

3. **التوجيه الشرطي** أو **المسارات المتفرعة** يمكن إعدادها بنفس الطريقة ضمن قائمة `routes`.

---

> هذا الملف يساعد على فهم كيفية إعداد واستخدام GoRouter في المشروع.

