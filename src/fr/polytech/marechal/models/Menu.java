package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Menu extends Model<Menu>
{
    private String name;
    private String description;
    private double price;

    private RelationsMap<Category, CategoryMenu> categories = new RelationsMap<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<OrderProduct> orderProducts = new ArrayList<>();


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

    public RelationsMap<Category, CategoryMenu> getCategories ()
    {
        return categories;
    }

    public void setCategories (RelationsMap<Category, CategoryMenu> categories)
    {
        this.categories = categories;
    }

    public void addCategory(Category category, CategoryMenu pivot)
    {
        this.categories.put(category, pivot);
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

    public ArrayList<OrderProduct> getOrderProducts ()
    {
        return orderProducts;
    }

    public void setOrderProducts (ArrayList<OrderProduct> orderProducts)
    {
        this.orderProducts = orderProducts;
    }

    public void addOrderProduct(OrderProduct orderProduct)
    {
        this.orderProducts.add(orderProduct);
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
        return "Menu{" + "id=" + getId() + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + ", " +
                "categories=" + categories + ", orders=" + orders + '}';
    }
}
