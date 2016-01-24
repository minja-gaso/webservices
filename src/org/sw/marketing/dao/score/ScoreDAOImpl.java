package org.sw.marketing.dao.score;

import java.sql.SQLException;
import java.sql.Statement;

import org.sw.marketing.dao.BaseDAO;
import org.sw.marketing.dao.DAO;
import org.sw.marketing.dao.SQLStatements;
import org.sw.marketing.data.form.Data.Form;
import org.sw.marketing.data.form.Data.Score;
import org.sw.marketing.util.DateToXmlGregorianCalendar;

public class ScoreDAOImpl extends BaseDAO implements ScoreDAO
{
	@Override
	public long insertScore(long formID)
	{
		long id = 0;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;
		
		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.INSERT_SCORE, Statement.RETURN_GENERATED_KEYS);
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
	public Score getScore(long scoreID)
	{
		Score score = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_SCORE);
			statement.setLong(1, scoreID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int begin = resultSet.getInt("score_range_begin");
				int end = resultSet.getInt("score_range_end");
				String title = resultSet.getString("score_title");
				String summary = resultSet.getString("score_summary");
				
				score = new Score();
				score.setId(scoreID);
				score.setBegin(begin);
				score.setEnd(end);
				score.setTitle(title);
				score.setSummary(summary);
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
		
		return score;
	}

	@Override
	public java.util.List<Score> getScores(long formID)
	{
		java.util.List<Score> scores = null;
		Score score = null;
		
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.GET_SCORES);
			statement.setLong(1, formID);
			resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				if(scores == null)
				{
					scores = new java.util.ArrayList<Score>();
				}
				
				long id = resultSet.getLong("score_id");
				int begin = resultSet.getInt("score_range_begin");
				int end = resultSet.getInt("score_range_end");
				String title = resultSet.getString("score_title");
				String summary = resultSet.getString("score_summary");
				
				score = new Score();
				score.setId(id);
				score.setBegin(begin);
				score.setEnd(end);
				score.setTitle(title);
				score.setSummary(summary);
				
				scores.add(score);
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
		
		return scores;
	}
	
	@Override
	public void updateScore(Score score)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.UPDATE_SCORE);
			statement.setInt(1, score.getBegin());
			statement.setInt(2, score.getEnd());
			statement.setString(3, score.getTitle());
			statement.setString(4, score.getSummary());
			statement.setLong(5, score.getId());
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
	public void deleteScore(long scoreID)
	{
		DAO dao = new BaseDAO();
		java.sql.Connection connection = null;
		java.sql.PreparedStatement statement = null;
		java.sql.ResultSet resultSet = null;

		try
		{
			connection = dao.getConnection();
			statement = connection.prepareStatement(SQLStatements.DELETE_SCORE);
			statement.setLong(1, scoreID);
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
