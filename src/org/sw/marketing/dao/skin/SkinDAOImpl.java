package org.sw.marketing.dao.skin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.data.skin.Data;
import org.sw.marketing.data.skin.Skin;
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
			statement.setString(1, data.getUser().getEmailAddress());
			statement.setLong(2, data.getUser().getId());
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
	

	@Override
	public Skin getSkin(long skinID)
	{
		Skin skin = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SkinSQL.GET_SKIN);
			statement.setLong(1, skinID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("skin_id");
				java.util.Date timestamp = resultSet.getTimestamp("skin_creation_timestamp");
				String title = resultSet.getString("skin_title");
				boolean editable = resultSet.getBoolean("is_skin_editable");
				String skinUrl = resultSet.getString("skin_url");
				String skinSelector = resultSet.getString("skin_selector");
				String skinCssOverrides = resultSet.getString("skin_css_overrides");
				String skinHtml = resultSet.getString("skin_html");
				String skinCalendarCss = resultSet.getString("skin_calendar_css");
				String skinFormCss = resultSet.getString("skin_form_css");
				boolean deleted = resultSet.getBoolean("is_skin_deleted");

				skin = new Skin();
				skin.setCreationTimestamp(DateToXmlGregorianCalendar.convert(timestamp, false));
				skin.setId(id);
				skin.setTitle(title);
				skin.setEditable(editable);
				skin.setSkinUrl(skinUrl);
				skin.setSkinSelector(skinSelector);
				skin.setSkinCssOverrides(skinCssOverrides);
				skin.setSkinHtml(skinHtml);
				skin.setCalendarCss(skinCalendarCss);
				skin.setFormCss(skinFormCss);
				skin.setDeleted(deleted);
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

		return skin;
	}


	@Override
	public long createSkin(Data data)
	{
		long skinID = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SkinSQL.INSERT_SKIN, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, data.getUser().getId());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				skinID = resultSet.getLong(1);
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
	
		return skinID;
	}
	
	@Override
	public void updateSkin(Skin skin)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SkinSQL.UPDATE_SKIN);
			statement.setString(1, skin.getTitle());
			statement.setString(2, skin.getSkinUrl());
			statement.setString(3, skin.getSkinSelector());
			statement.setString(4, skin.getSkinCssOverrides());
			statement.setBoolean(5, skin.isEditable());
			statement.setString(6, skin.getSkinHtml());
			statement.setString(7, skin.getCalendarCss());
			statement.setString(8, skin.getFormCss());
			statement.setLong(9, skin.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}

	public void deleteSkin(long skinID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SkinSQL.DELETE_SKIN);
			statement.setLong(1, skinID);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
	}
}
