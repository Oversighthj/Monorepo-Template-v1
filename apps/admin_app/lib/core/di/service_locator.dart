import 'package:get_it/get_it.dart';
import '../network/api_client.dart';
import '../network/network_info.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

final sl = GetIt.instance;

Future<void> initServiceLocator() async {
  // Network
  sl.registerLazySingleton<NetworkInfo>(
    () => NetworkInfoImpl(connectivity: sl()),
  );
  sl.registerLazySingleton(() => Connectivity());
  sl.registerLazySingleton<ApiClient>(() => ApiClient(baseUrl: 'http://localhost:8080'));
}



//sl.registerLazySingleton<ApiClient>(() => ApiClient(baseUrl: 'http://192.168.100.2:3000'));