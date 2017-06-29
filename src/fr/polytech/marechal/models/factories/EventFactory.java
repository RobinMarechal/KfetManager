package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Event;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventFactory extends ModelFactory<Event>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "events";
    }

    @Override
    public ArrayList<Event> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Event> getModelInstanceClass ()
    {
        return Event.class;
    }
}
