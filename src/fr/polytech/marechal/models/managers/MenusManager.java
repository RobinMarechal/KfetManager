package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Menu;

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
}
