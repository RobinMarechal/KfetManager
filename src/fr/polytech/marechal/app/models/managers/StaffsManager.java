package fr.polytech.marechal.app.models.managers;

import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.Customer;
import fr.polytech.marechal.app.models.Staff;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class StaffsManager extends ModelManager<Staff>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "staff";
    }

    @Override
    protected Class<Staff> getModelInstanceClass ()
    {
        return Staff.class;
    }

    public Customer getCustomer (int staffId)
    {
        return getCustomer(staffId, new UrlParametersMap());
    }

    public Customer getCustomer (int staffId, UrlParametersMap parameters)
    {
        return null;
    }
}
