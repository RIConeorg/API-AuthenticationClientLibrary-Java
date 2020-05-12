package riconeapi.models.authentication.xpress;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/6/2019
 */
@SuppressWarnings("unused")
public class XPressEndpoint implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("token")
    private String token;
    private static final long serialVersionUID = 1L;

    /**
     * Get provider name.
     * @return A String type.
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Get provider href.
     * @return A String type.
     */
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    /**
     * Get provider id.
     * @return A String type.
     */
    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    /**
     * Get the token for API authentication.
     * @return A String type.
     */
    @JsonProperty("token")
    public String getToken() { return token; }
}
