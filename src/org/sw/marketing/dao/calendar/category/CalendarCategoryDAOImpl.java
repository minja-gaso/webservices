package org.sw.marketing.dao.calendar.category;

import java.sql.SQLException;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.calendar.Data.Calendar.Category;
import org.sw.marketing.data.calendar.Data.Calendar.Role;

public class CalendarCategoryDAOImpl extends BaseDAO implements CalendarCategoryDAO
{
	@Override
	public void insert(Category category)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.INSERT_CALENDAR_CATEGORY);
			statement.setString(1, category.getLabel());
			statement.setLong(2, category.getFkCalendarId());
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
	
	@Override
	public void delete(long categoryID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.DELETE_CALENDAR_CATEGORY);
			statement.setLong(1, categoryID);
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

	@Override
	public List<Category> getCategories(long calendarID)
	{
		java.util.List<Category> categories = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_CATEGORIES);
			statement.setLong(1, calendarID);
			resultSet = statement.executeQuery();

			Category category = null;
			while (resultSet.next())
			{
				if (categories == null)
				{
					categories = new java.util.ArrayList<Category>();
				}
				
				long id = resultSet.getLong("category_id");
				String label = resultSet.getString("category_label");
				long fkCalendarId = resultSet.getLong("fk_calendar_id");

				category = new Category();
				category.setId(id);
				category.setLabel(label);
				category.setFkCalendarId(fkCalendarId);
				
				categories.add(category);
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
		
		return categories;
	}
}
