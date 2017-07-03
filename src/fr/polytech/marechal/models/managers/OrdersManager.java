package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Order;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class OrdersManager extends ModelManager<Order>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "orders";
    }

    @Override
    protected Class<Order> getModelInstanceClass ()
    {
        return Order.class;
    }
}
