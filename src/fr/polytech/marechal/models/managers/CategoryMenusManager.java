package fr.polytech.marechal.models.managers;

import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.CategoryMenu;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CategoryMenusManager extends ModelManager<CategoryMenu>
{
    @Override
    public String getBaseUrl ()
    {
        return null;
    }

    @Override
    protected Class<CategoryMenu> getModelInstanceClass ()
    {
        return CategoryMenu.class;
    }
}
