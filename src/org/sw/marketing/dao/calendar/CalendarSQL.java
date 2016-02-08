package org.sw.marketing.dao.calendar;

public class CalendarSQL
{
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE user_email = ?";
	public static final String GET_CALENDARS = "SELECT * FROM calendar.calendars WHERE is_calendar_deleted = false";
	public static final String GET_CALENDAR = "SELECT * FROM calendar.calendars WHERE calendar_id = ?";
	
	public static final String INSERT_CALENDAR = "INSERT INTO calendar.calendars (fk_user_id) VALUES (?)";	
	
	public static final String UPDATE_CALENDAR = "UPDATE calendar.calendars SET calendar_title = ?, calendar_pretty_url = ?, calendar_skin_url = ?, calendar_skin_selector = ? WHERE calendar_id = ?";

	public static final String DELETE_CALENDAR = "UPDATE calendar.calendars SET is_calendar_deleted = true WHERE calendar_id = ?";
}
