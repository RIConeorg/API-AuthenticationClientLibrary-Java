package authentication;

import riconeapi.authentication.Authenticator;
import riconeapi.exceptions.AuthenticationException;
import riconeapi.models.authentication.DecodedToken;
import riconeapi.models.authentication.Endpoint;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.1
 * @since Sep 19, 2017
 */
public class TestAuthentication {
    public static void main(String[] args) throws AuthenticationException {
        String authUrl = "https://auth.test.ricone.org/login";
        String clientId = "dpaDemo";
        String clientSecret = "deecd889bff5ed0101a86680752f5f9";
        String providerId = "sandbox";

        Authenticator auth = Authenticator.getInstance();
        auth.authenticate(authUrl, clientId, clientSecret);

        // Specified endpoint and return all endpoints
        System.out.println("#### Specified endpoint and return all endpoints - false ####");
        for(Endpoint e : auth.getEndpoints("sandbox", false)) {
            System.out.println("name: " + e.getName());
            System.out.println("href: " + e.getHref());
            System.out.println("provider_id: " + e.getProviderId());
            System.out.println("token: " + e.getToken());
        }

        // Specified endpoint and return all endpoints
        System.out.println("#### Specified endpoint and return all endpoints - true ####");
        for(Endpoint e : auth.getEndpoints("sandbox", true)) {
            System.out.println("name: " + e.getName());
            System.out.println("href: " + e.getHref());
            System.out.println("provider_id: " + e.getProviderId());
            System.out.println("token: " + e.getToken());
        }

        // Get all endpoint info
        System.out.println("#### Get all endpoint info ####");
        for(Endpoint e : auth.getEndpoints()) {
            System.out.println("name: " + e.getName());
            System.out.println("href: " + e.getHref());
            System.out.println("provider_id: " + e.getProviderId());
            System.out.println("token: " + e.getToken());
        }

        // Get endpoint info by specific provider
        System.out.println("#### Get endpoint info by specific provider ####");
        for(Endpoint e : auth.getEndpoints(providerId)) {
            System.out.println("name: " + e.getName());
            System.out.println("href: " + e.getHref());
            System.out.println("provider_id: " + e.getProviderId());
            System.out.println("token: " + e.getToken());
        }

        // Get token
        System.out.println("#### Get token ####");
        System.out.println("token: " + auth.getToken());

        // Decoded token info
        System.out.println("#### Decoded token info ####");
        DecodedToken dt = new DecodedToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBsaWNhdGlvbl9pZCI6ImRwYURlbW8iLCJpYXQiOjE1NTY3MzQzOTMsImV4cCI6MTU1NjczNzk5MywiaXNzIjoiaHR0cDovL2F1dGgucmljb25lLm9yZy8ifQ.N_qov09iAds-bk9Liy8rsYteRNugq_2tbA02E0zFY7s");
        System.out.println("application_id: " + dt.getDecodedToken().getApplication_id());
        System.out.println("iat: " + dt.getDecodedToken().getIat());
        System.out.println("exp: " + dt.getDecodedToken().getExp());
        System.out.println("iss: " + dt.getDecodedToken().getIss());

    }

}
