package org.sw.marketing.dao.blog.skin;

import java.sql.SQLException;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.blog.BlogSQL;
import org.sw.marketing.data.blog.Skin;
import org.sw.marketing.data.blog.User;

public class BlogSkinDAOImpl extends BaseDAO implements BlogSkinDAO
{
	@Override
	public List<Skin> getSkins(User user)
	{
		java.util.List<Skin> skins = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(BlogSQL.GET_SKINS);
			statement.setString(1, user.getEmailAddress());
			statement.setLong(2, user.getId());
			resultSet = statement.executeQuery();

			Skin skin = null;
			while (resultSet.next())
			{
				if (skins == null)
				{
					skins = new java.util.ArrayList<Skin>();
				}
				
				long id = resultSet.getLong("skin_id");
				String title = resultSet.getString("skin_title");

				skin = new Skin();
				skin.setId(id);
				skin.setTitle(title);
				
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
			statement = connection.prepareStatement(BlogSQL.GET_SKIN);
			statement.setLong(1, skinID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("skin_id");
				String title = resultSet.getString("skin_title");
				String html = resultSet.getString("skin_html");
				String overridesCss = resultSet.getString("skin_css_overrides");
				String calendarCss = resultSet.getString("skin_calendar_css");

				skin = new Skin();
				skin.setId(id);
				skin.setTitle(title);
				skin.setSkinHtml(html);
				skin.setSkinCssOverrides(overridesCss);
				skin.setCalendarCss(calendarCss);
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
}
