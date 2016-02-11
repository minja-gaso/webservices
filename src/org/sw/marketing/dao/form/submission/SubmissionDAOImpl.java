package org.sw.marketing.dao.form.submission;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.form.FormSQL;
import org.sw.marketing.data.form.Data.Submission;

public class SubmissionDAOImpl extends BaseDAO implements SubmissionDAO
{
	@Override
	public long insert(long formID)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.INSERT_SUBMISSION, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, formID);
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
		finally
		{
			closeConnection(connection, statement, resultSet);
		}
		
		return id;
	}

	@Override
	public List<Submission> getSubmissions(long formID)
	{
		java.util.List<Submission> submissionList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSIONS);
			statement.setLong(1, formID);
			resultSet = statement.executeQuery();

			Submission submission = null;
			while (resultSet.next())
			{
				if (submissionList == null)
				{
					submissionList = new java.util.ArrayList<Submission>();
				}

				long id = resultSet.getLong("submission_id");
//				long userID = resultSet.getLong("fk_user_id");

				submission = new Submission();
				submission.setId(id);
//				submission.setUserId(userID);

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

	@Override
	public List<Submission> getSubmissionsFromStartToEndDate(long formID, String startDateStr, String endDateStr)
	{
		java.util.List<Submission> submissionList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		DateFormat validDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = new Date();
		Date endDate = new Date();
		java.sql.Date sqlStartDate = null;
		java.sql.Date sqEndDate = null;
		try
		{
			startDate = validDateFormat.parse(startDateStr);
			endDate = validDateFormat.parse(endDateStr);
			sqlStartDate = new java.sql.Date(startDate.getTime());
			sqEndDate = new java.sql.Date(endDate.getTime());
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}


		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSIONS_FROM_START_TO_END_DATE);
			statement.setLong(1, formID);
			statement.setDate(2, sqlStartDate);
			statement.setDate(3, sqEndDate);
			resultSet = statement.executeQuery();

			Submission submission = null;
			while (resultSet.next())
			{
				if (submissionList == null)
				{
					submissionList = new java.util.ArrayList<Submission>();
				}

				long id = resultSet.getLong("submission_id");
//				long userID = resultSet.getLong("fk_user_id");

				submission = new Submission();
				submission.setId(id);
//				submission.setUserId(userID);

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
