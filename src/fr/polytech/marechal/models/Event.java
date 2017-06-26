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
public class Event extends Model<Event>
{
    private int id;
    private LocalDate date;
    private String description;

    private ArrayList<EventAccessory> eventAccessories = new ArrayList<>();
    private ArrayList<EventProduct> eventProducts = new ArrayList<>();

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

    public void addEventAccessories(EventAccessory eventAccessory)
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

    public void addEventProduct(EventProduct eventProduct)
    {
        this.eventProducts.add(eventProduct);
    }

    @Override
    protected void recopy (Event obj)
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
        return "Event{" + "id=" + id + ", date=" + date + ", description='" + description + '\'' + ", eventAccessories=" +
                eventAccessories + ", eventProducts=" + eventProducts + '}';
    }
}
