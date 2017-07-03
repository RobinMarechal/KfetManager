package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Product;

/**
 * @author Robin
 * @date 20/06/2017
 */
public class ProductsManager extends ModelManager<Product>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "products";
    }

    @Override
    protected Class<Product> getModelInstanceClass ()
    {
        return Product.class;
    }
}
