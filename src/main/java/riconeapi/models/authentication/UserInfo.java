package riconeapi.models.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.3.0
 * @since 5/7/2019
 */
@SuppressWarnings("unused")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String token;
    private List<Endpoint> endpoint;

    /**
     * Get the universally unique identifier.
     * @return UUID of type String.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Get the application user name.
     * @return User name of type String.
     */
    @JsonProperty("user_name")
    public String getUsername() {
        return username;
    }

    /**
     * Get the bearer token for API authentication.
     * @return Token of type String.
     */
    @JsonProperty("token")
    public String getToken() { return token; }

    /**
     * Get all endpoints.
     * @return A list of endpoint of type List.
     */
    @JsonProperty("endpoint")
    public List<Endpoint> getEndpoint() {
        return endpoint;
    }
}
