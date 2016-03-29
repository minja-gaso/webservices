package org.sw.marketing.dao.blog.topic;

import java.sql.SQLException;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.blog.BlogSQL;
import org.sw.marketing.data.blog.Data.Blog.Topic.Tag;

public class BlogTopicTagDAOImpl extends BaseDAO implements BlogTopicTagDAO
{
	@Override
	public void addTag(String tag, long eventID, long calendarID)
	{		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.INSERT_BLOG_TAG);
			statement.setString(1, tag);
			statement.setLong(2, eventID);
			statement.setLong(3, calendarID);
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
	public void deleteTags(long eventID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.DELETE_BLOG_TOPIC_TAGS);
			statement.setLong(1, eventID);
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
	public List<Tag> getTags(long eventID)
	{
		java.util.List<Tag> tags = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG_TOPIC_TAGS);
			statement.setLong(1, eventID);
			resultSet = statement.executeQuery();

			Tag tag = null;
			while (resultSet.next())
			{
				if (tags == null)
				{
					tags = new java.util.ArrayList<Tag>();
				}

				long id = resultSet.getLong("topic_tag_id");
				String label = resultSet.getString("topic_tag_name");

				tag = new Tag();
				tag.setId(id);
				tag.setLabel(label);

				tags.add(tag);
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

		return tags;
	}
}
