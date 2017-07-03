package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.EventProduct;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class EventProductsManager extends ModelManager<EventProduct>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "events/products";
    }

    @Override
    protected Class<EventProduct> getModelInstanceClass ()
    {
        return EventProduct.class;
    }
}
