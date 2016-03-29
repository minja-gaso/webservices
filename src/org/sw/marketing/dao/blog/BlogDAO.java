package org.sw.marketing.dao.blog;

import org.sw.marketing.data.blog.Data.Blog;
import org.sw.marketing.data.blog.User;

public interface BlogDAO
{
	public java.util.List<Blog> getBlogs(User user);
	public java.util.List<Blog> getBlogsManage(User user);
	
	public Blog getBlog(long calendarID);
	public Blog getBlogByPrettyUrl(String prettyUrl);
	
	public long createBlog(User user);
	
	public void updateBlog(Blog calendar);
	
	public void deleteBlog(long calendarID);
}
