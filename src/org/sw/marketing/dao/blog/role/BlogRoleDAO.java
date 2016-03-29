package org.sw.marketing.dao.blog.role;

import org.sw.marketing.data.blog.Role;

public interface BlogRoleDAO
{
	public void insert(Role role);
	
	public void delete(long roleID);
	
	public Role getUniqueRole(Role role);
	
	public java.util.List<Role> getRoles(long calendarID);
}
