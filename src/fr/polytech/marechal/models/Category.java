package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.models.managers.CategoriesManager;
import fr.polytech.marechal.models.managers.MenusManager;
import fr.polytech.marechal.models.managers.ProductsManager;
import fr.polytech.marechal.models.managers.SubcategoriesManager;

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
    private RelationsMap<Menu, CategoryMenu> menus = new RelationsMap<>();
    private ArrayList<Product> products = new ArrayList<>();


    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Category loadSubcategories (UrlParametersMap parameters)
    {
        subcategories = new SubcategoriesManager().ofUrl("categories/" + getId() + "/subcategories", parameters);
        return this;
    }

    public Category loadSubcategories ()
    {
        return loadSubcategories(new UrlParametersMap());
    }

    public Category loadMenus (UrlParametersMap parameters)
    {
        menus = new RelationsMap<>();
        menus.addModels(new MenusManager().ofUrl("categories/" + getId() + "/menus", parameters));
        return this;
    }

    public Category loadMenus ()
    {
        return loadMenus(new UrlParametersMap());
    }

    public Category loadProducts (UrlParametersMap parameters)
    {
        products = new ProductsManager().ofUrl("categories/" + getId() + "/products", parameters);
        return this;
    }

    public Category loadProducts ()
    {
        return loadProducts(new UrlParametersMap());
    }

    @Override
    public Category loadAll ()
    {
        Category tmp = new CategoriesManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Category loadAll (UrlParametersMap parameters)
    {
        loadProducts(parameters);
        loadMenus(parameters);
        loadSubcategories(parameters);
        return this;
    }

    @Override
    public ModelManager<Category> getManagerInstance ()
    {
        return new CategoriesManager();
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

    public RelationsMap<Menu, CategoryMenu> getMenus ()
    {
        return menus;
    }

    public void setMenus (RelationsMap<Menu, CategoryMenu> menus)
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
        name = obj.name;
        subcategories = obj.subcategories;
        menus = obj.menus;
        products = obj.products;
    }

    @Override
    public boolean existsInDatabase ()
    {
        if (getId() < 1)
        {
            return false;
        }

        return (new CategoriesManager().find(getId())) != null;
    }

    @Override
    public boolean save ()
    {
        return false;
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", getId());
        map.put("name", name);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Category{" + "id=" + getId() + ", name='" + name + '\'' + ", subcategories=" + subcategories + ", menus=" + menus + ", "
                + "products=" + products + '}';
    }
}
