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
public class Category extends Model<Category>
{
    private int id;
    private String name;

    private ArrayList<Subcategory> subcategories = new ArrayList<>();
    private ArrayList<Menu> menus = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();


    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public ArrayList<Subcategory> getSubcategories ()
    {
        return subcategories;
    }

    public void setSubcategories (ArrayList<Subcategory> subcategories)
    {
        this.subcategories = subcategories;
    }

    public void addSubcategory(Subcategory subcategory)
    {
        this.subcategories.add(subcategory);
    }

    public ArrayList<Menu> getMenus ()
    {
        return menus;
    }

    public void setMenus (ArrayList<Menu> menus)
    {
        this.menus = menus;
    }

    public void addMenu(Menu menu)
    {
        this.menus.add(menu);
    }

    public ArrayList<Product> getProducts ()
    {
        return products;
    }

    public void setProducts (ArrayList<Product> products)
    {
        this.products = products;
    }

    public void addProduct(Product product)
    {
        this.products.add(product);
    }

    @Override
    protected void recopy (Category obj)
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
        return "Category{" + "id=" + id + ", name='" + name + '\'' + ", subcategories=" + subcategories + ", menus=" + menus + ", " +
                "products=" + products + '}';
    }
}
