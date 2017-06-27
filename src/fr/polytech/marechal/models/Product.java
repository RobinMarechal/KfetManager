package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 12/06/2017
 */
public class Product extends Model<Product>
{
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int subcategoryId;

    private Subcategory subcategory;
    private Category category;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Restocking> restockings = new ArrayList<>();
    private ArrayList<ProductRestocking> productRestockings = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<OrderProduct> orderProducts = new ArrayList<>();

    public ArrayList<Restocking> getRestockings ()
    {
        return restockings;
    }

    public void setRestockings (ArrayList<Restocking> restockings)
    {
        this.restockings = restockings;
    }

    public void addRestocking (Restocking restocking)
    {
        this.restockings.add(restocking);
    }

    public ArrayList<ProductRestocking> getProductRestockings ()
    {
        return productRestockings ;
    }

    public void setProductRestockings (ArrayList<ProductRestocking> productRestockings)
    {
        this.productRestockings = productRestockings;
    }

    public void addProductRestocking (ProductRestocking productRestocking)
    {
        this.productRestockings.add(productRestocking);
    }

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

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    public int getSubcategoryId ()
    {
        return subcategoryId;
    }

    public void setSubcategoryId (int subcategoryId)
    {
        this.subcategoryId = subcategoryId;
    }

    public Subcategory getSubcategory ()
    {
        return subcategory;
    }

    public void setSubcategory (Subcategory subcategory)
    {
        this.subcategory = subcategory;
    }

    public Category getCategory ()
    {
        return category;
    }

    public void setCategory (Category category)
    {
        this.category = category;
    }

    public ArrayList<Order> getOrders ()
    {
        return orders;
    }

    public void setOrders (ArrayList<Order> orders)
    {
        this.orders = orders;
    }

    public void addOrder (Order order)
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

    public ArrayList<Event> getEvents ()
    {
        return events;
    }

    public void setEvents (ArrayList<Event> events)
    {
        this.events = events;
    }

    public void addEvent (Event event)
    {
        this.events.add(event);
    }

    @Override
    protected void recopy (Product obj)
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
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + ", " +
                "quantity=" + quantity + ", subcategoryId=" + subcategoryId + ", subcategory=" + subcategory + ", category=" + category + ", "
                + "orderProducts=" + orders + '}';
    }
}
