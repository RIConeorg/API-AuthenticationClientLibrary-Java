package riconeapi.models.authentication.oneroster;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/9/2020
 */
public class OneRosterAuthResponse {
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("endpoint")
    private List<OneRosterEndpoint> endpoint = null;

    /**
     * Get the expired time in seconds.
     * @return An Integer type.
     */
    @JsonProperty("expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * Get all endpoints.
     * @return A list of OneRosterEndpoint types.
     */
    @JsonProperty("endpoint")
    public List<OneRosterEndpoint> getEndpoint() {
        return endpoint;
    }
}
