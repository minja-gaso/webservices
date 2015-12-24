package org.sw.marketing.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDAO implements DAO
{	
	@Override
    public java.sql.Connection getConnection()
    {
		java.sql.Connection connection = null;
        try
        {
            connection = DataSourceCache.getDataSource().getConnection();
        }
        catch (java.sql.SQLException e)
        {
            e.printStackTrace();
        }        
        
        return connection;
    }

	@Override
	public void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
	{
		if(resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch(java.sql.SQLException e)
			{
				e.printStackTrace();
			}
		}

		if(statement != null)
		{
			try
			{
				statement.close();
			}
			catch(java.sql.SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(connection != null)
		{
			try
			{
				connection.close();
			}
			catch(java.sql.SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
