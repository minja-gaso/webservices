package org.sw.marketing.dao.sitebuilder;

import java.sql.SQLException;
import java.sql.Statement;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.blog.BlogSQL;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.skin.Skin;
import org.sw.marketing.data.website.Data.Website;
import org.sw.marketing.data.website.Data.Website.ArchivePage;
import org.sw.marketing.data.website.Data.Website.Page;
import org.sw.marketing.data.website.Data.Website.Template;
import org.sw.marketing.data.website.Data.Website.Page.Component;
import org.sw.marketing.data.website.Data.Website.Page.Component.Item;
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
				String footer = resultSet.getString("site_footer");
				String css = resultSet.getString("site_css");
				boolean deleted = resultSet.getBoolean("is_site_deleted");

				website = new Website();
				website.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				website.setId(id);
				website.setTitle(title);
				website.setVanityUrl(url);
				website.setFooter(footer);
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
			statement.setString(3, website.getFooter());
			statement.setString(4, website.getCss());
			statement.setLong(5, website.getId());
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
	public void deleteWebsite(long id)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.DELETE_WEBSITE);
			statement.setLong(1, id);
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
	public java.util.List<Template> getWebsiteTemplates(long siteID)
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
			statement.setLong(1, siteID);
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
	public Template getWebsiteTemplate(long templateID, long siteID)
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
			statement.setLong(2, siteID);
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
	public long createWebsiteTemplate(long websiteID)
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
			statement.setLong(1, websiteID);
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
	public void deleteWebsiteTemplate(long id)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.DELETE_WEBSITE_TEMPLATE);
			statement.setLong(1, id);
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
	public java.util.List<Page> getWebsitePages(long siteID)
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
			statement.setLong(1, siteID);
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
	public Page getWebsitePage(long pageID, long siteID)
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
			statement.setLong(2, siteID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("page_id");
				java.util.Date creationTimestamp = resultSet.getTimestamp("page_creation_timestamp");
				String title = resultSet.getString("page_title");
				String subtitle = resultSet.getString("page_subtitle");
				String html = resultSet.getString("page_html");
				long fkTemplateId = resultSet.getLong("fk_template_id");

				page = new Page();
				page.setId(id);
				page.setCreationTimestamp(DateToXmlGregorianCalendar.convert(creationTimestamp, false));
				page.setTitle(title);
				page.setSubtitle(subtitle);
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
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_PAGE_NO_SITE);
			statement.setLong(1, pageID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("page_id");
				java.util.Date creationTimestamp = resultSet.getTimestamp("page_creation_timestamp");
				String title = resultSet.getString("page_title");
				String subtitle = resultSet.getString("page_subtitle");
				String html = resultSet.getString("page_html");
				long fkTemplateId = resultSet.getLong("fk_template_id");
				long fkSiteId = resultSet.getLong("fk_site_id");

				page = new Page();
				page.setId(id);
				page.setCreationTimestamp(DateToXmlGregorianCalendar.convert(creationTimestamp, false));
				page.setTitle(title);
				page.setSubtitle(subtitle);
				page.setHtml(html);
				page.setFkTemplateId(fkTemplateId);
				page.setFkSiteId(fkSiteId);
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
			statement.setString(2, page.getSubtitle());
			statement.setString(3, page.getHtml());
			statement.setLong(4, page.getFkTemplateId());
			statement.setLong(5, page.getId());
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
	public long createWebsitePage(long siteID)
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
			statement.setLong(1, siteID);
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
	public void deleteWebsitePage(long id)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.DELETE_WEBSITE_PAGE);
			statement.setLong(1, id);
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

	public long archiveWebsitePage(Page page)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.COPY_WEBSITE_PAGE, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, page.getId());
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
	public Page getWebsitePageArchive(long id)
	{
		Page page = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_PAGE_ARCHIVE);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long archiveId = resultSet.getLong("page_archive_id");
