package authentication;

import riconeapi.authentication.Authenticator;
import riconeapi.authentication.Endpoint;
import riconeapi.exceptions.AuthenticationException;

import java.nio.file.InvalidPathException;
import java.time.LocalDateTime;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version x.x.x
 * @since 4/10/2020
 */
public class TestTokenExpiration {
    private static final String authUrl = "https://auth.test.ricone.org/oauth/login";
    private static final String clientId = "dpaDemo";
    private static final String clientSecret = "deecd889bff5ed0101a86680752f5f9";
    private static final String providerId = "sandbox";

    public static void main(String[] args) throws InvalidPathException, InterruptedException, AuthenticationException {
        Authenticator auth = Authenticator.getInstance();
        auth.authenticate(authUrl, clientId, clientSecret);

        while(auth.getEndpoints(providerId).isPresent()) {
            Endpoint e = auth.getEndpoints(providerId).get();
//            System.out.println(LocalDateTime.now() + " | " + e.getDecodedToken().willTokenExpire() + " | " + e.getDecodedToken().getToken().substring(e.getDecodedToken().getToken().lastIndexOf(".") + 1));
            Thread.sleep(420000); // 7minutes
        }
    }
}
