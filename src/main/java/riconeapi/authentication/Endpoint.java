package riconeapi.authentication;

import riconeapi.exceptions.AuthenticationException;
import riconeapi.models.authentication.oneroster.OneRosterEndpoint;
import riconeapi.models.authentication.xpress.XPressEndpoint;

import static riconeapi.authentication.DecodedToken.willTokenExpire;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/2/2020
 */

/**
 * The Endpoint class returns data about a specific provider. This includes the provider name,
 * href, provider id, token, and decoded token.
 */
public class Endpoint {
    private String name;
    private String href;
    private String providerId;
    private String token;
    private DecodedToken decodedToken;

    /**
     * Constructor for an xPress endpoint.
     * @param endpoint An xPress endpoint.
     * @param decodedToken The decoded token for an xPress endpoint.
     */
    Endpoint(XPressEndpoint endpoint, DecodedToken decodedToken) {
        super();
        this.name = endpoint.getName();
        this.href = endpoint.getHref();
        this.providerId = endpoint.getProviderId();
        this.token = endpoint.getToken();
        this.decodedToken = decodedToken;
    }

    /**
     * Constructor for a OneRoster endpoint.
     * @param endpoint A OneRoster endpoint.
     * @param decodedToken The decoded token for a OneRoster endpoint.
     */
    Endpoint(OneRosterEndpoint endpoint, DecodedToken decodedToken) {
        super();
        this.name = endpoint.getName();
        this.href = endpoint.getHref();
        this.providerId = endpoint.getProviderId();
        this.token = endpoint.getAccessToken();
        this.decodedToken = decodedToken;
    }

    /**
     * Get provider name.
     * @return A String type.
     */
    public String getName() { return name; }

    /**
     * Get provider href.
     * @return A String type.
     */
    public String getHref() { return href; }

    /**
     * Get provider id.
     * @return A String type.
     */
    public String getProviderId() { return providerId; }

    /**
     * Get the bearer token for API authentication.
     * @return A String type.
     */
    public String getToken() {
        if(willTokenExpire()) {
            try {
                Authenticator.getInstance().refreshToken();
            }
            catch(AuthenticationException e) {
                e.printStackTrace();
            }
        }
        return token;
    }

    /**
     * Get the decoded token information on a bearer token.
     * @return A DecodedToken type.
     */
    public DecodedToken getDecodedToken() { return decodedToken; }

    public void setDecodedToken(DecodedToken decodedToken) {
        this.decodedToken = decodedToken;
    }
}
