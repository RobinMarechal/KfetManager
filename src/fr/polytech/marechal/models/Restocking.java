package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Restocking extends Model<Restocking>
{
    private int id;
    private LocalDate date;
    private double cost;
    private String description;

    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts ()
    {
        return products;
    }

    public void setProducts (ArrayList<Product> products)
    {
        this.products = products;
    }

    public void addProduct(Product product)
    {
        this.products.add(product);
    }

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

    public double getCost ()
    {
        return cost;
    }

    public void setCost (double cost)
    {
        this.cost = cost;
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
    protected void recopy (Restocking obj)
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
        return "Restocking{" + "id=" + id + ", date=" + date + ", cost=" + cost + ", description='" + description + '\'' + '}';
    }
}
