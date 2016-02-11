package org.sw.marketing.dao.calendar.category;

import org.sw.marketing.data.calendar.Data.Calendar.Category;

public interface CalendarCategoryDAO
{
	public void insert(Category category);
	
	public void delete(long categoryID);
	
	public java.util.List<Category> getCategories(long calendarID);
}
