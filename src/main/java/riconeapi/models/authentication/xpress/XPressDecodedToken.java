package riconeapi.models.authentication.xpress;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 2.0.0
 * @since 4/7/2020
 */
@SuppressWarnings("unused")
public class XPressDecodedToken implements Serializable {
    private static final long serialVersionUID = -6241263199433681248L;
    @JsonProperty("application_id")
    private String applicationId;
    @JsonProperty("iat")
    private Integer iat;
    @JsonProperty("exp")
    private Integer exp;
    @JsonProperty("iss")
    private String iss;

    public XPressDecodedToken() {
        super();
    }

    /**
     * Get applicaiton id.
     * @return A String type.
     */
    @JsonProperty("application_id")
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Get issued at time.
     * @return A Integer type.
     */
    @JsonProperty("iat")
    public Integer getIat() {
        return iat;
    }

    /**
     * Get expiration time.
     * @return A Integer type.
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
    public String getIss() { return iss; }
}
