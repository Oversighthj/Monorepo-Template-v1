// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'booking_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$BookingDTO extends BookingDTO {
  @override
  final int? id;
  @override
  final int propertyId;
  @override
  final int userId;
  @override
  final DateTime startAt;
  @override
  final DateTime endAt;
  @override
  final BookingStatus status;

  factory _$BookingDTO([void Function(BookingDTOBuilder)? updates]) =>
      (new BookingDTOBuilder()..update(updates))._build();

  _$BookingDTO._(
      {this.id,
      required this.propertyId,
      required this.userId,
      required this.startAt,
      required this.endAt,
      required this.status})
      : super._() {
    BuiltValueNullFieldError.checkNotNull(
        propertyId, r'BookingDTO', 'propertyId');
    BuiltValueNullFieldError.checkNotNull(userId, r'BookingDTO', 'userId');
    BuiltValueNullFieldError.checkNotNull(startAt, r'BookingDTO', 'startAt');
    BuiltValueNullFieldError.checkNotNull(endAt, r'BookingDTO', 'endAt');
    BuiltValueNullFieldError.checkNotNull(status, r'BookingDTO', 'status');
  }

  @override
  BookingDTO rebuild(void Function(BookingDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  BookingDTOBuilder toBuilder() => new BookingDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is BookingDTO &&
        id == other.id &&
        propertyId == other.propertyId &&
        userId == other.userId &&
        startAt == other.startAt &&
        endAt == other.endAt &&
        status == other.status;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, propertyId.hashCode);
    _$hash = $jc(_$hash, userId.hashCode);
    _$hash = $jc(_$hash, startAt.hashCode);
    _$hash = $jc(_$hash, endAt.hashCode);
    _$hash = $jc(_$hash, status.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'BookingDTO')
          ..add('id', id)
          ..add('propertyId', propertyId)
          ..add('userId', userId)
          ..add('startAt', startAt)
          ..add('endAt', endAt)
          ..add('status', status))
        .toString();
  }
}

class BookingDTOBuilder implements Builder<BookingDTO, BookingDTOBuilder> {
  _$BookingDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  int? _propertyId;
  int? get propertyId => _$this._propertyId;
  set propertyId(int? propertyId) => _$this._propertyId = propertyId;

  int? _userId;
  int? get userId => _$this._userId;
  set userId(int? userId) => _$this._userId = userId;

  DateTime? _startAt;
  DateTime? get startAt => _$this._startAt;
  set startAt(DateTime? startAt) => _$this._startAt = startAt;

  DateTime? _endAt;
  DateTime? get endAt => _$this._endAt;
  set endAt(DateTime? endAt) => _$this._endAt = endAt;

  BookingStatus? _status;
  BookingStatus? get status => _$this._status;
  set status(BookingStatus? status) => _$this._status = status;

  BookingDTOBuilder() {
    BookingDTO._defaults(this);
  }

  BookingDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _propertyId = $v.propertyId;
      _userId = $v.userId;
      _startAt = $v.startAt;
      _endAt = $v.endAt;
      _status = $v.status;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(BookingDTO other) {
    ArgumentError.checkNotNull(other, 'other');
    _$v = other as _$BookingDTO;
  }

  @override
  void update(void Function(BookingDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  BookingDTO build() => _build();

  _$BookingDTO _build() {
    final _$result = _$v ??
        new _$BookingDTO._(
          id: id,
          propertyId: BuiltValueNullFieldError.checkNotNull(
              propertyId, r'BookingDTO', 'propertyId'),
          userId: BuiltValueNullFieldError.checkNotNull(
              userId, r'BookingDTO', 'userId'),
          startAt: BuiltValueNullFieldError.checkNotNull(
              startAt, r'BookingDTO', 'startAt'),
          endAt: BuiltValueNullFieldError.checkNotNull(
              endAt, r'BookingDTO', 'endAt'),
          status: BuiltValueNullFieldError.checkNotNull(
              status, r'BookingDTO', 'status'),
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
