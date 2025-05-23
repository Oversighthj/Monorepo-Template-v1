import 'package:get_it/get_it.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

import '../network/api_client.dart';
import '../network/network_info.dart';
import 'package:shared_preferences/shared_preferences.dart';

final sl = GetIt.instance;

/// تهيئة جميع الـ singleton المستخدمة في admin_app
Future<void> initServiceLocator() async {
  // اتصال الشبكة (Connectivity)
  sl.registerLazySingleton(() => Connectivity());

  // كلاس فحص الشبكة
  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );

  // ApiClient الموحّد مع تحميل التوكن
  final prefs = await SharedPreferences.getInstance();
  final client = ApiClient();
  client.setToken(prefs.getString('auth_token'));
  sl.registerLazySingleton<ApiClient>(() => client);
}
