import 'package:dio/dio.dart';
import 'package:built_value/serializer.dart';
import 'package:built_value/standard_json_plugin.dart';
import 'package:template_api/template_api.dart' as gen;

class ApiClient {
  ApiClient._()
      : _dio = Dio(BaseOptions(baseUrl: _defaultBase)),
        _serializers = (gen.serializers.toBuilder()
              ..addPlugin(StandardJsonPlugin()))
            .build();

  static final ApiClient instance = ApiClient._();

  factory ApiClient() => instance;

  static const _defaultBase = 'http://localhost:8080';

  final Dio _dio;
  final Serializers _serializers;

  gen.TaskApi get taskApi => gen.TaskApi(_dio, _serializers);
}
