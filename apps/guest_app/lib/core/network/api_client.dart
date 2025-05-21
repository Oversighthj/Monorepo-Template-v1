import 'package:dio/dio.dart';
import 'package:built_value/serializer.dart';
import 'package:built_value/standard_json_plugin.dart';
import 'package:template_api/template_api.dart' as gen;

/// غلاف يوفّر Dio و Serializers مهيّأة للعملاء المولّدين.
class ApiClient {
  ApiClient._internal()
      : _dio = Dio(BaseOptions(baseUrl: _defaultBase)),
        _serializers = (gen.serializers.toBuilder()
              ..addPlugin(StandardJsonPlugin()))
            .build();

  /// ‎Singleton موحّد
  static final ApiClient instance = ApiClient._internal();

  /// يُسهّل الاستدعاء في GetIt: ApiClient()
  factory ApiClient() => instance;

  static const _defaultBase = 'http://localhost:8080';

  final Dio _dio;
  final Serializers _serializers;

  /// العميل العام (DefaultApi)
  gen.DefaultApi get defaultApi => gen.DefaultApi(_dio, _serializers);

  /// عميل الحجوزات المولَّد (BookingApi)
  gen.BookingApi get bookingApi => gen.BookingApi(_dio, _serializers);
}
