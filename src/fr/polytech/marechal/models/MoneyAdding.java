package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class MoneyAdding extends Model<MoneyAdding>
{
    private int id;
    private LocalDate date;
    private double amount;
    private String reason;
    private String description;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public LocalDate getDate ()
    {
        return date;
    }

    public void setDate (LocalDate date)
    {
        this.date = date;
    }

    public double getAmount ()
    {
        return amount;
    }

    public void setAmount (double amount)
    {
        this.amount = amount;
    }

    public String getReason ()
    {
        return reason;
    }

    public void setReason (String reason)
    {
        this.reason = reason;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    protected void recopy (MoneyAdding obj)
    {

    }

    @Override
    public boolean update (HashMap<String, Object> data)
    {
        return false;
    }

    @Override
    protected String getPrimaryKeyValue ()
    {
        return null;
    }

    @Override
    public void buildFromResultMap (QueryResult rs) throws SQLException
    {

    }

    @Override
    public boolean save ()
    {
        return false;
    }

    @Override
    public String toString ()
    {
        return "MoneyAdding{" + "id=" + id + ", date=" + date + ", amount=" + amount + ", reason='" + reason + '\'' + ", description='" +
                description + '\'' + '}';
    }
}
