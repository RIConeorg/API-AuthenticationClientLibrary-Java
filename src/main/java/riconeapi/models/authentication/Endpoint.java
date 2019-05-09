package riconeapi.models.authentication;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import riconeapi.authentication.Authenticator;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.3.0
 * @since 5/7/2019
 */
@SuppressWarnings("unused")
public class Endpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String href;
    private String providerId;
    private String token;

    /**
     * Get provider name.
     * @return Provider name of type String.
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Get provider href.
     * @return Href of type String.
     */
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    /**
     * Get provider id.
     * @return Provider id of type String.
     */
    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    /**
     * Get the bearer token for API authentication.
     * @return Token of type String.
     */
    @JsonProperty("token")
    public String getToken() {
        return Authenticator.getInstance().getToken();
    }
}
