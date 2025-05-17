.
├── ./.github
│   └── ./.github/workflows
│       ├── ./.github/workflows/README.md
│       └── ./.github/workflows/ci.yaml
├── ./.gitignore
├── ./CONTRIBUTING.md
├── ./README.md
├── ./READMEAR.md
├── ./TREE.md
├── ./TREE2.md
├── ./USAGE.md
├── ./apps
│   ├── ./apps/admin_app
│   │   ├── ./apps/admin_app/.gitignore
│   │   ├── ./apps/admin_app/.metadata
│   │   ├── ./apps/admin_app/README.md
│   │   ├── ./apps/admin_app/analysis_options.yaml
│   │   ├── ./apps/admin_app/android
│   │   │   ├── ./apps/admin_app/android/.gitignore
│   │   │   ├── ./apps/admin_app/android/app
│   │   │   │   ├── ./apps/admin_app/android/app/build.gradle.kts
│   │   │   │   └── ./apps/admin_app/android/app/src
│   │   │   │       ├── ./apps/admin_app/android/app/src/debug
│   │   │   │       │   └── ./apps/admin_app/android/app/src/debug/AndroidManifest.xml
│   │   │   │       ├── ./apps/admin_app/android/app/src/main
│   │   │   │       │   ├── ./apps/admin_app/android/app/src/main/AndroidManifest.xml
│   │   │   │       │   ├── ./apps/admin_app/android/app/src/main/kotlin
│   │   │   │       │   │   └── ./apps/admin_app/android/app/src/main/kotlin/com
│   │   │   │       │   │       └── ./apps/admin_app/android/app/src/main/kotlin/com/yourco
│   │   │   │       │   │           └── ./apps/admin_app/android/app/src/main/kotlin/com/yourco/admin
│   │   │   │       │   │               └── ./apps/admin_app/android/app/src/main/kotlin/com/yourco/admin/MainActivity.kt
│   │   │   │       │   └── ./apps/admin_app/android/app/src/main/res
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/drawable
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/drawable/launch_background.xml
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/drawable-v21
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/drawable-v21/launch_background.xml
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/mipmap-hdpi
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/mipmap-hdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/mipmap-mdpi
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/mipmap-mdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/mipmap-xhdpi
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/mipmap-xhdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/mipmap-xxhdpi
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/mipmap-xxhdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/mipmap-xxxhdpi
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/admin_app/android/app/src/main/res/values
│   │   │   │       │       │   └── ./apps/admin_app/android/app/src/main/res/values/styles.xml
│   │   │   │       │       └── ./apps/admin_app/android/app/src/main/res/values-night
│   │   │   │       │           └── ./apps/admin_app/android/app/src/main/res/values-night/styles.xml
│   │   │   │       └── ./apps/admin_app/android/app/src/profile
│   │   │   │           └── ./apps/admin_app/android/app/src/profile/AndroidManifest.xml
│   │   │   ├── ./apps/admin_app/android/build.gradle.kts
│   │   │   ├── ./apps/admin_app/android/gradle
│   │   │   │   └── ./apps/admin_app/android/gradle/wrapper
│   │   │   │       └── ./apps/admin_app/android/gradle/wrapper/gradle-wrapper.properties
│   │   │   ├── ./apps/admin_app/android/gradle.properties
│   │   │   └── ./apps/admin_app/android/settings.gradle.kts
│   │   ├── ./apps/admin_app/coverage
│   │   │   └── ./apps/admin_app/coverage/lcov.info
│   │   ├── ./apps/admin_app/integration_test
│   │   │   └── ./apps/admin_app/integration_test/app_test.dart
│   │   ├── ./apps/admin_app/ios
│   │   │   ├── ./apps/admin_app/ios/.gitignore
│   │   │   ├── ./apps/admin_app/ios/Flutter
│   │   │   │   ├── ./apps/admin_app/ios/Flutter/AppFrameworkInfo.plist
│   │   │   │   ├── ./apps/admin_app/ios/Flutter/Debug.xcconfig
│   │   │   │   └── ./apps/admin_app/ios/Flutter/Release.xcconfig
│   │   │   ├── ./apps/admin_app/ios/Runner
│   │   │   │   ├── ./apps/admin_app/ios/Runner/AppDelegate.swift
│   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets
│   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Contents.json
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-1024x1024@1x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@1x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@2x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@3x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@1x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@2x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@3x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@1x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@2x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@3x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-60x60@2x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-60x60@3x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-76x76@1x.png
│   │   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-76x76@2x.png
│   │   │   │   │   │   └── ./apps/admin_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-83.5x83.5@2x.png
│   │   │   │   │   └── ./apps/admin_app/ios/Runner/Assets.xcassets/LaunchImage.imageset
│   │   │   │   │       ├── ./apps/admin_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/Contents.json
│   │   │   │   │       ├── ./apps/admin_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage.png
│   │   │   │   │       ├── ./apps/admin_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage@2x.png
│   │   │   │   │       ├── ./apps/admin_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage@3x.png
│   │   │   │   │       └── ./apps/admin_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/README.md
│   │   │   │   ├── ./apps/admin_app/ios/Runner/Base.lproj
│   │   │   │   │   ├── ./apps/admin_app/ios/Runner/Base.lproj/LaunchScreen.storyboard
│   │   │   │   │   └── ./apps/admin_app/ios/Runner/Base.lproj/Main.storyboard
│   │   │   │   ├── ./apps/admin_app/ios/Runner/Info.plist
│   │   │   │   └── ./apps/admin_app/ios/Runner/Runner-Bridging-Header.h
│   │   │   ├── ./apps/admin_app/ios/Runner.xcodeproj
│   │   │   │   ├── ./apps/admin_app/ios/Runner.xcodeproj/project.pbxproj
│   │   │   │   ├── ./apps/admin_app/ios/Runner.xcodeproj/project.xcworkspace
│   │   │   │   │   ├── ./apps/admin_app/ios/Runner.xcodeproj/project.xcworkspace/contents.xcworkspacedata
│   │   │   │   │   └── ./apps/admin_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata
│   │   │   │   │       ├── ./apps/admin_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   │   │       └── ./apps/admin_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata/WorkspaceSettings.xcsettings
│   │   │   │   └── ./apps/admin_app/ios/Runner.xcodeproj/xcshareddata
│   │   │   │       └── ./apps/admin_app/ios/Runner.xcodeproj/xcshareddata/xcschemes
│   │   │   │           └── ./apps/admin_app/ios/Runner.xcodeproj/xcshareddata/xcschemes/Runner.xcscheme
│   │   │   ├── ./apps/admin_app/ios/Runner.xcworkspace
│   │   │   │   ├── ./apps/admin_app/ios/Runner.xcworkspace/contents.xcworkspacedata
│   │   │   │   └── ./apps/admin_app/ios/Runner.xcworkspace/xcshareddata
│   │   │   │       ├── ./apps/admin_app/ios/Runner.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   │       └── ./apps/admin_app/ios/Runner.xcworkspace/xcshareddata/WorkspaceSettings.xcsettings
│   │   │   └── ./apps/admin_app/ios/RunnerTests
│   │   │       └── ./apps/admin_app/ios/RunnerTests/RunnerTests.swift
│   │   ├── ./apps/admin_app/lib
│   │   │   ├── ./apps/admin_app/lib/README.md
│   │   │   ├── ./apps/admin_app/lib/core
│   │   │   │   ├── ./apps/admin_app/lib/core/README.md
│   │   │   │   ├── ./apps/admin_app/lib/core/di
│   │   │   │   │   ├── ./apps/admin_app/lib/core/di/README.md
│   │   │   │   │   └── ./apps/admin_app/lib/core/di/service_locator.dart
│   │   │   │   ├── ./apps/admin_app/lib/core/errors
│   │   │   │   │   └── ./apps/admin_app/lib/core/errors/failure.dart
│   │   │   │   ├── ./apps/admin_app/lib/core/network
│   │   │   │   │   ├── ./apps/admin_app/lib/core/network/api_client.dart
│   │   │   │   │   └── ./apps/admin_app/lib/core/network/network_info.dart
│   │   │   │   └── ./apps/admin_app/lib/core/utils
│   │   │   │       └── ./apps/admin_app/lib/core/utils/constants.dart
│   │   │   ├── ./apps/admin_app/lib/features
│   │   │   │   └── ./apps/admin_app/lib/features/feature
│   │   │   │       └── ./apps/admin_app/lib/features/feature/feature_page.dart
│   │   │   ├── ./apps/admin_app/lib/main.dart
│   │   │   └── ./apps/admin_app/lib/routes
│   │   │       ├── ./apps/admin_app/lib/routes/README.md
│   │   │       └── ./apps/admin_app/lib/routes/app_router.dart
│   │   ├── ./apps/admin_app/linux
│   │   │   ├── ./apps/admin_app/linux/.gitignore
│   │   │   ├── ./apps/admin_app/linux/CMakeLists.txt
│   │   │   ├── ./apps/admin_app/linux/flutter
│   │   │   │   ├── ./apps/admin_app/linux/flutter/CMakeLists.txt
│   │   │   │   ├── ./apps/admin_app/linux/flutter/generated_plugin_registrant.cc
│   │   │   │   ├── ./apps/admin_app/linux/flutter/generated_plugin_registrant.h
│   │   │   │   └── ./apps/admin_app/linux/flutter/generated_plugins.cmake
│   │   │   └── ./apps/admin_app/linux/runner
│   │   │       ├── ./apps/admin_app/linux/runner/CMakeLists.txt
│   │   │       ├── ./apps/admin_app/linux/runner/main.cc
│   │   │       ├── ./apps/admin_app/linux/runner/my_application.cc
│   │   │       └── ./apps/admin_app/linux/runner/my_application.h
│   │   ├── ./apps/admin_app/macos
│   │   │   ├── ./apps/admin_app/macos/.gitignore
│   │   │   ├── ./apps/admin_app/macos/Flutter
│   │   │   │   ├── ./apps/admin_app/macos/Flutter/Flutter-Debug.xcconfig
│   │   │   │   ├── ./apps/admin_app/macos/Flutter/Flutter-Release.xcconfig
│   │   │   │   └── ./apps/admin_app/macos/Flutter/GeneratedPluginRegistrant.swift
│   │   │   ├── ./apps/admin_app/macos/Runner
│   │   │   │   ├── ./apps/admin_app/macos/Runner/AppDelegate.swift
│   │   │   │   ├── ./apps/admin_app/macos/Runner/Assets.xcassets
│   │   │   │   │   └── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/Contents.json
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_1024.png
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_128.png
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_16.png
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_256.png
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_32.png
│   │   │   │   │       ├── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_512.png
│   │   │   │   │       └── ./apps/admin_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_64.png
│   │   │   │   ├── ./apps/admin_app/macos/Runner/Base.lproj
│   │   │   │   │   └── ./apps/admin_app/macos/Runner/Base.lproj/MainMenu.xib
│   │   │   │   ├── ./apps/admin_app/macos/Runner/Configs
│   │   │   │   │   ├── ./apps/admin_app/macos/Runner/Configs/AppInfo.xcconfig
│   │   │   │   │   ├── ./apps/admin_app/macos/Runner/Configs/Debug.xcconfig
│   │   │   │   │   ├── ./apps/admin_app/macos/Runner/Configs/Release.xcconfig
│   │   │   │   │   └── ./apps/admin_app/macos/Runner/Configs/Warnings.xcconfig
│   │   │   │   ├── ./apps/admin_app/macos/Runner/DebugProfile.entitlements
│   │   │   │   ├── ./apps/admin_app/macos/Runner/Info.plist
│   │   │   │   ├── ./apps/admin_app/macos/Runner/MainFlutterWindow.swift
│   │   │   │   └── ./apps/admin_app/macos/Runner/Release.entitlements
│   │   │   ├── ./apps/admin_app/macos/Runner.xcodeproj
│   │   │   │   ├── ./apps/admin_app/macos/Runner.xcodeproj/project.pbxproj
│   │   │   │   ├── ./apps/admin_app/macos/Runner.xcodeproj/project.xcworkspace
│   │   │   │   │   └── ./apps/admin_app/macos/Runner.xcodeproj/project.xcworkspace/xcshareddata
│   │   │   │   │       └── ./apps/admin_app/macos/Runner.xcodeproj/project.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   │   └── ./apps/admin_app/macos/Runner.xcodeproj/xcshareddata
│   │   │   │       └── ./apps/admin_app/macos/Runner.xcodeproj/xcshareddata/xcschemes
│   │   │   │           └── ./apps/admin_app/macos/Runner.xcodeproj/xcshareddata/xcschemes/Runner.xcscheme
│   │   │   ├── ./apps/admin_app/macos/Runner.xcworkspace
│   │   │   │   ├── ./apps/admin_app/macos/Runner.xcworkspace/contents.xcworkspacedata
│   │   │   │   └── ./apps/admin_app/macos/Runner.xcworkspace/xcshareddata
│   │   │   │       └── ./apps/admin_app/macos/Runner.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   └── ./apps/admin_app/macos/RunnerTests
│   │   │       └── ./apps/admin_app/macos/RunnerTests/RunnerTests.swift
│   │   ├── ./apps/admin_app/pubspec.lock
│   │   ├── ./apps/admin_app/pubspec.yaml
│   │   ├── ./apps/admin_app/pubspec_overrides.yaml
│   │   ├── ./apps/admin_app/structure.txt
│   │   ├── ./apps/admin_app/test
│   │   │   ├── ./apps/admin_app/test/api_client_mock.dart
│   │   │   ├── ./apps/admin_app/test/feature_page_test.dart
│   │   │   └── ./apps/admin_app/test/widget_test.dart
│   │   ├── ./apps/admin_app/test_driver
│   │   │   └── ./apps/admin_app/test_driver/app_test.dart
│   │   ├── ./apps/admin_app/web
│   │   │   ├── ./apps/admin_app/web/favicon.png
│   │   │   ├── ./apps/admin_app/web/icons
│   │   │   │   ├── ./apps/admin_app/web/icons/Icon-192.png
│   │   │   │   ├── ./apps/admin_app/web/icons/Icon-512.png
│   │   │   │   ├── ./apps/admin_app/web/icons/Icon-maskable-192.png
│   │   │   │   └── ./apps/admin_app/web/icons/Icon-maskable-512.png
│   │   │   ├── ./apps/admin_app/web/index.html
│   │   │   └── ./apps/admin_app/web/manifest.json
│   │   └── ./apps/admin_app/windows
│   │       ├── ./apps/admin_app/windows/.gitignore
│   │       ├── ./apps/admin_app/windows/CMakeLists.txt
│   │       ├── ./apps/admin_app/windows/flutter
│   │       │   ├── ./apps/admin_app/windows/flutter/CMakeLists.txt
│   │       │   ├── ./apps/admin_app/windows/flutter/generated_plugin_registrant.cc
│   │       │   ├── ./apps/admin_app/windows/flutter/generated_plugin_registrant.h
│   │       │   └── ./apps/admin_app/windows/flutter/generated_plugins.cmake
│   │       └── ./apps/admin_app/windows/runner
│   │           ├── ./apps/admin_app/windows/runner/CMakeLists.txt
│   │           ├── ./apps/admin_app/windows/runner/Runner.rc
│   │           ├── ./apps/admin_app/windows/runner/flutter_window.cpp
│   │           ├── ./apps/admin_app/windows/runner/flutter_window.h
│   │           ├── ./apps/admin_app/windows/runner/main.cpp
│   │           ├── ./apps/admin_app/windows/runner/resource.h
│   │           ├── ./apps/admin_app/windows/runner/resources
│   │           │   └── ./apps/admin_app/windows/runner/resources/app_icon.ico
│   │           ├── ./apps/admin_app/windows/runner/runner.exe.manifest
│   │           ├── ./apps/admin_app/windows/runner/utils.cpp
│   │           ├── ./apps/admin_app/windows/runner/utils.h
│   │           ├── ./apps/admin_app/windows/runner/win32_window.cpp
│   │           └── ./apps/admin_app/windows/runner/win32_window.h
│   ├── ./apps/cleaner_app
│   │   ├── ./apps/cleaner_app/.gitignore
│   │   ├── ./apps/cleaner_app/.metadata
│   │   ├── ./apps/cleaner_app/README.md
│   │   ├── ./apps/cleaner_app/analysis_options.yaml
│   │   ├── ./apps/cleaner_app/android
│   │   │   ├── ./apps/cleaner_app/android/.gitignore
│   │   │   ├── ./apps/cleaner_app/android/app
│   │   │   │   ├── ./apps/cleaner_app/android/app/build.gradle.kts
│   │   │   │   └── ./apps/cleaner_app/android/app/src
│   │   │   │       ├── ./apps/cleaner_app/android/app/src/debug
│   │   │   │       │   └── ./apps/cleaner_app/android/app/src/debug/AndroidManifest.xml
│   │   │   │       ├── ./apps/cleaner_app/android/app/src/main
│   │   │   │       │   ├── ./apps/cleaner_app/android/app/src/main/AndroidManifest.xml
│   │   │   │       │   ├── ./apps/cleaner_app/android/app/src/main/kotlin
│   │   │   │       │   │   └── ./apps/cleaner_app/android/app/src/main/kotlin/com
│   │   │   │       │   │       └── ./apps/cleaner_app/android/app/src/main/kotlin/com/yourco
│   │   │   │       │   │           └── ./apps/cleaner_app/android/app/src/main/kotlin/com/yourco/cleaner
│   │   │   │       │   │               └── ./apps/cleaner_app/android/app/src/main/kotlin/com/yourco/cleaner/MainActivity.kt
│   │   │   │       │   └── ./apps/cleaner_app/android/app/src/main/res
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/drawable
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/drawable/launch_background.xml
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/drawable-v21
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/drawable-v21/launch_background.xml
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/mipmap-hdpi
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/mipmap-hdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/mipmap-mdpi
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/mipmap-mdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/mipmap-xhdpi
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/mipmap-xhdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/mipmap-xxhdpi
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/mipmap-xxhdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/mipmap-xxxhdpi
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png
│   │   │   │       │       ├── ./apps/cleaner_app/android/app/src/main/res/values
│   │   │   │       │       │   └── ./apps/cleaner_app/android/app/src/main/res/values/styles.xml
│   │   │   │       │       └── ./apps/cleaner_app/android/app/src/main/res/values-night
│   │   │   │       │           └── ./apps/cleaner_app/android/app/src/main/res/values-night/styles.xml
│   │   │   │       └── ./apps/cleaner_app/android/app/src/profile
│   │   │   │           └── ./apps/cleaner_app/android/app/src/profile/AndroidManifest.xml
│   │   │   ├── ./apps/cleaner_app/android/build.gradle.kts
│   │   │   ├── ./apps/cleaner_app/android/gradle
│   │   │   │   └── ./apps/cleaner_app/android/gradle/wrapper
│   │   │   │       └── ./apps/cleaner_app/android/gradle/wrapper/gradle-wrapper.properties
│   │   │   ├── ./apps/cleaner_app/android/gradle.properties
│   │   │   └── ./apps/cleaner_app/android/settings.gradle.kts
│   │   ├── ./apps/cleaner_app/coverage
│   │   │   └── ./apps/cleaner_app/coverage/lcov.info
│   │   ├── ./apps/cleaner_app/integration_test
│   │   │   └── ./apps/cleaner_app/integration_test/app_test.dart
│   │   ├── ./apps/cleaner_app/ios
│   │   │   ├── ./apps/cleaner_app/ios/.gitignore
│   │   │   ├── ./apps/cleaner_app/ios/Flutter
│   │   │   │   ├── ./apps/cleaner_app/ios/Flutter/AppFrameworkInfo.plist
│   │   │   │   ├── ./apps/cleaner_app/ios/Flutter/Debug.xcconfig
│   │   │   │   └── ./apps/cleaner_app/ios/Flutter/Release.xcconfig
│   │   │   ├── ./apps/cleaner_app/ios/Runner
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner/AppDelegate.swift
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets
│   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Contents.json
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-1024x1024@1x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@1x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@2x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@3x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@1x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@2x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@3x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@1x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@2x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@3x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-60x60@2x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-60x60@3x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-76x76@1x.png
│   │   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-76x76@2x.png
│   │   │   │   │   │   └── ./apps/cleaner_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-83.5x83.5@2x.png
│   │   │   │   │   └── ./apps/cleaner_app/ios/Runner/Assets.xcassets/LaunchImage.imageset
│   │   │   │   │       ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/Contents.json
│   │   │   │   │       ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage.png
│   │   │   │   │       ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage@2x.png
│   │   │   │   │       ├── ./apps/cleaner_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage@3x.png
│   │   │   │   │       └── ./apps/cleaner_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/README.md
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Base.lproj
│   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Base.lproj/LaunchScreen.storyboard
│   │   │   │   │   └── ./apps/cleaner_app/ios/Runner/Base.lproj/Main.storyboard
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner/Info.plist
│   │   │   │   └── ./apps/cleaner_app/ios/Runner/Runner-Bridging-Header.h
│   │   │   ├── ./apps/cleaner_app/ios/Runner.xcodeproj
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner.xcodeproj/project.pbxproj
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner.xcodeproj/project.xcworkspace
│   │   │   │   │   ├── ./apps/cleaner_app/ios/Runner.xcodeproj/project.xcworkspace/contents.xcworkspacedata
│   │   │   │   │   └── ./apps/cleaner_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata
│   │   │   │   │       ├── ./apps/cleaner_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   │   │       └── ./apps/cleaner_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata/WorkspaceSettings.xcsettings
│   │   │   │   └── ./apps/cleaner_app/ios/Runner.xcodeproj/xcshareddata
│   │   │   │       └── ./apps/cleaner_app/ios/Runner.xcodeproj/xcshareddata/xcschemes
│   │   │   │           └── ./apps/cleaner_app/ios/Runner.xcodeproj/xcshareddata/xcschemes/Runner.xcscheme
│   │   │   ├── ./apps/cleaner_app/ios/Runner.xcworkspace
│   │   │   │   ├── ./apps/cleaner_app/ios/Runner.xcworkspace/contents.xcworkspacedata
│   │   │   │   └── ./apps/cleaner_app/ios/Runner.xcworkspace/xcshareddata
│   │   │   │       ├── ./apps/cleaner_app/ios/Runner.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   │       └── ./apps/cleaner_app/ios/Runner.xcworkspace/xcshareddata/WorkspaceSettings.xcsettings
│   │   │   └── ./apps/cleaner_app/ios/RunnerTests
│   │   │       └── ./apps/cleaner_app/ios/RunnerTests/RunnerTests.swift
│   │   ├── ./apps/cleaner_app/lib
│   │   │   ├── ./apps/cleaner_app/lib/README.md
│   │   │   ├── ./apps/cleaner_app/lib/core
│   │   │   │   ├── ./apps/cleaner_app/lib/core/README.md
│   │   │   │   ├── ./apps/cleaner_app/lib/core/di
│   │   │   │   │   ├── ./apps/cleaner_app/lib/core/di/README.md
│   │   │   │   │   └── ./apps/cleaner_app/lib/core/di/service_locator.dart
│   │   │   │   ├── ./apps/cleaner_app/lib/core/errors
│   │   │   │   │   └── ./apps/cleaner_app/lib/core/errors/failure.dart
│   │   │   │   ├── ./apps/cleaner_app/lib/core/network
│   │   │   │   │   ├── ./apps/cleaner_app/lib/core/network/api_client.dart
│   │   │   │   │   └── ./apps/cleaner_app/lib/core/network/network_info.dart
│   │   │   │   └── ./apps/cleaner_app/lib/core/utils
│   │   │   │       └── ./apps/cleaner_app/lib/core/utils/constants.dart
│   │   │   ├── ./apps/cleaner_app/lib/features
│   │   │   │   └── ./apps/cleaner_app/lib/features/feature
│   │   │   │       └── ./apps/cleaner_app/lib/features/feature/feature_page.dart
│   │   │   ├── ./apps/cleaner_app/lib/main.dart
│   │   │   └── ./apps/cleaner_app/lib/routes
│   │   │       ├── ./apps/cleaner_app/lib/routes/README.md
│   │   │       └── ./apps/cleaner_app/lib/routes/app_router.dart
│   │   ├── ./apps/cleaner_app/linux
│   │   │   ├── ./apps/cleaner_app/linux/.gitignore
│   │   │   ├── ./apps/cleaner_app/linux/CMakeLists.txt
│   │   │   ├── ./apps/cleaner_app/linux/flutter
│   │   │   │   ├── ./apps/cleaner_app/linux/flutter/CMakeLists.txt
│   │   │   │   ├── ./apps/cleaner_app/linux/flutter/generated_plugin_registrant.cc
│   │   │   │   ├── ./apps/cleaner_app/linux/flutter/generated_plugin_registrant.h
│   │   │   │   └── ./apps/cleaner_app/linux/flutter/generated_plugins.cmake
│   │   │   └── ./apps/cleaner_app/linux/runner
│   │   │       ├── ./apps/cleaner_app/linux/runner/CMakeLists.txt
│   │   │       ├── ./apps/cleaner_app/linux/runner/main.cc
│   │   │       ├── ./apps/cleaner_app/linux/runner/my_application.cc
│   │   │       └── ./apps/cleaner_app/linux/runner/my_application.h
│   │   ├── ./apps/cleaner_app/macos
│   │   │   ├── ./apps/cleaner_app/macos/.gitignore
│   │   │   ├── ./apps/cleaner_app/macos/Flutter
│   │   │   │   ├── ./apps/cleaner_app/macos/Flutter/Flutter-Debug.xcconfig
│   │   │   │   ├── ./apps/cleaner_app/macos/Flutter/Flutter-Release.xcconfig
│   │   │   │   └── ./apps/cleaner_app/macos/Flutter/GeneratedPluginRegistrant.swift
│   │   │   ├── ./apps/cleaner_app/macos/Runner
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/AppDelegate.swift
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets
│   │   │   │   │   └── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/Contents.json
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_1024.png
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_128.png
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_16.png
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_256.png
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_32.png
│   │   │   │   │       ├── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_512.png
│   │   │   │   │       └── ./apps/cleaner_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_64.png
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Base.lproj
│   │   │   │   │   └── ./apps/cleaner_app/macos/Runner/Base.lproj/MainMenu.xib
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Configs
│   │   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Configs/AppInfo.xcconfig
│   │   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Configs/Debug.xcconfig
│   │   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Configs/Release.xcconfig
│   │   │   │   │   └── ./apps/cleaner_app/macos/Runner/Configs/Warnings.xcconfig
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/DebugProfile.entitlements
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/Info.plist
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner/MainFlutterWindow.swift
│   │   │   │   └── ./apps/cleaner_app/macos/Runner/Release.entitlements
│   │   │   ├── ./apps/cleaner_app/macos/Runner.xcodeproj
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner.xcodeproj/project.pbxproj
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner.xcodeproj/project.xcworkspace
│   │   │   │   │   └── ./apps/cleaner_app/macos/Runner.xcodeproj/project.xcworkspace/xcshareddata
│   │   │   │   │       └── ./apps/cleaner_app/macos/Runner.xcodeproj/project.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   │   └── ./apps/cleaner_app/macos/Runner.xcodeproj/xcshareddata
│   │   │   │       └── ./apps/cleaner_app/macos/Runner.xcodeproj/xcshareddata/xcschemes
│   │   │   │           └── ./apps/cleaner_app/macos/Runner.xcodeproj/xcshareddata/xcschemes/Runner.xcscheme
│   │   │   ├── ./apps/cleaner_app/macos/Runner.xcworkspace
│   │   │   │   ├── ./apps/cleaner_app/macos/Runner.xcworkspace/contents.xcworkspacedata
│   │   │   │   └── ./apps/cleaner_app/macos/Runner.xcworkspace/xcshareddata
│   │   │   │       └── ./apps/cleaner_app/macos/Runner.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│   │   │   └── ./apps/cleaner_app/macos/RunnerTests
│   │   │       └── ./apps/cleaner_app/macos/RunnerTests/RunnerTests.swift
│   │   ├── ./apps/cleaner_app/pubspec.lock
│   │   ├── ./apps/cleaner_app/pubspec.yaml
│   │   ├── ./apps/cleaner_app/pubspec_overrides.yaml
│   │   ├── ./apps/cleaner_app/structure.txt
│   │   ├── ./apps/cleaner_app/test
│   │   │   ├── ./apps/cleaner_app/test/api_client_mock.dart
│   │   │   ├── ./apps/cleaner_app/test/feature_page_test.dart
│   │   │   └── ./apps/cleaner_app/test/widget_test.dart
│   │   ├── ./apps/cleaner_app/test_driver
│   │   │   └── ./apps/cleaner_app/test_driver/app_test.dart
│   │   ├── ./apps/cleaner_app/web
│   │   │   ├── ./apps/cleaner_app/web/favicon.png
│   │   │   ├── ./apps/cleaner_app/web/icons
│   │   │   │   ├── ./apps/cleaner_app/web/icons/Icon-192.png
│   │   │   │   ├── ./apps/cleaner_app/web/icons/Icon-512.png
│   │   │   │   ├── ./apps/cleaner_app/web/icons/Icon-maskable-192.png
│   │   │   │   └── ./apps/cleaner_app/web/icons/Icon-maskable-512.png
│   │   │   ├── ./apps/cleaner_app/web/index.html
│   │   │   └── ./apps/cleaner_app/web/manifest.json
│   │   └── ./apps/cleaner_app/windows
│   │       ├── ./apps/cleaner_app/windows/.gitignore
│   │       ├── ./apps/cleaner_app/windows/CMakeLists.txt
│   │       ├── ./apps/cleaner_app/windows/flutter
│   │       │   ├── ./apps/cleaner_app/windows/flutter/CMakeLists.txt
│   │       │   ├── ./apps/cleaner_app/windows/flutter/generated_plugin_registrant.cc
│   │       │   ├── ./apps/cleaner_app/windows/flutter/generated_plugin_registrant.h
│   │       │   └── ./apps/cleaner_app/windows/flutter/generated_plugins.cmake
│   │       └── ./apps/cleaner_app/windows/runner
│   │           ├── ./apps/cleaner_app/windows/runner/CMakeLists.txt
│   │           ├── ./apps/cleaner_app/windows/runner/Runner.rc
│   │           ├── ./apps/cleaner_app/windows/runner/flutter_window.cpp
│   │           ├── ./apps/cleaner_app/windows/runner/flutter_window.h
│   │           ├── ./apps/cleaner_app/windows/runner/main.cpp
│   │           ├── ./apps/cleaner_app/windows/runner/resource.h
│   │           ├── ./apps/cleaner_app/windows/runner/resources
│   │           │   └── ./apps/cleaner_app/windows/runner/resources/app_icon.ico
│   │           ├── ./apps/cleaner_app/windows/runner/runner.exe.manifest
│   │           ├── ./apps/cleaner_app/windows/runner/utils.cpp
│   │           ├── ./apps/cleaner_app/windows/runner/utils.h
│   │           ├── ./apps/cleaner_app/windows/runner/win32_window.cpp
│   │           └── ./apps/cleaner_app/windows/runner/win32_window.h
│   └── ./apps/guest_app
│       ├── ./apps/guest_app/.gitignore
│       ├── ./apps/guest_app/.metadata
│       ├── ./apps/guest_app/README.md
│       ├── ./apps/guest_app/analysis_options.yaml
│       ├── ./apps/guest_app/android
│       │   ├── ./apps/guest_app/android/.gitignore
│       │   ├── ./apps/guest_app/android/app
│       │   │   ├── ./apps/guest_app/android/app/build.gradle.kts
│       │   │   └── ./apps/guest_app/android/app/src
│       │   │       ├── ./apps/guest_app/android/app/src/debug
│       │   │       │   └── ./apps/guest_app/android/app/src/debug/AndroidManifest.xml
│       │   │       ├── ./apps/guest_app/android/app/src/main
│       │   │       │   ├── ./apps/guest_app/android/app/src/main/AndroidManifest.xml
│       │   │       │   ├── ./apps/guest_app/android/app/src/main/kotlin
│       │   │       │   │   └── ./apps/guest_app/android/app/src/main/kotlin/com
│       │   │       │   │       └── ./apps/guest_app/android/app/src/main/kotlin/com/yourco
│       │   │       │   │           └── ./apps/guest_app/android/app/src/main/kotlin/com/yourco/guest
│       │   │       │   │               └── ./apps/guest_app/android/app/src/main/kotlin/com/yourco/guest/MainActivity.kt
│       │   │       │   └── ./apps/guest_app/android/app/src/main/res
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/drawable
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/drawable/launch_background.xml
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/drawable-v21
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/drawable-v21/launch_background.xml
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/mipmap-hdpi
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/mipmap-hdpi/ic_launcher.png
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/mipmap-mdpi
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/mipmap-mdpi/ic_launcher.png
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/mipmap-xhdpi
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/mipmap-xhdpi/ic_launcher.png
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/mipmap-xxhdpi
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/mipmap-xxhdpi/ic_launcher.png
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/mipmap-xxxhdpi
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png
│       │   │       │       ├── ./apps/guest_app/android/app/src/main/res/values
│       │   │       │       │   └── ./apps/guest_app/android/app/src/main/res/values/styles.xml
│       │   │       │       └── ./apps/guest_app/android/app/src/main/res/values-night
│       │   │       │           └── ./apps/guest_app/android/app/src/main/res/values-night/styles.xml
│       │   │       └── ./apps/guest_app/android/app/src/profile
│       │   │           └── ./apps/guest_app/android/app/src/profile/AndroidManifest.xml
│       │   ├── ./apps/guest_app/android/build.gradle.kts
│       │   ├── ./apps/guest_app/android/gradle
│       │   │   └── ./apps/guest_app/android/gradle/wrapper
│       │   │       └── ./apps/guest_app/android/gradle/wrapper/gradle-wrapper.properties
│       │   ├── ./apps/guest_app/android/gradle.properties
│       │   └── ./apps/guest_app/android/settings.gradle.kts
│       ├── ./apps/guest_app/coverage
│       │   └── ./apps/guest_app/coverage/lcov.info
│       ├── ./apps/guest_app/integration_test
│       │   └── ./apps/guest_app/integration_test/app_test.dart
│       ├── ./apps/guest_app/ios
│       │   ├── ./apps/guest_app/ios/.gitignore
│       │   ├── ./apps/guest_app/ios/Flutter
│       │   │   ├── ./apps/guest_app/ios/Flutter/AppFrameworkInfo.plist
│       │   │   ├── ./apps/guest_app/ios/Flutter/Debug.xcconfig
│       │   │   └── ./apps/guest_app/ios/Flutter/Release.xcconfig
│       │   ├── ./apps/guest_app/ios/Runner
│       │   │   ├── ./apps/guest_app/ios/Runner/AppDelegate.swift
│       │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets
│       │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Contents.json
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-1024x1024@1x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@1x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@2x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-20x20@3x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@1x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@2x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-29x29@3x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@1x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@2x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-40x40@3x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-60x60@2x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-60x60@3x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-76x76@1x.png
│       │   │   │   │   ├── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-76x76@2x.png
│       │   │   │   │   └── ./apps/guest_app/ios/Runner/Assets.xcassets/AppIcon.appiconset/Icon-App-83.5x83.5@2x.png
│       │   │   │   └── ./apps/guest_app/ios/Runner/Assets.xcassets/LaunchImage.imageset
│       │   │   │       ├── ./apps/guest_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/Contents.json
│       │   │   │       ├── ./apps/guest_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage.png
│       │   │   │       ├── ./apps/guest_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage@2x.png
│       │   │   │       ├── ./apps/guest_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/LaunchImage@3x.png
│       │   │   │       └── ./apps/guest_app/ios/Runner/Assets.xcassets/LaunchImage.imageset/README.md
│       │   │   ├── ./apps/guest_app/ios/Runner/Base.lproj
│       │   │   │   ├── ./apps/guest_app/ios/Runner/Base.lproj/LaunchScreen.storyboard
│       │   │   │   └── ./apps/guest_app/ios/Runner/Base.lproj/Main.storyboard
│       │   │   ├── ./apps/guest_app/ios/Runner/Info.plist
│       │   │   └── ./apps/guest_app/ios/Runner/Runner-Bridging-Header.h
│       │   ├── ./apps/guest_app/ios/Runner.xcodeproj
│       │   │   ├── ./apps/guest_app/ios/Runner.xcodeproj/project.pbxproj
│       │   │   ├── ./apps/guest_app/ios/Runner.xcodeproj/project.xcworkspace
│       │   │   │   ├── ./apps/guest_app/ios/Runner.xcodeproj/project.xcworkspace/contents.xcworkspacedata
│       │   │   │   └── ./apps/guest_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata
│       │   │   │       ├── ./apps/guest_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│       │   │   │       └── ./apps/guest_app/ios/Runner.xcodeproj/project.xcworkspace/xcshareddata/WorkspaceSettings.xcsettings
│       │   │   └── ./apps/guest_app/ios/Runner.xcodeproj/xcshareddata
│       │   │       └── ./apps/guest_app/ios/Runner.xcodeproj/xcshareddata/xcschemes
│       │   │           └── ./apps/guest_app/ios/Runner.xcodeproj/xcshareddata/xcschemes/Runner.xcscheme
│       │   ├── ./apps/guest_app/ios/Runner.xcworkspace
│       │   │   ├── ./apps/guest_app/ios/Runner.xcworkspace/contents.xcworkspacedata
│       │   │   └── ./apps/guest_app/ios/Runner.xcworkspace/xcshareddata
│       │   │       ├── ./apps/guest_app/ios/Runner.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│       │   │       └── ./apps/guest_app/ios/Runner.xcworkspace/xcshareddata/WorkspaceSettings.xcsettings
│       │   └── ./apps/guest_app/ios/RunnerTests
│       │       └── ./apps/guest_app/ios/RunnerTests/RunnerTests.swift
│       ├── ./apps/guest_app/lib
│       │   ├── ./apps/guest_app/lib/README.md
│       │   ├── ./apps/guest_app/lib/core
│       │   │   ├── ./apps/guest_app/lib/core/README.md
│       │   │   ├── ./apps/guest_app/lib/core/di
│       │   │   │   ├── ./apps/guest_app/lib/core/di/README.md
│       │   │   │   └── ./apps/guest_app/lib/core/di/service_locator.dart
│       │   │   ├── ./apps/guest_app/lib/core/errors
│       │   │   │   └── ./apps/guest_app/lib/core/errors/failure.dart
│       │   │   ├── ./apps/guest_app/lib/core/network
│       │   │   │   ├── ./apps/guest_app/lib/core/network/api_client.dart
│       │   │   │   └── ./apps/guest_app/lib/core/network/network_info.dart
│       │   │   └── ./apps/guest_app/lib/core/utils
│       │   │       └── ./apps/guest_app/lib/core/utils/constants.dart
│       │   ├── ./apps/guest_app/lib/features
│       │   │   └── ./apps/guest_app/lib/features/feature
│       │   │       └── ./apps/guest_app/lib/features/feature/feature_page.dart
│       │   ├── ./apps/guest_app/lib/main.dart
│       │   └── ./apps/guest_app/lib/routes
│       │       ├── ./apps/guest_app/lib/routes/README.md
│       │       └── ./apps/guest_app/lib/routes/app_router.dart
│       ├── ./apps/guest_app/linux
│       │   ├── ./apps/guest_app/linux/.gitignore
│       │   ├── ./apps/guest_app/linux/CMakeLists.txt
│       │   ├── ./apps/guest_app/linux/flutter
│       │   │   ├── ./apps/guest_app/linux/flutter/CMakeLists.txt
│       │   │   ├── ./apps/guest_app/linux/flutter/generated_plugin_registrant.cc
│       │   │   ├── ./apps/guest_app/linux/flutter/generated_plugin_registrant.h
│       │   │   └── ./apps/guest_app/linux/flutter/generated_plugins.cmake
│       │   └── ./apps/guest_app/linux/runner
│       │       ├── ./apps/guest_app/linux/runner/CMakeLists.txt
│       │       ├── ./apps/guest_app/linux/runner/main.cc
│       │       ├── ./apps/guest_app/linux/runner/my_application.cc
│       │       └── ./apps/guest_app/linux/runner/my_application.h
│       ├── ./apps/guest_app/macos
│       │   ├── ./apps/guest_app/macos/.gitignore
│       │   ├── ./apps/guest_app/macos/Flutter
│       │   │   ├── ./apps/guest_app/macos/Flutter/Flutter-Debug.xcconfig
│       │   │   ├── ./apps/guest_app/macos/Flutter/Flutter-Release.xcconfig
│       │   │   └── ./apps/guest_app/macos/Flutter/GeneratedPluginRegistrant.swift
│       │   ├── ./apps/guest_app/macos/Runner
│       │   │   ├── ./apps/guest_app/macos/Runner/AppDelegate.swift
│       │   │   ├── ./apps/guest_app/macos/Runner/Assets.xcassets
│       │   │   │   └── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/Contents.json
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_1024.png
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_128.png
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_16.png
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_256.png
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_32.png
│       │   │   │       ├── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_512.png
│       │   │   │       └── ./apps/guest_app/macos/Runner/Assets.xcassets/AppIcon.appiconset/app_icon_64.png
│       │   │   ├── ./apps/guest_app/macos/Runner/Base.lproj
│       │   │   │   └── ./apps/guest_app/macos/Runner/Base.lproj/MainMenu.xib
│       │   │   ├── ./apps/guest_app/macos/Runner/Configs
│       │   │   │   ├── ./apps/guest_app/macos/Runner/Configs/AppInfo.xcconfig
│       │   │   │   ├── ./apps/guest_app/macos/Runner/Configs/Debug.xcconfig
│       │   │   │   ├── ./apps/guest_app/macos/Runner/Configs/Release.xcconfig
│       │   │   │   └── ./apps/guest_app/macos/Runner/Configs/Warnings.xcconfig
│       │   │   ├── ./apps/guest_app/macos/Runner/DebugProfile.entitlements
│       │   │   ├── ./apps/guest_app/macos/Runner/Info.plist
│       │   │   ├── ./apps/guest_app/macos/Runner/MainFlutterWindow.swift
│       │   │   └── ./apps/guest_app/macos/Runner/Release.entitlements
│       │   ├── ./apps/guest_app/macos/Runner.xcodeproj
│       │   │   ├── ./apps/guest_app/macos/Runner.xcodeproj/project.pbxproj
│       │   │   ├── ./apps/guest_app/macos/Runner.xcodeproj/project.xcworkspace
│       │   │   │   └── ./apps/guest_app/macos/Runner.xcodeproj/project.xcworkspace/xcshareddata
│       │   │   │       └── ./apps/guest_app/macos/Runner.xcodeproj/project.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│       │   │   └── ./apps/guest_app/macos/Runner.xcodeproj/xcshareddata
│       │   │       └── ./apps/guest_app/macos/Runner.xcodeproj/xcshareddata/xcschemes
│       │   │           └── ./apps/guest_app/macos/Runner.xcodeproj/xcshareddata/xcschemes/Runner.xcscheme
│       │   ├── ./apps/guest_app/macos/Runner.xcworkspace
│       │   │   ├── ./apps/guest_app/macos/Runner.xcworkspace/contents.xcworkspacedata
│       │   │   └── ./apps/guest_app/macos/Runner.xcworkspace/xcshareddata
│       │   │       └── ./apps/guest_app/macos/Runner.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist
│       │   └── ./apps/guest_app/macos/RunnerTests
│       │       └── ./apps/guest_app/macos/RunnerTests/RunnerTests.swift
│       ├── ./apps/guest_app/pubspec.lock
│       ├── ./apps/guest_app/pubspec.yaml
│       ├── ./apps/guest_app/pubspec_overrides.yaml
│       ├── ./apps/guest_app/structure.txt
│       ├── ./apps/guest_app/test
│       │   ├── ./apps/guest_app/test/api_client_mock.dart
│       │   ├── ./apps/guest_app/test/feature_page_test.dart
│       │   └── ./apps/guest_app/test/widget_test.dart
│       ├── ./apps/guest_app/test_driver
│       │   └── ./apps/guest_app/test_driver/app_test.dart
│       ├── ./apps/guest_app/web
│       │   ├── ./apps/guest_app/web/favicon.png
│       │   ├── ./apps/guest_app/web/icons
│       │   │   ├── ./apps/guest_app/web/icons/Icon-192.png
│       │   │   ├── ./apps/guest_app/web/icons/Icon-512.png
│       │   │   ├── ./apps/guest_app/web/icons/Icon-maskable-192.png
│       │   │   └── ./apps/guest_app/web/icons/Icon-maskable-512.png
│       │   ├── ./apps/guest_app/web/index.html
│       │   └── ./apps/guest_app/web/manifest.json
│       └── ./apps/guest_app/windows
│           ├── ./apps/guest_app/windows/.gitignore
│           ├── ./apps/guest_app/windows/CMakeLists.txt
│           ├── ./apps/guest_app/windows/flutter
│           │   ├── ./apps/guest_app/windows/flutter/CMakeLists.txt
│           │   ├── ./apps/guest_app/windows/flutter/generated_plugin_registrant.cc
│           │   ├── ./apps/guest_app/windows/flutter/generated_plugin_registrant.h
│           │   └── ./apps/guest_app/windows/flutter/generated_plugins.cmake
│           └── ./apps/guest_app/windows/runner
│               ├── ./apps/guest_app/windows/runner/CMakeLists.txt
│               ├── ./apps/guest_app/windows/runner/Runner.rc
│               ├── ./apps/guest_app/windows/runner/flutter_window.cpp
│               ├── ./apps/guest_app/windows/runner/flutter_window.h
│               ├── ./apps/guest_app/windows/runner/main.cpp
│               ├── ./apps/guest_app/windows/runner/resource.h
│               ├── ./apps/guest_app/windows/runner/resources
│               │   └── ./apps/guest_app/windows/runner/resources/app_icon.ico
│               ├── ./apps/guest_app/windows/runner/runner.exe.manifest
│               ├── ./apps/guest_app/windows/runner/utils.cpp
│               ├── ./apps/guest_app/windows/runner/utils.h
│               ├── ./apps/guest_app/windows/runner/win32_window.cpp
│               └── ./apps/guest_app/windows/runner/win32_window.h
├── ./config.example.yml
├── ./config.yml
├── ./docker-compose.yml
├── ./melos.yaml
├── ./pPHVRXCn4CRVVOeXaGe997clKDGs_KEH2bMIYeUcgXplP6FLQrlsR4g4a7e510S0ZjQJCDvTGjIAleAqKhJsvz-tyyt4Uv5aeB8muXajiK28NYeqSYu4QJ88tUwPiowWTp35mXj92FTtNs6oZeG5x0Urr8IAoe3ThX1IaGlGEiGL6kSnm61cHwTmhZqQRR45CW9lGdkAqWZ1PZSoHsZzDfdP5n2rpTdvH1DKlfqMV180_2o2B5ZyUgTmSsr.svg
├── ./package.json
├── ./packages
│   ├── ./packages/template_api
│   │   ├── ./packages/template_api/.gitignore
│   │   ├── ./packages/template_api/.openapi-generator
│   │   │   ├── ./packages/template_api/.openapi-generator/FILES
│   │   │   └── ./packages/template_api/.openapi-generator/VERSION
│   │   ├── ./packages/template_api/.openapi-generator-ignore
│   │   ├── ./packages/template_api/README.md
│   │   ├── ./packages/template_api/analysis_options.yaml
│   │   ├── ./packages/template_api/doc
│   │   │   ├── ./packages/template_api/doc/DefaultApi.md
│   │   │   └── ./packages/template_api/doc/FeatureDTO.md
│   │   ├── ./packages/template_api/lib
│   │   │   ├── ./packages/template_api/lib/src
│   │   │   │   ├── ./packages/template_api/lib/src/api
│   │   │   │   │   └── ./packages/template_api/lib/src/api/default_api.dart
│   │   │   │   ├── ./packages/template_api/lib/src/api.dart
│   │   │   │   ├── ./packages/template_api/lib/src/api_util.dart
│   │   │   │   ├── ./packages/template_api/lib/src/auth
│   │   │   │   │   ├── ./packages/template_api/lib/src/auth/api_key_auth.dart
│   │   │   │   │   ├── ./packages/template_api/lib/src/auth/auth.dart
│   │   │   │   │   ├── ./packages/template_api/lib/src/auth/basic_auth.dart
│   │   │   │   │   ├── ./packages/template_api/lib/src/auth/bearer_auth.dart
│   │   │   │   │   └── ./packages/template_api/lib/src/auth/oauth.dart
│   │   │   │   ├── ./packages/template_api/lib/src/date_serializer.dart
│   │   │   │   ├── ./packages/template_api/lib/src/model
│   │   │   │   │   ├── ./packages/template_api/lib/src/model/date.dart
│   │   │   │   │   ├── ./packages/template_api/lib/src/model/feature_dto.dart
│   │   │   │   │   └── ./packages/template_api/lib/src/model/feature_dto.g.dart
│   │   │   │   ├── ./packages/template_api/lib/src/serializers.dart
│   │   │   │   └── ./packages/template_api/lib/src/serializers.g.dart
│   │   │   └── ./packages/template_api/lib/template_api.dart
│   │   ├── ./packages/template_api/pubspec.yaml
│   │   └── ./packages/template_api/test
│   │       ├── ./packages/template_api/test/default_api_test.dart
│   │       └── ./packages/template_api/test/feature_dto_test.dart
│   └── ./packages/ui_kit
│       ├── ./packages/ui_kit/.gitignore
│       ├── ./packages/ui_kit/.metadata
│       ├── ./packages/ui_kit/CHANGELOG.md
│       ├── ./packages/ui_kit/LICENSE
│       ├── ./packages/ui_kit/README.md
│       ├── ./packages/ui_kit/analysis_options.yaml
│       ├── ./packages/ui_kit/lib
│       │   └── ./packages/ui_kit/lib/ui_kit.dart
│       ├── ./packages/ui_kit/pubspec.yaml
│       └── ./packages/ui_kit/test
│           └── ./packages/ui_kit/test/ui_kit_test.dart
├── ./pnpm-lock.yaml
├── ./pnpm-workspace.yaml
├── ./pubspec.lock
├── ./pubspec.yaml
├── ./requirements.txt
├── ./scripts
│   ├── ./scripts/apply_changes.py
│   ├── ./scripts/gen_tree.py
│   ├── ./scripts/generate.sh
│   ├── ./scripts/generate_api_client.sh
│   └── ./scripts/new_project.sh
├── ./services
│   └── ./services/backend
│       ├── ./services/backend/Dockerfile
│       ├── ./services/backend/README.md
│       ├── ./services/backend/api-docs.json
│       ├── ./services/backend/mvnw
│       ├── ./services/backend/mvnw.cmd
│       ├── ./services/backend/pom.xml
│       ├── ./services/backend/src
│       │   ├── ./services/backend/src/gen
│       │   │   ├── ./services/backend/src/gen/.openapi-generator
│       │   │   │   ├── ./services/backend/src/gen/.openapi-generator/FILES
│       │   │   │   └── ./services/backend/src/gen/.openapi-generator/VERSION
│       │   │   ├── ./services/backend/src/gen/.openapi-generator-ignore
│       │   │   ├── ./services/backend/src/gen/README.md
│       │   │   ├── ./services/backend/src/gen/pom.xml
│       │   │   └── ./services/backend/src/gen/src
│       │   │       └── ./services/backend/src/gen/src/gen
│       │   │           └── ./services/backend/src/gen/src/gen/org
│       │   │               └── ./services/backend/src/gen/src/gen/org/openapitools
│       │   │                   ├── ./services/backend/src/gen/src/gen/org/openapitools/api
│       │   │                   │   ├── ./services/backend/src/gen/src/gen/org/openapitools/api/ApiUtil.java
│       │   │                   │   └── ./services/backend/src/gen/src/gen/org/openapitools/api/DefaultApi.java
│       │   │                   └── ./services/backend/src/gen/src/gen/org/openapitools/model
│       │   │                       └── ./services/backend/src/gen/src/gen/org/openapitools/model/FeatureDTO.java
│       │   ├── ./services/backend/src/main
│       │   │   ├── ./services/backend/src/main/java
│       │   │   │   └── ./services/backend/src/main/java/com
│       │   │   │       └── ./services/backend/src/main/java/com/example
│       │   │   │           └── ./services/backend/src/main/java/com/example/app
│       │   │   │               ├── ./services/backend/src/main/java/com/example/app/TemplateApplication.java
│       │   │   │               └── ./services/backend/src/main/java/com/example/app/controller
│       │   │   │                   └── ./services/backend/src/main/java/com/example/app/controller/FeatureController.java
│       │   │   └── ./services/backend/src/main/resources
│       │   │       ├── ./services/backend/src/main/resources/application.properties
│       │   │       └── ./services/backend/src/main/resources/openapi.yaml
│       │   └── ./services/backend/src/test
│       │       └── ./services/backend/src/test/java
│       │           ├── ./services/backend/src/test/java/com
│       │           │   └── ./services/backend/src/test/java/com/example
│       │           │       └── ./services/backend/src/test/java/com/example/app
│       │           │           └── ./services/backend/src/test/java/com/example/app/TemplateApplicationTests.java
│       │           └── ./services/backend/src/test/java/org
│       │               └── ./services/backend/src/test/java/org/openapitools
│       │                   └── ./services/backend/src/test/java/org/openapitools/OpenApiGeneratorApplicationTests.java
│       └── ./services/backend/structure.txt
└── ./tests
    ├── ./tests/test_bad_config.py
    ├── ./tests/test_exclude_include.py
    └── ./tests/test_path_traversal.py

268 directories, 525 files
