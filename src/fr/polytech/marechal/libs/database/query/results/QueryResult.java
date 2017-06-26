package fr.polytech.marechal.libs.database.query.results;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class QueryResult extends HashMap<String, Object>
{
    public int getInt(String colName)
    {
        Object col = get(colName);
        return col == null ? null : Integer.parseInt(col.toString());
    }

    public boolean getBoolean(String colName)
    {
        Object col = get(colName);
        return col == null ? null : Boolean.parseBoolean(get(colName).toString());
    }

    public double getDouble(String colName)
    {
        Object col = get(colName);
        return col == null ? null : Double.parseDouble(get(colName).toString());
    }

    public String getString(String colName)
    {
        Object col = get(colName);
        return col == null ? null : get(colName).toString();
    }

    public LocalDate getDate(String colName)
    {
        return null;
    }

    public LocalTime getTime (String colName)
    {
        return null;
    }

    public LocalDateTime getDateTime (String colName)
    {
        return null;
    }

}
