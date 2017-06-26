package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.ModelFactory;
import fr.polytech.marechal.models.EventProduct;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventProductFactory extends ModelFactory<EventProduct>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "events/products";
    }

    @Override
    public ArrayList<EventProduct> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<EventProduct> getModelInstanceClass ()
    {
        return EventProduct.class;
    }
}
