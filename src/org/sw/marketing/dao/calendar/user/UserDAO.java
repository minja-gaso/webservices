package org.sw.marketing.dao.calendar.user;

import org.sw.marketing.data.calendar.User;

public interface UserDAO
{
	public User getUserByEmail(String email);
}
