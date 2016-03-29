package org.sw.marketing.dao.blog.topic;

import org.sw.marketing.data.blog.Data.Blog.Topic;
import org.sw.marketing.data.calendar.Data.Calendar.Event;
import org.sw.marketing.data.calendar.Data.Calendar.Search;

public interface BlogTopicDAO
{
	/*
	 * create
	 */
	public long createBlogTopic(long blogID);
	
	/*
	 * read
	 */
	public Topic getBlogTopic(long topicID);
	public java.util.List<Topic> getBlogTopics(long blogID);
	public java.util.List<Event> getBlogTopicsByCategory(Search search);
	public java.util.List<Event> getBlogTopicsByTag(Search search);
	public java.util.List<Topic> getBlogTopicsToolbox(long blogID);
	
	/*
	 * update
	 */
	public void updateBlogTopic(Topic topic);
	public void updateCalendarRecurringEvent(Event event);
	public void updateCalendarRecurringEventVisibility(Event event);
	
	/*
	 * delete
	 */
	public void delete(long eventID);
	public void deleteRecurring(long eventID);
	
	/*
	 * other
	 */
	public long copyEvent(Event event);	

	public java.util.List<Long> getMatchedEventsForSearch(String keyword);
	public java.util.List<Long> getMatchedEventsForTag(Search search);
}
