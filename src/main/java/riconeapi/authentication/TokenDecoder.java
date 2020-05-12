package riconeapi.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import riconeapi.models.authentication.oneroster.OneRosterDecodedToken;
import riconeapi.models.authentication.xpress.XPressDecodedToken;

import java.util.Base64;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/2/2020
 */

/**
 * Abstract class for decoding a token.
 */
public abstract class TokenDecoder {
    static <T> T decodeToken(String token, Class<T> clazz) throws JWTVerificationException {
        try {
            DecodedJWT jwt = JWT.decode(token);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(base64UrlDecode(jwt.getPayload()), clazz);
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new JWTVerificationException("Invalid authorization token provided.");
        }
    }

    private static String base64UrlDecode(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }

    /**
     * Return the decoded token information on an xPress token.
     * @param dt An xPress decoded token.
     * @param token An endpoint token.
     * @return A DecodedToken type.
     */
    static DecodedToken getDecodedToken(XPressDecodedToken dt, String token) {
        return new DecodedToken(dt.getApplicationId(), null, null, token, dt.getIat(), dt.getExp(), dt.getIss());
    }

    /**
     * Return the decoded token information on an OneRoster token.
     * @param dt A OneRoster decoded token.
     * @param token An endpoint token.
     * @return A DecodedToken type.
     */
    static DecodedToken getDecodedToken(OneRosterDecodedToken dt, String token) {
        return new DecodedToken(dt.getAppId(), dt.getProviderId(), dt.getHref(), token, dt.getIat(), dt.getExp(), dt.getIss());
    }
}
