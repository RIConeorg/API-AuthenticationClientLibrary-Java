/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     1.0
 * @since       Sep 16, 2016
 * Filename		Endpoint.java
 */
package riconeapi.models.authentication;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endpoint implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String href;
	private String providerId;
	private String token;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	@JsonProperty("provider_id")
	public String getProviderId()
	{
		return providerId;
	}

	@JsonProperty("provider_id")
	public void setProviderId(String providerId)
	{
		this.providerId = providerId;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

}
