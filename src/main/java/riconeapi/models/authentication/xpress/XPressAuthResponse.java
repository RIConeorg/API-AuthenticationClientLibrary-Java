package riconeapi.models.authentication.xpress;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/7/2019
 */
@SuppressWarnings("unused")
public class XPressAuthResponse implements Serializable {
    private static final long serialVersionUID = -1464166513365070769L;
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_name")
    private String username;
    @JsonProperty("token")
    private String token;
    @JsonProperty("endpoint")
    private List<XPressEndpoint> endpoint;

    /**
     * Get the universally unique identifier (UUID).
     * @return A String type.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Get the application user name.
     * @return A String type.
     */
    @JsonProperty("user_name")
    public String getUsername() {
        return username;
    }

    /**
     * Get the bearer token for API authentication.
     * @return A String type.
     */
    @JsonProperty("token")
    public String getToken() { return token; }

    /**
     * Get all endpoints.
     * @return A list of XPressEndpoint types.
     */
    @JsonProperty("endpoint")
    public List<XPressEndpoint> getEndpoint() {
        return endpoint;
    }
}
