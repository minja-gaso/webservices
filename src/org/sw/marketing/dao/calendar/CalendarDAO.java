package org.sw.marketing.dao.calendar;

import org.sw.marketing.data.calendar.Data.Calendar;
import org.sw.marketing.data.calendar.Data.User;

public interface CalendarDAO
{
	public java.util.List<Calendar> getCalendars();
	public Calendar getCalendar(long calendarID);
	public Calendar getCalendarByPrettyUrl(String prettyUrl);
	
	public long createCalendar(User user);
	
	public void updateCalendar(Calendar calendar);
	
	public void deleteCalendar(long calendarID);
}
