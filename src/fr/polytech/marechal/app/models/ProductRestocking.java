package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.managers.ProductRestockingsManager;
import fr.polytech.marechal.app.models.managers.ProductsManager;
import fr.polytech.marechal.app.models.managers.RestockingsManager;

import java.io.IOException;
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

    public ProductRestocking loadProduct ()
    {
        return loadProduct(new UrlParametersMap());
    }

    public ProductRestocking loadProduct (UrlParametersMap parameters)
    {
        product = new ProductsManager().find(productId, parameters);
        return this;
    }

    public ProductRestocking loadRestocking ()
    {
        return loadRestocking(new UrlParametersMap());
    }

    public ProductRestocking loadRestocking (UrlParametersMap parameters)
    {
        restocking = new RestockingsManager().find(restockingId, parameters);
        return this;
    }


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
        this.productId = obj.productId;
        this.restockingId = obj.restockingId;
        this.product = obj.product;
        this.restocking = obj.restocking;
    }

    @Override
    public boolean saveAll () throws IOException
    {
        if(productId < 1 || restockingId < 1)
            return false;

        return save();
    }

    @Override
    public ProductRestocking loadAll ()
    {
        ProductRestocking pr = new ProductRestockingsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(pr);
        return this;
    }

    @Override
    public ProductRestocking loadAll (UrlParametersMap parameters)
    {
        loadRestocking(parameters);
        loadProduct(parameters);
        return this;
    }

    @Override
    public ModelManager<ProductRestocking> getManagerInstance ()
    {
        return new ProductRestockingsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("product_id", productId);
        map.put("restocking_id", restockingId);
        map.put("quantity", quantity);

        return map;
    }

    @Override
    public String toString ()
    {
        return "ProductRestocking{" + "id=" + getId() + ", productId=" + productId + ", quantity=" + quantity + ", restockingId=" +
                restockingId + ", product=" + product + ", restocking=" + restocking + '}';
    }
}
