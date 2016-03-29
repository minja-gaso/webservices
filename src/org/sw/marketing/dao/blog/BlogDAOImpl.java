package org.sw.marketing.dao.blog;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.data.blog.Data.Blog;
import org.sw.marketing.data.blog.User;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class BlogDAOImpl extends BaseDAO implements BlogDAO
{
	@Override
	public List<Blog> getBlogs(User user)
	{
		java.util.List<Blog> blogs = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOGS);
			statement.setString(1, user.getEmailAddress());
			statement.setLong(2, user.getId());
			resultSet = statement.executeQuery();

			Blog blog = null;
			while (resultSet.next())
			{
				if (blogs == null)
				{
					blogs = new java.util.ArrayList<Blog>();
				}

				long id = resultSet.getLong("blog_id");
				java.util.Date timestamp = resultSet.getTimestamp("blog_creation_timestamp");
				String title = resultSet.getString("blog_title");
				String prettyUrl = resultSet.getString("blog_pretty_url");
				boolean deleted = resultSet.getBoolean("is_blog_deleted");

				blog = new Blog();
				blog.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				blog.setId(id);
				blog.setTitle(title);
				blog.setPrettyUrl(prettyUrl);
				blog.setDeleted(deleted);

				blogs.add(blog);
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

		return blogs;
	}
	@Override
	public List<Blog> getBlogsManage(User user)
	{
		java.util.List<Blog> calendars = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOGS_MANAGE);
			statement.setString(1, user.getEmailAddress());
			statement.setLong(2, user.getId());
			resultSet = statement.executeQuery();

			Blog blog = null;
			while (resultSet.next())
			{
				if (calendars == null)
				{
					calendars = new java.util.ArrayList<Blog>();
				}

				long id = resultSet.getLong("blog_id");
				java.util.Date timestamp = resultSet.getTimestamp("blog_creation_timestamp");
				String title = resultSet.getString("blog_title");
				String prettyUrl = resultSet.getString("blog_pretty_url");
				boolean deleted = resultSet.getBoolean("is_blog_deleted");

				blog = new Blog();
				blog.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				blog.setId(id);
				blog.setTitle(title);
				blog.setPrettyUrl(prettyUrl);
				blog.setDeleted(deleted);

				calendars.add(blog);
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

		return calendars;
	}

	@Override
	public Blog getBlog(long calendarID)
	{
		Blog blog = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG);
			statement.setLong(1, calendarID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("blog_id");
				java.util.Date timestamp = resultSet.getTimestamp("blog_creation_timestamp");
				String title = resultSet.getString("blog_title");
				String prettyUrl = resultSet.getString("blog_pretty_url");
				boolean deleted = resultSet.getBoolean("is_blog_deleted");
				long fkSkinId = resultSet.getLong("fk_skin_id");

				blog = new Blog();
				blog.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				blog.setId(id);
				blog.setTitle(title);
				blog.setPrettyUrl(prettyUrl);
				blog.setDeleted(deleted);
				blog.setFkSkinId(fkSkinId);
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
		
		return blog;
	}

	@Override
	public Blog getBlogByPrettyUrl(String prettyUrl)
	{
		Blog blog = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG_BY_PRETTY_URL);
			statement.setString(1, prettyUrl);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("blog_id");
				java.util.Date timestamp = resultSet.getTimestamp("blog_creation_timestamp");
				String title = resultSet.getString("blog_title");
				boolean deleted = resultSet.getBoolean("is_blog_deleted");
				long fkSkinId = resultSet.getLong("fk_skin_id");

				blog = new Blog();
				blog.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				blog.setId(id);
				blog.setTitle(title);
				blog.setPrettyUrl(prettyUrl);
				blog.setDeleted(deleted);
				blog.setFkSkinId(fkSkinId);
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
		
		return blog;
	}

	@Override
	public long createBlog(User user)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.INSERT_BLOG, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, user.getId());
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
	public void updateBlog(Blog blog)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.UPDATE_BLOG);
			statement.setString(1, blog.getTitle());
			statement.setString(2, blog.getPrettyUrl());
			statement.setLong(3, blog.getFkSkinId());
			statement.setLong(4, blog.getId());
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
	public void deleteBlog(long blogID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.DELETE_CALENDAR);
			statement.setLong(1, blogID);
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
