//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:template_api/src/model/task_status.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'task_dto.g.dart';

/// TaskDTO
///
/// Properties:
/// * [id] 
/// * [bookingId] 
/// * [cleanerId] 
/// * [type] 
/// * [status] 
/// * [due] 
@BuiltValue()
abstract class TaskDTO implements Built<TaskDTO, TaskDTOBuilder> {
  @BuiltValueField(wireName: r'id')
  int? get id;

  @BuiltValueField(wireName: r'bookingId')
  int get bookingId;

  @BuiltValueField(wireName: r'cleanerId')
  int get cleanerId;

  @BuiltValueField(wireName: r'type')
  String get type;

  @BuiltValueField(wireName: r'status')
  TaskStatus get status;
  // enum statusEnum {  PENDING,  DONE,  };

  @BuiltValueField(wireName: r'due')
  DateTime get due;

  TaskDTO._();

  factory TaskDTO([void updates(TaskDTOBuilder b)]) = _$TaskDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(TaskDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<TaskDTO> get serializer => _$TaskDTOSerializer();
}

class _$TaskDTOSerializer implements PrimitiveSerializer<TaskDTO> {
  @override
  final Iterable<Type> types = const [TaskDTO, _$TaskDTO];

  @override
  final String wireName = r'TaskDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    TaskDTO object, {
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
    yield r'cleanerId';
    yield serializers.serialize(
      object.cleanerId,
      specifiedType: const FullType(int),
    );
    yield r'type';
    yield serializers.serialize(
      object.type,
      specifiedType: const FullType(String),
    );
    yield r'status';
    yield serializers.serialize(
      object.status,
      specifiedType: const FullType(TaskStatus),
    );
    yield r'due';
    yield serializers.serialize(
      object.due,
      specifiedType: const FullType(DateTime),
    );
  }

  @override
  Object serialize(
    Serializers serializers,
    TaskDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) {
    return _serializeProperties(serializers, object, specifiedType: specifiedType).toList();
  }

  void _deserializeProperties(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
    required List<Object?> serializedList,
    required TaskDTOBuilder result,
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
        case r'cleanerId':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.cleanerId = valueDes;
          break;
        case r'type':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.type = valueDes;
          break;
        case r'status':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(TaskStatus),
          ) as TaskStatus;
          result.status = valueDes;
          break;
        case r'due':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(DateTime),
          ) as DateTime;
          result.due = valueDes;
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  TaskDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = TaskDTOBuilder();
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

