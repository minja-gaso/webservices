package org.sw.marketing.dao.blog.skin;

import org.sw.marketing.data.blog.Skin;
import org.sw.marketing.data.blog.User;

public interface BlogSkinDAO
{
	public java.util.List<Skin> getSkins(User user);
	
	public Skin getSkin(long skinID);
}