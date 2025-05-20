//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'message_dto.g.dart';

/// MessageDTO
///
/// Properties:
/// * [id] 
/// * [bookingId] 
/// * [senderId] 
/// * [content] 
/// * [sentAt] 
@BuiltValue()
abstract class MessageDTO implements Built<MessageDTO, MessageDTOBuilder> {
  @BuiltValueField(wireName: r'id')
  int? get id;

  @BuiltValueField(wireName: r'bookingId')
  int get bookingId;

  @BuiltValueField(wireName: r'senderId')
  int get senderId;

  @BuiltValueField(wireName: r'content')
  String get content;

  @BuiltValueField(wireName: r'sentAt')
  DateTime get sentAt;

  MessageDTO._();

  factory MessageDTO([void updates(MessageDTOBuilder b)]) = _$MessageDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(MessageDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<MessageDTO> get serializer => _$MessageDTOSerializer();
}

class _$MessageDTOSerializer implements PrimitiveSerializer<MessageDTO> {
  @override
  final Iterable<Type> types = const [MessageDTO, _$MessageDTO];

  @override
  final String wireName = r'MessageDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    MessageDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    if (object.id != null) {
      yield r'id';
      yield serializers.serialize(
        object.id,
        specifiedType: const FullType(int),
      );
    }
    yield r'bookingId';
    yield serializers.serialize(
      object.bookingId,
      specifiedType: const FullType(int),
    );
    yield r'senderId';
    yield serializers.serialize(
      object.senderId,
      specifiedType: const FullType(int),
    );
    yield r'content';
    yield serializers.serialize(
      object.content,
      specifiedType: const FullType(String),
    );
    yield r'sentAt';
    yield serializers.serialize(
      object.sentAt,
      specifiedType: const FullType(DateTime),
    );
  }

  @override
  Object serialize(
    Serializers serializers,
    MessageDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) {
    return _serializeProperties(serializers, object, specifiedType: specifiedType).toList();
  }

  void _deserializeProperties(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
    required List<Object?> serializedList,
    required MessageDTOBuilder result,
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
        case r'bookingId':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.bookingId = valueDes;
          break;
        case r'senderId':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.senderId = valueDes;
          break;
        case r'content':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.content = valueDes;
          break;
        case r'sentAt':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(DateTime),
          ) as DateTime;
          result.sentAt = valueDes;
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  MessageDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = MessageDTOBuilder();
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

