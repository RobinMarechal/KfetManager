package fr.polytech.marechal.libs.database.query.builders;

import fr.polytech.marechal.libs.database.query.Join;
import fr.polytech.marechal.libs.database.query.Query;
import fr.polytech.marechal.libs.database.query.QueryBuilderException;
import fr.polytech.marechal.libs.database.query.builders.enums.Functions;
import fr.polytech.marechal.libs.database.query.builders.enums.OrderBy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Robin
 * @date 12/06/2017
 */
public class SelectQueryBuilder extends ConditionalQueryBuilder<SelectQueryBuilder>
{
    private ArrayList<String> fields;
    private ArrayList<String> orderBy;
    private int limit;
    private int offset;
    private ArrayList<String> groupBy;
    private ArrayList<String> having;
    private ArrayList<String> havingAndOr;
    private ArrayList<Join> joins;

    public SelectQueryBuilder ()
    {
        super();

        fields = new ArrayList<>();
        orderBy = new ArrayList<>();
        limit = 0;
        offset = 0;
        groupBy = new ArrayList<>();
        having = new ArrayList<>();
        havingAndOr = new ArrayList<>();
        joins = new ArrayList<>();
    }

    public SelectQueryBuilder (String table)
    {
        super(table);

        fields = new ArrayList<>();
        orderBy = new ArrayList<>();
        limit = 0;
        offset = 0;
        groupBy = new ArrayList<>();
        having = new ArrayList<>();
        havingAndOr = new ArrayList<>();
        joins = new ArrayList<>();
    }

    public SelectQueryBuilder (SelectQueryBuilder obj)
    {
        super(obj);

        this.table = obj.table;
        this.fields = (ArrayList<String>) obj.fields.clone();
        this.orderBy = (ArrayList<String>) obj.orderBy.clone();
        this.limit = obj.limit;
        this.offset = obj.offset;
        this.groupBy = (ArrayList<String>) obj.groupBy.clone();
        this.having = (ArrayList<String>) obj.having.clone();
        this.havingAndOr = (ArrayList<String>) obj.havingAndOr.clone();
        this.joins = (ArrayList<Join>) obj.joins.clone();
    }

    public static SelectQueryBuilder select (String table)
    {
        SelectQueryBuilder obj = new SelectQueryBuilder();
        obj.table = table;
        return obj;
    }

    public SelectQueryBuilder addFields (String... fields)
    {
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    public SelectQueryBuilder addFunction (Functions function, String field)
    {
        String s = function.toString() + "(" + field + ") AS " + function.toString().toLowerCase() + "_" + field;
        this.addFields(s);
        return this;
    }

    public SelectQueryBuilder count (String field)
    {
        return addFunction(Functions.COUNT, field);
    }

    public SelectQueryBuilder count ()
    {
        String s = Functions.COUNT.toString() + "(*) AS " + Functions.COUNT.toString().toLowerCase() + "_all";
        this.addFields(s);
        return this;
    }

    public SelectQueryBuilder sum (String field)
    {
        return addFunction(Functions.SUM, field);
    }

    public SelectQueryBuilder max (String field)
    {
        return addFunction(Functions.MAX, field);
    }

    public SelectQueryBuilder min (String field)
    {
        return addFunction(Functions.MIN, field);
    }

    public SelectQueryBuilder average (String field)
    {
        return addFunction(Functions.AVG, field);
    }

    public SelectQueryBuilder setTable (String table)
    {
        this.table = table;
        return this;
    }

    public SelectQueryBuilder orderBy (String field, OrderBy order)
    {
        this.orderBy.add(field + " " + order.toString());
        return this;
    }

    public SelectQueryBuilder orderBy (String field)
    {
        return orderBy(field, OrderBy.ASC);
    }

    public SelectQueryBuilder groupBy (String field)
    {
        this.groupBy.add(field);
        return this;
    }

    public SelectQueryBuilder having (Functions function, String field, String operator, Object value)
    {
        String str = function.toString() + "(" + field + ")" + " " + operator + " " + value.toString();
        having.add(str);
        havingAndOr.add("AND");
        return this;
    }

    public SelectQueryBuilder orHaving (Functions function, String field, String operator, Object value)
    {
        String str = function.toString() + "(" + field + ")" + " " + operator + " " + value.toString();
        having.add(str);
        havingAndOr.add("OR");
        return this;
    }

    public SelectQueryBuilder limit (int limit)
    {
        this.limit = limit;
        return this;
    }

    public SelectQueryBuilder offset (int offset)
    {
        this.offset = offset;
        return this;
    }

    public SelectQueryBuilder innerJoin (String table, String field1, String operator, String field2)
    {
        return join(new Join(table, "INNER JOIN").addCondition(field1, operator, field2));
    }

    public SelectQueryBuilder leftJoin (String table, String field1, String operator, String field2)
    {
        return join(new Join(table, "LEFT JOIN").addCondition(field1, operator, field2));
    }

    public SelectQueryBuilder rightJoin (String table, String field1, String operator, String field2)
    {
        return join(new Join(table, "RIGHT JOIN").addCondition(field1, operator, field2));
    }

    public SelectQueryBuilder naturalJoin (String table, String field1, String operator, String field2)
    {
        return join(new Join(table, "NATURAL JOIN").addCondition(field1, operator, field2));
    }

    public SelectQueryBuilder fullJoin (String table, String field1, String operator, String field2)
    {
        return join(new Join(table, "FULL JOIN").addCondition(field1, operator, field2));
    }

    public SelectQueryBuilder join (Join join)
    {
        this.joins.add(join);
        return this;
    }


    @Override
    public Query buildQuery ()
    {
        String query = "SELECT ";

        // Fields

        if (fields.isEmpty())
        {
            query += "*";
        }
        else
        {
            query += fields.get(0);
            for (int i = 1; i < fields.size(); i++)
            {
                query += ", " + fields.get(i);
            }
        }

        query += " FROM ";

        if (table == null || table.isEmpty())
        {
            throw new QueryBuilderException("the table is not specified");
        }


        // ProductsTable

        query += table;

        // Joins

        if (!joins.isEmpty())
        {
            query += System.lineSeparator() + "\t" + joins.get(0).toString();
            for (int i = 1; i < joins.size(); i++)
            {
                query += " " + joins.get(i).toString();
            }
        }

        // Where

        query += buildWhereClause();

        // Group by

        if (!groupBy.isEmpty())
        {
            query += System.lineSeparator() + "GROUP BY " + groupBy.get(0);
            for (int i = 1; i < groupBy.size(); i++)
            {
                query += ", " + groupBy.get(i);
            }

            if (!having.isEmpty())
            {
                query += System.lineSeparator() + "\tHAVING " + having.get(0);
                for (int i = 1; i < having.size(); i++)
                {
                    query += System.lineSeparator() + "\t\t" + havingAndOr.get(i) + " " + having.get(i);
                }
            }
        }

        // Order By

        if (!orderBy.isEmpty())
        {
            query += System.lineSeparator() + "ORDER BY " + orderBy.get(0);
            for (int i = 1; i < orderBy.size(); i++)
            {
                query += ", " + orderBy.get(i);
            }
        }

        // Limit

        if (limit > 0)
        {
            query += System.lineSeparator() + "LIMIT " + limit;
            if (offset > 0)
            {
                query += " OFFSET " + offset;
            }
        }

        this.query = new Query(query);
        return this.query;
    }

    public String toString ()
    {
        return getQuery().toString();
    }
}
