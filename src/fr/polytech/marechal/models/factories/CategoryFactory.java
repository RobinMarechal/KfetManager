package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Category;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 20/06/2017
 */
public class CategoryFactory extends ModelFactory<Category>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "categories";
    }

    @Override
    public ArrayList<Category> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Category> getModelInstanceClass ()
    {
        return Category.class;
    }
}
