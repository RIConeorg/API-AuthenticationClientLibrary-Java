package authentication;

import riconeapi.authentication.Authenticator;
import riconeapi.exceptions.AuthenticationException;
import riconeapi.models.authentication.Endpoint;

/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     1.1
 * @since       Sep 19, 2017
 */
public class TestAuthentication
{
	private static String authUrl = "https://auth.test.ricone.org/login";
	private static String clientId = "dpaDemo";
	private static String clientSecret = "deecd889bff5ed0101a86680752f5f9";
	private static String providerId = "sandbox";
	
	public static void main(String[] args) throws AuthenticationException
	{
		Authenticator auth = Authenticator.getInstance();
		auth.authenticate(authUrl, clientId, clientSecret);

		// Specified endpoint and return all endpoints
		for(Endpoint e : auth.getEndpoints("sandbox", true))
		{
			System.out.println(e.getName());
			System.out.println(e.getHref());
			System.out.println(e.getProviderId());
			System.out.println(e.getToken());
		}

		// Get all endpoint info
//		for(Endpoint e : auth.getEndpoints())
//		{
//			System.out.println(e.getName());
//			System.out.println(e.getHref());
//			System.out.println(e.getProviderId());
//			System.out.println(e.getToken());
//		}
		
		// Get endpoint info by specific provider
//		for(Endpoint e : auth.getEndpoints(providerId))
//		{
//			System.out.println(e.getName());
//			System.out.println(e.getHref());
//			System.out.println(e.getProviderId());
//			System.out.println(e.getToken());
//		}
		
		// Get token
//		System.out.println(auth.getToken());

	}

}
