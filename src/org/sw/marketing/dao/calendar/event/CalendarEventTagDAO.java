package org.sw.marketing.dao.calendar.event;

import org.sw.marketing.data.calendar.Data.Calendar.Event.Tag;

public interface CalendarEventTagDAO
{
	public void addTag(String tag, long eventID);
	
	public void deleteTags(long eventID);
	
	public java.util.List<Tag> getTags(long eventID);
}
