package org.sw.marketing.dao.calendar.event;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.calendar.Data.Calendar.Event;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class CalendarEventDAOImpl extends BaseDAO implements CalendarEventDAO
{
	@Override
	public long createCalendarEvent(long calendarID)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.INSERT_CALENDAR_EVENT, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, calendarID);
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
	public Event getCalendarEvent(long eventID)
	{
		Event event = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_EVENT);
			statement.setLong(1, eventID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("event_id");
				java.util.Date startDate = resultSet.getTimestamp("event_start_date");
				java.util.Date endDate = resultSet.getTimestamp("event_end_date");
				java.sql.Time startTime = resultSet.getTime("event_start_time");
				java.sql.Time endTime = resultSet.getTime("event_end_time");
				String title = resultSet.getString("event_title");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");
				long categoryId = resultSet.getLong("fk_category_id");

				event = new Event();
				event.setId(id);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);
				event.setCategoryId(categoryId);
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
		
		return event;
	}

	@Override
	public void updateCalendarEvent(Event event)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.UPDATE_CALENDAR_EVENT);
			
			/*
			 * declare date and time objects
			 */
			java.util.Date date = null;
			java.sql.Date sqlDate = null;
			java.sql.Time sqlTime = null;
			
			/*
			 * start date
			 */
			date = event.getStartDate().toGregorianCalendar().getTime();
			sqlDate = new java.sql.Date(date.getTime());
			statement.setDate(1, sqlDate);
			
			/*
			 * start time
			 */
			date = event.getStartTime().toGregorianCalendar().getTime();
			sqlTime = new java.sql.Time(date.getTime());
			statement.setTime(2, sqlTime);
			
			/*
			 * end date
			 */
			date = event.getEndDate().toGregorianCalendar().getTime();
			sqlDate = new java.sql.Date(date.getTime());
			statement.setDate(3, sqlDate);
			
			/*
			 * end time
			 */
			date = event.getEndTime().toGregorianCalendar().getTime();
			sqlTime = new java.sql.Time(date.getTime());
			statement.setTime(4, sqlTime);
			
			statement.setString(5, event.getTitle());
			statement.setBoolean(6, event.isLocationOwned());
			statement.setString(7, event.getLocation());
			statement.setString(8, event.getLocationAdditional());
			statement.setString(9, event.getDescription());
			statement.setString(10, event.getSpeaker());
			statement.setString(11, event.getRegistrationLabel());
			statement.setString(12, event.getRegistrationUrl());
			statement.setString(13, event.getContactName());
			statement.setString(14, event.getContactPhone());
			statement.setString(15, event.getContactEmail());
			statement.setString(16, event.getCost());
			statement.setString(17, event.getFileName());
			statement.setString(18, event.getFileDescription());
			statement.setLong(19, event.getCategoryId());
			statement.setLong(20, event.getId());
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
	public List<Event> getCalendarEvents(long calendarID)
	{
		java.util.List<Event> events = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_EVENTS);
			statement.setLong(1, calendarID);
			resultSet = statement.executeQuery();

			Event event = null;
			while (resultSet.next())
			{
				if (events == null)
				{
					events = new java.util.ArrayList<Event>();
				}
				
				long id = resultSet.getLong("event_id");
				java.util.Date startDate = resultSet.getTimestamp("event_start_date");
				java.util.Date endDate = resultSet.getTimestamp("event_end_date");
				java.sql.Time startTime = resultSet.getTime("event_start_time");
				java.sql.Time endTime = resultSet.getTime("event_end_time");
				String title = resultSet.getString("event_title");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");

				event = new Event();
				event.setId(id);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);

				events.add(event);
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

		return events;
	}
}
