// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$UserDTO extends UserDTO {
  @override
  final int? id;
  @override
  final UserRole role;
  @override
  final String email;
  @override
  final String passwordHash;

  factory _$UserDTO([void Function(UserDTOBuilder)? updates]) =>
      (new UserDTOBuilder()..update(updates))._build();

  _$UserDTO._(
      {this.id,
      required this.role,
      required this.email,
      required this.passwordHash})
      : super._() {
    BuiltValueNullFieldError.checkNotNull(role, r'UserDTO', 'role');
    BuiltValueNullFieldError.checkNotNull(email, r'UserDTO', 'email');
    BuiltValueNullFieldError.checkNotNull(
        passwordHash, r'UserDTO', 'passwordHash');
  }

  @override
  UserDTO rebuild(void Function(UserDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  UserDTOBuilder toBuilder() => new UserDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is UserDTO &&
        id == other.id &&
        role == other.role &&
        email == other.email &&
        passwordHash == other.passwordHash;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, role.hashCode);
    _$hash = $jc(_$hash, email.hashCode);
    _$hash = $jc(_$hash, passwordHash.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'UserDTO')
          ..add('id', id)
          ..add('role', role)
          ..add('email', email)
          ..add('passwordHash', passwordHash))
        .toString();
  }
}

class UserDTOBuilder implements Builder<UserDTO, UserDTOBuilder> {
  _$UserDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  UserRole? _role;
  UserRole? get role => _$this._role;
  set role(UserRole? role) => _$this._role = role;

  String? _email;
  String? get email => _$this._email;
  set email(String? email) => _$this._email = email;

  String? _passwordHash;
  String? get passwordHash => _$this._passwordHash;
  set passwordHash(String? passwordHash) => _$this._passwordHash = passwordHash;

  UserDTOBuilder() {
    UserDTO._defaults(this);
  }

  UserDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _role = $v.role;
      _email = $v.email;
      _passwordHash = $v.passwordHash;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(UserDTO other) {
    ArgumentError.checkNotNull(other, 'other');
    _$v = other as _$UserDTO;
  }

  @override
  void update(void Function(UserDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  UserDTO build() => _build();

  _$UserDTO _build() {
    final _$result = _$v ??
        new _$UserDTO._(
          id: id,
          role: BuiltValueNullFieldError.checkNotNull(role, r'UserDTO', 'role'),
          email:
              BuiltValueNullFieldError.checkNotNull(email, r'UserDTO', 'email'),
          passwordHash: BuiltValueNullFieldError.checkNotNull(
              passwordHash, r'UserDTO', 'passwordHash'),
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
