package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.models.managers.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 12/06/2017
 */
public class Product extends Model<Product>
{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int subcategoryId;

    private Subcategory subcategory;
    private Category category;
    private RelationsMap<Order, OrderProduct> orders = new RelationsMap<>();
    private RelationsMap<Restocking, ProductRestocking> restockings = new RelationsMap<>();
    private RelationsMap<Event, EventProduct> events = new RelationsMap<>();

    public Product loadSubcategory ()
    {
        return loadSubcategory(new UrlParametersMap());
    }

    public Product loadSubcategory (UrlParametersMap parameters)
    {
        subcategory = new SubcategoriesManager().find(subcategoryId, parameters);
        return this;
    }

    public Product loadCategory ()
    {
        return loadCategory(new UrlParametersMap());
    }

    public Product loadCategory (UrlParametersMap parameters)
    {
        ArrayList<Category> categories = new CategoriesManager().ofUrl("products/" + getId() + "/category", parameters);
        if (!categories.isEmpty())
        {
            category = categories.get(0);
        }

        return this;
    }

    public Product loadOrders ()
    {
        return loadOrders(new UrlParametersMap());
    }

    public Product loadOrders (UrlParametersMap parameters)
    {
        ArrayList<Order> orderList = new OrdersManager().ofUrl("products/" + getId() + "/orders", parameters);
        orders = new RelationsMap<>();
        orders.addModels(orderList);
        return this;
    }

    public Product loadRestockings ()
    {
        return loadRestockings(new UrlParametersMap());
    }

    public Product loadRestockings (UrlParametersMap parameters)
    {
        ArrayList<Restocking> restockings = new RestockingsManager().ofUrl("products/" + getId() + "/restockings", parameters);
        this.restockings = new RelationsMap<>();
        this.restockings.addModels(restockings);
        return this;
    }

    public Product loadEvents ()
    {
        return loadEvents(new UrlParametersMap());
    }

    public Product loadEvents (UrlParametersMap parameters)
    {
        ArrayList<Event> events = new EventsManager().ofUrl("products/" + getId() + "/events", parameters);
        this.events = new RelationsMap<>();
        this.events.addModels(events);
        return this;
    }

    public RelationsMap<Restocking, ProductRestocking> getRestockings ()
    {
        return restockings;
    }

    public void setRestockings (RelationsMap<Restocking, ProductRestocking> restockings)
    {
        this.restockings = restockings;
    }

    public void addRestocking (Restocking restocking, @Nullable ProductRestocking pivot)
    {
        if (pivot == null)
        {
            pivot = new ProductRestocking();
            pivot.setProductId(getId());
            pivot.setRestockingId(restocking.getId());
            pivot.setQuantity(1);
        }

        this.restockings.put(restocking, pivot);
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

    public RelationsMap<Order, OrderProduct> getOrders ()
    {
        return orders;
    }

    public void setOrders (RelationsMap<Order, OrderProduct> orders)
    {
        this.orders = orders;
    }

    public void addOrder (Order order, @Nullable OrderProduct pivot)
    {
        if (pivot == null)
        {
            pivot = new OrderProduct();
            pivot.setProductId(getId());
            pivot.setOrderId(order.getId());
            pivot.setQuantity(1);
        }

        this.orders.put(order, pivot);
    }

    public RelationsMap<Event, EventProduct> getEvents ()
    {
        return events;
    }

    public void setEvents (RelationsMap<Event, EventProduct> events)
    {
        this.events = events;
    }

    public void addEvent (Event event, @Nullable EventProduct pivot)
    {
        if (pivot == null)
        {
            pivot = new EventProduct();
            pivot.setProductId(getId());
            pivot.setEventId(event.getId());
        }

        this.events.put(event, pivot);
    }

    @Override
    protected void recopy (Product obj)
    {
        this.name = obj.name;
        this.description = obj.description;
        this.price = obj.price;
        this.quantity = obj.quantity;
        this.subcategoryId = obj.subcategoryId;
        this.subcategory = obj.subcategory;
        this.category = obj.category;
        this.orders = obj.orders;
        this.restockings = obj.restockings;
        this.events = obj.events;
    }

    @Override
    public boolean save () throws IOException
    {
        boolean success = true;

        if (subcategoryId < 1 && subcategory == null)
        {
            return false;
        }
        else if (subcategoryId < 1)
        {
            success &= subcategory.save();
            subcategoryId = subcategory.getId();
        }

        success &= saveWithoutRelations();

        // for each associated model
        for (RelationsMap.Pair<Restocking, ProductRestocking> e : restockings.pairList())
        {
            // we get the model's instance and the relation's pivot
            Restocking m = e.getModel();
            ProductRestocking p = e.getPivot();

            // if the model isn't saved in db, we save it
            if (m.getId() < 1)
            {
                success = (m.save() && success);
            }

            // If the pivot is null, we create it
            if (p == null)
            {
                p = new ProductRestocking();
            }

            // We set the right category's id and menu's id
            p.setProductId(getId());
            p.setRestockingId(m.getId());

            // then we save the pivot
            success = (p.saveWithoutRelations() && success);
        }

        return success;
    }

    @Override
    public Product loadAll ()
    {
        Product tmp = new ProductsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Product loadAll (UrlParametersMap parameters)
    {
        loadSubcategory(parameters);
        loadCategory(parameters);
        loadRestockings(parameters);
        loadEvents(parameters);
        loadOrders(parameters);
        return this;
    }

    @Override
    public ModelManager<Product> getManagerInstance ()
    {
        return new ProductsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("name", name);
        map.put("description", description);
        map.put("price", price);
        map.put("quantity", quantity);
        map.put("subcategory_id", subcategoryId);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Product{" + "id=" + getId() + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + "," +
                "" + "" + "" + "" + "" + " " + "quantity=" + quantity + ", subcategoryId=" + subcategoryId + ", subcategory=" +
                subcategory + ", " + "category=" + category + ", " + "orderProducts=" + orders + '}';
    }
}
