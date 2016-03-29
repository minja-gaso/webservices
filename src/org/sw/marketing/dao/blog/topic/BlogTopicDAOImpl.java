package org.sw.marketing.dao.blog.topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.blog.BlogSQL;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.blog.Data.Blog.Topic;
import org.sw.marketing.data.calendar.Data.Calendar.Event;
import org.sw.marketing.data.calendar.Data.Calendar.Search;
import org.sw.marketing.data.calendar.Data.Calendar.Event.EventRecurrence;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class BlogTopicDAOImpl extends BaseDAO implements BlogTopicDAO
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
			statement = connection.prepareStatement(BlogSQL.DELETE_BLOG_TOPIC);
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
	public long createBlogTopic(long blogID)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.INSERT_BLOG_TOPIC, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, blogID);
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
	public Topic getBlogTopic(long topicID)
	{
		Topic topic = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG_TOPIC);
			statement.setLong(1, topicID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				topic = getTopic(resultSet);
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
		
		return topic;
	}

	@Override
	public void updateBlogTopic(Topic topic)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.UPDATE_BLOG_TOPIC);
			
			/*
			 * declare date and time objects
			 */
			java.util.Date date = null;
			java.sql.Date sqlDate = null;
			java.sql.Time sqlTime = null;
			
			/*
			 * start date
			 */
			date = topic.getPublishDate().toGregorianCalendar().getTime();
			sqlDate = new java.sql.Date(date.getTime());
			statement.setDate(1, sqlDate);
			
			/*
			 * start time
			 */
			date = topic.getPublishTime().toGregorianCalendar().getTime();
			sqlTime = new java.sql.Time(date.getTime());
			statement.setTime(2, sqlTime);
			
			statement.setString(3, topic.getTitle());
			statement.setString(4, topic.getDescription());		
			statement.setBoolean(5, topic.isPublished());
			statement.setLong(6, topic.getId());
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
	public List<Topic> getBlogTopics(long blogID)
	{
		java.util.List<Topic> topics = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG_TOPICS_TOOLBOX);
			statement.setLong(1, blogID);
			resultSet = statement.executeQuery();

			Topic topic = null;
			while (resultSet.next())
			{
				if (topics == null)
				{
					topics = new java.util.ArrayList<Topic>();
				}
				
				topic = getTopic(resultSet);

				topics.add(topic);
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

		return topics;
	}
	@Override
	public List<Event> getBlogTopicsByCategory(Search search)
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
			statement.setLong(1, search.getCategoryId());
			statement.setLong(2, search.getFkCalendarId());
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
	public List<Event> getBlogTopicsByTag(Search search)
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
			statement.setLong(1, search.getTagId());
			statement.setLong(2, search.getFkCalendarId());
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
	public List<Topic> getBlogTopicsToolbox(long blogID)
	{
		java.util.List<Topic> topics = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_BLOG_TOPICS_TOOLBOX);
			statement.setLong(1, blogID);
			resultSet = statement.executeQuery();

			Topic topic = null;
			while (resultSet.next())
			{
				if (topics == null)
				{
					topics = new java.util.ArrayList<Topic>();
				}
				
				topic = getTopic(resultSet);
				topics.add(topic);
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

		return topics;
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

	@Override
	public List<Long> getMatchedEventsForTag(Search search)
	{
		java.util.List<Long> eventIDs = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_EVENTS_BY_TAG);
			statement.setLong(1, search.getTagId());
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				if(eventIDs == null)
				{
					eventIDs = new java.util.ArrayList<Long>();
				}
				long id = resultSet.getLong("fk_event_id");
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
	
	
	public Topic getTopic(ResultSet resultSet) throws SQLException
	{	
		long id = resultSet.getLong("topic_id");
		boolean published = resultSet.getBoolean("is_topic_published");
		java.util.Date publishedDate = resultSet.getTimestamp("topic_publish_date");
		java.sql.Time publishedTime = resultSet.getTime("topic_publish_time");
		String title = resultSet.getString("topic_title");
		String description = resultSet.getString("topic_description");
		long blogId = resultSet.getLong("fk_blog_id");

		Topic topic = new Topic();
		topic.setId(id);
		topic.setPublished(published);
		topic.setPublishDate(DateToXmlGregorianCalendar.convert(publishedDate, false));
		topic.setPublishTime(DateToXmlGregorianCalendar.convert(publishedTime, false));
		topic.setTitle(title);
		topic.setDescription(description);
		topic.setFkId(blogId);
		
		return topic;
	}
}
