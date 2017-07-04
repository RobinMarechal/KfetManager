package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.models.managers.CategoriesManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
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
        subcategories = new CategoriesManager().getSubcategories(getId(), parameters);
        return this;
    }

    public Category loadSubcategories ()
    {
        return loadSubcategories(new UrlParametersMap());
    }

    public Category loadMenus (UrlParametersMap parameters)
    {
        menus = new RelationsMap<>();
        menus.addModels(new CategoriesManager().getMenus(getId(), parameters));
        return this;
    }

    public Category loadMenus ()
    {
        return loadMenus(new UrlParametersMap());
    }

    public Category loadProducts (UrlParametersMap parameters)
    {
        products = new CategoriesManager().getProducts(getId(), parameters);
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
        subcategories = new ArrayList<>();
        subcategories.forEach(subcategory -> addSubcategory(subcategory));
    }

    public void addSubcategory (Subcategory subcategory)
    {
        subcategory.setCategoryId(getId());
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

    public void addMenu (@NotNull Menu menu, @Nullable CategoryMenu pivot)
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
    public boolean save () throws IOException
    {
        boolean success = saveWithoutRelations();

        // we save each subcategory
        for (Subcategory s : subcategories)
        {
            s.setCategoryId(getId());
            success = (s.save() && success);
        }

        // for each menus associated
        for (RelationsMap.Pair<Menu, CategoryMenu> e : menus.pairList())
        {
            // we get the menu instance and the relation's pivot
            Menu m = e.getModel();
            CategoryMenu p = e.getPivot();

            // if the menu isn't saved in db, we save it
            if (m.getId() < 1)
            {
                success = (m.save() && success);
            }

            // If the pivot is null, we create it
            if (p == null)
            {
                p = new CategoryMenu();
            }

            // We set the right category's id and menu's id
            p.setCategoryId(getId());
            p.setMenuId(m.getId());

            // then we save the pivot
            success = (p.saveWithoutRelations() && success);
        }

        return success;
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
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
