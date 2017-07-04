package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Product;
import fr.polytech.marechal.models.ProductRestocking;
import fr.polytech.marechal.models.Restocking;

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

    public Product getProduct (int productRestockingId)
    {
        return getProduct(productRestockingId, new UrlParametersMap());
    }

    public Product getProduct (int productRestockingId, UrlParametersMap parameters)
    {
        return null;
    }

    public Restocking getRestocking (int productRestockingId)
    {
        return getRestocking(productRestockingId, new UrlParametersMap());
    }

    public Restocking getRestocking (int productRestockingId, UrlParametersMap parameters)
    {
        return null;
    }
}
