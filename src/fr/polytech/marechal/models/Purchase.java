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
public class Purchase extends Model<Purchase>
{
    private int id;
    private double cost;
    private int quantity;
    private LocalDate date;
    private String description;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public double getCost ()
    {
        return cost;
    }

    public void setCost (double cost)
    {
        this.cost = cost;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    public LocalDate getDate ()
    {
        return date;
    }

    public void setDate (LocalDate date)
    {
        this.date = date;
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
    protected void recopy (Purchase obj)
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
        return "Purchase{" + "id=" + id + ", cost=" + cost + ", quantity=" + quantity + ", date=" + date + ", description='" +
                description + '\'' + '}';
    }
}
