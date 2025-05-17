# CI/CD Workflows

هذا المجلد يحتوي على إعدادات GitHub Actions لأتمتة بناء، توليد الكود، التحليل، والاختبارات قبل الدمج.

## هيكل المجلد

```text
.github/workflows/
└── ci.yaml     # التعريف الرئيسي لخط سير العمل (workflow)
```

## محتوى `ci.yaml`

```yaml
name: CI Pipeline

on:
  pull_request:
    branches: [ main ]
  push:
    branches: [ main ]

jobs:
  backend:
    name: Build & Generate Backend
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Generate OpenAPI code
        working-directory: demo
        run: mvn openapi-generator:generate
      - name: Build Backend
        working-directory: demo
        run: mvn compile

  flutter:
    name: Build & Test Flutter
    needs: backend
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up Flutter
        uses: subosito/flutter-action@v2
        with:
          channel: 'stable'
      - name: Install dependencies
        working-directory: pilot_app
        run: flutter pub get
      - name: Generate code
        working-directory: pilot_app
        run: flutter pub run build_runner build --delete-conflicting-outputs
      - name: Analyze
        working-directory: pilot_app
        run: flutter analyze --fatal-warnings
      - name: Run tests
        working-directory: pilot_app
        run: flutter test
```

## خطوات العمل

1. **Checkout**: يستخرج الكود من المستودع.
2. **Backend**:
   - توليد كود الـ API إنطلاقاً من `openapi.yaml`.
   - تجميع الكود ([`mvn compile`]).
3. **Flutter** (بعد نجاح الـ backend):
   - تثبيت التبعيات (`flutter pub get`).
   - توليد الكود (`build_runner`).
   - تحليل الكود (`flutter analyze --fatal-warnings`).
   - اختبارات الوحدة (`flutter test`).

## متى يُشغل

- عند كل **push** أو **pull request** على الفرع `main`.
- يضمن فشل الدمج إذا لم ينجح البناء أو التحليل أو الاختبارات.

---

> يساعد هذا الملف في فهم كيف يتم ضمان جودة الكود عبر CI/CD قبل دمجه إلى `main`.

