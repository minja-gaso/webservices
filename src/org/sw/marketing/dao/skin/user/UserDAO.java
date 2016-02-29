package org.sw.marketing.dao.skin.user;

import org.sw.marketing.data.skin.User;

public interface UserDAO
{
	public User getUserByEmail(String email);
	
	public void insert(User user);
}
