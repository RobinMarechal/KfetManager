package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.app.models.managers.ProductsManager;
import fr.polytech.marechal.app.models.managers.RestockingsManager;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Restocking extends Model<Restocking>
{
    private LocalDate date;
    private double cost;
    private String description;

    private RelationsMap<Product, ProductRestocking> products = new RelationsMap<>();

    public Restocking loadProducts ()
    {
        return loadProducts(new UrlParametersMap());
    }

    public Restocking loadProducts (UrlParametersMap parameters)
    {
        ArrayList<Product> list = new ProductsManager().ofUrl("restockings/"+getId()+"/products", parameters);
        products = new RelationsMap<>();
        products.addModels(list);
        return this;
    }

    public RelationsMap<Product, ProductRestocking> getProducts ()
    {
        return products;
    }

    public void setProducts (RelationsMap<Product, ProductRestocking> products)
    {
        this.products = products;
    }

    public void addProduct (Product product, @Nullable ProductRestocking pivot)
    {
        if (pivot == null)
        {
            pivot = new ProductRestocking();
            pivot.setRestockingId(getId());
            pivot.setProductId(product.getId());
            pivot.setQuantity(1);
        }

        this.products.put(product, pivot);
    }

    public LocalDate getDate ()
    {
        return date;
    }

    public void setDate (LocalDate date)
    {
        this.date = date;
    }

    public double getCost ()
    {
        return cost;
    }

    public void setCost (double cost)
    {
        this.cost = cost;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    protected void recopy (Restocking obj)
    {
        date = obj.date;
        cost = obj.cost;
        description = obj.description;
        products = obj.products;
    }

    @Override
    public boolean saveAll () throws IOException
    {
        boolean success = save();

        // for each associated model
        for (RelationsMap.Pair<Product, ProductRestocking> e : products.pairList())
        {
            // we get the model's instance and the relation's pivot
            Product m = e.getModel();
            ProductRestocking p = e.getPivot();

            // if the model isn't saved in db, we saveAll it
            if (m.getId() < 1)
            {
                success &= m.saveAll();
            }

            // If the pivot is null, we create it
            if (p == null)
            {
                p = new ProductRestocking();
            }

            // We set the right category's id and menu's id
            p.setRestockingId(getId());
            p.setProductId(m.getId());

            // then we saveAll the pivot
            success &= p.save();
        }

        return success;
    }

    @Override
    public Restocking loadAll ()
    {
        Restocking tmp = new RestockingsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Restocking loadAll (UrlParametersMap parameters)
    {
        loadProducts(parameters);
        return this;
    }

    @Override
    public ModelManager<Restocking> getManagerInstance ()
    {
        return new RestockingsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("date", date);
        map.put("description", description);
        map.put("cost", cost);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Restocking{" + "id=" + getId() + ", date=" + date + ", cost=" + cost + ", description='" + description + '\'' + '}';
    }
}
