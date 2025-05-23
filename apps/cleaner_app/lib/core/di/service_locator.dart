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
  sl.registerLazySingleton(() => Connectivity());

  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

<<<<<<< Updated upstream
  sl.registerLazySingleton<ApiClient>(() => ApiClient());
}
=======
  final prefs = await SharedPreferences.getInstance();
  final client = ApiClient();
  client.setToken(prefs.getString('auth_token'));
  sl.registerLazySingleton<ApiClient>(() => client);
}
>>>>>>> Stashed changes
