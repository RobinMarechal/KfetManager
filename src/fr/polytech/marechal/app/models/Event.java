package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.app.models.managers.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Event extends Model<Event>
{
    private LocalDate date;
    private String description;

    private ArrayList<EventAccessory> eventAccessories = new ArrayList<>();
    private ArrayList<EventProduct> eventProducts = new ArrayList<>();
    private RelationsMap<Product, EventProduct> products = new RelationsMap<>();


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

    public ArrayList<EventAccessory> getEventAccessories ()
    {
        return eventAccessories;
    }

    public void setEventAccessories (ArrayList<EventAccessory> eventAccessories)
    {
        this.eventAccessories = eventAccessories;
    }

    public void addEventAccessories (EventAccessory eventAccessory)
    {
        this.eventAccessories.add(eventAccessory);
    }

    public ArrayList<EventProduct> getEventProducts ()
    {
        return eventProducts;
    }

    public void setEventProducts (ArrayList<EventProduct> eventProducts)
    {
        this.eventProducts = eventProducts;
    }

    public void addEventProduct (EventProduct eventProduct)
    {
        this.eventProducts.add(eventProduct);
    }

    public RelationsMap<Product, EventProduct> getProducts ()
    {
        return products;
    }

    public void setProducts (RelationsMap<Product, EventProduct> products)
    {
        this.products = products;
    }

    public void addProduct (Product product, EventProduct pivot)
    {
        this.products.put(product, pivot);
    }

    public Event loadEventAccessories ()
    {
        return loadEventAccessories(new UrlParametersMap());
    }

    public Event loadEventAccessories (UrlParametersMap parameters)
    {
        eventAccessories = new EventAccessoriesManager().ofUrl("events/" + id + "/accessories", parameters);
        return this;
    }

    public Event loadEventProducts ()
    {
        return loadEventProducts(new UrlParametersMap());
    }

    public Event loadEventProducts (UrlParametersMap parameters)
    {
        eventProducts = new EventProductsManager().ofUrl("events/" + id + "/products", parameters);
        return this;
    }

    public Event loadProducts ()
    {
        return loadProducts(new UrlParametersMap());
    }

    public Event loadProducts (UrlParametersMap parameters)
    {
        ArrayList<Product> productList = new ProductsManager().ofUrl("events/" + id + "/products", parameters);
        this.products = new RelationsMap<>();
        this.products.addModels(productList);
        return this;
    }

    @Override
    protected void recopy (Event obj)
    {
        date = obj.date;
        description = obj.description;
        this.eventProducts = obj.eventProducts;
        this.eventAccessories = obj.eventAccessories;
        this.products = obj.products;
    }

    @Override
    public boolean saveAll () throws IOException
    {
        boolean success = save();

        for (EventAccessory ea : eventAccessories)
        {
            ea.setEventId(getId());
            success &= ea.saveAll();
        }

        for (EventProduct ep : eventProducts)
        {
            ep.setEventId(getId());
            success &= ep.saveAll();
        }

        return success;
    }

    @Override
    public Event loadAll ()
    {
        Event tmp = new EventsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Event loadAll (UrlParametersMap parameters)
    {
        loadProducts(parameters);
        loadEventProducts(parameters);
        loadEventAccessories(parameters);
        return this;
    }

    @Override
    public ModelManager<Event> getManagerInstance ()
    {
        return new EventsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("date", date);
        map.put("description", description);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Event{" + "id=" + getId() + ", date=" + date + ", description='" + description + '\'' + ", eventAccessories=" +
                eventAccessories + ", eventProducts=" + eventProducts + '}';
    }
}
