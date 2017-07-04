package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.models.managers.CategoriesManager;
import fr.polytech.marechal.models.managers.MenusManager;
import fr.polytech.marechal.models.managers.OrdersManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Menu extends Model<Menu>
{
    private String name;
    private String description;
    private double price;

    private RelationsMap<Category, CategoryMenu> categories = new RelationsMap<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Menu loadCategories ()
    {
        return loadCategories(new UrlParametersMap());
    }

    public Menu loadCategories (UrlParametersMap parameters)
    {
        ArrayList<Category> categoryList = new CategoriesManager().ofUrl("menus/" + getId() + "/categories", parameters);
        this.categories = new RelationsMap<>();
        categories.addModels(categoryList);
        return this;
    }

    public Menu loadOrders ()
    {
        return loadOrders(new UrlParametersMap());
    }

    public Menu loadOrders (UrlParametersMap parameters)
    {
        orders = new OrdersManager().ofUrl("menus/" + getId() + "/orders", parameters);
        return this;
    }


    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    public RelationsMap<Category, CategoryMenu> getCategories ()
    {
        return categories;
    }

    public void setCategories (RelationsMap<Category, CategoryMenu> categories)
    {
        this.categories = categories;
    }

    public void addCategory (Category category, CategoryMenu pivot)
    {
        this.categories.put(category, pivot);
    }

    public ArrayList<Order> getOrders ()
    {
        return orders;
    }

    public void setOrders (ArrayList<Order> orders)
    {
        this.orders = orders;
    }

    public void addOrder (Order order)
    {
        this.orders.add(order);
    }

    @Override
    protected void recopy (Menu obj)
    {
        this.name = obj.name;
        this.description = obj.description;
        this.price = obj.price;
        this.categories = obj.categories;
        this.orders = obj.orders;
    }


    @Override
    public boolean save () throws IOException
    {
        boolean success = saveWithoutRelations();

        for (Order o : orders)
        {
            o.setMenuId(getId());
            success &= o.save();
        }

        // for each associated category
        for (RelationsMap.Pair<Category, CategoryMenu> e : categories.pairList())
        {
            // we get the category's instance and the relation's pivot
            Category m = e.getModel();
            CategoryMenu p = e.getPivot();

            // if the category isn't saved in db, we save it
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
            p.setMenuId(getId());
            p.setCategoryId(m.getId());

            // then we save the pivot
            success = (p.saveWithoutRelations() && success);
        }

        return success;
    }

    @Override
    public Menu loadAll ()
    {
        Menu tmp = new MenusManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Menu loadAll (UrlParametersMap parameters)
    {
        loadOrders(parameters);
        loadCategories(parameters);
        return this;
    }

    @Override
    public ModelManager<Menu> getManagerInstance ()
    {
        return new MenusManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("price", price);
        map.put("description", description);
        map.put("name", name);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Menu{" + "id=" + getId() + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + ", "
                + "categories=" + categories + ", orders=" + orders + '}';
    }
}
