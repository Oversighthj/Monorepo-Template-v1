// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'feature_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$FeatureDTO extends FeatureDTO {
  @override
  final int? id;
  @override
  final String? name;

  factory _$FeatureDTO([void Function(FeatureDTOBuilder)? updates]) =>
      (new FeatureDTOBuilder()..update(updates))._build();

  _$FeatureDTO._({this.id, this.name}) : super._();

  @override
  FeatureDTO rebuild(void Function(FeatureDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  FeatureDTOBuilder toBuilder() => new FeatureDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is FeatureDTO && id == other.id && name == other.name;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, name.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'FeatureDTO')
          ..add('id', id)
          ..add('name', name))
        .toString();
  }
}

class FeatureDTOBuilder implements Builder<FeatureDTO, FeatureDTOBuilder> {
  _$FeatureDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  String? _name;
  String? get name => _$this._name;
  set name(String? name) => _$this._name = name;

  FeatureDTOBuilder() {
    FeatureDTO._defaults(this);
  }

  FeatureDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _name = $v.name;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(FeatureDTO other) {
    ArgumentError.checkNotNull(other, 'other');
    _$v = other as _$FeatureDTO;
  }

  @override
  void update(void Function(FeatureDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  FeatureDTO build() => _build();

  _$FeatureDTO _build() {
    final _$result = _$v ??
        new _$FeatureDTO._(
          id: id,
          name: name,
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
