// apps/guest_app/lib/core/di/service_locator.dart
import 'package:get_it/get_it.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

import '../network/api_client.dart';
import '../network/network_info.dart';
<<<<<<< Updated upstream
=======
import 'package:shared_preferences/shared_preferences.dart';
>>>>>>> Stashed changes

final sl = GetIt.instance;

Future<void> initServiceLocator() async {
  // Connectivity
  sl.registerLazySingleton(() => Connectivity());

  // Network helpers
  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

  // ApiClient singleton (يستخدم قاعدة http://localhost:8080 تلقائياً)
<<<<<<< Updated upstream
  sl.registerLazySingleton<ApiClient>(() => ApiClient());
}
=======
  // sl.registerLazySingleton<ApiClient>(() => ApiClient());
  // ApiClient singleton with persisted token
  final prefs = await SharedPreferences.getInstance();
  final client = ApiClient();
  client.setToken(prefs.getString('auth_token'));
  sl.registerLazySingleton<ApiClient>(() => client);
}
>>>>>>> Stashed changes
