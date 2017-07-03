package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.MoneyAdding;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class MoneyAddingsManager extends ModelManager<MoneyAdding>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "money_addings";
    }

    @Override
    protected Class<MoneyAdding> getModelInstanceClass ()
    {
        return MoneyAdding.class;
    }
}
