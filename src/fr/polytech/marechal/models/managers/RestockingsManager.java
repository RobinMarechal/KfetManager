package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Restocking;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class RestockingsManager extends ModelManager<Restocking>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "restockings";
    }

    @Override
    protected Class<Restocking> getModelInstanceClass ()
    {
        return Restocking.class;
    }
}
