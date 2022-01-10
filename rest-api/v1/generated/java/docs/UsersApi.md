# UsersApi

All URIs are relative to *http://localhost:8762/tramways/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createUser**](UsersApi.md#createUser) | **POST** /users | Creates a new user
[**deleteUser**](UsersApi.md#deleteUser) | **DELETE** /users/{id} | Deletes a user
[**editPassword**](UsersApi.md#editPassword) | **PUT** /users/{id}/password | Edit user&#39;s password
[**editRoles**](UsersApi.md#editRoles) | **PUT** /users/{id}/roles | Edit user&#39;s roles
[**enableUser**](UsersApi.md#enableUser) | **PUT** /users/{id}/enable | Enable or disable a user
[**getUser**](UsersApi.md#getUser) | **GET** /users/{id} | Gets a user
[**getUsers**](UsersApi.md#getUsers) | **GET** /users | Gets all users
[**logged**](UsersApi.md#logged) | **GET** /users/logged | Gets logged user
[**login**](UsersApi.md#login) | **POST** /users/login | Logs a user in
[**resetUser**](UsersApi.md#resetUser) | **PUT** /users/{id}/reset | Reset user&#39;s password


<a name="createUser"></a>
# **createUser**
> User createUser(userRequest)

Creates a new user

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    UserRequest userRequest = new UserRequest(); // UserRequest | 
    try {
      User result = apiInstance.createUser(userRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#createUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userRequest** | [**UserRequest**](UserRequest.md)|  | [optional]

### Return type

[**User**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Created |  -  |

<a name="deleteUser"></a>
# **deleteUser**
> deleteUser(id)

Deletes a user

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.deleteUser(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#deleteUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="editPassword"></a>
# **editPassword**
> editPassword(id, changePasswordRequest)

Edit user&#39;s password

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String id = "id_example"; // String | 
    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(); // ChangePasswordRequest | 
    try {
      apiInstance.editPassword(id, changePasswordRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#editPassword");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **changePasswordRequest** | [**ChangePasswordRequest**](ChangePasswordRequest.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="editRoles"></a>
# **editRoles**
> editRoles(id, userRole)

Edit user&#39;s roles

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String id = "id_example"; // String | 
    List<UserRole> userRole = Arrays.asList(); // List<UserRole> | 
    try {
      apiInstance.editRoles(id, userRole);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#editRoles");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **userRole** | [**List&lt;UserRole&gt;**](UserRole.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="enableUser"></a>
# **enableUser**
> enableUser(id, booleanWrapper)

Enable or disable a user

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String id = "id_example"; // String | 
    BooleanWrapper booleanWrapper = new BooleanWrapper(); // BooleanWrapper | 
    try {
      apiInstance.enableUser(id, booleanWrapper);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#enableUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **booleanWrapper** | [**BooleanWrapper**](BooleanWrapper.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Ok |  -  |

<a name="getUser"></a>
# **getUser**
> User getUser(id)

Gets a user

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      User result = apiInstance.getUser(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#getUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**User**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | User |  -  |
**404** | User not found |  -  |

<a name="getUsers"></a>
# **getUsers**
> List&lt;User&gt; getUsers()

Gets all users

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    try {
      List<User> result = apiInstance.getUsers();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#getUsers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;User&gt;**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | List of users |  -  |

<a name="logged"></a>
# **logged**
> User logged()

Gets logged user

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    try {
      User result = apiInstance.logged();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#logged");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Ok |  -  |

<a name="login"></a>
# **login**
> StringWrapper login(loginRequest)

Logs a user in

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");

    UsersApi apiInstance = new UsersApi(defaultClient);
    LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
    try {
      StringWrapper result = apiInstance.login(loginRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#login");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loginRequest** | [**LoginRequest**](LoginRequest.md)|  | [optional]

### Return type

[**StringWrapper**](StringWrapper.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Ok |  -  |

<a name="resetUser"></a>
# **resetUser**
> resetUser(id, stringWrapper)

Reset user&#39;s password

### Example
```java
// Import classes:
import it.tramways.users.api.ApiClient;
import it.tramways.users.api.ApiException;
import it.tramways.users.api.Configuration;
import it.tramways.users.api.auth.*;
import it.tramways.users.api.models.*;
import it.tramways.users.api.v1.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8762/tramways/rest");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String id = "id_example"; // String | 
    StringWrapper stringWrapper = new StringWrapper(); // StringWrapper | 
    try {
      apiInstance.resetUser(id, stringWrapper);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#resetUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **stringWrapper** | [**StringWrapper**](StringWrapper.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

