package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Purchase;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class PurchasesManager extends ModelManager<Purchase>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "purchases";
    }

    @Override
    protected Class<Purchase> getModelInstanceClass ()
    {
        return Purchase.class;
    }
}
