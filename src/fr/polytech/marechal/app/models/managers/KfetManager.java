package fr.polytech.marechal.app.models.managers;

import fr.polytech.marechal.app.models.Kfet;
import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;

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
