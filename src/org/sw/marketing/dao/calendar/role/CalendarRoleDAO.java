package org.sw.marketing.dao.calendar.role;

import org.sw.marketing.data.calendar.Role;

public interface CalendarRoleDAO
{
	public void insert(Role role);
	
	public void delete(long roleID);
	
	public Role getUniqueRole(Role role);
	
	public java.util.List<Role> getRoles(long calendarID);
}
