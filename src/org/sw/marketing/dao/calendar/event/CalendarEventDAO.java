package org.sw.marketing.dao.calendar.event;

import org.sw.marketing.data.calendar.Data.Calendar.Event;

public interface CalendarEventDAO
{
	public void deleteRecurring(long eventID);
	public void deleteFirstRecurring(long eventID);
	
	public long createCalendarEvent(long calendarID);
	public long createCalendarEventRecurring(long calendarID, Event event);
	
	public Event getCalendarEvent(long eventID);

	public void updateCalendarEvent(Event event);
	public void updateCalendarRecurringEvent(Event event);
	
	public java.util.List<Event> getCalendarEvents(long calendarID);
}
