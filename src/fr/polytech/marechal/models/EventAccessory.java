package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventAccessory extends Model<EventAccessory>
{
    private int eventId;
    private String name;
    private double cost;
    private int quantity;

    private Event event;

    public int getEventId ()
    {
        return eventId;
    }

    public void setEventId (int eventId)
    {
        this.eventId = eventId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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

    public Event getEvent ()
    {
        return event;
    }

    public void setEvent (Event event)
    {
        this.event = event;
    }

    @Override
    protected void recopy (EventAccessory obj)
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
        return "EventAccessory{" + "id=" + getId() + ", eventId=" + eventId + ", name='" + name + '\'' + ", cost=" + cost + ", quantity=" +
                quantity + ", event=" + event + '}';
    }
}
