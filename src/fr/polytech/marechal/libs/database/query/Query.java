package fr.polytech.marechal.libs.database.query;

import fr.polytech.marechal.libs.database.Database;
import fr.polytech.marechal.libs.database.query.results.QueryResultList;

import java.sql.SQLException;

/**
 * @author Robin
 * @date 12/06/2017
 */
public class Query
{
    private String query;

    public Query()
    {

    }

    public Query(Query obj)
    {
        this.query = obj.query;
    }

    public Query (String query)
    {
        this.query = query;
    }

    public String getQuery ()
    {
        return query;
    }

    public void setQuery (String query)
    {
        this.query = query;
    }

    public QueryResultList execute () throws SQLException
    {
        return Database.execute(query);
    }

    @Override
    public String toString ()
    {
        return query;
    }
}
