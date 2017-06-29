package fr.polytech.marechal.models.factories;

import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.CategoryMenu;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CategoryMenuFactory extends ModelFactory<CategoryMenu>
{
    @Override
    public String getBaseUrl ()
    {
        return null;
    }

    @Override
    public ArrayList<CategoryMenu> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<CategoryMenu> getModelInstanceClass ()
    {
        return CategoryMenu.class;
    }
}
