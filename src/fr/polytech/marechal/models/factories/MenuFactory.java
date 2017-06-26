package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.ModelFactory;
import fr.polytech.marechal.models.Menu;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class MenuFactory extends ModelFactory<Menu>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "menus";
    }

    @Override
    public ArrayList<Menu> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Menu> getModelInstanceClass ()
    {
        return Menu.class;
    }
}
