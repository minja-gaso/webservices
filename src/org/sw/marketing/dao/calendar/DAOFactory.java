package org.sw.marketing.dao.calendar;

import org.sw.marketing.dao.calendar.CalendarDAO;
import org.sw.marketing.dao.calendar.CalendarDAOImpl;
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
}
