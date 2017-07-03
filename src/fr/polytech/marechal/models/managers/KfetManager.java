package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Kfet;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class KfetManager extends ModelManager<Kfet>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "kfet";
    }

    @Override
    protected Class<Kfet> getModelInstanceClass ()
    {
        return Kfet.class;
    }
}
