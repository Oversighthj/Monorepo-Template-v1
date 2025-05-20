# template_api.api.MessageApi

## Load the API package
```dart
import 'package:template_api/api.dart';
```

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**messagesGet**](MessageApi.md#messagesget) | **GET** /messages | Get message list
[**messagesPost**](MessageApi.md#messagespost) | **POST** /messages | Create message


# **messagesGet**
> BuiltList<MessageDTO> messagesGet(bookingId)

Get message list

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getMessageApi();
final int bookingId = 789; // int | 

try {
    final response = api.messagesGet(bookingId);
    print(response);
} catch on DioError (e) {
    print('Exception when calling MessageApi->messagesGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bookingId** | **int**|  | [optional] 

### Return type

[**BuiltList&lt;MessageDTO&gt;**](MessageDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **messagesPost**
> messagesPost(messageDTO)

Create message

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getMessageApi();
final MessageDTO messageDTO = ; // MessageDTO | 

try {
    api.messagesPost(messageDTO);
} catch on DioError (e) {
    print('Exception when calling MessageApi->messagesPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **messageDTO** | [**MessageDTO**](MessageDTO.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

