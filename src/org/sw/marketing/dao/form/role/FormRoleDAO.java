package org.sw.marketing.dao.form.role;

import org.sw.marketing.data.form.Role;

public interface FormRoleDAO
{
	public void insert(Role role);
	
	public void delete(long roleID);
	
	public Role getUniqueRole(Role role);
	
	public java.util.List<Role> getRoles(long calendarID);
}
