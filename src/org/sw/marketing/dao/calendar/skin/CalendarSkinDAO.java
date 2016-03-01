package org.sw.marketing.dao.calendar.skin;

import org.sw.marketing.data.calendar.Skin;
import org.sw.marketing.data.calendar.User;

public interface CalendarSkinDAO
{
	public java.util.List<Skin> getSkins(User user);
	
	public Skin getSkin(long skinID);
}
