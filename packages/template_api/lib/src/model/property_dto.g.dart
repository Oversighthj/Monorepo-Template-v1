// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'property_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$PropertyDTO extends PropertyDTO {
  @override
  final int? id;
  @override
  final String? name;
  @override
  final String? address;
  @override
  final int ownerId;
  @override
  final bool? active;

  factory _$PropertyDTO([void Function(PropertyDTOBuilder)? updates]) =>
      (new PropertyDTOBuilder()..update(updates))._build();

  _$PropertyDTO._(
      {this.id, this.name, this.address, required this.ownerId, this.active})
      : super._() {
    BuiltValueNullFieldError.checkNotNull(ownerId, r'PropertyDTO', 'ownerId');
  }

  @override
  PropertyDTO rebuild(void Function(PropertyDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  PropertyDTOBuilder toBuilder() => new PropertyDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is PropertyDTO &&
        id == other.id &&
        name == other.name &&
        address == other.address &&
        ownerId == other.ownerId &&
        active == other.active;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, name.hashCode);
    _$hash = $jc(_$hash, address.hashCode);
    _$hash = $jc(_$hash, ownerId.hashCode);
    _$hash = $jc(_$hash, active.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'PropertyDTO')
          ..add('id', id)
          ..add('name', name)
          ..add('address', address)
          ..add('ownerId', ownerId)
          ..add('active', active))
        .toString();
  }
}

class PropertyDTOBuilder implements Builder<PropertyDTO, PropertyDTOBuilder> {
  _$PropertyDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  String? _name;
  String? get name => _$this._name;
  set name(String? name) => _$this._name = name;

  String? _address;
  String? get address => _$this._address;
  set address(String? address) => _$this._address = address;

  int? _ownerId;
  int? get ownerId => _$this._ownerId;
  set ownerId(int? ownerId) => _$this._ownerId = ownerId;

  bool? _active;
  bool? get active => _$this._active;
  set active(bool? active) => _$this._active = active;

  PropertyDTOBuilder() {
    PropertyDTO._defaults(this);
  }

  PropertyDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _name = $v.name;
      _address = $v.address;
      _ownerId = $v.ownerId;
      _active = $v.active;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(PropertyDTO other) {
    ArgumentError.checkNotNull(other, 'other');
    _$v = other as _$PropertyDTO;
  }

  @override
  void update(void Function(PropertyDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  PropertyDTO build() => _build();

  _$PropertyDTO _build() {
    final _$result = _$v ??
        new _$PropertyDTO._(
          id: id,
          name: name,
          address: address,
          ownerId: BuiltValueNullFieldError.checkNotNull(
              ownerId, r'PropertyDTO', 'ownerId'),
          active: active,
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
