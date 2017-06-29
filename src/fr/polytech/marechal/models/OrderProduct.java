package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class OrderProduct extends Model<OrderProduct>
{
    private int orderId;
    private int productId;
    private int menuId;
    private double quantity;

    private Order order;
    private Product product;
    private Menu menu;


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

    public int getMenuId ()
    {
        return menuId;
    }

    public void setMenuId (int menuId)
    {
        this.menuId = menuId;
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

    public Menu getMenu ()
    {
        return menu;
    }

    public void setMenu (Menu menu)
    {
        this.menu = menu;
    }

    @Override
    protected void recopy (OrderProduct obj)
    {

    }

    @Override
    public boolean update (HashMap<String, Object> data)
    {
        return false;
    }

    @Override
    protected String getPrimaryKeyValue ()
    {
        return null;
    }

    @Override
    public void buildFromResultMap (QueryResult rs) throws SQLException
    {

    }

    @Override
    public boolean save ()
    {
        return false;
    }

    @Override
    public String toString ()
    {
        return "OrderProduct{" + "id=" + getId() + ", orderId=" + orderId + ", productId=" + productId + ", menuId=" + menuId + ", quantity="
                + quantity + ", order=" + order + ", product=" + product + ", menu=" + menu + '}';
    }
}
