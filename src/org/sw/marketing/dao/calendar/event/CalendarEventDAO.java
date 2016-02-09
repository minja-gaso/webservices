package org.sw.marketing.dao.calendar.event;

import org.sw.marketing.data.calendar.Data.Calendar.Event;

public interface CalendarEventDAO
{
	public long createCalendarEvent(long calendarID);
	
	public Event getCalendarEvent(long eventID);
	
	public void updateCalendarEvent(Event event);
	
	public java.util.List<Event> getCalendarEvents(long calendarID);
}
