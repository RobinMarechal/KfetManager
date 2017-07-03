package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Subcategory;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class SubcategoriesManager extends ModelManager<Subcategory>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "subcategories";
    }

    @Override
    protected Class<Subcategory> getModelInstanceClass ()
    {
        return Subcategory.class;
    }
}
