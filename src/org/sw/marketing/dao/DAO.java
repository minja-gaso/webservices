package org.sw.marketing.dao;

public interface DAO
{
	public java.sql.Connection getConnection();
	
	public void closeConnection(java.sql.Connection connection, java.sql.Statement statement, java.sql.ResultSet resultSet);
}
