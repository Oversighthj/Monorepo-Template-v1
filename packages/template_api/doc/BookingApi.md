# template_api.api.BookingApi

## Load the API package
```dart
import 'package:template_api/api.dart';
```

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**bookingsGet**](BookingApi.md#bookingsget) | **GET** /bookings | Get booking list
[**bookingsIdDelete**](BookingApi.md#bookingsiddelete) | **DELETE** /bookings/{id} | Delete booking
[**bookingsIdGet**](BookingApi.md#bookingsidget) | **GET** /bookings/{id} | Get booking
[**bookingsIdPut**](BookingApi.md#bookingsidput) | **PUT** /bookings/{id} | Update booking
[**bookingsPost**](BookingApi.md#bookingspost) | **POST** /bookings | Create booking


# **bookingsGet**
> BuiltList<BookingDTO> bookingsGet(propertyId, userId)

Get booking list

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getBookingApi();
final int propertyId = 789; // int | 
final int userId = 789; // int | 

try {
    final response = api.bookingsGet(propertyId, userId);
    print(response);
} catch on DioError (e) {
    print('Exception when calling BookingApi->bookingsGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **propertyId** | **int**|  | [optional] 
 **userId** | **int**|  | [optional] 

### Return type

[**BuiltList&lt;BookingDTO&gt;**](BookingDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **bookingsIdDelete**
> bookingsIdDelete(id)

Delete booking

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getBookingApi();
final int id = 789; // int | 

try {
    api.bookingsIdDelete(id);
} catch on DioError (e) {
    print('Exception when calling BookingApi->bookingsIdDelete: $e\n');
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

# **bookingsIdGet**
> BookingDTO bookingsIdGet(id)

Get booking

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getBookingApi();
final int id = 789; // int | 

try {
    final response = api.bookingsIdGet(id);
    print(response);
} catch on DioError (e) {
    print('Exception when calling BookingApi->bookingsIdGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

[**BookingDTO**](BookingDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **bookingsIdPut**
> BookingDTO bookingsIdPut(id, bookingDTO)

Update booking

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getBookingApi();
final int id = 789; // int | 
final BookingDTO bookingDTO = ; // BookingDTO | 

try {
    final response = api.bookingsIdPut(id, bookingDTO);
    print(response);
} catch on DioError (e) {
    print('Exception when calling BookingApi->bookingsIdPut: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 
 **bookingDTO** | [**BookingDTO**](BookingDTO.md)|  | 

### Return type

[**BookingDTO**](BookingDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **bookingsPost**
> bookingsPost(bookingDTO)

Create booking

### Example
```dart
import 'package:template_api/api.dart';

final api = TemplateApi().getBookingApi();
final BookingDTO bookingDTO = ; // BookingDTO | 

try {
    api.bookingsPost(bookingDTO);
} catch on DioError (e) {
    print('Exception when calling BookingApi->bookingsPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bookingDTO** | [**BookingDTO**](BookingDTO.md)|  | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

