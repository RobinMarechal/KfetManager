package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.*;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class OrdersManager extends ModelManager<Order>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "orders";
    }

    @Override
    protected Class<Order> getModelInstanceClass ()
    {
        return Order.class;
    }

    public Menu getMenu (int orderId)
    {
        return getMenu(orderId, new UrlParametersMap());
    }

    public Menu getMenu (int orderId, UrlParametersMap parameters)
    {
        return null;
    }

    public Customer getCustomer (int menuId)
    {
        return getCustomer(menuId, new UrlParametersMap());
    }

    public Customer getCustomer (int menuId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<OrderProduct> getOrderProducts (int orderId)
    {
        return getOrderProducts(orderId, new UrlParametersMap());
    }

    public ArrayList<OrderProduct> getOrderProducts (int orderId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<Product> getProducts (int orderId)
    {
        return getProducts(orderId, new UrlParametersMap());
    }

    public ArrayList<Product> getProducts (int orderId, UrlParametersMap parameters)
    {
        return null;
    }
}
