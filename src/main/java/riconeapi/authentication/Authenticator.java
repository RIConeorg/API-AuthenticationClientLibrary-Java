package riconeapi.authentication;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import riconeapi.exceptions.AuthenticationException;
import riconeapi.models.authentication.oneroster.OneRosterAuthResponse;
import riconeapi.models.authentication.oneroster.OneRosterDecodedToken;
import riconeapi.models.authentication.oneroster.OneRosterEndpoint;
import riconeapi.models.authentication.xpress.XPressDecodedToken;
import riconeapi.models.authentication.xpress.XPressEndpoint;
import riconeapi.models.authentication.xpress.XPressAuthResponse;

import java.util.*;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/10/2020
 */

/*
  Handles authentication for user to Authentication server. Included methods return user and provider information necessary to
  access the data API (i.e. token and url).
 */
public class Authenticator {
    private RestTemplate restTemplate;
    private static Authenticator instance = null;
    private String authUrl;
    private String clientId;
    private String clientSecret;
    private SpecEnum specFlag;
    private List<Endpoint> endpoints = new ArrayList<>();

    private Authenticator() {
        restTemplate = new RestTemplate();
    }

    /**
     * Singleton instantiation.
     * @return Authenticator singleton.
     */
    public static Authenticator getInstance() {
        if(instance == null) {
            instance = new Authenticator();
        }
        return instance;
    }

    /**
     * Establish connection to authenticate to xPress or OneRoster authentication server.
     * @param authUrl      The authentication server url.
     * @param clientId     The clientId for the application.
     * @param clientSecret The clientSecret for the application.
     * @throws AuthenticationException If login does not succeed.
     */
    public void authenticate(String authUrl, String clientId, String clientSecret) throws AuthenticationException {
        this.authUrl = authUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        endpoints.clear();

        if(this.authUrl.endsWith("/oauth/login")) {
            this.specFlag = SpecEnum.ONEROSTER;
            oneRosterLogin(authUrl, clientId, clientSecret);
        }
        else if(this.authUrl.endsWith("/login")) {
            this.specFlag = SpecEnum.XPRESS;
            xPressLogin(authUrl, clientId, clientSecret);
        }
    }

    /**
     * Re-authenticates with xPress or OneRoster authentication server if token is expired.
     * @throws AuthenticationException If login does not succeed.
     */
    void refreshToken() throws AuthenticationException {
        if(specFlag.equals(SpecEnum.XPRESS)) {
            endpoints.clear();
            xPressLogin(authUrl, clientId, clientSecret);
        }
        else if(specFlag.equals(SpecEnum.ONEROSTER)) {
            endpoints.clear();
            oneRosterLogin(authUrl, clientId, clientSecret);
        }
    }

    /**
     * POST to xPress authentication server with provided credentials.
     * @param authUrl      The authentication server url.
     * @param clientId     The clientId for the application.
     * @param clientSecret The clientSecret for the application.
     * @return A list of type Endpoint.
     * @throws AuthenticationException If login does not succeed.
     */
    private List<Endpoint> xPressLogin(String authUrl, String clientId, String clientSecret) throws AuthenticationException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", clientId);
        body.add("password", clientSecret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

        try {
            ResponseEntity<XPressAuthResponse> user = restTemplate.exchange(authUrl, HttpMethod.POST, entity, XPressAuthResponse.class);
            if(user.hasBody()) {
                for(XPressEndpoint endpoint : user.getBody().getEndpoint()) {
                    DecodedToken decodedToken = TokenDecoder.getDecodedToken(TokenDecoder.decodeToken(endpoint.getToken(), XPressDecodedToken.class), endpoint.getToken());
                    endpoints.add(new Endpoint(endpoint, decodedToken));
                }
            }
        }
        catch(Exception e) {
            throw new AuthenticationException("401 UNAUTHORIZED", e, true, true);
        }
        return endpoints;
    }

    /**
     * POST to OneRoster authentication server with provided credentials.
     * @param authUrl      The authentication server url.
     * @param clientId     The clientId for the application.
     * @param clientSecret The clientSecret for the application.
     * @return A list of type Endpoint.
     * @throws AuthenticationException If login does not succeed.
     */
    private List<Endpoint> oneRosterLogin(String authUrl, String clientId, String clientSecret) throws AuthenticationException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
        try {
            ResponseEntity<OneRosterAuthResponse> user = restTemplate.exchange(authUrl, HttpMethod.POST, entity, OneRosterAuthResponse.class);
            if(user.hasBody()) {
                for(OneRosterEndpoint endpoint : user.getBody().getEndpoint()) {
                    DecodedToken decodedToken = TokenDecoder.getDecodedToken(TokenDecoder.decodeToken(endpoint.getAccessToken(), OneRosterDecodedToken.class), endpoint.getAccessToken());
                    endpoints.add(new Endpoint(endpoint, decodedToken));
                }
            }
        }
        catch(Exception e) {
            throw new AuthenticationException("401 UNAUTHORIZED", e, true, true);
        }
        return endpoints;
    }

    /**
     * Retrieve a provider's token.
     * @param providerId The providerId to be returned.
     * @return String type.
     */
    public String getToken(String providerId) {
//        return Objects.requireNonNull(user.getBody()).getToken();
        if(endpoints != null) {
           Optional<Endpoint> e = getEndpoints(providerId);
           if(e.isPresent()) {
               return e.get().getToken();
           }
        }
        return null;
    }

    /**
     * Details of a specific endpoint by providerId.
     * @param providerId The providerId to be returned.
     * @return Optional of type Endpoint.
     */
    public Optional<Endpoint> getEndpoints(String providerId) {
        if(endpoints != null) {
            return endpoints.stream().filter(endpoint -> endpoint.getProviderId().equalsIgnoreCase(providerId)).findFirst();
        }
        return Optional.empty();
    }

    /**
     * A list of all endpoints an application is associated with and it's details.
     * @return List of type Endpoint.
     */
    public List<Endpoint> getEndpoints() {
        if(endpoints != null) {
            return endpoints;
        }
        return new ArrayList<>();
    }
}
