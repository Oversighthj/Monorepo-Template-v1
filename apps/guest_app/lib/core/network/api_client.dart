import 'package:dio/dio.dart';
import 'package:built_value/serializer.dart';
import 'package:built_value/standard_json_plugin.dart';   // ★ NEW
import 'package:template_api/template_api.dart' as gen;

class ApiClient {
  // ---------- Singleton ----------
  ApiClient._internal()
      : _dio = Dio(BaseOptions(baseUrl: _defaultBase)),
        _serializers = (gen.serializers.toBuilder()
              ..addPlugin(StandardJsonPlugin()))              // ★ FIX
            .build();

  static final ApiClient instance = ApiClient._internal();

  /// يسهّل الاستدعاء عبر ApiClient() في GetIt أو أي DI
  factory ApiClient() => instance;                             // ★ NEW

  static const _defaultBase = 'http://localhost:8080';

  final Dio _dio;
  final Serializers _serializers;

  gen.DefaultApi get defaultApi => gen.DefaultApi(_dio, _serializers);
}
