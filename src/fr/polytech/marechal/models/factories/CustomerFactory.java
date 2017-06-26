package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.ModelFactory;
import fr.polytech.marechal.models.Customer;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CustomerFactory extends ModelFactory<Customer>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "customers";
    }

    @Override
    public ArrayList<Customer> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Customer> getModelInstanceClass ()
    {
        return Customer.class;
    }
}
