package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Category;
import fr.polytech.marechal.models.Customer;
import fr.polytech.marechal.models.Menu;
import fr.polytech.marechal.models.Order;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class MenusManager extends ModelManager<Menu>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "menus";
    }

    @Override
    protected Class<Menu> getModelInstanceClass ()
    {
        return Menu.class;
    }

    public ArrayList<Category> getCategories (int menuId)
    {
        return getCategories(menuId, new UrlParametersMap());
    }

    public ArrayList<Category> getCategories (int menuId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<Order> getOrders (int menuId)
    {
        return getOrders(menuId, new UrlParametersMap());
    }

    public ArrayList<Order> getOrders (int menuId, UrlParametersMap parameters)
    {
        return null;
    }

    public Customer getCustomer (int menuId)
    {
        return getCustomer(menuId, new UrlParametersMap());
    }

    public Customer getCustomer (int menuId, UrlParametersMap parameters)
    {
        return null;
    }
}
