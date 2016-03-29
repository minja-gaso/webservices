package org.sw.marketing.dao.blog;

public class BlogSQL
{
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE user_email = ?";
//	public static final String GET_CALENDARS = "SELECT * FROM calendar.calendars WHERE is_calendar_deleted = false";
	public static final String GET_BLOGS = "SELECT * FROM blog.blogs LEFT JOIN blog.roles ON role_email = ? AND fk_blog_id = blog_id WHERE (role_type = 'admin' or fk_user_id = ?) AND is_blog_deleted = false ORDER BY blog_title ASC";
	public static final String GET_BLOGS_MANAGE = "SELECT DISTINCT blog_id, blog_creation_timestamp, blog_title, blog_pretty_url, is_blog_deleted, fk_user_id FROM blog.blogs LEFT JOIN blog.roles ON role_email = ? AND fk_blog_id = blog_id WHERE role_type = 'admin' OR role_type = 'manager' or fk_user_id = ? AND is_blog_deleted = false";
	public static final String GET_BLOG = "SELECT * FROM blog.blogs WHERE blog_id = ?";
	public static final String GET_BLOG_BY_PRETTY_URL = "SELECT * FROM blog.blogs WHERE blog_pretty_url = ?";
	public static final String GET_BLOG_TOPICS = "SELECT * FROM blog.topics WHERE fk_blog_id = ? AND is_topic_published = true ORDER BY topic_publish_date ASC, topic_publish_time ASC";
	public static final String GET_CALENDAR_EVENTS_BY_CATEGORY = "SELECT * FROM calendar.events WHERE event_end_date >= current_date AND fk_category_id = ? AND fk_calendar_id = ? AND is_event_published = true AND (is_event_recurring_visible_on_list_screen = true OR (is_event_recurring_visible_on_list_screen = false AND event_parent_id = 0)) ORDER BY event_start_date ASC, event_start_time ASC";
	//public static final String GET_CALENDAR_EVENTS_BY_TAG = "SELECT * FROM calendar.events WHERE event_end_date >= current_date AND fk_category_id = ? AND is_event_published = true AND (is_event_recurring_visible_on_list_screen = true OR (is_event_recurring_visible_on_list_screen = false AND event_parent_id = 0)) ORDER BY event_start_date ASC, event_start_time ASC";
	public static final String GET_BLOG_TOPICS_TOOLBOX = "SELECT * FROM blog.topics WHERE fk_blog_id = ? ORDER BY topic_publish_date ASC, topic_publish_time ASC";
	public static final String GET_CALENDAR_RECURRING_EVENTS = "SELECT * FROM calendar.events WHERE event_end_date >= current_date AND event_parent_id = ? ORDER BY event_start_date ASC";
	public static final String GET_CALENDAR_EVENTS_BY_SEARCH = "SELECT * FROM (SELECT event_id, event_end_date, event_title, event_description, event_location, to_tsvector(event_title) || to_tsvector(event_description) || to_tsvector(event_location) as document FROM calendar.events GROUP BY event_id ORDER BY event_start_date ASC, event_start_time ASC) p_search WHERE p_search.document @@ to_tsquery(?) AND event_end_date >= current_date";
	public static final String GET_CALENDAR_EVENTS_BY_TAG = "SELECT * FROM calendar.event_tags WHERE event_tag_id = ?";
	public static final String GET_BLOG_TOPIC = "SELECT * FROM blog.topics WHERE topic_id = ?";
	public static final String GET_BLOG_TOPIC_TAGS = "SELECT * FROM blog.tags WHERE fk_topic_id = ?";
	public static final String GET_BLOG_ROLES = "SELECT * FROM blog.roles WHERE fk_blog_id = ?";
	public static final String GET_CALENDAR_ROLE_UNIQUE_CHECK = "SELECT * FROM calendar.roles WHERE role_type = ? AND role_email = ? AND fk_calendar_id = ?";
	public static final String GET_CALENDAR_CATEGORIES = "SELECT * FROM calendar.categories WHERE fk_calendar_id = ?";
	public static final String GET_SKINS = "SELECT * FROM skin.skins LEFT JOIN blog.roles ON role_email = ? WHERE (role_type = 'admin' OR role_type = 'manager' OR fk_user_id = ?)";
	public static final String GET_SKIN = "SELECT * FROM skin.skins WHERE skin_id = ?";
	
	
	public static final String INSERT_BLOG = "INSERT INTO blog.blogs (fk_user_id) VALUES (?)";	
	public static final String INSERT_BLOG_TOPIC = "INSERT INTO blog.topics (fk_blog_id) VALUES (?)";
	public static final String INSERT_BLOG_TAG = "INSERT INTO blog.tags (topic_tag_name, fk_topic_id, fk_blog_id) VALUES (?, ?, ?)";
	public static final String INSERT_ROLE = "INSERT INTO blog.roles (role_type, role_email, fk_blog_id) VALUES (?, ?, ?)";
	public static final String INSERT_CALENDAR_CATEGORY = "INSERT INTO calendar.categories (category_label, fk_calendar_id) VALUES (?, ?)";
	
