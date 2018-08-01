package riconeapi.authentication;

import org.joda.time.DateTime;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import riconeapi.exceptions.AuthenticationException;
import riconeapi.models.authentication.DecodedToken;
import riconeapi.models.authentication.Endpoint;
import riconeapi.models.authentication.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     1.2
 * @since       Aug 1, 2018
 */
public class Authenticator {
	private static Authenticator instance = null;
	private static ResponseEntity<UserInfo> user = null;
	private static String authUrl;
	private static String clientId;
	private static String clientSecret;

	private Authenticator() {
	}

	/**
	 * Singleton instantiation.
	 *
	 * @return Authenticator singleton.
	 */
	public static Authenticator getInstance() {
		if (instance == null) {
			instance = new Authenticator();
		}
		return instance;
	}

	/**
	 * Establish connection to authenticate to authentication server.
	 *
	 * @param authUrl      The authentication server url.
	 * @param clientId     The clientId for the application.
	 * @param clientSecret The clientSecret for the application.
	 * @throws AuthenticationException If login does not succeed.
	 */
	public void authenticate(String authUrl, String clientId, String clientSecret) throws AuthenticationException {
		Authenticator.authUrl = authUrl;
		Authenticator.clientId = clientId;
		Authenticator.clientSecret = clientSecret;
		login(authUrl, clientId, clientSecret);
	}

	/**
	 * POST to authentication server with provided credentials.
	 *
	 * @param authUrl      The authentication server url.
	 * @param clientId     The clientId for the application.
	 * @param clientSecret The clientSecret for the application.
	 * @throws AuthenticationException If login does not succeed.
	 */
	private void login(String authUrl, String clientId, String clientSecret) throws AuthenticationException {
		RestTemplate rt = new RestTemplate();

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("username", clientId);
		body.add("password", clientSecret);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

		try {
			Authenticator.user = rt.exchange(authUrl, HttpMethod.POST, entity, UserInfo.class);
		} catch (Exception e) {
			throw new AuthenticationException("401 UNAUTHORIZED", e, true, true);
		}
	}

	/**
	 * Re-authenticates with authentication server if token is expired.
	 *
	 * @param token The token to be validated.
	 * @throws AuthenticationException If login does not succeed.
	 */
	public void refreshToken(String token) throws AuthenticationException {
		DecodedToken decoded = new DecodedToken(token);
		DateTime dt = new DateTime(decoded.getDecodedToken().getExp() * 1000);
		if (dt.isBeforeNow()) {
			Authenticator.getInstance().login(authUrl, clientId, clientSecret);
		}
	}

	/**
	 * Retrieve an application's token.
	 *
	 * @return Token value of type String.
	 */
	public String getToken() {
		return user.getBody().getToken();
	}

	/**
	 * Details of a specific endpoint by providerId.
	 *
	 * @param providerId the providerId to be returned.
	 * @return A list of type Endpoint.
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public List<Endpoint> getEndpoints(String providerId) {
		ArrayList<Endpoint> endpoints = new ArrayList();

		for (Endpoint e : user.getBody().getEndpoint()) {
			if (e.getProviderId().equals(providerId)) {
				endpoints.add(e);
			}
		}
		return endpoints;
	}

	/**
	 * A list of all endpoints an application is associated with and it's details.
	 *
	 * @return A list of type Endpoint.
	 */
	public List<Endpoint> getEndpoints() {
		return user.getBody().getEndpoint();
	}

	/**
	 * Details of a single endpoint if returnAllEndpoints set to false. If returnAllEndpoints is true,
	 * details of all endpoints associated to application are returned.
	 *
	 * @param providerId         The providerId to be returned.
	 * @param returnAllEndpoints All endpoints the application is associated with.
	 * @return A list of type Endpoint if Boolean set to true otherwise a single endpoint.
	 */
	public List<Endpoint> getEndpoints(String providerId, Boolean returnAllEndpoints) {
		ArrayList endpoints = new ArrayList();

		for (Endpoint e : user.getBody().getEndpoint()) {
			if (returnAllEndpoints) {
				endpoints.add(e);
			} else if (e.getProviderId().equals(providerId)) {
				endpoints.add(e);
			}
		}
		return endpoints;
	}

	/**
	 * A single endpoint an application is associated with and it's details.
	 *
	 * @param providerId The providerId to be returned.
	 * @return A single Endpoint type.
	 */
	public Endpoint getEndpoint(String providerId) {
		Endpoint endpoint = null;

		for(Endpoint e : user.getBody().getEndpoint()){
			if(e.getProviderId().equalsIgnoreCase(providerId)){
				endpoint = e;
			}
		}
		return endpoint;
	}
}
