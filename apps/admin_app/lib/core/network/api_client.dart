import 'package:dio/dio.dart';
import '../../config.dart';

class ApiClient {
  final Dio _dio;
  String? _token; // <‑‑ now USED via interceptor

  ApiClient({Dio? dio}) : _dio = dio ?? Dio() {
    _dio.options.baseUrl = Config.apiBaseUrl;
    _dio.interceptors.add(InterceptorsWrapper(onRequest: (options, handler) {
      if (_token?.isNotEmpty ?? false) {
        options.headers['Authorization'] = 'Bearer $_token';
      }
      return handler.next(options);
    }));
  }

  void setToken(String? t) { _token = t; }
  Dio get dio => _dio;
}
