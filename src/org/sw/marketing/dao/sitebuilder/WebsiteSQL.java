package org.sw.marketing.dao.sitebuilder;

public class WebsiteSQL
{
	public final static String GET_WEBSITES = "SELECT * FROM sitebuilder.site WHERE is_site_deleted = false ORDER BY site_title ASC";
	public final static String GET_WEBSITE = "SELECT * FROM sitebuilder.site WHERE site_id = ?";
	public final static String UPDATE_WEBSITE = "UPDATE sitebuilder.site SET site_title = ?, site_url = ?, site_footer = ?, site_css = ? WHERE site_id = ?";
	public final static String CREATE_WEBSITE = "INSERT INTO sitebuilder.site DEFAULT VALUES";
	public final static String DELETE_WEBSITE = "UPDATE sitebuilder.site SET is_site_deleted = true WHERE site_id = ?";

	public final static String GET_WEBSITE_TEMPLATES = "SELECT * FROM sitebuilder.template WHERE fk_site_id = ?";
	public final static String GET_WEBSITE_TEMPLATE = "SELECT * FROM sitebuilder.template WHERE template_id = ? AND fk_site_id = ?";
	public final static String UPDATE_WEBSITE_TEMPLATE = "UPDATE sitebuilder.template SET template_title = ?, template_html = ? WHERE template_id = ?";
	public final static String CREATE_WEBSITE_TEMPLATE = "INSERT INTO sitebuilder.template (fk_site_id) VALUES (?)";
	public final static String DELETE_WEBSITE_TEMPLATE = "DELETE FROM sitebuilder.template WHERE template_id = ?";
	
	public final static String GET_WEBSITE_PAGES = "SELECT * FROM sitebuilder.page WHERE fk_site_id = ?";
	public final static String GET_WEBSITE_PAGE = "SELECT * FROM sitebuilder.page WHERE page_id = ? AND fk_site_id = ?";
	public final static String UPDATE_WEBSITE_PAGE = "UPDATE sitebuilder.page SET page_title = ?, page_subtitle = ?, page_html = ?, fk_template_id = ? WHERE page_id = ?";
	public final static String CREATE_WEBSITE_PAGE = "INSERT INTO sitebuilder.page (fk_site_id) VALUES (?)";
	public final static String DELETE_WEBSITE_PAGE = "DELETE FROM sitebuilder.page WHERE page_id = ?";
	public static final String COPY_WEBSITE_PAGE = "INSERT INTO sitebuilder.page_archive (page_archive_creation_timestamp, page_archive_title, page_archive_subtitle, page_archive_html, fk_page_id) SELECT now(), page_title, page_subtitle, page_html, page_id FROM sitebuilder.page WHERE page_id = ?";

	public final static String GET_WEBSITE_PAGE_ARCHIVES = "SELECT * FROM sitebuilder.page_archive WHERE fk_page_id = ? ORDER BY page_archive_id DESC";
	public final static String GET_WEBSITE_PAGE_ARCHIVE = "SELECT * FROM sitebuilder.page_archive WHERE page_archive_id = ?";
	public final static String APPLY_ARCHIVE_PAGE = "UPDATE sitebuilder.page SET page_title = ?, page_subtitle = ?, page_html = ? WHERE page_id = ?";

	public final static String GET_COMPONENTS = "SELECT * FROM sitebuilder.component WHERE fk_page_id = ? ORDER BY component_order_number ASC, component_id DESC";
	public final static String GET_COMPONENT = "SELECT * FROM sitebuilder.component WHERE component_id = ?";
	public final static String GET_COMPONENT_BY_ORDER_NUMBER = "SELECT * FROM sitebuilder.component WHERE component_order_number = ?";
	public final static String CREATE_COMPONENT = "INSERT INTO sitebuilder.component (component_type, fk_page_id) VALUES (?, ?)";
	public final static String UPDATE_COMPONENT = "UPDATE sitebuilder.component SET component_title = ?, component_order_number = ? WHERE component_id = ?";
	public final static String DELETE_COMPONENT = "DELETE FROM sitebuilder.component WHERE component_id = ?";
	
	public final static String GET_COMPONENT_ITEMS = "SELECT * FROM sitebuilder.component_item WHERE fk_component_id = ? ORDER BY component_item_order_number ASC";
	public final static String GET_COMPONENT_ITEM = "SELECT * FROM sitebuilder.component_item WHERE component_item_id = ?";
	public final static String GET_COMPONENT_ITEM_BY_ORDER_NUMBER = "SELECT * FROM sitebuilder.component_item WHERE component_item_order_number = ?";
	public final static String CREATE_COMPONENT_ITEM = "INSERT INTO sitebuilder.component_item (fk_component_id) VALUES (?)";
	public final static String UPDATE_COMPONENT_ITEM = "UPDATE sitebuilder.component_item SET component_item_order_number = ?, component_item_title = ?, component_item_html = ? WHERE component_item_id = ?";
	public final static String DELETE_COMPONENT_ITEM = "DELETE FROM sitebuilder.component_item WHERE component_item_id = ?";
}
