// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'task_status.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

const TaskStatus _$PENDING = const TaskStatus._('PENDING');
const TaskStatus _$DONE = const TaskStatus._('DONE');

TaskStatus _$valueOf(String name) {
  switch (name) {
    case 'PENDING':
      return _$PENDING;
    case 'DONE':
      return _$DONE;
    default:
      throw new ArgumentError(name);
  }
}

final BuiltSet<TaskStatus> _$values =
    new BuiltSet<TaskStatus>(const <TaskStatus>[
  _$PENDING,
  _$DONE,
]);

class _$TaskStatusMeta {
  const _$TaskStatusMeta();
  TaskStatus get PENDING => _$PENDING;
  TaskStatus get DONE => _$DONE;
  TaskStatus valueOf(String name) => _$valueOf(name);
  BuiltSet<TaskStatus> get values => _$values;
}

abstract class _$TaskStatusMixin {
  // ignore: non_constant_identifier_names
  _$TaskStatusMeta get TaskStatus => const _$TaskStatusMeta();
}

Serializer<TaskStatus> _$taskStatusSerializer = new _$TaskStatusSerializer();

class _$TaskStatusSerializer implements PrimitiveSerializer<TaskStatus> {
  static const Map<String, Object> _toWire = const <String, Object>{
    'PENDING': 'PENDING',
    'DONE': 'DONE',
  };
  static const Map<Object, String> _fromWire = const <Object, String>{
    'PENDING': 'PENDING',
    'DONE': 'DONE',
  };

  @override
  final Iterable<Type> types = const <Type>[TaskStatus];
  @override
  final String wireName = 'TaskStatus';

  @override
  Object serialize(Serializers serializers, TaskStatus object,
          {FullType specifiedType = FullType.unspecified}) =>
      _toWire[object.name] ?? object.name;

  @override
  TaskStatus deserialize(Serializers serializers, Object serialized,
          {FullType specifiedType = FullType.unspecified}) =>
      TaskStatus.valueOf(
          _fromWire[serialized] ?? (serialized is String ? serialized : ''));
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
