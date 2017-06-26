package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventProduct extends Model<EventProduct>
{
    private int id;
    private int productId;
    private int eventId;
    private double cost;
    private double price;
    private int quantitySold;
    private int quantityBought;
    private String name;

    private Event event;
    private Product product;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getProductId ()
    {
        return productId;
    }

    public void setProductId (int productId)
    {
        this.productId = productId;
    }

    public int getEventId ()
    {
        return eventId;
    }

    public void setEventId (int eventId)
    {
        this.eventId = eventId;
    }

    public double getCost ()
    {
        return cost;
    }

    public void setCost (double cost)
    {
        this.cost = cost;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    public int getQuantitySold ()
    {
        return quantitySold;
    }

    public void setQuantitySold (int quantitySold)
    {
        this.quantitySold = quantitySold;
    }

    public int getQuantityBought ()
    {
        return quantityBought;
    }

    public void setQuantityBought (int quantityBought)
    {
        this.quantityBought = quantityBought;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Event getEvent ()
    {
        return event;
    }

    public void setEvent (Event event)
    {
        this.event = event;
    }

    public Product getProduct ()
    {
        return product;
    }

    public void setProduct (Product product)
    {
        this.product = product;
    }

    @Override
    protected void recopy (EventProduct obj)
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
        return "EventProduct{" + "id=" + id + ", productId=" + productId + ", eventId=" + eventId + ", cost=" + cost + ", price=" + price
                + ", quantitySold=" + quantitySold + ", quantityBought=" + quantityBought + ", name='" + name + '\'' + ", event=" + event
                + ", product=" + product + '}';
    }
}
