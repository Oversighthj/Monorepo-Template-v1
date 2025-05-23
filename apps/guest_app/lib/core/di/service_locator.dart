import 'package:get_it/get_it.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:template_api/api_client.dart';

final sl = GetIt.instance;

Future<void> setupLocator() async {
  // SharedPreferences (singleton)
  final prefs = await SharedPreferences.getInstance();
  if (!sl.isRegistered<SharedPreferences>()) {
    sl.registerSingleton<SharedPreferences>(prefs);
  }

  // ApiClient with token injection (ensure single registration)
  if (!sl.isRegistered<ApiClient>()) {
    sl.registerLazySingleton<ApiClient>(() {
      final client = ApiClient();
      final token = prefs.getString('auth_token');
      if (token != null && token.isNotEmpty) {
        client.addDefaultHeader('Authorization', 'Bearer $token');
      }
      return client;
    });
  }

  // Register other services/repositories below …
  // e.g. sl.registerLazySingleton(() => BookingRepository(sl<ApiClient>()));
}
