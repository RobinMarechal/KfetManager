package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.managers.CategoriesManager;
import fr.polytech.marechal.models.managers.ProductsManager;
import fr.polytech.marechal.models.managers.SubcategoriesManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class Subcategory extends Model<Subcategory>
{
    private int categoryId;
    private String name;
    private double price;

    private Category category;
    private ArrayList<Product> products = new ArrayList<>();

    public Subcategory loadCategory ()
    {
        return loadCategory(new UrlParametersMap());
    }

    public Subcategory loadCategory (UrlParametersMap parameters)
    {
        ArrayList<Category> list = new CategoriesManager().ofUrl("subcategories/" + getId() +"/category", parameters);
        if(!list.isEmpty())
        {
            category = list.get(0);
        }

        return this;
    }

    public Subcategory loadProducts ()
    {
        return loadProducts(new UrlParametersMap());
    }

    public Subcategory loadProducts (UrlParametersMap parameters)
    {
        products = new ProductsManager().ofUrl("subcategories/" + getId() +"/products", parameters);
        return this;
    }

    public int getCategoryId ()
    {
        return categoryId;
    }

    public void setCategoryId (int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    public Category getCategory ()
    {
        return category;
    }

    public void setCategory (Category category)
    {
        this.category = category;
    }

    public ArrayList<Product> getProducts ()
    {
        return products;
    }

    public void setProducts (ArrayList<Product> products)
    {
        this.products = products;
    }

    public void addProduct (Product product)
    {
        this.products.add(product);
    }

    @Override
    protected void recopy (Subcategory obj)
    {
        this.categoryId = obj.categoryId;
        this.name = obj.name;
        this.price = obj.price;
        this.products = obj.products;
        this.category = obj.category;
    }

    @Override
    public boolean save () throws IOException
    {
        if(categoryId < 1)
            return false;

        boolean success = saveWithoutRelations();

        for (Product p : products)
        {
            p.setSubcategoryId(getId());
            success &= p.save();
        }

        return success;
    }

    @Override
    public Subcategory loadAll ()
    {
        Subcategory tmp = new SubcategoriesManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Subcategory loadAll (UrlParametersMap parameters)
    {
        loadCategory(parameters);
        loadProducts(parameters);
        return this;
    }

    @Override
    public ModelManager<Subcategory> getManagerInstance ()
    {
        return new SubcategoriesManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("category_id", categoryId);
        map.put("name", name);
        map.put("price", price);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Subcategory{" + "id=" + getId() + ", categoryId=" + categoryId + ", name='" + name + '\'' + ", price=" + price + ", " +
                "category=" + category + ", products=" + products + '}';
    }
}
