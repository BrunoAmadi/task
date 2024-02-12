/*
 * Task API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.ApiV1TasksPostRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class DefaultApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DefaultApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DefaultApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for apiV1TasksPost
     * @param apiV1TasksPostRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Title must be between 3 and 50 characters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call apiV1TasksPostCall(ApiV1TasksPostRequest apiV1TasksPostRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = apiV1TasksPostRequest;

        // create path and map variables
        String localVarPath = "/api/v1/tasks";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call apiV1TasksPostValidateBeforeCall(ApiV1TasksPostRequest apiV1TasksPostRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiV1TasksPostRequest' is set
        if (apiV1TasksPostRequest == null) {
            throw new ApiException("Missing the required parameter 'apiV1TasksPostRequest' when calling apiV1TasksPost(Async)");
        }
        

        okhttp3.Call localVarCall = apiV1TasksPostCall(apiV1TasksPostRequest, _callback);
        return localVarCall;

    }

    /**
     * Create a new task
     * Endpoint for create a new task
     * @param apiV1TasksPostRequest  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Title must be between 3 and 50 characters </td><td>  -  </td></tr>
     </table>
     */
    public void apiV1TasksPost(ApiV1TasksPostRequest apiV1TasksPostRequest) throws ApiException {
        apiV1TasksPostWithHttpInfo(apiV1TasksPostRequest);
    }

    /**
     * Create a new task
     * Endpoint for create a new task
     * @param apiV1TasksPostRequest  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Title must be between 3 and 50 characters </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> apiV1TasksPostWithHttpInfo(ApiV1TasksPostRequest apiV1TasksPostRequest) throws ApiException {
        okhttp3.Call localVarCall = apiV1TasksPostValidateBeforeCall(apiV1TasksPostRequest, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Create a new task (asynchronously)
     * Endpoint for create a new task
     * @param apiV1TasksPostRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Title must be between 3 and 50 characters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call apiV1TasksPostAsync(ApiV1TasksPostRequest apiV1TasksPostRequest, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = apiV1TasksPostValidateBeforeCall(apiV1TasksPostRequest, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
