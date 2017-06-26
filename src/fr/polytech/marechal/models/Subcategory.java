package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class Subcategory extends Model<Subcategory>
{
    private int id;
    private int categoryId;
    private String name;
    private double price;

    private Category category;
    private ArrayList<Product> products = new ArrayList<>();

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
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
        return "Subcategory{" + "id=" + id + ", categoryId=" + categoryId + ", name='" + name + '\'' + ", price=" + price + ", category="
                + category + ", products=" + products + '}';
    }
}
