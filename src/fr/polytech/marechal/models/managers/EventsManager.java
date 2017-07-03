package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Event;

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
}
