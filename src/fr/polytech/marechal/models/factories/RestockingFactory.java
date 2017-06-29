package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.Restocking;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class RestockingFactory extends ModelFactory<Restocking>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "restockings";
    }

    @Override
    public ArrayList<Restocking> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Restocking> getModelInstanceClass ()
    {
        return Restocking.class;
    }
}
