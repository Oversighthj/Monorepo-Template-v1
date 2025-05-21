// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'message_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$MessageDTO extends MessageDTO {
  @override
  final int? id;
  @override
  final int bookingId;
  @override
  final int senderId;
  @override
  final String content;
  @override
  final DateTime sentAt;

  factory _$MessageDTO([void Function(MessageDTOBuilder)? updates]) =>
      (new MessageDTOBuilder()..update(updates))._build();

  _$MessageDTO._(
      {this.id,
      required this.bookingId,
      required this.senderId,
      required this.content,
      required this.sentAt})
      : super._() {
    BuiltValueNullFieldError.checkNotNull(
        bookingId, r'MessageDTO', 'bookingId');
    BuiltValueNullFieldError.checkNotNull(senderId, r'MessageDTO', 'senderId');
    BuiltValueNullFieldError.checkNotNull(content, r'MessageDTO', 'content');
    BuiltValueNullFieldError.checkNotNull(sentAt, r'MessageDTO', 'sentAt');
  }

  @override
  MessageDTO rebuild(void Function(MessageDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  MessageDTOBuilder toBuilder() => new MessageDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is MessageDTO &&
        id == other.id &&
        bookingId == other.bookingId &&
        senderId == other.senderId &&
        content == other.content &&
        sentAt == other.sentAt;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, bookingId.hashCode);
    _$hash = $jc(_$hash, senderId.hashCode);
    _$hash = $jc(_$hash, content.hashCode);
    _$hash = $jc(_$hash, sentAt.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'MessageDTO')
          ..add('id', id)
          ..add('bookingId', bookingId)
          ..add('senderId', senderId)
          ..add('content', content)
          ..add('sentAt', sentAt))
        .toString();
  }
}

class MessageDTOBuilder implements Builder<MessageDTO, MessageDTOBuilder> {
  _$MessageDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  int? _bookingId;
  int? get bookingId => _$this._bookingId;
  set bookingId(int? bookingId) => _$this._bookingId = bookingId;

  int? _senderId;
  int? get senderId => _$this._senderId;
  set senderId(int? senderId) => _$this._senderId = senderId;

  String? _content;
  String? get content => _$this._content;
  set content(String? content) => _$this._content = content;

  DateTime? _sentAt;
  DateTime? get sentAt => _$this._sentAt;
  set sentAt(DateTime? sentAt) => _$this._sentAt = sentAt;

  MessageDTOBuilder() {
    MessageDTO._defaults(this);
  }

  MessageDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _bookingId = $v.bookingId;
      _senderId = $v.senderId;
      _content = $v.content;
      _sentAt = $v.sentAt;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(MessageDTO other) {
    ArgumentError.checkNotNull(other, 'other');
    _$v = other as _$MessageDTO;
  }

  @override
  void update(void Function(MessageDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  MessageDTO build() => _build();

  _$MessageDTO _build() {
    final _$result = _$v ??
        new _$MessageDTO._(
          id: id,
          bookingId: BuiltValueNullFieldError.checkNotNull(
              bookingId, r'MessageDTO', 'bookingId'),
          senderId: BuiltValueNullFieldError.checkNotNull(
              senderId, r'MessageDTO', 'senderId'),
          content: BuiltValueNullFieldError.checkNotNull(
              content, r'MessageDTO', 'content'),
          sentAt: BuiltValueNullFieldError.checkNotNull(
              sentAt, r'MessageDTO', 'sentAt'),
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
