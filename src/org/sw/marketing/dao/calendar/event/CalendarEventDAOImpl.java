package org.sw.marketing.dao.calendar.event;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.calendar.Data.Calendar.Event;
import org.sw.marketing.data.calendar.Data.Calendar.Event.EventRecurrence;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class CalendarEventDAOImpl extends BaseDAO implements CalendarEventDAO
{	
	@Override
	public void delete(long eventID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.DELETE_CALENDAR_EVENT);
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
	public void deleteRecurring(long eventID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.DELETE_CALENDAR_EVENTS_RECURRING);
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
	public long copyEvent(Event event)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.COPY_CALENDAR_EVENT, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, event.getId());
			statement.setLong(2, event.getId());
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
				boolean published = resultSet.getBoolean("is_event_published");
				java.util.Date startDate = resultSet.getTimestamp("event_start_date");
				java.util.Date endDate = resultSet.getTimestamp("event_end_date");
				java.sql.Time startTime = resultSet.getTime("event_start_time");
				java.sql.Time endTime = resultSet.getTime("event_end_time");
				String title = resultSet.getString("event_title");
				String titleRecurringLabel = resultSet.getString("event_title_recurring_label");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String agenda = resultSet.getString("event_agenda");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");
				long parentId = resultSet.getLong("event_parent_id");
				long categoryId = resultSet.getLong("fk_category_id");
				long calendarId = resultSet.getLong("fk_calendar_id");
				
				/*
				 * recurring events object
				 */
				boolean recurring = resultSet.getBoolean("is_event_recurring");
				boolean recurringVisibleOnListScreen = resultSet.getBoolean("is_event_recurring_visible_on_list_screen");
				boolean recurringMonthly = resultSet.getBoolean("is_event_recurring_monthly");
				String type = resultSet.getString("event_recurring_type");
				int limit = resultSet.getInt("event_recurring_limit");
				int interval = resultSet.getInt("event_recurring_interval");
				String intervalType = resultSet.getString("event_recurring_interval_type");
				boolean monday = resultSet.getBoolean("is_event_recurring_monday");
				boolean tuesday = resultSet.getBoolean("is_event_recurring_tuesday");
				boolean wednesday = resultSet.getBoolean("is_event_recurring_wednesday");
				boolean thursday = resultSet.getBoolean("is_event_recurring_thursday");
				boolean friday = resultSet.getBoolean("is_event_recurring_friday");
				boolean saturday = resultSet.getBoolean("is_event_recurring_saturday");
				boolean sunday = resultSet.getBoolean("is_event_recurring_sunday");
				boolean exact = resultSet.getBoolean("is_event_recurring_day_exact");
				EventRecurrence recurrence = new EventRecurrence();
				recurrence.setRecurring(recurring);
				recurrence.setVisibleOnListScreen(recurringVisibleOnListScreen);
				recurrence.setRecurringMonthly(recurringMonthly);
				recurrence.setType(type);
				recurrence.setLimit(limit);
				recurrence.setInterval(interval);
				recurrence.setIntervalType(intervalType);
				recurrence.setMonday(monday);
				recurrence.setTuesday(tuesday);
				recurrence.setWednesday(wednesday);
				recurrence.setThursday(thursday);
				recurrence.setFriday(friday);
				recurrence.setSaturday(saturday);
				recurrence.setSunday(sunday);
				recurrence.setExact(exact);

				event = new Event();
				event.setId(id);
				event.setPublished(published);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setTitleRecurringLabel(titleRecurringLabel);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setAgenda(agenda);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);
				event.setParentId(parentId);
				event.setCategoryId(categoryId);
				event.setEventRecurrence(recurrence);
				event.setFkId(calendarId);
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
			
			/*
			 * event reccurence
			 */
			EventRecurrence recurrence = event.getEventRecurrence();
			
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
			statement.setBoolean(20, recurrence.isRecurring());
			statement.setString(21, recurrence.getType());
			statement.setInt(22, recurrence.getLimit());
			statement.setInt(23, recurrence.getInterval());
			statement.setString(24, recurrence.getIntervalType());
			statement.setBoolean(25, recurrence.isMonday());
			statement.setBoolean(26, recurrence.isTuesday());
			statement.setBoolean(27, recurrence.isWednesday());
			statement.setBoolean(28, recurrence.isThursday());
			statement.setBoolean(29, recurrence.isFriday());
			statement.setBoolean(30, recurrence.isSaturday());
			statement.setBoolean(31, recurrence.isSunday());
			statement.setBoolean(32, recurrence.isRecurringMonthly());
			statement.setLong(33, event.getParentId());
			statement.setBoolean(34, recurrence.isVisibleOnListScreen());
			statement.setBoolean(35, event.isPublished());
			statement.setString(36, event.getTitleRecurringLabel());
			statement.setString(37, event.getAgenda());
			statement.setLong(38, event.getId());
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
	public void updateCalendarRecurringEvent(Event event)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.UPDATE_CALENDAR_RECURRING_EVENT);
			
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
			
			/*
			 * event reccurence
			 */
			EventRecurrence recurrence = event.getEventRecurrence();
			
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
			statement.setBoolean(20, recurrence.isRecurring());
			statement.setString(21, recurrence.getType());
			statement.setInt(22, recurrence.getLimit());
			statement.setInt(23, recurrence.getInterval());
			statement.setString(24, recurrence.getIntervalType());
			statement.setBoolean(25, recurrence.isMonday());
			statement.setBoolean(26, recurrence.isTuesday());
			statement.setBoolean(27, recurrence.isWednesday());
			statement.setBoolean(28, recurrence.isThursday());
			statement.setBoolean(29, recurrence.isFriday());
			statement.setBoolean(30, recurrence.isSaturday());
			statement.setBoolean(31, recurrence.isSunday());
			statement.setBoolean(32, recurrence.isRecurringMonthly());
