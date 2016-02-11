package org.sw.marketing.dao.calendar;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.data.calendar.Data.Calendar;
import org.sw.marketing.data.calendar.Data.User;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class CalendarDAOImpl extends BaseDAO implements CalendarDAO
{
	@Override
	public List<Calendar> getCalendars(User user)
	{
		java.util.List<Calendar> calendars = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDARS);
			statement.setString(1, user.getEmailAddress());
			statement.setLong(2, user.getId());
			resultSet = statement.executeQuery();

			Calendar calendar = null;
			while (resultSet.next())
			{
				if (calendars == null)
				{
					calendars = new java.util.ArrayList<Calendar>();
				}

				long id = resultSet.getLong("calendar_id");
				java.util.Date timestamp = resultSet.getTimestamp("calendar_creation_timestamp");
				String type = resultSet.getString("calendar_type");
				String title = resultSet.getString("calendar_title");
				String prettyUrl = resultSet.getString("calendar_pretty_url");
				boolean deleted = resultSet.getBoolean("is_calendar_deleted");
				String skinUrl = resultSet.getString("calendar_skin_url");
				String skinSelector = resultSet.getString("calendar_skin_selector");

				calendar = new Calendar();
				calendar.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				calendar.setId(id);
				calendar.setType(type);
				calendar.setTitle(title);
				calendar.setPrettyUrl(prettyUrl);
				calendar.setDeleted(deleted);
				calendar.setSkinUrl(skinUrl);
				calendar.setSkinSelector(skinSelector);

				calendars.add(calendar);
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
	public List<Calendar> getCalendarsManage(User user)
	{
		java.util.List<Calendar> calendars = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDARS_MANAGE);
			statement.setString(1, user.getEmailAddress());
			statement.setLong(2, user.getId());
			resultSet = statement.executeQuery();

			Calendar calendar = null;
			while (resultSet.next())
			{
				if (calendars == null)
				{
					calendars = new java.util.ArrayList<Calendar>();
				}

				long id = resultSet.getLong("calendar_id");
				java.util.Date timestamp = resultSet.getTimestamp("calendar_creation_timestamp");
				String type = resultSet.getString("calendar_type");
				String title = resultSet.getString("calendar_title");
				String prettyUrl = resultSet.getString("calendar_pretty_url");
				boolean deleted = resultSet.getBoolean("is_calendar_deleted");
				String skinUrl = resultSet.getString("calendar_skin_url");
				String skinSelector = resultSet.getString("calendar_skin_selector");

				calendar = new Calendar();
				calendar.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				calendar.setId(id);
				calendar.setType(type);
				calendar.setTitle(title);
				calendar.setPrettyUrl(prettyUrl);
				calendar.setDeleted(deleted);
				calendar.setSkinUrl(skinUrl);
				calendar.setSkinSelector(skinSelector);

				calendars.add(calendar);
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
	public Calendar getCalendar(long calendarID)
	{
		Calendar calendar = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR);
			statement.setLong(1, calendarID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("calendar_id");
				java.util.Date timestamp = resultSet.getTimestamp("calendar_creation_timestamp");
				String type = resultSet.getString("calendar_type");
				String title = resultSet.getString("calendar_title");
				String prettyUrl = resultSet.getString("calendar_pretty_url");
				boolean deleted = resultSet.getBoolean("is_calendar_deleted");
				String skinUrl = resultSet.getString("calendar_skin_url");
				String skinSelector = resultSet.getString("calendar_skin_selector");

				calendar = new Calendar();
				calendar.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				calendar.setId(id);
				calendar.setType(type);
				calendar.setTitle(title);
				calendar.setPrettyUrl(prettyUrl);
				calendar.setDeleted(deleted);
				calendar.setSkinUrl(skinUrl);
				calendar.setSkinSelector(skinSelector);
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
		
		return calendar;
	}

	@Override
	public Calendar getCalendarByPrettyUrl(String prettyUrl)
	{
		Calendar calendar = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDARS);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("calendar_id");
				java.util.Date timestamp = resultSet.getTimestamp("calendar_creation_timestamp");
				String type = resultSet.getString("calendar_type");
				String title = resultSet.getString("calendar_title");
				boolean deleted = resultSet.getBoolean("is_calendar_deleted");
				String skinUrl = resultSet.getString("calendar_skin_url");
				String skinSelector = resultSet.getString("calendar_skin_selector");

				calendar = new Calendar();
				calendar.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				calendar.setId(id);
				calendar.setType(type);
				calendar.setTitle(title);
				calendar.setPrettyUrl(prettyUrl);
				calendar.setDeleted(deleted);
				calendar.setSkinUrl(skinUrl);
				calendar.setSkinSelector(skinSelector);
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
		
		return calendar;
	}

	@Override
	public long createCalendar(User user)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.INSERT_CALENDAR, Statement.RETURN_GENERATED_KEYS);
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
	public void updateCalendar(Calendar calendar)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.UPDATE_CALENDAR);
			statement.setString(1, calendar.getTitle());
			statement.setString(2, calendar.getPrettyUrl());
			statement.setString(3, calendar.getSkinUrl());
			statement.setString(4, calendar.getSkinSelector());
			statement.setLong(5, calendar.getId());
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
	public void deleteCalendar(long calendarID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.DELETE_CALENDAR);
			statement.setLong(1, calendarID);
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
