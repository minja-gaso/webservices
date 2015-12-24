package org.sw.marketing.dao.question;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.SQLStatements;
import org.sw.marketing.data.form.Data.Form.Question;

public class QuestionDAOImpl extends BaseDAO implements QuestionDAO
{
	@Override
	public int getNextNumber(int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		int nextNumber = 0;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_NEXT_NUMBER);
			statement.setInt(1, formId);
			resultSet = statement.executeQuery();
	
			while (resultSet.next())
			{
				nextNumber = resultSet.getInt("question_number");
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
	
		return nextNumber;
	}
	
	@Override
	public int getLatestPage(int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		int latestPage = 0;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_LATEST_PAGE);
			statement.setInt(1, formId);
			resultSet = statement.executeQuery();
	
			while (resultSet.next())
			{
				latestPage = resultSet.getInt("question_page");
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
	
		return latestPage;
	}

	@Override
	public int insertQuestion(int questionNumber, int page, int formId)
	{
		int id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, questionNumber);
			statement.setInt(2, page);
			statement.setInt(3, formId);
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
	public void deletePageBreak(int pageNumber)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.DELETE_PAGE_BREAK);
			statement.setInt(1, pageNumber);
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
	public List<Question> getQuestions(int formId)
	{
		java.util.List<Question> questionList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_QUESTIONS);
			statement.setInt(1, formId);
			resultSet = statement.executeQuery();

			Question question = null;
			while (resultSet.next())
			{
				if (questionList == null)
				{
					questionList = new java.util.ArrayList<Question>();
				}

				int id = resultSet.getInt("question_id");
				int number = resultSet.getInt("question_number");
				String type = resultSet.getString("question_type");
				String label = resultSet.getString("question_label");
				int page = resultSet.getInt("question_page");
				String defaultAnswer = resultSet.getString("question_default_value");
				String filter = resultSet.getString("question_filter");
				int maxCharacterLimit = resultSet.getInt("question_max_character_limit");
				int maxWordLimit = resultSet.getInt("question_max_word_limit");
				boolean required = resultSet.getBoolean("is_question_required");

				question = new Question();
				question.setId(id);
				question.setNumber(number);
				question.setType(type);
				question.setLabel(label);
				question.setPage(page);
				question.setDefaultAnswer(defaultAnswer);
				question.setFilter(filter);
				question.setMaxCharacterLimit(maxCharacterLimit);
				question.setMaxWordLimit(maxWordLimit);
				question.setRequired(required);
				questionList.add(question);
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

		return questionList;
	}
	@Override
	public List<Question> getQuestionsByPage(int formId, int formPage)
	{
		java.util.List<Question> questionList = null;

		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_QUESTIONS_BY_PAGE);
			statement.setInt(1, formPage);
			statement.setInt(2, formId);
			resultSet = statement.executeQuery();

			Question question = null;
			while (resultSet.next())
			{
				if (questionList == null)
				{
					questionList = new java.util.ArrayList<Question>();
				}

				int id = resultSet.getInt("question_id");
				int number = resultSet.getInt("question_number");
				String type = resultSet.getString("question_type");
				String label = resultSet.getString("question_label");
				int page = resultSet.getInt("question_page");
				String defaultAnswer = resultSet.getString("question_default_value");
				String filter = resultSet.getString("question_filter");
				int maxCharacterLimit = resultSet.getInt("question_max_character_limit");
				int maxWordLimit = resultSet.getInt("question_max_word_limit");
				boolean required = resultSet.getBoolean("is_question_required");

				question = new Question();
				question.setId(id);
				question.setNumber(number);
				question.setType(type);
				question.setLabel(label);
				question.setPage(page);
				question.setDefaultAnswer(defaultAnswer);
				question.setFilter(filter);
				question.setMaxCharacterLimit(maxCharacterLimit);
				question.setMaxWordLimit(maxWordLimit);
				question.setRequired(required);
				questionList.add(question);
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

		return questionList;
	}
	
	@Override
	public Question getQuestion(int questionId)
	{
		Question question = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_QUESTION);
			statement.setInt(1, questionId);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int id = resultSet.getInt("question_id");
				int number = resultSet.getInt("question_number");
				String type = resultSet.getString("question_type");
				String label = resultSet.getString("question_label");
				int page = resultSet.getInt("question_page");
				String defaultAnswer = resultSet.getString("question_default_value");
				String filter = resultSet.getString("question_filter");
				int maxCharacterLimit = resultSet.getInt("question_max_character_limit");
				int maxWordLimit = resultSet.getInt("question_max_word_limit");
				boolean required = resultSet.getBoolean("is_question_required");

				question = new Question();
				question.setId(id);
				question.setNumber(number);
				question.setType(type);
				question.setLabel(label);
				question.setPage(page);
				question.setDefaultAnswer(defaultAnswer);
				question.setFilter(filter);
				question.setMaxCharacterLimit(maxCharacterLimit);
				question.setMaxWordLimit(maxWordLimit);
				question.setRequired(required);
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
		
		return question;
	}
	
	@Override
	public Question getQuestionByNumber(int numberId)
	{
		Question question = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_QUESTION_BY_NUMBER);
			statement.setInt(1, numberId);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int id = resultSet.getInt("question_id");
				int number = resultSet.getInt("question_number");
				String type = resultSet.getString("question_type");
				String label = resultSet.getString("question_label");
				int page = resultSet.getInt("question_page");
				String defaultAnswer = resultSet.getString("question_default_value");
				String filter = resultSet.getString("question_filter");
				int maxCharacterLimit = resultSet.getInt("question_max_character_limit");
				int maxWordLimit = resultSet.getInt("question_max_word_limit");
				boolean required = resultSet.getBoolean("is_question_required");

				question = new Question();
				question.setId(id);
				question.setNumber(number);
				question.setType(type);
				question.setLabel(label);
				question.setPage(page);
				question.setDefaultAnswer(defaultAnswer);
				question.setFilter(filter);
				question.setMaxCharacterLimit(maxCharacterLimit);
				question.setMaxWordLimit(maxWordLimit);
				question.setRequired(required);
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
		
		return question;
	}

	@Override
	public int getQuestionPageCount(int page)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		int questionPageCount = 0;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_QUESTION_COUNT_FOR_PAGE);
			statement.setInt(1, page);
			resultSet = statement.executeQuery();
	
			while (resultSet.next())
			{
				questionPageCount = resultSet.getInt("question_count_for_page");
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
	
		return questionPageCount;
	}

	@Override
	public int getMostRecentQuestionNumber(int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		int mostRecentNumber = 0;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_MOST_RECENT_QUESTION_NUMBER);
			statement.setInt(1, formId);
			resultSet = statement.executeQuery();
	
			while (resultSet.next())
			{
				mostRecentNumber = resultSet.getInt("most_recent_question_number");
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
	
		return mostRecentNumber;
	}

	@Override
	public void updateQuestion(Question question)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.UPDATE_QUESTION);
			statement.setInt(1, question.getNumber());
			statement.setString(2, question.getType());
			statement.setString(3, question.getLabel());
			statement.setInt(4, question.getPage());
			statement.setString(5, question.getDefaultAnswer());
			statement.setString(6, question.getFilter());
			statement.setInt(7, question.getMaxCharacterLimit());
			statement.setInt(8, question.getMaxWordLimit());
			statement.setBoolean(9, question.isRequired());
			statement.setInt(10, question.getId());
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

