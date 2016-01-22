package org.sw.marketing.dao.answer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.SQLStatements;
import org.sw.marketing.data.form.Data;
import org.sw.marketing.data.form.Data.Form;
import org.sw.marketing.data.form.Data.Form.Question;
import org.sw.marketing.data.form.Data.Form.Question.PossibleAnswer;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class AnswerDAOImpl extends BaseDAO implements AnswerDAO
{
	@Override
	public void insertAnswerToQuestion(long questionId, PossibleAnswer answer)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_ANSWER_TO_QUESTION);
			statement.setString(1, answer.getLabel());
			statement.setLong(2, questionId);
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
	public void insertAnswerToForm(long formID, PossibleAnswer answer)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_ANSWER_TO_FORM);
			statement.setString(1, answer.getLabel());
			statement.setInt(2, answer.getValue());
			statement.setLong(3, formID);
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
	public List<PossibleAnswer> getPossibleAnswers(long questionId)
	{ 
		java.util.List<PossibleAnswer> answerList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_ANSWERS_FOR_QUESTION);
			statement.setLong(1, questionId);
			resultSet = statement.executeQuery();

			PossibleAnswer answer = null;
			while (resultSet.next())
			{
				if (answerList == null)
				{
					answerList = new java.util.ArrayList<PossibleAnswer>();
				}

				long id = resultSet.getLong("answer_id");
				String label = resultSet.getString("answer_label");

				answer = new PossibleAnswer();
				answer.setId(id);
				answer.setLabel(label);

				answerList.add(answer);
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

		return answerList;
	}

	@Override
	public List<Data.PossibleAnswer> getPossibleAnswersByForm(long formID)
	{ 
		java.util.List<Data.PossibleAnswer> answerList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_ANSWERS_FOR_FORM);
			statement.setLong(1, formID);
			resultSet = statement.executeQuery();

			Data.PossibleAnswer answer = null;
			while (resultSet.next())
			{
				if (answerList == null)
				{
					answerList = new java.util.ArrayList<Data.PossibleAnswer>();
				}

				long id = resultSet.getLong("answer_id");
				String label = resultSet.getString("answer_label");
				int value = resultSet.getInt("answer_value");

				answer = new Data.PossibleAnswer();
				answer.setId(id);
				answer.setLabel(label);
				answer.setValue(value);

				answerList.add(answer);
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

		return answerList;
	}


	@Override
	public PossibleAnswer getPossibleAnswer(long id)
	{ 
		PossibleAnswer answer = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_ANSWER);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				answer = new PossibleAnswer();

				String label = resultSet.getString("answer_label");

				answer = new PossibleAnswer();
				answer.setId(id);
				answer.setLabel(label);
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
	public PossibleAnswer getPossibleAnswerForForm(long id)
	{ 
		PossibleAnswer answer = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_ANSWER_FOR_FORM);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				answer = new PossibleAnswer();

				String label = resultSet.getString("answer_label");

				answer = new PossibleAnswer();
				answer.setId(id);
				answer.setLabel(label);
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
	public String getPossibleAnswerLabel(long answerID)
	{
		String answerLabel = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_ANSWER);
			statement.setLong(1, answerID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				answerLabel = resultSet.getString("answer_label");
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

		return answerLabel;
	}
	
	@Override
	public void updatePossibleAnswer(PossibleAnswer answer)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.UPDATE_POSSIBLE_ANSWER_FOR_FORM);
			statement.setString(1, answer.getLabel());
			statement.setInt(2, answer.getValue());
			statement.setLong(3, answer.getId());
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
	public void deleteAnswerForForm(long answerID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.DELETE_ANSWER_FOR_FORM);
			statement.setLong(1, answerID);
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
