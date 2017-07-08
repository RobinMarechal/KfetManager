package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.managers.OrderProductsManager;
import fr.polytech.marechal.app.models.managers.OrdersManager;
import fr.polytech.marechal.app.models.managers.ProductsManager;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class OrderProduct extends Model<OrderProduct>
{
    private int orderId;
    private int productId;
    private double quantity;

    private Order order;
    private Product product;

    public OrderProduct loadOrder ()
    {
        return loadOrder(new UrlParametersMap());
    }

    public OrderProduct loadOrder (UrlParametersMap parameters)
    {
        order = new OrdersManager().find(orderId, parameters);
        return this;
    }

    public OrderProduct loadProduct ()
    {
        return loadProduct(new UrlParametersMap());
    }

    public OrderProduct loadProduct (UrlParametersMap parameters)
    {
        product = new ProductsManager().find(productId, parameters);
        return this;
    }

    public int getOrderId ()
    {
        return orderId;
    }

    public void setOrderId (int orderId)
    {
        this.orderId = orderId;
    }

    public int getProductId ()
    {
        return productId;
    }

    public void setProductId (int productId)
    {
        this.productId = productId;
    }

    public double getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (double quantity)
    {
        this.quantity = quantity;
    }

    public Order getOrder ()
    {
        return order;
    }

    public void setOrder (Order order)
    {
        this.order = order;
    }

    public Product getProduct ()
    {
        return product;
    }

    public void setProduct (Product product)
    {
        this.product = product;
    }


    @Override
    protected void recopy (OrderProduct obj)
    {
        orderId = obj.orderId;
        productId = obj.productId;
        quantity = obj.quantity;
        order = obj.order;
        product = obj.product;
    }


    @Override
    public boolean saveAll () throws IOException
    {
        if(productId < 1 || orderId < 1)
            return false;

        return save();
    }

    @Override
    public OrderProduct loadAll ()
    {
        OrderProduct tmp = new OrderProductsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public OrderProduct loadAll (UrlParametersMap parameters)
    {
        loadProduct(parameters);
        loadOrder(parameters);
        return this;
    }

    @Override
    public ModelManager<OrderProduct> getManagerInstance ()
    {
        return new OrderProductsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("product_id", productId);
        map.put("order_id", orderId);
        map.put("quantity", quantity);

        return map;
    }

    @Override
    public String toString ()
    {
        return "OrderProduct{" + "id=" + getId() + ", orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + ", order="
                + order + ", product=" + product + '}';
    }
}
