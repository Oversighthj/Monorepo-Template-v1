//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_import

import 'package:one_of_serializer/any_of_serializer.dart';
import 'package:one_of_serializer/one_of_serializer.dart';
import 'package:built_collection/built_collection.dart';
import 'package:built_value/json_object.dart';
import 'package:built_value/serializer.dart';
import 'package:built_value/standard_json_plugin.dart';
import 'package:built_value/iso_8601_date_time_serializer.dart';
import 'package:template_api/src/date_serializer.dart';
import 'package:template_api/src/model/date.dart';

import 'package:template_api/src/model/booking_dto.dart';
import 'package:template_api/src/model/booking_status.dart';
import 'package:template_api/src/model/feature_dto.dart';
import 'package:template_api/src/model/message_dto.dart';
import 'package:template_api/src/model/property_dto.dart';
import 'package:template_api/src/model/task_dto.dart';
import 'package:template_api/src/model/task_status.dart';
import 'package:template_api/src/model/user_dto.dart';
import 'package:template_api/src/model/user_role.dart';

part 'serializers.g.dart';

@SerializersFor([
  BookingDTO,
  BookingStatus,
  FeatureDTO,
  MessageDTO,
  PropertyDTO,
  TaskDTO,
  TaskStatus,
  UserDTO,
  UserRole,
])
Serializers serializers = (_$serializers.toBuilder()
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(FeatureDTO)]),
        () => ListBuilder<FeatureDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(PropertyDTO)]),
        () => ListBuilder<PropertyDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(MessageDTO)]),
        () => ListBuilder<MessageDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(TaskDTO)]),
        () => ListBuilder<TaskDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(UserDTO)]),
        () => ListBuilder<UserDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(BookingDTO)]),
        () => ListBuilder<BookingDTO>(),
      )
      ..add(const OneOfSerializer())
      ..add(const AnyOfSerializer())
      ..add(const DateSerializer())
      ..add(Iso8601DateTimeSerializer()))
    .build();

Serializers standardSerializers =
    (serializers.toBuilder()..addPlugin(StandardJsonPlugin())).build();
