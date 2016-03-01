package org.sw.marketing.dao.skin;

import org.sw.marketing.dao.skin.role.SkinRoleDAO;
import org.sw.marketing.dao.skin.role.SkinRoleDAOImpl;
import org.sw.marketing.dao.skin.user.UserDAO;
import org.sw.marketing.dao.skin.user.UserDAOImpl;

public class DAOFactory
{
	public static UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}
	public static SkinDAO getSkinDAO()
	{
		return new SkinDAOImpl();
	}
	public static SkinRoleDAO getRoleDAO()
	{
		return new SkinRoleDAOImpl();
	}
}
