package org.sw.marketing.dao.blog.file;

import java.sql.SQLException;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.blog.BlogSQL;
import org.sw.marketing.data.blog.Data.Blog.Topic.File;

public class BlogTopicFileDAOImpl extends BaseDAO implements BlogTopicFileDAO
{
	@Override
	public void insert(File file)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.INSERT_BLOG_FILE);
			statement.setString(1, file.getType());
			statement.setString(2, file.getName());
			statement.setString(3, file.getDescription());
			statement.setLong(4, file.getFkTopicId());
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
	public java.util.List<File> getFiles(long topicID)
	{
		java.util.List<File> files = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG_TOPIC_FILES);
			statement.setLong(1, topicID);
			resultSet = statement.executeQuery();
			
			File file = null;
			while (resultSet.next())
			{
				if (files == null)
				{
					files = new java.util.ArrayList<File>();
				}
				
				long id = resultSet.getLong("topic_file_id");
				String type = resultSet.getString("topic_file_type");
				String name = resultSet.getString("topic_file_name");
				String description = resultSet.getString("topic_file_description");
				long fkTopicId = resultSet.getLong("fk_topic_Id");

				file = new File();
				file.setId(id);
				file.setType(type);
				file.setName(name);
				file.setDescription(description);
				file.setFkTopicId(fkTopicId);
				
				files.add(file);
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
		
		return files;
	}
}
