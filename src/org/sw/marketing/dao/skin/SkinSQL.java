package org.sw.marketing.dao.skin;

public class SkinSQL
{
	public static final String GET_SKINS = "SELECT * FROM skin.skins LEFT JOIN skin.roles ON role_email = ? AND fk_skin_id = skin_id WHERE (role_type = 'admin' or fk_user_id = ?) AND is_skin_deleted = false";
	public static final String GET_SKIN = "SELECT * FROM skin.skins WHERE skin_id = ?";	
	public static final String GET_ROLES = "SELECT * FROM skin.roles WHERE fk_skin_id = ?";
	public static final String GET_ROLE_UNIQUE_CHECK = "SELECT * FROM skin.roles WHERE role_type = ? AND role_email = ? AND fk_skin_id = ?";
	
	public static final String INSERT_SKIN = "INSERT INTO skin.skins (fk_user_id) VALUES (?)";	
	public static final String INSERT_ROLE = "INSERT INTO skin.roles (role_type, role_email, fk_skin_id) VALUES (?, ?, ?)";
	
	public static final String UPDATE_SKIN = "UPDATE skin.skins SET skin_title = ?, skin_url = ?, skin_selector = ?, skin_css_overrides = ?, is_skin_editable = ?, skin_html = ?, skin_calendar_css = ?, skin_form_css = ? WHERE skin_id = ?";

	public static final String DELETE_ROLE = "DELETE FROM skin.roles WHERE role_id = ?";	
}
