package org.sw.marketing.facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.User;
import facebook4j.auth.AccessToken;

public class Facebook4jFactory
{
	public static final String appId = "492881780891150";
	public static final String appSecret = "b55614e3d04ed7444c31a04b8ebf54ad";
	public static final String commaSeparetedPermissions = "email";
	public static void main(String[] args)
	{
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthPermissions(commaSeparetedPermissions);
		facebook.setOAuthAccessToken(new AccessToken("CAAHARft3Xg4BAMZAybBlvwMZAbOc6THpZCGdwyIYJUDYq3nidTWZCDZAAGgXVlbngFPpVS6aLbrYI7t3HiHeZCfM0Iim5fNye9qynQRMZBYZArIhsCS78rpjb0ZAhrPueto0S1M1QJpMKHvyAhSXtUy7k9NVxA9d00lQpTVf3rtndIAzuO6Fs5cRERmJQeapxYUpsNKIEwzmdFQZDZD", null));
		
		try
		{
			User user = facebook.getMe();
			System.out.println(user.getEmail());
		}
		catch (FacebookException e1)
		{
			e1.printStackTrace();
		}
	}
}
