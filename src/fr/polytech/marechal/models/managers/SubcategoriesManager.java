package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Category;
import fr.polytech.marechal.models.Product;
import fr.polytech.marechal.models.Subcategory;

import java.util.ArrayList;

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

    public Category getCategory (int subcategoryId)
    {
        return getCategory(subcategoryId, new UrlParametersMap());
    }

    public Category getCategory (int subcategoryId, UrlParametersMap parameters)
    {
        return null;
    }

    public ArrayList<Product> getProducts (int subcategoryId)
    {
        return getProducts(subcategoryId, new UrlParametersMap());
    }

    public ArrayList<Product> getProducts (int subcategoryId, UrlParametersMap parameters)
    {
        return null;
    }
}
