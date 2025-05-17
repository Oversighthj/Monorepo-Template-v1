# Dependency Injection (DI)

هذا المجلد يختص بإعداد حقن التبعيات (Dependency Injection) لتسهيل إدارة وإنشاء الخدمات ضمن تطبيق Flutter.

## الهيكل

```text
pilot_app/lib/core/di/
└── service_locator.dart    # تكوين GetIt لتسجيل واسترجاع الخدمات
```

## الإعداد في `service_locator.dart`

```dart
import 'package:get_it/get_it.dart';
import '../../features/user/data/user_remote_data_source.dart';
import '../../features/user/data/user_repository_impl.dart';

final getIt = GetIt.instance;

void setupDependencies() {
  // تسجيل بيانات المستخدم
  getIt.registerLazySingleton<UserRemoteDataSource>(
    () => UserRemoteDataSource(getIt()),
  );
  getIt.registerLazySingleton<UserRepository>(
    () => UserRepositoryImpl(getIt()),
  );

  // إضافة خدمات أخرى عند الحاجة
}
```

## كيفية الاستخدام

1. **تهيئة DI قبل تشغيل التطبيق** في `main.dart`:
   ```dart
   void main() {
     setupDependencies();
     runApp(const MyApp());
   }
   ```
2. **استرجاع الخدمات** في أي مكان:
   ```dart
   final userRepo = getIt<UserRepository>();
   ```

## ملاحظات

- **LazySingleton**: يتم إنشاء الكائن لأول مرة عند الطلب.
- **RegisterFactory**: يُستخدم لإنشاء كائن جديد في كل مرة (يمكن إضافته حسب الحاجة).

---

> يُفضل إضافة التسجيلات اللازمة لكل خدمة جديدة ضمن هذه الدالة لضمان سهولة الصيانة والاختبار.

