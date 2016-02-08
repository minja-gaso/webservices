package org.sw.marketing.dao.calendar.user;

import java.sql.SQLException;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.calendar.CalendarSQL;
import org.sw.marketing.data.calendar.Data.User;

public class UserDAOImpl extends BaseDAO implements UserDAO
{
	@Override
	public User getUserByEmail(String email)
	{
		User user = null;
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(CalendarSQL.GET_USER_BY_EMAIL);
			statement.setString(1, email);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("user_id");
				String emailAddress = resultSet.getString("user_email");
				String firstName = resultSet.getString("user_first_name");
				String lastName = resultSet.getString("user_last_name");
				boolean active = resultSet.getBoolean("is_user_active");
				boolean admin = resultSet.getBoolean("is_user_admin");

				user = new User();
				user.setId(id);
				user.setEmailAddress(emailAddress);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setActive(active);
				user.setAdmin(admin);
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

		return user;
	}
}
