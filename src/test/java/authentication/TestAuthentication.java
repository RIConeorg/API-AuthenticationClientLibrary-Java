package authentication;

import riconeapi.authentication.Authenticator;
import riconeapi.authentication.Endpoint;
import riconeapi.exceptions.AuthenticationException;

import java.util.Optional;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.1
 * @since Sep 19, 2017
 */
public class TestAuthentication {
    private static final String authUrl = "https://auth.test.ricone.org/login";
//    private static final String authUrl = "https://auth.test.ricone.org/oauth/login";
    private static final String clientId = "dpaDemo";
    private static final String clientSecret = "deecd889bff5ed0101a86680752f5f9";
    private static final String providerId = "sandbox";

    public static void main(String[] args) throws AuthenticationException, InterruptedException {
        Authenticator auth = Authenticator.getInstance();
        auth.authenticate(authUrl, clientId, clientSecret);

        // Get all endpoint info
        getAllEndpoints(auth);

        // Get endpoint info by specific provider
//        getSingleEndpoint(auth, providerId);

        // Get token
//        getToken(auth, providerId);

        // Decoded token info
//        getDecodedToken(auth, providerId);

//        getRefreshToken(auth);
    }

    private static void getAllEndpoints(Authenticator authenticator) {
        System.out.println("#### Get all endpoint info ####");
        for(Endpoint e : authenticator.getEndpoints()) {
            System.out.println("name: " + e.getName() + " | href: " + e.getHref() + " | provider_id: " + e.getProviderId() + " | token: " + e.getToken());
        }
    }

    private static void getSingleEndpoint(Authenticator authenticator, String providerId) {
        System.out.println("#### Get endpoint info by specific provider ####");
        Optional<Endpoint> e = authenticator.getEndpoints(providerId);
        e.ifPresent(endpoint -> System.out.println("name: " + endpoint.getName() + " | href: " + endpoint.getHref() + " | provider_id: " + endpoint.getProviderId() + " | token: " + endpoint.getToken()));
    }

    private static void getToken(Authenticator authenticator, String providerId) {
        System.out.println("#### Get token ####");
        System.out.println("token: " + authenticator.getToken(providerId));
    }

    private static void getDecodedToken(Authenticator authenticator, String providerId) {
        for(Endpoint e : authenticator.getEndpoints()) {
            System.out.println("" + e.getDecodedToken().getApplicationId() + " | " + e.getDecodedToken().getIat() + " | " + e.getDecodedToken().getExp() + " | " + e.getDecodedToken().getIss() + " | " + e.getProviderId() + " | " + e.getToken());
        }
    }

//    private static void getRefreshToken(Authenticator authenticator) throws AuthenticationException, InterruptedException {
//        getDecodedToken(authenticator, providerId);
//        Thread.sleep(120000);
//        authenticator.refreshToken();
//        System.out.println("REFRESHED=========================================================");
//        getDecodedToken(authenticator, providerId);
//    }
}
