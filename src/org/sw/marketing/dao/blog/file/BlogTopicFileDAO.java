package org.sw.marketing.dao.blog.file;

import org.sw.marketing.data.blog.Data.Blog.Topic.File;

public interface BlogTopicFileDAO
{
	public void insert(File file);
	
	public java.util.List<File> getFiles(long topicID);
}
