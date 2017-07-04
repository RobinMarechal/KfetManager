package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Product;
import fr.polytech.marechal.models.ProductRestocking;
import fr.polytech.marechal.models.Restocking;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class RestockingsManager extends ModelManager<Restocking>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "restockings";
    }

    @Override
    protected Class<Restocking> getModelInstanceClass ()
    {
        return Restocking.class;
    }

    public ArrayList<Product> getProducts (int restockingId)
    {
        return getProducts(restockingId, new UrlParametersMap());
    }

    public ArrayList<Product> getProducts (int restockingId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<ProductRestocking> getProductRestockings (int restockingId)
    {
        return getProductRestockings(restockingId, new UrlParametersMap());
    }

    public ArrayList<ProductRestocking> getProductRestockings (int restockingId, UrlParametersMap parameters)
    {
        return null;
    }
}
