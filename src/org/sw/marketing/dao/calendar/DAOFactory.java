package org.sw.marketing.dao.calendar;

import org.sw.marketing.dao.calendar.CalendarDAO;
import org.sw.marketing.dao.calendar.CalendarDAOImpl;
import org.sw.marketing.dao.calendar.category.CalendarCategoryDAO;
import org.sw.marketing.dao.calendar.category.CalendarCategoryDAOImpl;
import org.sw.marketing.dao.calendar.event.CalendarEventDAO;
import org.sw.marketing.dao.calendar.event.CalendarEventDAOImpl;
import org.sw.marketing.dao.calendar.event.CalendarEventTagDAO;
import org.sw.marketing.dao.calendar.event.CalendarEventTagDAOImpl;
import org.sw.marketing.dao.calendar.role.CalendarRoleDAO;
import org.sw.marketing.dao.calendar.role.CalendarRoleDAOImpl;
import org.sw.marketing.dao.calendar.skin.CalendarSkinDAO;
import org.sw.marketing.dao.calendar.skin.CalendarSkinDAOImpl;
import org.sw.marketing.dao.calendar.user.UserDAO;
import org.sw.marketing.dao.calendar.user.UserDAOImpl;

public class DAOFactory
{
	public static UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}	
	public static CalendarDAO getCalendarDAO()
	{
		return new CalendarDAOImpl();
	}
	public static CalendarEventDAO getCalendarEventDAO()
	{
		return new CalendarEventDAOImpl();
	}
	public static CalendarEventTagDAO getCalendarEventTagDAO()
	{
		return new CalendarEventTagDAOImpl();
	}
	public static CalendarRoleDAO getCalendarRoleDAO()
	{
		return new CalendarRoleDAOImpl();
	}
	public static CalendarCategoryDAO getCalendarCategoryDAO()
	{
		return new CalendarCategoryDAOImpl();
	}
	public static CalendarSkinDAO getCalendarSkinDAO()
	{
		return new CalendarSkinDAOImpl();
	}
}
