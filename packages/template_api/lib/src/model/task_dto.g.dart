// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'task_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$TaskDTO extends TaskDTO {
  @override
  final int? id;
  @override
  final int bookingId;
  @override
  final int cleanerId;
  @override
  final String type;
  @override
  final TaskStatus status;
  @override
  final DateTime due;

  factory _$TaskDTO([void Function(TaskDTOBuilder)? updates]) =>
      (new TaskDTOBuilder()..update(updates))._build();

  _$TaskDTO._(
      {this.id,
      required this.bookingId,
      required this.cleanerId,
      required this.type,
      required this.status,
      required this.due})
      : super._() {
    BuiltValueNullFieldError.checkNotNull(bookingId, r'TaskDTO', 'bookingId');
    BuiltValueNullFieldError.checkNotNull(cleanerId, r'TaskDTO', 'cleanerId');
    BuiltValueNullFieldError.checkNotNull(type, r'TaskDTO', 'type');
    BuiltValueNullFieldError.checkNotNull(status, r'TaskDTO', 'status');
    BuiltValueNullFieldError.checkNotNull(due, r'TaskDTO', 'due');
  }

  @override
  TaskDTO rebuild(void Function(TaskDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  TaskDTOBuilder toBuilder() => new TaskDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is TaskDTO &&
        id == other.id &&
        bookingId == other.bookingId &&
        cleanerId == other.cleanerId &&
        type == other.type &&
        status == other.status &&
        due == other.due;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, bookingId.hashCode);
    _$hash = $jc(_$hash, cleanerId.hashCode);
    _$hash = $jc(_$hash, type.hashCode);
    _$hash = $jc(_$hash, status.hashCode);
    _$hash = $jc(_$hash, due.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'TaskDTO')
          ..add('id', id)
          ..add('bookingId', bookingId)
          ..add('cleanerId', cleanerId)
          ..add('type', type)
          ..add('status', status)
          ..add('due', due))
        .toString();
  }
}

class TaskDTOBuilder implements Builder<TaskDTO, TaskDTOBuilder> {
  _$TaskDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  int? _bookingId;
  int? get bookingId => _$this._bookingId;
  set bookingId(int? bookingId) => _$this._bookingId = bookingId;

  int? _cleanerId;
  int? get cleanerId => _$this._cleanerId;
  set cleanerId(int? cleanerId) => _$this._cleanerId = cleanerId;

  String? _type;
  String? get type => _$this._type;
  set type(String? type) => _$this._type = type;

  TaskStatus? _status;
  TaskStatus? get status => _$this._status;
  set status(TaskStatus? status) => _$this._status = status;

  DateTime? _due;
  DateTime? get due => _$this._due;
  set due(DateTime? due) => _$this._due = due;

  TaskDTOBuilder() {
    TaskDTO._defaults(this);
  }

  TaskDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _bookingId = $v.bookingId;
      _cleanerId = $v.cleanerId;
      _type = $v.type;
      _status = $v.status;
      _due = $v.due;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(TaskDTO other) {
    ArgumentError.checkNotNull(other, 'other');
    _$v = other as _$TaskDTO;
  }

  @override
  void update(void Function(TaskDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  TaskDTO build() => _build();

  _$TaskDTO _build() {
    final _$result = _$v ??
        new _$TaskDTO._(
          id: id,
          bookingId: BuiltValueNullFieldError.checkNotNull(
              bookingId, r'TaskDTO', 'bookingId'),
          cleanerId: BuiltValueNullFieldError.checkNotNull(
              cleanerId, r'TaskDTO', 'cleanerId'),
          type: BuiltValueNullFieldError.checkNotNull(type, r'TaskDTO', 'type'),
          status: BuiltValueNullFieldError.checkNotNull(
              status, r'TaskDTO', 'status'),
          due: BuiltValueNullFieldError.checkNotNull(due, r'TaskDTO', 'due'),
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
