# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**apiV1TasksPost**](DefaultApi.md#apiV1TasksPost) | **POST** /api/v1/tasks | Create a new task |


<a name="apiV1TasksPost"></a>
# **apiV1TasksPost**
> apiV1TasksPost(apiV1TasksPostRequest)

Create a new task

Endpoint for create a new task

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    ApiV1TasksPostRequest apiV1TasksPostRequest = new ApiV1TasksPostRequest(); // ApiV1TasksPostRequest | 
    try {
      apiInstance.apiV1TasksPost(apiV1TasksPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1TasksPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **apiV1TasksPostRequest** | [**ApiV1TasksPostRequest**](ApiV1TasksPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** |  |  -  |
| **400** | Title must be between 3 and 50 characters |  -  |

