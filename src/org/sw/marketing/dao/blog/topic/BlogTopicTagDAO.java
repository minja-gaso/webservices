package org.sw.marketing.dao.blog.topic;

import org.sw.marketing.data.blog.Data.Blog.Topic.Tag;

public interface BlogTopicTagDAO
{
	public void addTag(String tag, long eventID, long calendarID);
	
	public void deleteTags(long eventID);
	
	public java.util.List<Tag> getTags(long eventID);
}
