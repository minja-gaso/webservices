package org.sw.marketing.dao.skin;

import org.sw.marketing.data.skin.Data;
import org.sw.marketing.data.skin.Skin;

public interface SkinDAO
{
	public java.util.List<Skin> getSkins(Data data);	
	public Skin getSkin(long skinID);
	
	public long createSkin(Data data);
	
	public void updateSkin(Skin skin);
	
	public void deleteSkin(long skinID);
}
