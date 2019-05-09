package riconeapi.models.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.3.0
 * @since 5/7/2019
 */
@SuppressWarnings("unused")
public class DecodedToken {
    private String application_id;
    private long iat;
    private long exp;
    private String iss;
    private String token;

    public DecodedToken() {
    }

    public DecodedToken(String token) {
        this.token = token;
    }

    /**
     * @return Payload data inside an encrypted JWT token
     */
    public DecodedToken getDecodedToken() {
        try {
            ObjectMapper map = new ObjectMapper();
            String[] base64EncodedSegments = token.split("\\.");
            return map.readValue(base64UrlDecode(base64EncodedSegments[1]), DecodedToken.class);
        }
        catch(Exception e) {
            return null;
        }
    }

    private String base64UrlDecode(String input) {
        Base64 decoder = new Base64(true);
        byte[] decodedBytes = decoder.decode(input);
        return new String(decodedBytes);
    }

    /**
     * Get applicaiton id.
     * @return Application Id of type String.
     */
    public String getApplication_id() {
        return application_id;
    }

    /**
     * Get issued at time.
     * @return Issued at value of type Long.
     */
    public long getIat() {
        return iat;
    }

    /**
     * Get expiration time.
     * @return Expiration time of type Long.
     */
    public long getExp() {
        return exp;
    }

    /**
     * Get issuer.
     * @return Issuer of type String.
     */
    public String getIss() {
        return iss;
    }
}
