package org.sw.marketing.dao.calendar;

import org.sw.marketing.data.calendar.Data.Calendar;
import org.sw.marketing.data.calendar.Data.User;

public interface CalendarDAO
{
	public java.util.List<Calendar> getCalendars(User user);
//	public java.util.List<Calendar> getCalendarsAdmin(User user);
	public java.util.List<Calendar> getCalendarsManage(User user);
	
	public Calendar getCalendar(long calendarID);
	public Calendar getCalendarByPrettyUrl(String prettyUrl);
	
	public long createCalendar(User user);
	
	public void updateCalendar(Calendar calendar);
	
	public void deleteCalendar(long calendarID);
}
