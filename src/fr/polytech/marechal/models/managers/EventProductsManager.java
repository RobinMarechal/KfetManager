package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Event;
import fr.polytech.marechal.models.EventProduct;
import fr.polytech.marechal.models.Product;

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
    
    public Event getEvent (int eventProductId)
    {
        return getEvent(eventProductId, new UrlParametersMap());
    }

    public Event getEvent (int eventProductId, UrlParametersMap parameters)
    {
        return null;
    }

    public Product getProduct (int eventProductId)
    {
        return getProduct(eventProductId, new UrlParametersMap());
    }

    public Product getProduct (int eventProductId, UrlParametersMap parameters)
    {
        return null;
    }

}
