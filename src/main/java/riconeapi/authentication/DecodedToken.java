package riconeapi.authentication;

import riconeapi.exceptions.AuthenticationException;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/6/2020
 */
public class DecodedToken implements Serializable {
    private static final long serialVersionUID = -2096389183257003754L;
    private String applicationId;
    private String providerId;
    private String href;
    private String token;
    private int iat;
    private static int exp;
    private String iss;

    DecodedToken(String applicationId, String providerId, String href, String token, int iat, int exp, String iss) {
        this.applicationId = applicationId;
        this.providerId = providerId;
        this.href = href;
        this.token = token;
        this.iat = iat;
        this.exp = exp;
        this.iss = iss;
    }

    /**
     * Get applicaiton id.
     * @return A String type.
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Get provider id.
     * @return A String type.
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * Get provider href.
     * @return A String type.
     */
    public String getHref() {
        return href;
    }

    /**
     * Get bearer token.
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
     * Get issued at time.
     * @return An Integer type.
     */
    public Integer getIat() {
        return iat;
    }

    /**
     * Get expiration time.
     * @return An Integer type.
     */
    public Integer getExp() {
        return exp;
    }

    /**
     * Get issuer.
     * @return A String type.
     */
    public String getIss() {
        return iss;
    }

    /**
     * Checks if an endpoint token will expire within the next 10 minutes.
     * @return A Boolean type.
     */
    static Boolean willTokenExpire() {
        try {
            LocalDateTime expiry = LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.systemDefault()).minusMinutes(10);
            return LocalDateTime.now().isAfter(expiry);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
