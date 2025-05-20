# template_api.api.DefaultApi

## Load the API package
```dart
import 'package:template_api/api.dart';
```

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**featureGet**](DefaultApi.md#featureget) | **GET** /feature | Get feature list
[**propertiesGet**](DefaultApi.md#propertiesget) | **GET** /properties | Get property list
[**propertiesIdDelete**](DefaultApi.md#propertiesiddelete) | **DELETE** /properties/{id} | Delete property
[**propertiesIdGet**](DefaultApi.md#propertiesidget) | **GET** /properties/{id} | Get property
[**propertiesIdPut**](DefaultApi.md#propertiesidput) | **PUT** /properties/{id} | Update property
[**propertiesPost**](DefaultApi.md#propertiespost) | **POST** /properties | Create property
[**statusGet**](DefaultApi.md#statusget) | **GET** /status | Status check
[**usersGet**](DefaultApi.md#usersget) | **GET** /users | Get user list
[**usersPost**](DefaultApi.md#userspost) | **POST** /users | Create user


# **featureGet**
> BuiltList<FeatureDTO> featureGet()

Get feature list

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();

try {
    final response = api.featureGet();
    print(response);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->featureGet: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BuiltList&lt;FeatureDTO&gt;**](FeatureDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **propertiesGet**
> BuiltList<PropertyDTO> propertiesGet(ownerId)

Get property list

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();
final int ownerId = 789; // int | 

try {
    final response = api.propertiesGet(ownerId);
    print(response);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->propertiesGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ownerId** | **int**|  | [optional] 

### Return type

[**BuiltList&lt;PropertyDTO&gt;**](PropertyDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **propertiesIdDelete**
> propertiesIdDelete(id)

Delete property

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();
final int id = 789; // int | 

try {
    api.propertiesIdDelete(id);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->propertiesIdDelete: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **propertiesIdGet**
> PropertyDTO propertiesIdGet(id)

Get property

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();
final int id = 789; // int | 

try {
    final response = api.propertiesIdGet(id);
    print(response);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->propertiesIdGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

[**PropertyDTO**](PropertyDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **propertiesIdPut**
> PropertyDTO propertiesIdPut(id, propertyDTO)

Update property

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();
final int id = 789; // int | 
final PropertyDTO propertyDTO = ; // PropertyDTO | 

try {
    final response = api.propertiesIdPut(id, propertyDTO);
    print(response);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->propertiesIdPut: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 
 **propertyDTO** | [**PropertyDTO**](PropertyDTO.md)|  | 

### Return type

[**PropertyDTO**](PropertyDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **propertiesPost**
> propertiesPost(propertyDTO)

Create property

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();
final PropertyDTO propertyDTO = ; // PropertyDTO | 

try {
    api.propertiesPost(propertyDTO);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->propertiesPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **propertyDTO** | [**PropertyDTO**](PropertyDTO.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **statusGet**
> String statusGet()

Status check

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();

try {
    final response = api.statusGet();
    print(response);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->statusGet: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **usersGet**
> BuiltList<UserDTO> usersGet()

Get user list

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();

try {
    final response = api.usersGet();
    print(response);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->usersGet: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BuiltList&lt;UserDTO&gt;**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **usersPost**
> usersPost(userDTO)

Create user

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getDefaultApi();
final UserDTO userDTO = ; // UserDTO | 

try {
    api.usersPost(userDTO);
} catch on DioError (e) {
    print('Exception when calling DefaultApi->usersPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userDTO** | [**UserDTO**](UserDTO.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

