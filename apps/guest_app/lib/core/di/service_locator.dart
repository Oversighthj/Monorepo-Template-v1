// apps/guest_app/lib/core/di/service_locator.dart
import 'package:get_it/get_it.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

import '../network/api_client.dart';
import '../network/network_info.dart';

final sl = GetIt.instance;

Future<void> initServiceLocator() async {
  // Connectivity
  sl.registerLazySingleton(() => Connectivity());

  // Network helpers
  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

  // ApiClient singleton (يستخدم قاعدة http://localhost:8080 تلقائياً)
  sl.registerLazySingleton<ApiClient>(() => ApiClient());
}
