package fr.polytech.marechal.libs.mvc;

import fr.polytech.marechal.libs.database.query.builders.CreateQueryBuilder;
import fr.polytech.marechal.libs.database.query.builders.SelectQueryBuilder;
import fr.polytech.marechal.libs.database.query.builders.UpdateQueryBuilder;
import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.database.query.results.QueryResultList;
import fr.polytech.marechal.libs.mvc.exceptions.ModelException;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 12/06/2017
 */
public abstract class Model<T extends Model>
{
    protected String table;
    protected String primaryKey = "id";

    protected abstract void recopy (T obj);

    public String getTable ()
    {
        return table;
    }

    public void setTable (String table)
    {
        this.table = table;
    }

    protected static QueryResultList modelCreate (String table, HashMap<String, Object> data) throws SQLException
    {
        return CreateQueryBuilder.table(table).setValues(data).buildQuery().execute();
    }

    public abstract boolean update (HashMap<String, Object> data);

    protected static QueryResultList modelUpdate (String table, HashMap<String, Object> data) throws SQLException
    {
        return UpdateQueryBuilder.table(table).setValues(data).buildQuery().execute();
    }

    public boolean isStoredInDatabase () throws SQLException
    {
        QueryResultList results = SelectQueryBuilder.select(table).count().where(primaryKey, "=", getPrimaryKeyValue()).buildQuery()
                                                    .execute();
        return !results.isEmpty() && results.get(0).getInt("count_all") != 0;
    }

    protected abstract String getPrimaryKeyValue ();

    // ABSTRACT

    public abstract void buildFromResultMap (QueryResult rs) throws SQLException;

    public boolean isSaved ()
    {
        try
        {
            return isStoredInDatabase();
        }
        catch (SQLException e)
        {
            throw new ModelException(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public abstract boolean save ();

    //
}
