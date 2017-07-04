package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Order;
import fr.polytech.marechal.models.OrderProduct;
import fr.polytech.marechal.models.Product;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class OrderProductsManager extends ModelManager<OrderProduct>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "orders/products";
    }

    @Override
    protected Class<OrderProduct> getModelInstanceClass ()
    {
        return OrderProduct.class;
    }

    public Product getProduct (int orderProductId)
    {
        return getProduct(orderProductId, new UrlParametersMap());
    }

    public Product getProduct (int orderProductId, UrlParametersMap parameters)
    {
        return null;
    }

    public Order getOrder (int orderProductId)
    {
        return getOrder(orderProductId, new UrlParametersMap());
    }

    public Order getOrder (int orderProductId, UrlParametersMap parameters)
    {
        return null;
    }
}