	public static final String COPY_CALENDAR_EVENT = "INSERT INTO calendar.events (event_start_time, event_end_time, event_title, is_event_location_owned, event_location, event_location_additional_information, event_description, event_speaker, event_registration_label, event_registration_url, event_contact_name, event_contact_phone, event_contact_email, event_cost, event_image_file_name, event_image_file_description, fk_category_id, fk_calendar_id, event_parent_id, is_event_recurring_visible_on_list_screen, is_event_published) SELECT event_start_time, event_end_time, event_title, is_event_location_owned, event_location, event_location_additional_information, event_description, event_speaker, event_registration_label, event_registration_url, event_contact_name, event_contact_phone, event_contact_email, event_cost, event_image_file_name, event_image_file_description, fk_category_id, fk_calendar_id, ?, is_event_recurring_visible_on_list_screen, is_event_published FROM calendar.events WHERE event_id = ?";
	
	public static final String UPDATE_BLOG = "UPDATE blog.blogs SET blog_title = ?, blog_pretty_url = ?, fk_skin_id = ? WHERE blog_id = ?";
	public static final String UPDATE_BLOG_TOPIC = "UPDATE blog.topics SET topic_publish_date = ?, topic_publish_time = ?, topic_title = ?, topic_description = ?, is_topic_published = ? WHERE topic_id = ?";
	public static final String UPDATE_CALENDAR_RECURRING_EVENT = "UPDATE calendar.events SET event_start_date = ?, event_start_time = ?, event_end_date = ?, event_end_time = ?, event_title = ?, is_event_location_owned = ?, event_location = ?, event_location_additional_information = ?, event_description = ?, event_speaker = ?, event_registration_label = ?, event_registration_url = ?, event_contact_name = ?, event_contact_phone = ?, event_contact_email = ?, event_cost = ?, event_image_file_name = ?, event_image_file_description = ?, fk_category_id = ?, is_event_recurring = ?, event_recurring_type = ?, event_recurring_limit = ?, event_recurring_interval = ?, event_recurring_interval_type = ?, is_event_recurring_monday = ?, is_event_recurring_tuesday = ?, is_event_recurring_wednesday = ?, is_event_recurring_thursday = ?, is_event_recurring_friday = ?, is_event_recurring_saturday = ?, is_event_recurring_sunday = ?, is_event_recurring_monthly = ? WHERE event_parent_id = ?";
	
	public static final String DELETE_CALENDAR = "UPDATE blog.blogs SET is_blog_deleted = true WHERE blog_id = ?";
	public static final String DELETE_BLOG_ROLE = "DELETE FROM blog.roles WHERE role_id = ?";
	public static final String DELETE_CALENDAR_CATEGORY = "DELETE FROM calendar.categories WHERE category_id = ?";
	public static final String DELETE_BLOG_TOPIC = "DELETE FROM blog.topics WHERE topic_id = ?";
	public static final String DELETE_BLOG_TOPIC_TAGS = "DELETE FROM blog.tags WHERE fk_topic_id = ?";
	public static final String DELETE_CALENDAR_EVENTS_RECURRING = "DELETE FROM calendar.events WHERE event_parent_id > 0 AND event_parent_id = ?";
	public static final String DELETE_CALENDAR_EVENTS_FIRST_RECURRING = "DELETE FROM calendar.events WHERE event_parent_id > 0 AND event_parent_id = ? AND event_parent_id IN (SELECT event_parent_id FROM calendar.events ORDER BY event_start_date ASC LIMIT 1)";
}
