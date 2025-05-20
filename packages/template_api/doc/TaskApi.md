# template_api.api.TaskApi

## Load the API package
```dart
import 'package:template_api/api.dart';
```

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**tasksGet**](TaskApi.md#tasksget) | **GET** /tasks | Get task list
[**tasksIdDelete**](TaskApi.md#tasksiddelete) | **DELETE** /tasks/{id} | Delete task
[**tasksIdGet**](TaskApi.md#tasksidget) | **GET** /tasks/{id} | Get task
[**tasksIdPut**](TaskApi.md#tasksidput) | **PUT** /tasks/{id} | Update task
[**tasksPost**](TaskApi.md#taskspost) | **POST** /tasks | Create task


# **tasksGet**
> BuiltList<TaskDTO> tasksGet(bookingId, cleanerId)

Get task list

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getTaskApi();
final int bookingId = 789; // int | 
final int cleanerId = 789; // int | 

try {
    final response = api.tasksGet(bookingId, cleanerId);
    print(response);
} catch on DioError (e) {
    print('Exception when calling TaskApi->tasksGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bookingId** | **int**|  | [optional] 
 **cleanerId** | **int**|  | [optional] 

### Return type

[**BuiltList&lt;TaskDTO&gt;**](TaskDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **tasksIdDelete**
> tasksIdDelete(id)

Delete task

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getTaskApi();
final int id = 789; // int | 

try {
    api.tasksIdDelete(id);
} catch on DioError (e) {
    print('Exception when calling TaskApi->tasksIdDelete: $e\n');
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

# **tasksIdGet**
> TaskDTO tasksIdGet(id)

Get task

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getTaskApi();
final int id = 789; // int | 

try {
    final response = api.tasksIdGet(id);
    print(response);
} catch on DioError (e) {
    print('Exception when calling TaskApi->tasksIdGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

[**TaskDTO**](TaskDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **tasksIdPut**
> TaskDTO tasksIdPut(id, taskDTO)

Update task

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getTaskApi();
final int id = 789; // int | 
final TaskDTO taskDTO = ; // TaskDTO | 

try {
    final response = api.tasksIdPut(id, taskDTO);
    print(response);
} catch on DioError (e) {
    print('Exception when calling TaskApi->tasksIdPut: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 
 **taskDTO** | [**TaskDTO**](TaskDTO.md)|  | 

### Return type

[**TaskDTO**](TaskDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **tasksPost**
> tasksPost(taskDTO)

Create task

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getTaskApi();
final TaskDTO taskDTO = ; // TaskDTO | 

try {
    api.tasksPost(taskDTO);
} catch on DioError (e) {
    print('Exception when calling TaskApi->tasksPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskDTO** | [**TaskDTO**](TaskDTO.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

