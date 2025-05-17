# Core Module

هذا المجلد يحتوي على المكونات والأدوات المشتركة التي يستخدمها تطبيق Flutter في أجزاء مختلفة.

## الهيكل

```text
pilot_app/lib/core/
├── di/                             # إعداد حقن التبعيات
│   └── service_locator.dart        # تكوين GetIt
└── README.md                       # هذا الملف
```

## المحتوى

### 1. `di/service_locator.dart`
- يستخدم `get_it` كـ Service Locator لحقن التبعيات عبر ساكن (`singleton`) أو بناء عند الطلب (`lazySingleton`).
- مثال للإعداد:
  ```dart
  import 'package:get_it/get_it.dart';
  import '../../features/user/data/user_remote_data_source.dart';
  
  final getIt = GetIt.instance;

  void setupDependencies() {
    getIt.registerLazySingleton<UserRemoteDataSource>(
      () => UserRemoteDataSource(getIt()),
    );
    getIt.registerLazySingleton<UserRepository>(
      () => UserRepositoryImpl(getIt()),
    );
    // المزيد من الخدمات المشتركة
  }
  ```
- استدعِ `setupDependencies()` في `main.dart` قبل تشغيل التطبيق.

## كيف تستخدم

1. في `main.dart`:
   ```dart
   void main() {
     setupDependencies();
     runApp(const MyApp());
   }
   ```
2. لأي خدمة مسجلة:
   ```dart
   final userRepo = getIt<UserRepository>();
   ```

---

> هذا README يوضح كيفية إعداد واستخدام مكونات DI المشتركة في المشروع.

