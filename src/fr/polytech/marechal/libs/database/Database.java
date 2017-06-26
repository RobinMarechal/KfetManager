package fr.polytech.marechal.libs.database;

import fr.polytech.marechal.libs.database.query.results.QueryResultList;

import java.sql.*;

/**
 * @author Robin
 * @date 12/06/2017
 */
public class Database
{
    private String host = "localhost";
    private String dbName = "kfetmanager";
    private String user = "root";
    private String password = "";
    private String url;
    private final String urlPattern = "jdbc:mysql://{{host}}/{{dbName}}?autoReconnect=true&useSSL=false";

    private Connection connection;

    public Database()
    {
        String url = getUrl();
        try
        {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e)
        {
            System.out.println("Database connection failed");
            e.printStackTrace();
            connection = null;
        }
    }

    private String getUrl()
    {
        String url = urlPattern.replace("{{host}}", host).replace("{{dbName}}", dbName);
        return url;
    }

    /**
     * Get a connected database instance
     * @return the connected database instance
     */
    public static Database connect ()
    {
        return new Database();
    }

    public static QueryResultList execute (String q) throws SQLException
    {
        Connection      connection = connect().getConnection();
        Statement       statement = connection.createStatement();
        ResultSet       rs = statement.executeQuery(q);

        QueryResultList qrl = QueryResultList.fromResultSet(rs);

        rs.close();
        statement.close();
        connection.close();

        return qrl;
    }

    public String getHost ()
    {
        return host;
    }

    public void setHost (String host)
    {
        this.host = host;
    }

    public String getDbName ()
    {
        return dbName;
    }

    public void setDbName (String dbName)
    {
        this.dbName = dbName;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getUrlPattern ()
    {
        return urlPattern;
    }

    public Connection getConnection ()
    {
        return connection;
    }

    public void setConnection (Connection connection)
    {
        this.connection = connection;
    }
}
