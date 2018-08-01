
Copyright © 2014-2017 New York State Education Department. All rights reserved.

# RIC One API Java Authentication Client Library
The RIC One Authentication Client Library was developed using Java JDK 7, Spring’s RestTemplate
(http://projects.spring.io/spring-framework/), and FasterXML Jackson (http://wiki.fasterxml.com/JacksonHome).

### Features
* User can login to the authentication server using credentials to retrieve provider information
* Uses POJO object responses

#### Basic Use
```java
Authenticator auth = Authenticator.getInstance();
auth.authenticate(authUrl, clientId, clientSecret);

for(Endpoint e : auth.GetEndpoints())
{
	e.getToken();
}
```

### Project Contents
* Authentication Client Library
* Sample Program - TestAuthentication.java

### Getting Started
1. Review the authentication sections in the library <a href="http://www.ricone.org/vendors/ric-one-api/java-client-developers-guide/" target="_blank">documentation</a>
2. Get your OAuth server credentials
3. Download the Project

## Change Log
### v1.2
* Added new method to Authenticator that returns a single endpoint
    ```java
    Authenticator.getEndpoint(providerId)
    ```

### v1.1
* Added custom exception class for failed authentication - AuthenticationException.java
* Modified Athenticator.java
    * Throws AuthenticationException and will return 401 UNAUTHORIZED message on a failed login attempt
* New method added to Authenticator class
    * getEndpoints(String providerId, Boolean returnAllEndpoints)
        * Ability to look for a specific provider endpoint and all assigned endpoints to an application if Boolean value set to true.
          If Boolean is false and provider endpoint defined, only that endpoints details will be returned.

### v1.0
* Initial release
