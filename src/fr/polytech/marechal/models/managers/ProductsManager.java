package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.*;

import java.util.ArrayList;

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

    public ArrayList<Restocking> getRestockings (int productId)
    {
        return getRestockings(productId, new UrlParametersMap());
    }

    public ArrayList<Restocking> getRestockings (int productId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<EventProduct> getEventProducts (int productId)
    {
        return getEventProducts(productId, new UrlParametersMap());
    }

    public ArrayList<EventProduct> getEventProducts (int productId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<Event> getEvens (int productId)
    {
        return getEvens(productId, new UrlParametersMap());
    }

    public ArrayList<Event> getEvens (int productId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<OrderProduct> getOrderProducts (int productId)
    {
        return getOrderProducts(productId, new UrlParametersMap());
    }

    public ArrayList<OrderProduct> getOrderProducts (int productId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<Order> getOrders (int productId)
    {
        return getOrders(productId, new UrlParametersMap());
    }

    public ArrayList<Order> getOrders (int productsId, UrlParametersMap parameters)
    {
        return null;
    }
}
