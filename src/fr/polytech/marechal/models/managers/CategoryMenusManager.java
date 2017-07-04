package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Category;
import fr.polytech.marechal.models.CategoryMenu;
import fr.polytech.marechal.models.Menu;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CategoryMenusManager extends ModelManager<CategoryMenu>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "categories/menus";
    }

    @Override
    protected Class<CategoryMenu> getModelInstanceClass ()
    {
        return CategoryMenu.class;
    }

    public Menu getMenu (int categoryMenuId)
    {
        return getMenu(categoryMenuId, new UrlParametersMap());
    }

    public Menu getMenu (int categoryMenuId, UrlParametersMap parameters)
    {
        String url = "categories/menus/"+ categoryMenuId + "/menu";
        ArrayList<Menu> menus = new MenusManager().ofUrl(url, parameters);

        if(!menus.isEmpty())
            return menus.get(0);

        return null;
    }

    public Category getCategory (int categoryMenuId)
    {
        return getCategory(categoryMenuId, new UrlParametersMap());
    }

    public Category getCategory (int categoryMenuId, UrlParametersMap parameters)
    {
        String url = "categories/menus/"+ categoryMenuId + "/category";
        ArrayList<Category> categories = new CategoriesManager().ofUrl(url, parameters);

        if(!categories.isEmpty())
            return categories.get(0);

        return null;
    }

}