//			statement.setString(33, event.getTitleRecurringLabel());
//			statement.setString(34, event.getAgenda());
			statement.setLong(33, event.getId());
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
	public void updateCalendarRecurringEventVisibility(Event event)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement("UPDATE calendar.events SET is_event_recurring_visible_on_list_screen = ? WHERE event_parent_id = ?");
			statement.setBoolean(1, event.getEventRecurrence().isVisibleOnListScreen());
			statement.setLong(2, event.getId());
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
				String titleRecurringLabel = resultSet.getString("event_title_recurring_label");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String agenda = resultSet.getString("event_agenda");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");
				long parentId = resultSet.getLong("event_parent_id");

				event = new Event();
				event.setId(id);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setTitleRecurringLabel(titleRecurringLabel);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setAgenda(agenda);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);
				event.setParentId(parentId);

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
	@Override
	public List<Event> getCalendarEventsByCategory(long categoryID)
	{
		java.util.List<Event> events = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_EVENTS_BY_CATEGORY);
			statement.setLong(1, categoryID);
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
				String titleRecurringLabel = resultSet.getString("event_title_recurring_label");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String agenda = resultSet.getString("event_agenda");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");
				long parentId = resultSet.getLong("event_parent_id");

				event = new Event();
				event.setId(id);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setTitleRecurringLabel(titleRecurringLabel);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setAgenda(agenda);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);
				event.setParentId(parentId);

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

	@Override
	public List<Event> getCalendarEventsToolbox(long calendarID)
	{
		java.util.List<Event> events = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_EVENTS_TOOLBOX);
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
				boolean published = resultSet.getBoolean("is_event_published");
				java.util.Date startDate = resultSet.getTimestamp("event_start_date");
				java.util.Date endDate = resultSet.getTimestamp("event_end_date");
				java.sql.Time startTime = resultSet.getTime("event_start_time");
				java.sql.Time endTime = resultSet.getTime("event_end_time");
				String title = resultSet.getString("event_title");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String agenda = resultSet.getString("event_agenda");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");
				long parentId = resultSet.getLong("event_parent_id");

				event = new Event();
				event.setId(id);
				event.setPublished(published);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setAgenda(agenda);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);
				event.setParentId(parentId);

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

	@Override
	public List<Event> getCalendarRecurringEvents(long eventID)
	{
		java.util.List<Event> events = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_RECURRING_EVENTS);
			statement.setLong(1, eventID);
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
				String titleRecurringLabel = resultSet.getString("event_title_recurring_label");
				boolean locationOwned = resultSet.getBoolean("is_event_location_owned");
				String location = resultSet.getString("event_location");
				String locationAdditional = resultSet.getString("event_location_additional_information");
				String description = resultSet.getString("event_description");
				String agenda = resultSet.getString("event_agenda");
				String speaker = resultSet.getString("event_speaker");
				String registrationLabel = resultSet.getString("event_registration_label");
				String registrationUrl = resultSet.getString("event_registration_url");
				String contactName = resultSet.getString("event_contact_name");
				String contactPhone = resultSet.getString("event_contact_phone");
				String contactEmail = resultSet.getString("event_contact_email");
				String cost = resultSet.getString("event_cost");
				String fileName = resultSet.getString("event_image_file_name");
				String fileDescription = resultSet.getString("event_image_file_description");
				long parentId = resultSet.getLong("event_parent_id");
				
				/*
				 * recurring events object
				 */
				boolean recurring = resultSet.getBoolean("is_event_recurring");
				boolean recurringVisibleOnListScreen = resultSet.getBoolean("is_event_recurring_visible_on_list_screen");
				boolean recurringMonthly = resultSet.getBoolean("is_event_recurring_monthly");
				String type = resultSet.getString("event_recurring_type");
				int limit = resultSet.getInt("event_recurring_limit");
				int interval = resultSet.getInt("event_recurring_interval");
				String intervalType = resultSet.getString("event_recurring_interval_type");
				boolean monday = resultSet.getBoolean("is_event_recurring_monday");
				boolean tuesday = resultSet.getBoolean("is_event_recurring_tuesday");
				boolean wednesday = resultSet.getBoolean("is_event_recurring_wednesday");
				boolean thursday = resultSet.getBoolean("is_event_recurring_thursday");
				boolean friday = resultSet.getBoolean("is_event_recurring_friday");
				boolean saturday = resultSet.getBoolean("is_event_recurring_saturday");
				boolean sunday = resultSet.getBoolean("is_event_recurring_sunday");
				boolean exact = resultSet.getBoolean("is_event_recurring_day_exact");
				EventRecurrence recurrence = new EventRecurrence();
				recurrence.setRecurring(recurring);
				recurrence.setVisibleOnListScreen(recurringVisibleOnListScreen);
				recurrence.setRecurringMonthly(recurringMonthly);
				recurrence.setType(type);
				recurrence.setLimit(limit);
				recurrence.setInterval(interval);
				recurrence.setIntervalType(intervalType);
				recurrence.setMonday(monday);
				recurrence.setTuesday(tuesday);
				recurrence.setWednesday(wednesday);
				recurrence.setThursday(thursday);
				recurrence.setFriday(friday);
				recurrence.setSaturday(saturday);
				recurrence.setSunday(sunday);
				recurrence.setExact(exact);

				event = new Event();
				event.setId(id);
				event.setStartDate(DateToXmlGregorianCalendar.convert(startDate, false));
				event.setEndDate(DateToXmlGregorianCalendar.convert(endDate, false));
				event.setStartTime(DateToXmlGregorianCalendar.convert(startTime, false));
				event.setEndTime(DateToXmlGregorianCalendar.convert(endTime, false));
				event.setTitle(title);
				event.setTitleRecurringLabel(titleRecurringLabel);
				event.setLocationOwned(locationOwned);
				event.setLocation(location);
				event.setLocationAdditional(locationAdditional);
				event.setDescription(description);
				event.setAgenda(agenda);
				event.setSpeaker(speaker);
				event.setRegistrationLabel(registrationLabel);
				event.setRegistrationUrl(registrationUrl);
				event.setContactName(contactName);
				event.setContactPhone(contactPhone);
				event.setContactEmail(contactEmail);
				event.setCost(cost);
				event.setFileName(fileName);
				event.setFileDescription(fileDescription);
				event.setParentId(parentId);
				event.setEventRecurrence(recurrence);

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

	
	@Override
	public List<Long> getMatchedEventsForSearch(String keyword)
	{
		java.util.List<Long> eventIDs = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_EVENTS_BY_SEARCH);
			statement.setString(1, keyword);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				if(eventIDs == null)
				{
					eventIDs = new java.util.ArrayList<Long>();
				}
				long id = resultSet.getLong("event_id");
				eventIDs.add(id);
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

		return eventIDs;
	}
	
}
