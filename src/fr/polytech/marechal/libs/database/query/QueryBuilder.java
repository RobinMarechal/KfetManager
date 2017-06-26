package fr.polytech.marechal.libs.database.query;

/**
 * @author Robin
 * @date 14/06/2017
 */
public abstract class QueryBuilder
{
    protected String table;
    protected Query query;

    public QueryBuilder ()
    {

    }

    public QueryBuilder (String table)
    {
        this.table = table;
    }

    public QueryBuilder (QueryBuilder obj)
    {
        this.table = obj.table;
        this.query = obj.query;
    }

    public abstract Query buildQuery () throws QueryBuilderException;

    public Query getQuery()
    {
        return query;
    }
}
