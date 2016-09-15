
Copyright © 2014-2016 New York State Education Department. All rights reserved.

# RIC One API Java Authentication Client Library
The RIC One Authentication Client Library was developed using Java JDK 7, Spring’s RestTemplate
(http://projects.spring.io/spring-framework/), and FasterXML Jackson (http://wiki.fasterxml.com/JacksonHome).

### Features
* User can login to the authentication server using credentials to retrieve provider information
* Uses POJO object responses

#### Basic Use
```java
Authenticator auth = auth.getInstance(); 
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
### v1.0
* Initial release
