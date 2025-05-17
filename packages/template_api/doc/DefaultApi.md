# template_api.api.DefaultApi

## Load the API package
```dart
import 'package:template_api/api.dart';
```

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**featureGet**](DefaultApi.md#featureget) | **GET** /feature | Get feature list
[**statusGet**](DefaultApi.md#statusget) | **GET** /status | Status check


# **featureGet**
> BuiltList<FeatureDTO> featureGet()

Get feature list

Returns a list of features.

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

# **statusGet**
> String statusGet()

Status check

Returns 'alive' to indicate service is running.

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

