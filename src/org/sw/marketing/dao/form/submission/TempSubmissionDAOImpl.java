package org.sw.marketing.dao.form.submission;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Statement;
import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.form.FormSQL;
import org.sw.marketing.data.form.Data.Submission;

public class TempSubmissionDAOImpl extends BaseDAO implements TempSubmissionDAO
{
	@Override
	public long insert(long formID, String sessionID, String IP_ADDRESS)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.INSERT_SUBMISSION_TEMP, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, formID);
			statement.setString(2, sessionID);
			statement.setObject(3, InetAddress.getLocalHost().getHostAddress());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getLong(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}

	@Override
	public Submission getSubmissionBySessionID(long formID, String SESSION_ID)
	{
		Submission submission = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSION_TEMP);
			statement.setLong(1, formID);
			statement.setString(2, SESSION_ID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				long id = resultSet.getLong("submission_id");
				String ip = resultSet.getString("ip_address");
				
				submission = new Submission();
				submission.setId(id);
				submission.setIp(ip);
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

		return submission;
	}

	@Override
	public void copyTo(String SESSION_ID, long formID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.COPY_TO_SUBMISSIONS);
			statement.setString(1, SESSION_ID);
			statement.setLong(2, formID);
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
	public void deleteFromTemp(String SESSION_ID, long formID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.DELETE_FROM_TEMP_SUBMISSIONS);
			statement.setString(1, SESSION_ID);
			statement.setLong(2, formID);
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
