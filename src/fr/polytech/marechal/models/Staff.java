package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.managers.CustomersManager;
import fr.polytech.marechal.models.managers.StaffsManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Staff extends Model<Staff>
{
    private String firstname;
    private String lastname;
    private String email;
    private String role;

    private Customer customer;

    public Staff loadCustomer ()
    {
        return loadCustomer(new UrlParametersMap());
    }

    public Staff loadCustomer (UrlParametersMap parameters)
    {
        ArrayList<Customer> list = new CustomersManager().ofUrl("staff/"+getId()+"/customer", parameters);
        if(!list.isEmpty())
        {
            customer = list.get(0);
        }

        return this;
    }


    public String getFirstname ()
    {
        return firstname;
    }

    public void setFirstname (String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname ()
    {
        return lastname;
    }

    public void setLastname (String lastname)
    {
        this.lastname = lastname;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public Customer getCustomer ()
    {
        return customer;
    }

    public void setCustomer (Customer customer)
    {
        this.customer = customer;
    }

    @Override
    protected void recopy (Staff obj)
    {
        firstname = obj.firstname;
        lastname = obj.lastname;
        email = obj.email;
        role = obj.role;
        customer = obj.customer;
    }

    @Override
    public boolean existsInDatabase ()
    {
        return false;
    }

    @Override
    public boolean save ()
    {
        return false;
    }

    @Override
    public Staff loadAll ()
    {
        Staff tmp = new StaffsManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Staff loadAll (UrlParametersMap parameters)
    {
        loadCustomer(parameters);
        return this;
    }

    @Override
    public ModelManager<Staff> getManagerInstance ()
    {
        return new StaffsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        return null;
    }

    @Override
    public String toString ()
    {
        return "Staff{" + "id=" + getId() + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", email='" + email +
                '\'' + ", role='" + role + '\'' + ", customer=" + customer + '}';
    }
}
