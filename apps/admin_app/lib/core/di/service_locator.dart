import 'package:get_it/get_it.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

import '../network/api_client.dart';
import '../network/network_info.dart';

final sl = GetIt.instance;

/// تهيئة جميع الـ singleton المستخدمة في admin_app
Future<void> initServiceLocator() async {
  // اتصال الشبكة (Connectivity)
  sl.registerLazySingleton(() => Connectivity());

  // كلاس فحص الشبكة
  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

  // ApiClient الموحّد (يحتوي DefaultApi و BookingApi)
  sl.registerLazySingleton<ApiClient>(() => ApiClient());
}
