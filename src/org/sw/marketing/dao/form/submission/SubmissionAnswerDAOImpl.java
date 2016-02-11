package org.sw.marketing.dao.form.submission;

import java.sql.SQLException;
import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.form.FormSQL;
import org.sw.marketing.data.form.Data.Form.Question;
import org.sw.marketing.data.form.Data.Submission;
import org.sw.marketing.data.form.Data.Submission.Answer;

public class SubmissionAnswerDAOImpl extends BaseDAO implements SubmissionAnswerDAO
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
			statement = connection.prepareStatement(FormSQL.INSERT_SUBMISSION_ANSWER);			
			statement.setString(1, answer.getAnswerValue());
			boolean isMultipleChoice = false;
			if(question.getType().equals("radio") || question.getType().equals("checkbox"))
			{
				isMultipleChoice = true;
			}
			statement.setBoolean(2, isMultipleChoice);
			statement.setLong(3, question.getId());
			statement.setLong(4, submission.getId());
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
	public java.util.List<Answer> getSubmissionAnswers(long submissionID)
	{
		java.util.List<Answer> submissionAnswerList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSION_ANSWERS);
			statement.setLong(1, submissionID);
			resultSet = statement.executeQuery();

			Answer answer = null;
			while (resultSet.next())
			{
				if (submissionAnswerList == null)
				{
					submissionAnswerList = new java.util.ArrayList<Answer>();
				}

//				int id = resultSet.getInt("sub_answer_id");
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
	public Answer getSubmissionAnswer(long submissionID, long answerID)
	{
		Answer answer = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSION_ANSWER);
			statement.setLong(1, submissionID);
			statement.setLong(2, answerID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				answer = new Answer();
//				int id = resultSet.getInt("sub_answer_id");
				long questionID = resultSet.getLong("fk_question_id");
				String answerValue = resultSet.getString("sub_answer_value");
				boolean multipleChoice = resultSet.getBoolean("is_sub_answer_multiple_choice");

				answer = new Answer();
				answer.setQuestionId(questionID);
				answer.setAnswerValue(answerValue);
				answer.setMultipleChoice(multipleChoice);
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

		return answer;
	}
	
	@Override
	public Answer getSubmissionAnswerByValue(long submissionID, long answerID)
	{
		Answer answer = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(FormSQL.GET_SUBMISSION_ANSWER_BY_VALUE);
			statement.setLong(1, submissionID);
			statement.setString(2, String.valueOf(answerID));
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				answer = new Answer();
//				int id = resultSet.getInt("sub_answer_id");
				long questionID = resultSet.getLong("fk_question_id");
				String answerValue = resultSet.getString("sub_answer_value");
				boolean multipleChoice = resultSet.getBoolean("is_sub_answer_multiple_choice");

				answer = new Answer();
				answer.setQuestionId(questionID);
				answer.setAnswerValue(answerValue);
				answer.setMultipleChoice(multipleChoice);
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

		return answer;
	}
}
