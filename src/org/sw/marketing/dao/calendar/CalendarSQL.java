package org.sw.marketing.dao.calendar;

public class CalendarSQL
{
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE user_email = ?";
//	public static final String GET_CALENDARS = "SELECT * FROM calendar.calendars WHERE is_calendar_deleted = false";
	public static final String GET_CALENDARS = "SELECT * FROM calendar.calendars LEFT JOIN calendar.roles ON role_email = ? AND fk_calendar_id = calendar_id WHERE role_type = 'admin' or fk_user_id = ?  AND is_calendar_deleted = false";
	public static final String GET_CALENDARS_MANAGE = "SELECT DISTINCT calendar_id, calendar_creation_timestamp, calendar_type, calendar_title, calendar_pretty_url, calendar_skin_url, calendar_skin_selector, calendar_screen_public_calendar_intro, calendar_screen_public_calendar_closing, is_calendar_deleted, fk_user_id FROM calendar.calendars LEFT JOIN calendar.roles ON role_email = ? AND fk_calendar_id = calendar_id WHERE role_type = 'admin' OR role_type = 'manager' or fk_user_id = ? AND is_calendar_deleted = false";
	public static final String GET_CALENDAR = "SELECT * FROM calendar.calendars WHERE calendar_id = ?";
	public static final String GET_CALENDAR_EVENTS = "SELECT * FROM calendar.events WHERE fk_calendar_id = ? ORDER BY event_start_date ASC";
	public static final String GET_CALENDAR_EVENT = "SELECT * FROM calendar.events WHERE event_id = ?";
	public static final String GET_CALENDAR_ROLES = "SELECT * FROM calendar.roles WHERE fk_calendar_id = ?";
	public static final String GET_CALENDAR_ROLE_UNIQUE_CHECK = "SELECT * FROM calendar.roles WHERE role_type = ? AND role_email = ? AND fk_calendar_id = ?";
	public static final String GET_CALENDAR_CATEGORIES = "SELECT * FROM calendar.categories WHERE fk_calendar_id = ?";
	
	public static final String INSERT_CALENDAR = "INSERT INTO calendar.calendars (fk_user_id) VALUES (?)";	
	public static final String INSERT_CALENDAR_EVENT = "INSERT INTO calendar.events (fk_calendar_id) VALUES (?)";
	public static final String INSERT_ROLE = "INSERT INTO calendar.roles (role_type, role_email, fk_calendar_id) VALUES (?, ?, ?)";
	public static final String INSERT_CALENDAR_CATEGORY = "INSERT INTO calendar.categories (category_label, fk_calendar_id) VALUES (?, ?)";
	
	public static final String UPDATE_CALENDAR = "UPDATE calendar.calendars SET calendar_title = ?, calendar_pretty_url = ?, calendar_skin_url = ?, calendar_skin_selector = ? WHERE calendar_id = ?";
	public static final String UPDATE_CALENDAR_EVENT = "UPDATE calendar.events SET event_start_date = ?, event_start_time = ?, event_end_date = ?, event_end_time = ?, event_title = ?, is_event_location_owned = ?, event_location = ?, event_location_additional_information = ?, event_description = ?, event_speaker = ?, event_registration_label = ?, event_registration_url = ?, event_contact_name = ?, event_contact_phone = ?, event_contact_email = ?, event_cost = ?, event_image_file_name = ?, event_image_file_description = ?, fk_category_id = ? WHERE event_id = ?";
	
	public static final String DELETE_CALENDAR = "UPDATE calendar.calendars SET is_calendar_deleted = true WHERE calendar_id = ?";
	public static final String DELETE_CALENDAR_ROLE = "DELETE FROM calendar.roles WHERE role_id = ?";
	public static final String DELETE_CALENDAR_CATEGORY = "DELETE FROM calendar.categories WHERE category_id = ?";
}
