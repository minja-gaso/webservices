package org.sw.marketing.dao.form.skin;

import org.sw.marketing.data.form.Skin;
import org.sw.marketing.data.form.User;

public interface FormSkinDAO
{
	public java.util.List<Skin> getSkins(User user);
	
	public Skin getSkin(long skinID);
}
