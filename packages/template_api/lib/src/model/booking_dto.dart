//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:template_api/src/model/booking_status.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'booking_dto.g.dart';

/// BookingDTO
///
/// Properties:
/// * [id] 
/// * [propertyId] 
/// * [userId] 
/// * [startAt] 
/// * [endAt] 
/// * [status] 
@BuiltValue()
abstract class BookingDTO implements Built<BookingDTO, BookingDTOBuilder> {
  @BuiltValueField(wireName: r'id')
  int? get id;

  @BuiltValueField(wireName: r'propertyId')
  int get propertyId;

  @BuiltValueField(wireName: r'userId')
  int get userId;

  @BuiltValueField(wireName: r'startAt')
  DateTime get startAt;

  @BuiltValueField(wireName: r'endAt')
  DateTime get endAt;

  @BuiltValueField(wireName: r'status')
  BookingStatus get status;
  // enum statusEnum {  PENDING,  CONFIRMED,  CANCELLED,  };

  BookingDTO._();

  factory BookingDTO([void updates(BookingDTOBuilder b)]) = _$BookingDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(BookingDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<BookingDTO> get serializer => _$BookingDTOSerializer();
}

class _$BookingDTOSerializer implements PrimitiveSerializer<BookingDTO> {
  @override
  final Iterable<Type> types = const [BookingDTO, _$BookingDTO];

  @override
  final String wireName = r'BookingDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    BookingDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    if (object.id != null) {
      yield r'id';
      yield serializers.serialize(
        object.id,
        specifiedType: const FullType(int),
      );
    }
    yield r'propertyId';
    yield serializers.serialize(
      object.propertyId,
      specifiedType: const FullType(int),
    );
    yield r'userId';
    yield serializers.serialize(
      object.userId,
      specifiedType: const FullType(int),
    );
    yield r'startAt';
    yield serializers.serialize(
      object.startAt,
      specifiedType: const FullType(DateTime),
    );
    yield r'endAt';
    yield serializers.serialize(
      object.endAt,
      specifiedType: const FullType(DateTime),
    );
    yield r'status';
    yield serializers.serialize(
      object.status,
      specifiedType: const FullType(BookingStatus),
    );
  }

  @override
  Object serialize(
    Serializers serializers,
    BookingDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) {
    return _serializeProperties(serializers, object, specifiedType: specifiedType).toList();
  }

  void _deserializeProperties(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
    required List<Object?> serializedList,
    required BookingDTOBuilder result,
    required List<Object?> unhandled,
  }) {
    for (var i = 0; i < serializedList.length; i += 2) {
      final key = serializedList[i] as String;
      final value = serializedList[i + 1];
      switch (key) {
        case r'id':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.id = valueDes;
          break;
        case r'propertyId':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.propertyId = valueDes;
          break;
        case r'userId':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.userId = valueDes;
          break;
        case r'startAt':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(DateTime),
          ) as DateTime;
          result.startAt = valueDes;
          break;
        case r'endAt':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(DateTime),
          ) as DateTime;
          result.endAt = valueDes;
          break;
        case r'status':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(BookingStatus),
          ) as BookingStatus;
          result.status = valueDes;
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  BookingDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = BookingDTOBuilder();
    final serializedList = (serialized as Iterable<Object?>).toList();
    final unhandled = <Object?>[];
    _deserializeProperties(
      serializers,
      serialized,
      specifiedType: specifiedType,
      serializedList: serializedList,
      unhandled: unhandled,
      result: result,
    );
    return result.build();
  }
}

