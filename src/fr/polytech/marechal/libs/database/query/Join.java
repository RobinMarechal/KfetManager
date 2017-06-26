package fr.polytech.marechal.libs.database.query;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 12/06/2017
 */
public class Join
{
    private String table;
    private String type = "INNER JOIN";
    private ArrayList<String> fields1 = new ArrayList<>();
    private ArrayList<String> fields2 = new ArrayList<>();
    private ArrayList<String> operators = new ArrayList<>();
    private ArrayList<String> andOr = new ArrayList<>();

    public Join(String table)
    {
        this.table = table;
    }

    public Join(String table, String type)
    {
        this(table);
        this.type = type;
    }

    public Join addCondition(String field1, String operator, String field2)
    {
        fields1.add(field1);
        operators.add(operator);
        fields2.add(field2);
        andOr.add("AND");

        return this;
    }

    public Join addOrCondition(String field1, String operator, String field2)
    {
        fields1.add(field1);
        operators.add(operator);
        fields2.add(field2);
        andOr.add("OR");

        return this;
    }

    @Override
    public String toString ()
    {
        String str = type + " " + table;

        str += " ON " + fields1.get(0) + " " + operators.get(0) + " " + fields2.get(0);
        for (int i = 1; i < fields1.size(); i++)
        {
            str += " " +andOr.get(i) + " " + fields1.get(i) + " " + operators.get(i) + " " + fields2.get(i);
        }

        return str;
    }
}
