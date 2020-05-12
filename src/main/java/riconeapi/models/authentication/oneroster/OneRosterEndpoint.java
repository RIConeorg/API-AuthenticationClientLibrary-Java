package riconeapi.models.authentication.oneroster;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/9/2020
 */
@SuppressWarnings("unused")
public class OneRosterEndpoint {
    @JsonProperty("name")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;

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
    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Get the token type i.e. bearer.
     * @return A String type.
     */
    @JsonProperty("token_type")
    public String getTokenType() {
        return tokenType;
    }
}
