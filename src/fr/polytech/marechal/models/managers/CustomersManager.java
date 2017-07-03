package fr.polytech.marechal.models.managers;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.Customer;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CustomersManager extends ModelManager<Customer>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "customers";
    }

    @Override
    protected Class<Customer> getModelInstanceClass ()
    {
        return Customer.class;
    }
}
