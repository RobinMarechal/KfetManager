package fr.polytech.marechal.models.factories;

import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.ProductRestocking;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class ProductRestockingFactory extends ModelFactory<ProductRestocking>
{
    @Override
    public String getBaseUrl ()
    {
        return null;
    }

    @Override
    public ArrayList<ProductRestocking> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<ProductRestocking> getModelInstanceClass ()
    {
        return ProductRestocking.class;
    }
}
