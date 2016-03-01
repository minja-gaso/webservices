package org.sw.marketing.dao.skin.role;

import org.sw.marketing.data.skin.Skin.Role;

public interface SkinRoleDAO
{
	public void insert(Role role);
	
	public void delete(long roleID);
	
	public Role getUniqueRole(Role role);
	
	public java.util.List<Role> getRoles(long calendarID);
}
