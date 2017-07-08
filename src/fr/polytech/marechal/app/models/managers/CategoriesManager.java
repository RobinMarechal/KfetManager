package fr.polytech.marechal.app.models.managers;

import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.Category;
import fr.polytech.marechal.app.models.Menu;
import fr.polytech.marechal.app.models.Product;
import fr.polytech.marechal.app.models.Subcategory;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 20/06/2017
 */
public class CategoriesManager extends ModelManager<Category>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "categories";
    }

    @Override
    protected Class<Category> getModelInstanceClass ()
    {
        return Category.class;
    }

    public ArrayList<Menu> getMenus (int categoryId)
    {
        return getMenus(categoryId, new UrlParametersMap());
    }

    public ArrayList<Menu> getMenus (int categoryId, UrlParametersMap parameters)
    {
        String url = "categories/" + categoryId + "/menus";
        ArrayList<Menu> menus = new MenusManager().ofUrl(url, parameters);
        return menus;
    }

    public ArrayList<Subcategory> getSubcategories (int categoryId)
    {
        return getSubcategories(categoryId, new UrlParametersMap());
    }

    public ArrayList<Subcategory> getSubcategories (int categoryId, UrlParametersMap parameters)
    {
        String url = "categories/" + categoryId + "/subcategories";
        ArrayList<Subcategory> subcategories = new SubcategoriesManager().ofUrl(url, parameters);
        return subcategories;
    }

    public ArrayList<Product> getProducts (int categoryId)
    {
        return getProducts(categoryId, new UrlParametersMap());
    }

    public ArrayList<Product> getProducts (int categoryId, UrlParametersMap parameters)
    {
        String url = "categories/" + categoryId + "/products";
        ArrayList<Product> products = new ProductsManager().ofUrl(url, parameters);
        return products;
    }
}
