package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Subcategory;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class SubcategoryFactory extends ModelFactory<Subcategory>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "subcategories";
    }

    @Override
    public ArrayList<Subcategory> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Subcategory> getModelInstanceClass ()
    {
        return Subcategory.class;
    }
}
