package fr.polytech.marechal.models.factories;

import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.OrderProduct;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class OrderProductFactory extends ModelFactory<OrderProduct>
{
    @Override
    public String getBaseUrl ()
    {
        return null;
    }

    @Override
    public ArrayList<OrderProduct> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<OrderProduct> getModelInstanceClass ()
    {
        return OrderProduct.class;
    }
}
