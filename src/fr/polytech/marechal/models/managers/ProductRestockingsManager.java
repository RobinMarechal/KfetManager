package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.ProductRestocking;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class ProductRestockingsManager extends ModelManager<ProductRestocking>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "products/restockings";
    }

    @Override
    protected Class<ProductRestocking> getModelInstanceClass ()
    {
        return ProductRestocking.class;
    }
}
