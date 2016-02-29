package org.sw.marketing.dao.calendar.role;

import java.sql.SQLException;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.calendar.Role;

public class CalendarRoleDAOImpl extends BaseDAO implements CalendarRoleDAO
{
	@Override
	public void insert(Role role)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.INSERT_ROLE);
			statement.setString(1, role.getType());
			statement.setString(2, role.getEmail());
			statement.setLong(3, role.getFkId());
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
	public void delete(long roleID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.DELETE_CALENDAR_ROLE);
			statement.setLong(1, roleID);
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
	public Role getUniqueRole(Role role)
	{
		Role uniqueRole = null; 
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_ROLE_UNIQUE_CHECK);
			statement.setString(1, role.getType());
			statement.setString(2, role.getEmail());
			statement.setLong(3, role.getFkId());
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("role_id");
				String type = resultSet.getString("role_type");
				String email = resultSet.getString("role_email");
				long fkCalendarId = resultSet.getLong("fk_calendar_id");

				uniqueRole = new Role();
				uniqueRole.setId(id);
				uniqueRole.setType(type);
				uniqueRole.setEmail(email);
				uniqueRole.setFkId(fkCalendarId);
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
		
		return uniqueRole;
	}

	@Override
	public List<Role> getRoles(long calendarID)
	{
		java.util.List<Role> roles = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_CALENDAR_ROLES);
			statement.setLong(1, calendarID);
			resultSet = statement.executeQuery();

			Role role = null;
			while (resultSet.next())
			{
				if (roles == null)
				{
					roles = new java.util.ArrayList<Role>();
				}
				
				long id = resultSet.getLong("role_id");
				String type = resultSet.getString("role_type");
				String email = resultSet.getString("role_email");
				long fkCalendarId = resultSet.getLong("fk_calendar_id");

				role = new Role();
				role.setId(id);
				role.setType(type);
				role.setEmail(email);
				role.setFkId(fkCalendarId);
				
				roles.add(role);
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
		
		return roles;
	}
}
