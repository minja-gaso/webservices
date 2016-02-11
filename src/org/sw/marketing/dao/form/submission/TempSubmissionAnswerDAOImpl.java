package org.sw.marketing.dao.form.submission;

import java.sql.SQLException;
import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.form.FormSQL;
import org.sw.marketing.data.form.Data.Form.Question;
import org.sw.marketing.data.form.Data.Submission;
import org.sw.marketing.data.form.Data.Submission.Answer;

public class TempSubmissionAnswerDAOImpl extends BaseDAO implements TempSubmissionAnswerDAO
{
	@Override
	public void insert(Submission submission, Answer answer, Question question)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.INSERT_SUBMISSION_TEMP_ANSWER);			
			statement.setString(1, answer.getAnswerValue());
			statement.setInt(2, submission.getPage());
			boolean isMultipleChoice = false;
			if(question.getType().equals("radio") || question.getType().equals("checkbox"))
			{
				isMultipleChoice = true;
			}
			statement.setBoolean(3, isMultipleChoice);
			statement.setLong(4, question.getId());
			statement.setLong(5, submission.getId());
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
	public java.util.List<Answer> getSubmissionAnswersByPage(Submission submission)
	{
		java.util.List<Answer> submissionAnswerList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSION_TEMP_ANSWERS_BY_PAGE);
			statement.setLong(1, submission.getId());
			statement.setInt(2, submission.getPage());
			resultSet = statement.executeQuery();

			Answer answer = null;
			while (resultSet.next())
			{
				if (submissionAnswerList == null)
				{
					submissionAnswerList = new java.util.ArrayList<Answer>();
				}
				
				long questionID = resultSet.getLong("question_id");
				String answerValue = resultSet.getString("sub_answer_value");
				boolean multipleChoice = resultSet.getBoolean("is_sub_answer_multiple_choice");

				answer = new Answer();
				answer.setQuestionId(questionID);
				answer.setAnswerValue(answerValue);
				answer.setMultipleChoice(multipleChoice);

				submissionAnswerList.add(answer);
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

		return submissionAnswerList;
	}

	@Override
	public java.util.List<Answer> getSubmissionAnswers(Submission submission)
	{
		java.util.List<Answer> submissionAnswerList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSION_TEMP_ANSWERS);
			statement.setLong(1, submission.getId());
			resultSet = statement.executeQuery();

			Answer answer = null;
			while (resultSet.next())
			{
				if (submissionAnswerList == null)
				{
					submissionAnswerList = new java.util.ArrayList<Answer>();
				}
				
				long questionID = resultSet.getLong("question_id");
				String answerValue = resultSet.getString("sub_answer_value");
				boolean multipleChoice = resultSet.getBoolean("is_sub_answer_multiple_choice");

				answer = new Answer();
				answer.setQuestionId(questionID);
				answer.setAnswerValue(answerValue);
				answer.setMultipleChoice(multipleChoice);

				submissionAnswerList.add(answer);
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

		return submissionAnswerList;
	}

	@Override
	public void deleteSubmissionAnswersByPage(Submission submission)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.DELETE_SUBMISSION_TEMP_ANSWERS);
			statement.setLong(1, submission.getId());
			statement.setInt(2, submission.getPage());
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
	public void copyTo(Submission submission)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.COPY_TO_SUBMISSION_ANSWERS);
			statement.setLong(1, submission.getId());
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
	public void deleteFromTemp(Submission submission)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.DELETE_FROM_TEMP_SUBMISSION_ANSWERS);
			statement.setLong(1, submission.getId());
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
