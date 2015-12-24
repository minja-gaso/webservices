package org.sw.marketing.dao;

public class DataSourceCache
{
    public static javax.sql.DataSource getDataSource()
    {
        javax.naming.InitialContext context = null;
        javax.sql.DataSource dataSource = null;
        try
        {
            context = new javax.naming.InitialContext();
            dataSource = (javax.sql.DataSource) context.lookup("java:/comp/env/jdbc/postgres");
        }
        catch(javax.naming.NamingException ne)
        {
            ne.printStackTrace();
        }
        
        return dataSource;
    }
}
