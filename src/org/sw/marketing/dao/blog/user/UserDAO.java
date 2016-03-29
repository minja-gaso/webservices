package org.sw.marketing.dao.blog.user;

import org.sw.marketing.data.blog.User;

public interface UserDAO
{
	public User getUserByEmail(String email);
}
