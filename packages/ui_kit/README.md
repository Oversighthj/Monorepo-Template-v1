# ui_kit

A shared collection of reusable Flutter widgets that provide a consistent look and feel across the mono-repo apps (`admin_app`, `guest_app` and `cleaner_app`).

The package is kept lightweight so common UI elements can be updated in one place and consumed by each application.

## Installation

Add a dependency on `ui_kit` in your app's `pubspec.yaml` using a local path reference:

```yaml
dependencies:
  ui_kit:
    path: ../../packages/ui_kit
```

Import the package in Dart files where you need the shared widgets:

```dart
import 'package:ui_kit/ui_kit.dart';
```

## Available widgets

At the moment the package only exposes a simple `Calculator` class as a demo. New widgets can be added under `lib/` and exported through `ui_kit.dart`.

```dart
final calculator = Calculator();
final result = calculator.addOne(2); // returns 3
```

## Contributing

Feel free to extend the kit with additional components that can be reused by all apps. Pull requests are welcome!
