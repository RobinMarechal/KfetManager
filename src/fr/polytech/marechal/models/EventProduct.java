package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.managers.EventProductsManager;
import fr.polytech.marechal.models.managers.EventsManager;
import fr.polytech.marechal.models.managers.ProductsManager;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventProduct extends Model<EventProduct>
{
    private int productId;
    private int eventId;
    private double cost;
    private double price;
    private int quantitySold;
    private int quantityBought;
    private String name;

    private Event event;
    private Product product;

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

    public EventProduct loadEvent ()
    {
        return loadEvent(new UrlParametersMap());
    }

    public EventProduct loadEvent (UrlParametersMap parameters)
    {
        event = new EventsManager().find(eventId, parameters);
        return this;
    }

    public EventProduct loadProduct ()
    {
        return loadProduct(new UrlParametersMap());
    }

    public EventProduct loadProduct (UrlParametersMap parameters)
    {
        product = new ProductsManager().find(productId, parameters);
        return this;
    }

    @Override
    protected void recopy (EventProduct obj)
    {
        productId = obj.productId;
        eventId = obj.eventId;
        cost = obj.cost;
        price = obj.price;
        quantitySold = obj.quantitySold;
        quantityBought = obj.quantityBought;
        name = obj.name;
        event = obj.event;
        product = obj.product;
    }

    @Override
    public boolean save () throws IOException
    {
        if(eventId < 1)
            return false;

        return saveWithoutRelations();
    }

    @Override
    public EventProduct loadAll ()
    {
        EventProduct tmp = new EventProductsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public EventProduct loadAll (UrlParametersMap parameters)
    {
        loadEvent(parameters);
        loadProduct(parameters);
        return this;
    }

    @Override
    public ModelManager<EventProduct> getManagerInstance ()
    {
        return new EventProductsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("product_id", productId);
        map.put("event_id", eventId);
        map.put("cost", cost);
        map.put("price", price);
        map.put("quantity_sold", quantitySold);
        map.put("quantity_bought", quantityBought);
        map.put("name", name);

        return map;
    }

    @Override
    public String toString ()
    {
        return "EventProduct{" + "id=" + getId() + ", productId=" + productId + ", eventId=" + eventId + ", cost=" + cost + ", price=" +
                price + ", quantitySold=" + quantitySold + ", quantityBought=" + quantityBought + ", name='" + name + '\'' + ", event=" +
                event + ", product=" + product + '}';
    }
}
