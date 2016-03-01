package org.sw.marketing.dao.form.user;

import org.sw.marketing.data.form.User;

public interface UserDAO
{
	public User getUserByEmail(String email);
	
	public void insert(User user);
}
