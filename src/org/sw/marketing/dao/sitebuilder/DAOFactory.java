package org.sw.marketing.dao.sitebuilder;

public class DAOFactory
{
	public static WebsiteDAO getWebsiteDAO()
	{
		return new WebsiteDAOImpl();
	}
}
