/**
 * @author      Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version     1.0
 * @since       Sep 16, 2016
 * @filename	TestAuthentication.java
 */
package authentication;

import riconeapi.authentication.Authenticator;
import riconeapi.models.authentication.Endpoint;

public class TestAuthentication
{
	private static String authUrl = "https://auth.test.ricone.org/login";
	private static String clientId = "dpaDemo";
	private static String clientSecret = "deecd889bff5ed0101a86680752f5f9";
	private static String providerId = "sandbox";
	
	public static void main(String[] args)
	{
		Authenticator auth = Authenticator.getInstance();
		auth.authenticate(authUrl, clientId, clientSecret);
		
		// Get all endpoint info
		for(Endpoint e : auth.getEndpoints())
		{
			System.out.println(e.getName());
			System.out.println(e.getHref());
			System.out.println(e.getProviderId());
			System.out.println(e.getToken());
		}
		
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
