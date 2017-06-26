package fr.polytech.marechal.models.factories;

import configs.ApiConfig;
import fr.polytech.marechal.libs.mvc.ModelFactory;
import fr.polytech.marechal.models.Staff;

import java.util.ArrayList;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class StaffFactory extends ModelFactory<Staff>
{
    @Override
    public String getBaseUrl ()
    {
        return ApiConfig.getApiUrl() + "staff";
    }

    @Override
    public ArrayList<Staff> allWithRelations ()
    {
        return null;
    }

    @Override
    protected Class<Staff> getModelInstanceClass ()
    {
        return Staff.class;
    }
}
