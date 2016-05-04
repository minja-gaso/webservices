package org.sw.marketing.dao.sitebuilder;

import org.sw.marketing.data.website.Data.Website;
import org.sw.marketing.data.website.Data.Website.Page;
import org.sw.marketing.data.website.Data.Website.Template;

public interface WebsiteDAO
{
	public java.util.List<Website> getWebsites();
	public Website getWebsite(long id);
	public void updateWebsite(Website website);
	public long createWebsite();
	
	public java.util.List<Template> getWebsiteTemplates();
	public Template getWebsiteTemplate(long id);
	public void updateWebsiteTemplate(Template template);
	public long createWebsiteTemplate();

	public java.util.List<Page> getWebsitePages();
	public Page getWebsitePage(long id);
	public void updateWebsitePage(Page page);
	public long createWebsitePage();
}
