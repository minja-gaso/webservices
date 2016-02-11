package org.sw.marketing.dao.calendar.event;

import java.sql.SQLException;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.data.calendar.Data.Calendar.Event.Tag;

public class CalendarEventTagDAOImpl extends BaseDAO implements CalendarEventTagDAO
{
	@Override
	public void addTag(String tag, long eventID)
	{		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement("INSERT INTO calendar.event_tags (event_tag_name, fk_event_id) VALUES (?, ?)");
			statement.setString(1, tag);
			statement.setLong(2, eventID);
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
			statement = connection.prepareStatement("DELETE FROM calendar.event_tags WHERE fk_event_id = ?");
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
			statement = connection.prepareStatement("SELECT * FROM calendar.event_tags WHERE fk_event_id = ?");
			statement.setLong(1, eventID);
			resultSet = statement.executeQuery();

			Tag tag = null;
			while (resultSet.next())
			{
				if (tags == null)
				{
					tags = new java.util.ArrayList<Tag>();
				}

				long id = resultSet.getLong("event_tag_id");
				String label = resultSet.getString("event_tag_name");

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
