package org.sw.marketing.dao.sitebuilder;

public class WebsiteSQL
{
	public final static String GET_WEBSITES = "SELECT * FROM sitebuilder.site";
	public final static String GET_WEBSITE = "SELECT * FROM sitebuilder.site WHERE site_id = ?";
	public final static String UPDATE_WEBSITE = "UPDATE sitebuilder.site SET site_title = ?, site_url = ?, site_css = ? WHERE site_id = ?";
	public final static String CREATE_WEBSITE = "INSERT INTO sitebuilder.site DEFAULT VALUES";

	public final static String GET_WEBSITE_TEMPLATES = "SELECT * FROM sitebuilder.template";
	public final static String GET_WEBSITE_TEMPLATE = "SELECT * FROM sitebuilder.template WHERE template_id = ?";
	public final static String UPDATE_WEBSITE_TEMPLATE = "UPDATE sitebuilder.template SET template_title = ?, template_html = ? WHERE template_id = ?";
	public final static String CREATE_WEBSITE_TEMPLATE = "INSERT INTO sitebuilder.template DEFAULT VALUES";
	
	public final static String GET_WEBSITE_PAGES = "SELECT * FROM sitebuilder.page";
	public final static String GET_WEBSITE_PAGE = "SELECT * FROM sitebuilder.page WHERE page_id = ?";
	public final static String UPDATE_WEBSITE_PAGE = "UPDATE sitebuilder.page SET page_title = ?, page_html = ?, fk_template_id = ? WHERE page_id = ?";
	public final static String CREATE_WEBSITE_PAGE = "INSERT INTO sitebuilder.page DEFAULT VALUES";
}
