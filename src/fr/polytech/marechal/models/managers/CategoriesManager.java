package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Category;

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
}
