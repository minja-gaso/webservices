package org.sw.marketing.dao.skin;

import java.sql.SQLException;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.skin.Data;
import org.sw.marketing.data.skin.Data.Skin;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class SkinDAOImpl extends BaseDAO implements SkinDAO
{
	@Override
	public List<Skin> getSkins(Data data)
	{
		java.util.List<Skin> skins = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SkinSQL.GET_SKINS);
			statement.setLong(1, data.getUser().getId());
			resultSet = statement.executeQuery();

			Skin skin = null;
			while (resultSet.next())
			{
				if (skins == null)
				{
					skins = new java.util.ArrayList<Skin>();
				}

				long id = resultSet.getLong("skin_id");
				java.util.Date timestamp = resultSet.getTimestamp("skin_creation_timestamp");
				String title = resultSet.getString("skin_title");
				boolean editable = resultSet.getBoolean("is_skin_editable");
				String skinUrl = resultSet.getString("skin_url");
				String skinSelector = resultSet.getString("skin_selector");
				boolean deleted = resultSet.getBoolean("is_skin_deleted");

				skin = new Skin();
				skin.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				skin.setId(id);
				skin.setTitle(title);
				skin.setEditable(editable);
				skin.setDeleted(deleted);
				skin.setSkinUrl(skinUrl);
				skin.setSkinSelector(skinSelector);

				skins.add(skin);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}

		return skins;
	}
}
