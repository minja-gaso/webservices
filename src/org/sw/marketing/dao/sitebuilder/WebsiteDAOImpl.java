package org.sw.marketing.dao.sitebuilder;

import java.sql.SQLException;
import java.sql.Statement;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.blog.BlogSQL;
import org.sw.marketing.data.skin.Skin;
import org.sw.marketing.data.website.Data.Website;
import org.sw.marketing.data.website.Data.Website.Page;
import org.sw.marketing.data.website.Data.Website.Template;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class WebsiteDAOImpl extends BaseDAO implements WebsiteDAO
{
	@Override
	public java.util.List<Website> getWebsites()
	{
		java.util.List<Website> websites = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITES);
			resultSet = statement.executeQuery();

			Website website = null;
			while (resultSet.next())
			{
				if (websites == null)
				{
					websites = new java.util.ArrayList<Website>();
				}

				long id = resultSet.getLong("site_id");
				java.util.Date timestamp = resultSet.getTimestamp("site_creation_timestamp");
				String title = resultSet.getString("site_title");

				website = new Website();
				website.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				website.setId(id);
				website.setTitle(title);

				websites.add(website);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return websites;
	}
	
	@Override
	public Website getWebsite(long websiteId)
	{
		Website website = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE);
			statement.setLong(1, websiteId);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("site_id");
				java.util.Date timestamp = resultSet.getTimestamp("site_creation_timestamp");
				String title = resultSet.getString("site_title");
				String url = resultSet.getString("site_url");
				String css = resultSet.getString("site_css");
				boolean deleted = resultSet.getBoolean("is_site_deleted");

				website = new Website();
				website.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				website.setId(id);
				website.setTitle(title);
				website.setVanityUrl(url);
				website.setCss(css);
				website.setDeleted(deleted);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return website;
	}
	
	@Override
	public void updateWebsite(Website website)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.UPDATE_WEBSITE);
			statement.setString(1, website.getTitle());
			statement.setString(2, website.getVanityUrl());
			statement.setString(3, website.getCss());
			statement.setLong(4, website.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}
	
	@Override
	public long createWebsite()
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.CREATE_WEBSITE, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}
	
	@Override
	public java.util.List<Template> getWebsiteTemplates()
	{
		java.util.List<Template> templates = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_TEMPLATES);
			resultSet = statement.executeQuery();

			Template template = null;
			while (resultSet.next())
			{
				if (templates == null)
				{
					templates = new java.util.ArrayList<Template>();
				}

				long id = resultSet.getLong("template_id");
				String title = resultSet.getString("template_title");
				String html = resultSet.getString("template_html");

				template = new Template();
				template.setId(id);
				template.setTitle(title);
				template.setHtml(html);

				templates.add(template);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return templates;
	}
	
	@Override
	public Template getWebsiteTemplate(long templateID)
	{
		Template template = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_TEMPLATE);
			statement.setLong(1, templateID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("template_id");
				String title = resultSet.getString("template_title");
				String html = resultSet.getString("template_html");
				long fkSiteId = resultSet.getLong("fk_site_id");

				template = new Template();
				template.setId(id);
				template.setTitle(title);
				template.setHtml(html);
				template.setFkSiteId(fkSiteId);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return template;
	}
	
	@Override
	public void updateWebsiteTemplate(Template template)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.UPDATE_WEBSITE_TEMPLATE);
			statement.setString(1, template.getTitle());
			statement.setString(2, template.getHtml());
			statement.setLong(3, template.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}
	
	@Override
	public long createWebsiteTemplate()
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.CREATE_WEBSITE_TEMPLATE, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}
	
	@Override
	public java.util.List<Page> getWebsitePages()
	{
		java.util.List<Page> pages = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_PAGES);
			resultSet = statement.executeQuery();

			Page page = null;
			while (resultSet.next())
			{
				if (pages == null)
				{
					pages = new java.util.ArrayList<Page>();
				}

				long id = resultSet.getLong("page_id");
				String url = resultSet.getString("page_url");
				String title = resultSet.getString("page_title");
				String html = resultSet.getString("page_html");
				long fkId = resultSet.getLong("fk_template_id");

				page = new Page();
				page.setId(id);
				page.setUrl(url);
				page.setTitle(title);
				page.setHtml(html);
				page.setFkTemplateId(fkId);

				pages.add(page);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return pages;
	}
	
	@Override
	public Page getWebsitePage(long pageID)
	{
		Page page = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_PAGE);
			statement.setLong(1, pageID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("page_id");
				String title = resultSet.getString("page_title");
				String html = resultSet.getString("page_html");
				long fkTemplateId = resultSet.getLong("fk_template_id");

				page = new Page();
				page.setId(id);
				page.setTitle(title);
				page.setHtml(html);
				page.setFkTemplateId(fkTemplateId);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return page;
	}
	
	@Override
	public void updateWebsitePage(Page page)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.UPDATE_WEBSITE_PAGE);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getHtml());
			statement.setLong(3, page.getFkTemplateId());
			statement.setLong(4, page.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}
	
	@Override
	public long createWebsitePage()
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.CREATE_WEBSITE_PAGE, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}
}
