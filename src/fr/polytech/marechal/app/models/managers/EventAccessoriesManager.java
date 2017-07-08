package fr.polytech.marechal.app.models.managers;

import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.Event;
import fr.polytech.marechal.app.models.EventAccessory;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventAccessoriesManager extends ModelManager<EventAccessory>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "events/accessories";
    }

    @Override
    protected Class<EventAccessory> getModelInstanceClass ()
    {
        return EventAccessory.class;
    }

    public Event getEvent (int eventAccessoryId)
    {
        return getEvent(eventAccessoryId, new UrlParametersMap());
    }

    public Event getEvent (int eventAccessoryId, UrlParametersMap parameters)
    {
        return null;
    }

}
