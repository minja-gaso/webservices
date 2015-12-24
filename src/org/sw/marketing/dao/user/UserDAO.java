package org.sw.marketing.dao.user;

import org.sw.marketing.data.form.Data.User;

public interface UserDAO
{
	public User getUserByEmail(String email);
	
	public void insert(User user);
}
