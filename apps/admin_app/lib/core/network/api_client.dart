import 'package:dio/dio.dart';

/// كلاس يغلّف تعاملنا مع Dio ويضيف تصحيح للمسارات
/// يستقبل [baseUrl] (مثلاً http://localhost:8080 أو http://10.0.2.2:8080)
class ApiClient {
  final Dio _dio;

  /// ينشئ Dio بقاعدة [baseUrl].
  /// إذا كنت على محاكي أندرويد، قد يكون baseUrl = 'http://10.0.2.2:8080'.
  /// إذا على سطح مكتب (Linux/Windows/MacOS)، استخدم 'http://localhost:8080'.
  ApiClient({required String baseUrl})
      : _dio = Dio(BaseOptions(baseUrl: baseUrl)) {
    // (اختياري) لتسجيل الطلبات والاستجابات في الـ console
    // _dio.interceptors.add(LogInterceptor(
    //   requestBody: true,
    //   responseBody: true,
    // ));
  }

  /// هنا التعريف الصحيح لـ getter
  Dio get dio => _dio;

  /// طلب GET
  Future<Response<T>> get<T>(String path) =>
      _dio.get<T>(_ensureLeadingSlash(path));

  /// طلب POST
  Future<Response<T>> post<T>(String path, dynamic data) =>
      _dio.post<T>(_ensureLeadingSlash(path), data: data);

  /// طلب PUT
  Future<Response<T>> put<T>(String path, dynamic data) =>
      _dio.put<T>(_ensureLeadingSlash(path), data: data);

  /// طلب DELETE
  Future<Response<T>> delete<T>(String path) =>
      _dio.delete<T>(_ensureLeadingSlash(path));

  /// يضمن أن يبدأ [path] بـ '/' كي لا يتصل مباشرةً بدون '/'
  String _ensureLeadingSlash(String path) {
    if (!path.startsWith('/')) {
      return '/$path';
    }
    return path;
  }
}