//	@Override
//	public void swapQuestionNumbers(int moveDown, int moveUp)
//	{
//		DAO dao = new BaseDAO();
//		java.sql.Connection connection = null;
//		java.sql.PreparedStatement statement = null;
//		java.sql.ResultSet resultSet = null;
//	
//		try
//		{
//			connection = dao.getConnection();
//			statement = connection.prepareStatement(SQLStatements.UPDATE_QUESTION_ORDER);
//			statement.setInt(1, moveDown);
//			statement.setInt(2, moveUp);
//			statement.executeUpdate();
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			closeConnection(connection, statement, resultSet);
//		}
//	}

	@Override
	public void deleteQuestion(int questionId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.DELETE_QUESTION);
			statement.setInt(1, questionId);
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
	public void moveDownQuestions(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.MOVE_DOWN_QUESTIONS);
			statement.setInt(1, questionNumber);
			statement.setInt(2, formId);
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
	public void moveUpQuestions(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.MOVE_UP_QUESTIONS);
			statement.setInt(1, questionNumber);
			statement.setInt(2, formId);
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
	public void moveDownQuestion(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.MOVE_DOWN_QUESTION);
			statement.setInt(1, questionNumber);
//			statement.setInt(2, formId);
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
	public void moveUpQuestion(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.MOVE_UP_QUESTION);
			statement.setInt(1, questionNumber);
//			statement.setInt(2, formId);
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
	public void insertPageBreak(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_PAGE_BREAK);
			statement.setInt(1, questionNumber);
//			statement.setInt(2, formId);
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
	public void removePageBreak(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.REMOVE_PAGE_BREAK);
			statement.setInt(1, questionNumber);
//			statement.setInt(2, formId);
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
	public void incrementPageNumber(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INCREMENT_PAGE);
			statement.setInt(1, questionNumber);
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
	public void decrementPageNumber(int questionNumber, int formId)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
	
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.DECREMENT_PAGE);
			statement.setInt(1, questionNumber);
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
