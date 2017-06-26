package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.ModelFactory;
import fr.polytech.marechal.models.EventAccessory;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventAccessoryFactory extends ModelFactory<EventAccessory>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "events/accessories";
    }

    @Override
    public ArrayList<EventAccessory> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<EventAccessory> getModelInstanceClass ()
    {
        return EventAccessory.class;
    }
}
