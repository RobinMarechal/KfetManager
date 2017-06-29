package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class ProductRestocking extends Model<ProductRestocking>
{
    private int productId;
    private int quantity;
    private int restockingId;

    private Product product;
    private Restocking restocking;


    public int getProductId ()
    {
        return productId;
    }

    public void setProductId (int productId)
    {
        this.productId = productId;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    public int getRestockingId ()
    {
        return restockingId;
    }

    public void setRestockingId (int restockingId)
    {
        this.restockingId = restockingId;
    }

    public Product getProduct ()
    {
        return product;
    }

    public void setProduct (Product product)
    {
        this.product = product;
    }

    public Restocking getRestocking ()
    {
        return restocking;
    }

    public void setRestocking (Restocking restocking)
    {
        this.restocking = restocking;
    }

    @Override
    protected void recopy (ProductRestocking obj)
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
        return "ProductRestocking{" + "id=" + getId() + ", productId=" + productId + ", quantity=" + quantity + ", restockingId=" +
                restockingId + ", product=" + product + ", restocking=" + restocking + '}';
    }
}
