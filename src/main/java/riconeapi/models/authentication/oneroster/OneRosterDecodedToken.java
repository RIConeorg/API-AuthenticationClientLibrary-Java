package riconeapi.models.authentication.oneroster;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/9/2020
 */
@SuppressWarnings("unused")
public class OneRosterDecodedToken implements Serializable {
    private final static long serialVersionUID = -2898266389932101215L;
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("ims.global.org.security.scope")
    private String imsGlobalOrgSecurityScope;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("href")
    private String href;
    @JsonProperty("iat")
    private Integer iat;
    @JsonProperty("exp")
    private Integer exp;
    @JsonProperty("iss")
    private String iss;

    OneRosterDecodedToken() {
        super();
    }

    /**
     * Get applicaiton id.
     * @return A String type.
     */
    @JsonProperty("app_id")
    public String getAppId() {
        return appId;
    }

    /**
     * Get the ims.global.org.security.scope.
     * @return A String type.
     */
    @JsonProperty("ims.global.org.security.scope")
    public String getImsGlobalOrgSecurityScope() {
        return imsGlobalOrgSecurityScope;
    }

    /**
     * Get the provider id.
     * @return A String type.
     */
    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    /**
     * Get the provider href.
     * @return A String type.
     */
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    /**
     * Get issued at time.
     * @return An Integer type.
     */
    @JsonProperty("iat")
    public Integer getIat() {
        return iat;
    }

    /**
     * Get expiration time.
     * @return An Integer type.
     */
    @JsonProperty("exp")
    public Integer getExp() {
        return exp;
    }

    /**
     * Get issuer.
     * @return A String type.
     */
    @JsonProperty("iss")
    public String getIss() {
        return iss;
    }
}
