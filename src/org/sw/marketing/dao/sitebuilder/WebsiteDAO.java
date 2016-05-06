package org.sw.marketing.dao.sitebuilder;

import org.sw.marketing.data.website.Data.Website;
import org.sw.marketing.data.website.Data.Website.ArchivePage;
import org.sw.marketing.data.website.Data.Website.Page;
import org.sw.marketing.data.website.Data.Website.Template;

public interface WebsiteDAO
{
	public java.util.List<Website> getWebsites();
	public Website getWebsite(long id);
	public void updateWebsite(Website website);
	public long createWebsite();
	public void deleteWebsite(long id);
	
	public java.util.List<Template> getWebsiteTemplates(long siteID);
	public Template getWebsiteTemplate(long id, long siteID);
	public void updateWebsiteTemplate(Template template);
	public long createWebsiteTemplate(long id);
	public void deleteWebsiteTemplate(long id);

	public java.util.List<Page> getWebsitePages(long siteID);
	public Page getWebsitePage(long id, long siteID);
	public void updateWebsitePage(Page page);
	public long createWebsitePage(long siteID);
	public void deleteWebsitePage(long id);
	public long archiveWebsitePage(Page page);

	public Page getWebsitePageArchive(long id);
	public java.util.List<ArchivePage> getWebsitePageArchives(long id);
	public void applyArchivePage(Page page);
}
