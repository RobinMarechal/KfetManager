package fr.polytech.marechal.libs.database.query.builders;

import fr.polytech.marechal.libs.database.query.QueryBuilder;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 14/06/2017
 */
public abstract class ConditionalQueryBuilder<T> extends QueryBuilder
{
    protected ArrayList<String> where;
    protected ArrayList<String> whereAndOr;

    public ConditionalQueryBuilder (String table)
    {
        super(table);
        where = new ArrayList<>();
        whereAndOr = new ArrayList<>();
    }

    public ConditionalQueryBuilder ()
    {
        super();
        where = new ArrayList<>();
        whereAndOr = new ArrayList<>();
    }

    public ConditionalQueryBuilder (ConditionalQueryBuilder obj)
    {
        super(obj);
        where = obj.where;
        whereAndOr = obj.whereAndOr;
    }

    public T where (String field, String operator, Object value)
    {
        this.where.add(field + " " + operator + " '" + value.toString() + "'");
        this.whereAndOr.add("AND");
        return (T) this;
    }

    public T orWhere (String field, String operator, Object value)
    {
        this.where.add(field + " " + operator + " '" + value.toString() + "'");
        this.whereAndOr.add("OR");
        return (T) this;
    }

    protected String buildWhereClause ()
    {
        String clause = "";
        if (!where.isEmpty())
        {
            clause += System.lineSeparator() + "WHERE " + where.get(0);
            for (int i = 1; i < where.size(); i++)
            {
                clause += System.lineSeparator() + "\t" + whereAndOr.get(i) + " " + where.get(i);
            }
        }

        return clause;
    }
}
