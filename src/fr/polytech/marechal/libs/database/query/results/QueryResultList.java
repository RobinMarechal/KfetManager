package fr.polytech.marechal.libs.database.query.results;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class QueryResultList extends ArrayList<QueryResult>
{
    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public QueryResultList (int initialCapacity)
    {
        super(initialCapacity);
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public QueryResultList ()
    {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public QueryResultList (@NotNull Collection<? extends QueryResult> c)
    {
        super(c);
    }

    public static QueryResultList fromResultSet (ResultSet rs) throws SQLException
    {
        QueryResultList qr = new QueryResultList();

        while (rs.next())
        {
            QueryResult res = new QueryResult();

            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++)
            {
                String colName = metaData.getColumnName(i);
                Object colValue = rs.getObject(i);
                res.put(colName, colValue);

                qr.add(res);
            }
        }

        return qr;
    }
}
