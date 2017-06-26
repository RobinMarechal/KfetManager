package fr.polytech.marechal.libs.database.query.builders;

import fr.polytech.marechal.libs.database.query.Query;
import fr.polytech.marechal.libs.database.query.QueryBuilderException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robin
 * @date 14/06/2017
 */
public class UpdateQueryBuilder extends ConditionalQueryBuilder<UpdateQueryBuilder>
{
    private HashMap<String, Object> values;

    public UpdateQueryBuilder ()
    {
        super();
        values = new HashMap<>();
    }

    public UpdateQueryBuilder (String table)
    {
        super(table);
        values = new HashMap<>();
    }

    public UpdateQueryBuilder (UpdateQueryBuilder obj)
    {
        super(obj);
        this.values = obj.values;
    }

    public static UpdateQueryBuilder table (String table)
    {
        UpdateQueryBuilder builder = new UpdateQueryBuilder();
        builder.table = table;
        return builder;
    }

    public UpdateQueryBuilder setValues (HashMap<String, Object> values)
    {
        this.values = values;
        return this;
    }

    public UpdateQueryBuilder addValue (String key, Object value)
    {
        this.values.put(key, value);
        return this;
    }

    @Override
    public Query buildQuery () throws QueryBuilderException
    {
        String query = "UPDATE " + table + " SET ";

        if (values.isEmpty())
        {
            throw new QueryBuilderException("Cannot build an UPDATE query without data");
        }

        for (Map.Entry<String, Object> entry : values.entrySet())
        {
            query += System.lineSeparator() + "\t";
            query += entry.getKey() + " = '" + entry.getValue() + "', ";
        }

        query = query.substring(0, query.lastIndexOf(','));

        query += buildWhereClause();

        this.query = new Query(query);
        return this.query;
    }
}
