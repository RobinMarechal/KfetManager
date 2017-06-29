package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.RelationWithPivot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class Category extends Model<Category>
{
    private String name;

    private ArrayList<Subcategory> subcategories = new ArrayList<>();
    private RelationWithPivot<Menu, CategoryMenu> menus = new RelationWithPivot<>();
    private ArrayList<Product> products = new ArrayList<>();


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

    public void addSubcategory (Subcategory subcategory)
    {
        this.subcategories.add(subcategory);
    }

    public RelationWithPivot<Menu, CategoryMenu> getMenus ()
    {
        return menus;
    }

    public void setMenus (RelationWithPivot<Menu, CategoryMenu> menus)
    {
        this.menus = menus;
    }

    public void addMenu (Menu menu, CategoryMenu pivot)
    {
        this.menus.put(menu, pivot);
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
        return "Category{" + "id=" + getId() + ", name='" + name + '\'' + ", subcategories=" + subcategories + ", menus=" + menus + ", " +
                "products=" + products + '}';
    }
}
