package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelFactory;
import fr.polytech.marechal.models.MoneyAdding;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class MoneyAddingFactory extends ModelFactory<MoneyAdding>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "money_addings";
    }

    @Override
    public ArrayList<MoneyAdding> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<MoneyAdding> getModelInstanceClass ()
    {
        return MoneyAdding.class;
    }
}
