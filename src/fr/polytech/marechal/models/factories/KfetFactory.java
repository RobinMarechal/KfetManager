package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Kfet;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class KfetFactory extends ModelFactory<Kfet>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "kfet";
    }

    @Override
    public ArrayList<Kfet> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Kfet> getModelInstanceClass ()
    {
        return Kfet.class;
    }
}
