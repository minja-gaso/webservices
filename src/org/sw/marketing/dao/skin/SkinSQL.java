package org.sw.marketing.dao.skin;

public class SkinSQL
{
	public static String GET_SKINS = "SELECT * FROM skin.skins WHERE is_skin_deleted = false AND fk_user_id = ?";
}
