package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.ModelFactory;
import fr.polytech.marechal.models.Product;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 20/06/2017
 */
public class ProductFactory extends ModelFactory<Product>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "products";
    }

    @Override
    public ArrayList<Product> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Product> getModelInstanceClass ()
    {
        return Product.class;
    }
}
