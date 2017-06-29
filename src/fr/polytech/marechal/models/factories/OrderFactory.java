package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Order;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class OrderFactory extends ModelFactory<Order>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "orders";
    }

    @Override
    public ArrayList<Order> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Order> getModelInstanceClass ()
    {
        return Order.class;
    }
}
