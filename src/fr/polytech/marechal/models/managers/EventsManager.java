package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Event;
import fr.polytech.marechal.models.EventAccessory;
import fr.polytech.marechal.models.EventProduct;
import fr.polytech.marechal.models.Product;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventsManager extends ModelManager<Event>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "events";
    }

    @Override
    protected Class<Event> getModelInstanceClass ()
    {
        return Event.class;
    }
    
    public ArrayList<EventAccessory> getEventAccessories (int eventId)
    {
        return getEventAccessories(eventId, new UrlParametersMap());
    }

    public ArrayList<EventAccessory> getEventAccessories (int eventId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<EventProduct> getEventProducts (int eventId)
    {
        return getEventProducts(eventId, new UrlParametersMap());
    }

    public ArrayList<EventProduct> getEventProducts (int eventId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<Product> getProducts (int eventId)
    {
        return getProducts(eventId, new UrlParametersMap());
    }

    public ArrayList<Product> getProducts (int eventId, UrlParametersMap parameters)
    {
        return null;
    }
}
