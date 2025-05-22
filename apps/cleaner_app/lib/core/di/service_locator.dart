import 'package:get_it/get_it.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

import '../network/api_client.dart';
import '../network/network_info.dart';
import 'package:shared_preferences/shared_preferences.dart';

final sl = GetIt.instance;

Future<void> initServiceLocator() async {
  sl.registerLazySingleton(() => Connectivity());

  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

  final prefs = await SharedPreferences.getInstance();
  final client = ApiClient();
  client.setToken(prefs.getString('auth_token'));
  sl.registerLazySingleton<ApiClient>(() => client);
}
