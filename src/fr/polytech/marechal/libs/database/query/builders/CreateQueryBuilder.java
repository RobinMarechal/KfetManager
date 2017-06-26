package fr.polytech.marechal.libs.database.query.builders;

import fr.polytech.marechal.libs.database.query.Query;
import fr.polytech.marechal.libs.database.query.QueryBuilder;
import fr.polytech.marechal.libs.database.query.QueryBuilderException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robin
 * @date 14/06/2017
 */
public class CreateQueryBuilder extends QueryBuilder
{
    private HashMap<String, Object> values;

    public CreateQueryBuilder ()
    {
        super();
        values = new HashMap<>();
    }

    public CreateQueryBuilder (String table)
    {
        super(table);
        values = new HashMap<>();
    }

    public CreateQueryBuilder (CreateQueryBuilder obj)
    {
        super(obj);
        this.values = obj.values;
    }

    public static CreateQueryBuilder table (String table)
    {
        CreateQueryBuilder builder = new CreateQueryBuilder();
        builder.table = table;
        return builder;
    }

    public CreateQueryBuilder setValues (HashMap<String, Object> values)
    {
        this.values = values;
        return this;
    }

    public CreateQueryBuilder addValue (String key, Object value)
    {
        this.values.put(key, value);
        return this;
    }


    @Override
    public Query buildQuery ()
    {
        String query = "INSERT INTO " + table;

        if (this.values.isEmpty())
        {
            throw new QueryBuilderException("Cannot build an INSERT INTO query without data");
        }

        String fields = "(";
        String values = "VALUES(";

        for (Map.Entry<String, Object> entry : this.values.entrySet())
        {
            fields += entry.getKey() + ", ";
            values += "'" + entry.getValue().toString() + "', ";
        }

        fields = fields.substring(0, fields.lastIndexOf(','));
        values = values.substring(0, values.lastIndexOf(','));

        fields += ")";
        values += ")";

        query += " " + fields + System.lineSeparator() + "\t" + values;

        this.query = new Query(query);

        return this.query;
    }
}
