package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.managers.EventAccessoriesManager;
import fr.polytech.marechal.app.models.managers.EventsManager;

import java.io.IOException;
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

    public EventAccessory loadEvent ()
    {
        return loadEvent(new UrlParametersMap());
    }

    public EventAccessory loadEvent (UrlParametersMap parameters)
    {
        event = new EventsManager().find(eventId, parameters);
        return this;
    }

    @Override
    protected void recopy (EventAccessory obj)
    {
        eventId = obj.eventId;
        name = obj.name;
        cost = obj.cost;
        quantity = obj.quantity;
        event = obj.event;
    }

    @Override
    public boolean saveAll () throws IOException
    {
        if(eventId < 1)
            return false;

        return save();
    }

    @Override
    public EventAccessory loadAll ()
    {
        EventAccessory tmp = new EventAccessoriesManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public EventAccessory loadAll (UrlParametersMap parameters)
    {
        return loadEvent(parameters);
    }

    @Override
    public ModelManager<EventAccessory> getManagerInstance ()
    {
        return new EventAccessoriesManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("event_id", eventId);
        map.put("name", name);
        map.put("cost", cost);
        map.put("quantity", quantity);

        return map;
    }

    @Override
    public String toString ()
    {
        return "EventAccessory{" + "id=" + getId() + ", eventId=" + eventId + ", name='" + name + '\'' + ", cost=" + cost + ", quantity="
                + quantity + ", event=" + event + '}';
    }
}
