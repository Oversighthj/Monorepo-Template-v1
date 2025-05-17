# pilot_app

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://docs.flutter.dev/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://docs.flutter.dev/cookbook)

For help getting started with Flutter development, view the
[online documentation](https://docs.flutter.dev/), which offers tutorials,
samples, guidance on mobile development, and a full API reference.




# Flutter App - `pilot_app/`

هذا المجلد يحتوي على تطبيق Flutter المسؤول عن واجهة المستخدم (Frontend) للمشروع.

## نظرة عامة

- يستخدم Flutter 3.x
- بنية تعتمد على **features** لكل مجال (feature-driven)
- أدوات إدارة الحالة **Riverpod**
- التوجيه عبر **GoRouter**
- التوليد التلقائي للكود باستخدام **Freezed** و**json_serializable**
- حقن التبعيات عبر **get_it**

## هيكل المجلدات

```text
pilot_app/
├── pubspec.yaml                         # تعريف التبعيات
├── lib/
│   ├── core/                            # الأدوات المشتركة والخدمات
│   │   ├── di/
│   │   │   └── service_locator.dart     # إعداد get_it
│   │   └── README.md                    # يُشرح الأدوات المشتركة
│   ├── features/
│   │   ├── transport/                   # ميزة النقل
│   │   │   ├── models/                  # نماذج البيانات (@JsonSerializable)
│   │   │   │   └── transport_model.dart
│   │   │   └── README.md                # توثيق ميزة النقل
│   │   └── user/                        # ميزة المستخدم
│   │       ├── data/                    # طبقات البيانات
│   │       ├── presentation/            # الواجهات الصفحية
│   │       └── models/                  # user_model.dart
│   │       └── README.md                # توثيق ميزة المستخدم
│   ├── routes/                          # تعريف GoRouter
│   │   ├── app_router.dart
│   │   └── README.md                    # شرح التوجيه
│   └── main.dart                        # نقطة الانطلاق
├── test/                                # اختبارات الوحدات
└── README.md                            # (هذا الملف)
```

## التبعيات الرئيسية (pubspec.yaml)

```yaml
dependencies:
  flutter:
    sdk: flutter
  go_router: ^7.1.1
  flutter_riverpod: ^2.0.0
  get_it: ^7.7.0
  json_annotation: ^4.0.0
  freezed_annotation: ^2.4.4

dev_dependencies:
  build_runner: ^2.0.0
  json_serializable: ^6.0.0
  freezed: ^2.5.8
  flutter_test:
    sdk: flutter
```

- **Freezed & json_serializable**: لتوليد نماذج البيانات والكود الآمن.  
- **build_runner**: لتشغيل التوليد بامر:  
  ```bash
  flutter pub run build_runner build --delete-conflicting-outputs
  ```

## إعداد DI (Dependency Injection)

في `lib/core/di/service_locator.dart`:
```dart
final getIt = GetIt.instance;

void setupDependencies() {
  getIt.registerLazySingleton<ApiClient>(() => ApiClient());
  // خدمات أخرى
}
```

## التوجيه (Routing)

في `lib/routes/app_router.dart`:
```dart
final _router = GoRouter(routes: [
  GoRoute(path: '/', builder: (context, state) => HomePage()),
  GoRoute(path: '/user', builder: (context, state) => UserPage()),
]);
```

## تشغيل وبناء المشروع

```bash
cd pilot_app
flutter pub get
flutter pub run build_runner build --delete-conflicting-outputs
flutter analyze
flutter test
flutter run
```

---

> هذا README يعطي نظرة شاملة على مشروع Flutter، وبقية ملفات README ضمن المجلدات المتخصصة توضح التفاصيل لكل ميزة.


