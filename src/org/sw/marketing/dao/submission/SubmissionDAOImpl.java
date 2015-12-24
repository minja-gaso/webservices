package org.sw.marketing.dao.submission;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.SQLStatements;
import org.sw.marketing.data.form.Data.Submission;
import org.sw.marketing.data.form.Data.Submission.Answer;

public class SubmissionDAOImpl extends BaseDAO implements SubmissionDAO
{
	@Override
	public int insert(int formID)
	{
		int id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_SUBMISSION, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, formID);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id = resultSet.getInt(1);
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
		
		return id;
	}

	@Override
	public List<Submission> getSubmissions(int formID)
	{
		java.util.List<Submission> submissionList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_SUBMISSIONS);
			statement.setInt(1, formID);
			resultSet = statement.executeQuery();

			Submission submission = null;
			while (resultSet.next())
			{
				if (submissionList == null)
				{
					submissionList = new java.util.ArrayList<Submission>();
				}

				int id = resultSet.getInt("submission_id");
				int userID = resultSet.getInt("fk_user_id");

				submission = new Submission();
				submission.setId(id);
				submission.setUserId(userID);

				submissionList.add(submission);
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

		return submissionList;
	}
}
