package fr.polytech.marechal.models.managers;

import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.OrderProduct;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class OrderProductsManager extends ModelManager<OrderProduct>
{
    @Override
    public String getBaseUrl ()
    {
        return null;
    }

    @Override
    protected Class<OrderProduct> getModelInstanceClass ()
    {
        return OrderProduct.class;
    }
}
