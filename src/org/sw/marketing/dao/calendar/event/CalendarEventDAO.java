package org.sw.marketing.dao.calendar.event;

import org.sw.marketing.data.calendar.Data.Calendar.Event;
import org.sw.marketing.data.calendar.Data.Calendar.Search;

public interface CalendarEventDAO
{
	/*
	 * create
	 */
	public long createCalendarEvent(long calendarID);
	
	/*
	 * read
	 */
	public Event getCalendarEvent(long eventID);
	public java.util.List<Event> getCalendarEvents(long calendarID);
	public java.util.List<Event> getCalendarEventsByCategory(Search search);
	public java.util.List<Event> getCalendarEventsByTag(Search search);
	public java.util.List<Event> getCalendarEventsToolbox(long calendarID);
	public java.util.List<Event> getCalendarRecurringEvents(long calendarID);
	
	/*
	 * update
	 */
	public void updateCalendarEvent(Event event);
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