//				String archiveUrl = resultSet.getString("page_archive_url");
				java.util.Date archiveDate = resultSet.getTimestamp("page_archive_creation_timestamp");
				String archiveTitle = resultSet.getString("page_archive_title");
				String archiveSubtitle = resultSet.getString("page_archive_subtitle");
				String archiveHtml = resultSet.getString("page_archive_html");

				page = new Page();
				page.setId(archiveId);
//				page.setUrl(archiveUrl);
				page.setCreationTimestamp(DateToXmlGregorianCalendar.convert(archiveDate, false));
				page.setTitle(archiveTitle);
				page.setSubtitle(archiveSubtitle);
				page.setHtml(archiveHtml);

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
	public java.util.List<ArchivePage> getWebsitePageArchives(long id)
	{
		java.util.List<ArchivePage> pages = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_WEBSITE_PAGE_ARCHIVES);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			ArchivePage page = null;
			while (resultSet.next())
			{
				if (pages == null)
				{
					pages = new java.util.ArrayList<ArchivePage>();
				}

				long archiveId = resultSet.getLong("page_archive_id");
//				String archiveUrl = resultSet.getString("page_archive_url");
				java.util.Date archiveDate = resultSet.getTimestamp("page_archive_creation_timestamp");
				String archiveTitle = resultSet.getString("page_archive_title");
				String archiveSubtitle = resultSet.getString("page_archive_subtitle");
				String archiveHtml = resultSet.getString("page_archive_html");
				long fkId = resultSet.getLong("fk_page_id");

				page = new ArchivePage();
				page.setId(archiveId);
//				page.setUrl(archiveUrl);
				page.setCreationTimestamp(DateToXmlGregorianCalendar.convert(archiveDate, false));
				page.setTitle(archiveTitle);
				page.setSubtitle(archiveSubtitle);
				page.setHtml(archiveHtml);
				page.setFkPageId(fkId);

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
	public void applyArchivePage(Page page)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.APPLY_ARCHIVE_PAGE);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getSubtitle());
			statement.setString(3, page.getHtml());
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
	public java.util.List<Component> getComponents(long fkPageId)
	{
		java.util.List<Component> components = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_COMPONENTS);
			statement.setLong(1, fkPageId);
			resultSet = statement.executeQuery();

			Component component = null;
			while (resultSet.next())
			{
				if (components == null)
				{
					components = new java.util.ArrayList<Component>();
				}

				long id = resultSet.getLong("component_id");
				int orderNumber = resultSet.getInt("component_order_number");
				String typeValue = resultSet.getString("component_type_value");
				String type = resultSet.getString("component_type");
				String title = resultSet.getString("component_title");
				String value = resultSet.getString("component_value");
				String style = resultSet.getString("component_style");
				boolean itemPossible = resultSet.getBoolean("is_component_item_possible");
				long fkId = resultSet.getLong("fk_page_id");

				component = new Component();
				component.setId(id);
				component.setOrderNumber(orderNumber);
				component.setType(type);
				component.setTypeValue(typeValue);
				component.setTitle(title);
				component.setValue(value);
				component.setStyle(style);
				component.setItemPossible(itemPossible);
				component.setFkPageId(fkId);

				components.add(component);
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

		return components;
	}
	
	@Override
	public Component getComponent(long id)
	{
		Component component = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_COMPONENT);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int orderNumber = resultSet.getInt("component_order_number");
				String type = resultSet.getString("component_type");
				String typeValue = resultSet.getString("component_type_value");
				String title = resultSet.getString("component_title");
				String value = resultSet.getString("component_value");
				String style = resultSet.getString("component_style");
				boolean itemPossible = resultSet.getBoolean("is_component_item_possible");
				long fkId = resultSet.getLong("fk_page_id");

				component = new Component();
				component.setId(id);
				component.setOrderNumber(orderNumber);
				component.setType(type);
				component.setTypeValue(typeValue);
				component.setTitle(title);
				component.setValue(value);
				component.setStyle(style);
				component.setItemPossible(itemPossible);
				component.setFkPageId(fkId);
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

		return component;
	}

	@Override
	public Component getComponentByOrderNumber(int orderNumber)
	{
		Component component = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_COMPONENT_BY_ORDER_NUMBER);
			statement.setLong(1, orderNumber);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getInt("component_id");
				String type = resultSet.getString("component_type");
				String typeValue = resultSet.getString("component_type_value");
				String title = resultSet.getString("component_title");
				String value = resultSet.getString("component_value");
				String style = resultSet.getString("component_style");
				boolean itemPossible = resultSet.getBoolean("is_component_item_possible");
				long fkId = resultSet.getLong("fk_page_id");

				component = new Component();
				component.setId(id);
				component.setOrderNumber(orderNumber);
				component.setType(type);
				component.setTypeValue(typeValue);
				component.setTitle(title);
				component.setValue(value);
				component.setStyle(style);
				component.setItemPossible(itemPossible);
				component.setFkPageId(fkId);
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

		return component;
	}
	
	public long createComponent(String type, long fkPageId)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.CREATE_COMPONENT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, type);
			statement.setLong(2, fkPageId);
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
	public void updateComponent(Component component)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.UPDATE_COMPONENT);
			statement.setInt(1, component.getOrderNumber());
			statement.setString(2, component.getType());
			statement.setString(3, component.getTypeValue());
			statement.setString(4, component.getTitle());
			statement.setString(5, component.getValue());
			statement.setString(6, component.getStyle());
			statement.setBoolean(7, component.isItemPossible());
			statement.setLong(8, component.getId());
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
	public void deleteComponent(long id)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.DELETE_COMPONENT);
			statement.setLong(1, id);
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
	public java.util.List<Item> getComponentItems(long fkPageId)
	{
		java.util.List<Item> items = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_COMPONENT_ITEMS);
			statement.setLong(1, fkPageId);
			resultSet = statement.executeQuery();

			Item item = null;
			while (resultSet.next())
			{
				if (items == null)
				{
					items = new java.util.ArrayList<Item>();
				}

				long id = resultSet.getLong("component_item_id");
				int orderNumber = resultSet.getInt("component_item_order_number");
				String title = resultSet.getString("component_item_title");
				String html = resultSet.getString("component_item_html");
				long fkId = resultSet.getLong("fk_component_id");

				item = new Item();
				item.setId(id);
				item.setOrderNumber(orderNumber);
				item.setTitle(title);
				item.setHtml(html);
				item.setFkComponentId(fkId);

				items.add(item);
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

		return items;
	}
	
	@Override
	public Item getComponentItem(long id)
	{
		Item item = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_COMPONENT_ITEM);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int orderNumber = resultSet.getInt("component_item_order_number");
				String title = resultSet.getString("component_item_title");
				String html = resultSet.getString("component_item_html");
				long fkId = resultSet.getLong("fk_component_id");

				item = new Item();
				item.setId(id);
				item.setOrderNumber(orderNumber);
				item.setTitle(title);
				item.setHtml(html);
				item.setFkComponentId(fkId);
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

		return item;
	}

	@Override
	public Item getComponentItemByOrderNumber(int orderNumber)
	{
		Item item = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.GET_COMPONENT_ITEM_BY_ORDER_NUMBER);
			statement.setLong(1, orderNumber);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("component_item_id");
				String title = resultSet.getString("component_item_title");
				String html = resultSet.getString("component_item_html");
				long fkId = resultSet.getLong("fk_component_id");

				item = new Item();
				item.setId(id);
				item.setOrderNumber(orderNumber);
				item.setTitle(title);
				item.setHtml(html);
				item.setFkComponentId(fkId);
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

		return item;
	}
	
	public long createComponentItem(long fkComponentId)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.CREATE_COMPONENT_ITEM, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, fkComponentId);
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
	public void updateComponentItem(Item item)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.UPDATE_COMPONENT_ITEM);
			statement.setInt(1, item.getOrderNumber());
			statement.setString(2, item.getTitle());
			statement.setString(3, item.getHtml());
			statement.setLong(4, item.getId());
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
	public void deleteComponentItem(long id)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(WebsiteSQL.DELETE_COMPONENT_ITEM);
			statement.setLong(1, id);
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
}
