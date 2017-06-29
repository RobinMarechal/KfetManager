package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Purchase;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class PurchaseFactory extends ModelFactory<Purchase>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "purchases";
    }

    @Override
    public ArrayList<Purchase> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Purchase> getModelInstanceClass ()
    {
        return Purchase.class;
    }
}
