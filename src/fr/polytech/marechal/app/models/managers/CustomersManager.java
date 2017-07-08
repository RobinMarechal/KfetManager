package fr.polytech.marechal.app.models.managers;

import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.Customer;
import fr.polytech.marechal.app.models.Order;
import fr.polytech.marechal.app.models.Staff;

import java.util.ArrayList;

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

    public Staff getStaff (int customerId)
    {
        return getStaff(customerId, new UrlParametersMap());
    }
    
    public Staff getStaff (int customerId, UrlParametersMap parameters)
    {
        String url = "customers/"+ customerId + "/staff";
        ArrayList<Staff> staffList = new StaffsManager().ofUrl(url, parameters);

        if(!staffList.isEmpty())
            return staffList.get(0);

        return null;
    }
    
    public ArrayList<Order> getOrders (int customerId)
    {
        return getOrders(customerId, new UrlParametersMap());
    }
    
    public ArrayList<Order> getOrders (int customerId, UrlParametersMap parameters)
    {
        String url = "customers/" + customerId + "/orders";
        return new OrdersManager().ofUrl(url, parameters);
    }
}
