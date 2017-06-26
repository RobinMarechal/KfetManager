package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Menu extends Model<Menu>
{
    private int id;
    private String name;
    private String description;
    private double price;

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    public ArrayList<Category> getCategories ()
    {
        return categories;
    }

    public void setCategories (ArrayList<Category> categories)
    {
        this.categories = categories;
    }

    public void addCategory(Category category)
    {
        this.categories.add(category);
    }

    public ArrayList<Order> getOrders ()
    {
        return orders;
    }

    public void setOrders (ArrayList<Order> orders)
    {
        this.orders = orders;
    }

    public void addOrder(Order order)
    {
        this.orders.add(order);
    }

    @Override
    protected void recopy (Menu obj)
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
        return "Menu{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + ", " +
                "categories=" + categories + ", orders=" + orders + '}';
    }
}
