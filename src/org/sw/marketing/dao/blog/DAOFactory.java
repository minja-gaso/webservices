package org.sw.marketing.dao.blog;

import org.sw.marketing.dao.blog.file.BlogTopicFileDAO;
import org.sw.marketing.dao.blog.file.BlogTopicFileDAOImpl;
import org.sw.marketing.dao.blog.role.BlogRoleDAO;
import org.sw.marketing.dao.blog.role.BlogRoleDAOImpl;
import org.sw.marketing.dao.blog.skin.BlogSkinDAO;
import org.sw.marketing.dao.blog.skin.BlogSkinDAOImpl;
import org.sw.marketing.dao.blog.topic.BlogTopicDAO;
import org.sw.marketing.dao.blog.topic.BlogTopicDAOImpl;
import org.sw.marketing.dao.blog.topic.BlogTopicTagDAO;
import org.sw.marketing.dao.blog.topic.BlogTopicTagDAOImpl;
import org.sw.marketing.dao.blog.user.UserDAO;
import org.sw.marketing.dao.blog.user.UserDAOImpl;

public class DAOFactory
{
	public static UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}	
	public static BlogDAO getBlogDAO()
	{
		return new BlogDAOImpl();
	}
	public static BlogTopicDAO getBlogTopicDAO()
	{
		return new BlogTopicDAOImpl();
	}
	public static BlogTopicTagDAO getBlogTopicTagDAO()
	{
		return new BlogTopicTagDAOImpl();
	}
	public static BlogRoleDAO getBlogRoleDAO()
	{
		return new BlogRoleDAOImpl();
	}
	public static BlogSkinDAO getBlogSkinDAO()
	{
		return new BlogSkinDAOImpl();
	}
	public static BlogTopicFileDAO getBlogTopicFileDAO()
	{
		return new BlogTopicFileDAOImpl();
	}
}
