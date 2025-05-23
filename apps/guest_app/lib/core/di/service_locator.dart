// apps/guest_app/lib/core/di/service_locator.dart
import 'package:get_it/get_it.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'core/network/api_client.dart';

final sl = GetIt.instance;

Future<void> setupLocator() async {
  final prefs = await SharedPreferences.getInstance();

  // register only ONCE – no duplicates
  sl.registerLazySingleton<ApiClient>(() {
    final client = ApiClient();
    client.setToken(prefs.getString('auth_token'));
    return client;
  });

  // Network helpers
  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

  // ApiClient singleton with persisted token
  final prefs = await SharedPreferences.getInstance();
  final client = ApiClient();
  client.setToken(prefs.getString('auth_token'));
  sl.registerLazySingleton<ApiClient>(() => client);
}
