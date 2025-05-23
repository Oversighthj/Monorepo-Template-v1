import 'package:dio/dio.dart';
import '../config.dart';

class ApiClient {
  final Dio _dio;
  String? _token;

  ApiClient({Dio? dio}) : _dio = dio ?? Dio() {
    _dio.options.baseUrl = Config.apiBaseUrl;
    _dio.interceptors.add(InterceptorsWrapper(onRequest: (options, handler) {
      if (_token != null && _token!.isNotEmpty) {
        options.headers['Authorization'] = 'Bearer $_token';
      }
      return handler.next(options);
    }));
  }

  void setToken(String? token) => _token = token;
  Dio get dio => _dio;
}
