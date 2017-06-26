package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Customer extends Model<Customer>
{
    private int id;
    private String firstname;
    private String lastname;
    private int staffId;
    private double balance;

    private Staff staff;
    private ArrayList<Order> orders;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
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

    public int getStaffId ()
    {
        return staffId;
    }

    public void setStaffId (int staffId)
    {
        this.staffId = staffId;
    }

    public double getBalance ()
    {
        return balance;
    }

    public void setBalance (double balance)
    {
        this.balance = balance;
    }

    public Staff getStaff ()
    {
        return staff;
    }

    public void setStaff (Staff staff)
    {
        this.staff = staff;
    }

    public ArrayList<Order> getOrders ()
    {
        return orders;
    }

    public void setOrders (ArrayList<Order> orders)
    {
        this.orders = orders;
    }

    public void addOrder(Order order)
    {
        this.orders.add(order);
    }

    @Override

    protected void recopy (Customer obj)
    {

    }

    @Override
    public boolean update (HashMap<String, Object> data)
    {
        return false;
    }

    @Override
    protected String getPrimaryKeyValue ()
    {
        return null;
    }

    @Override
    public void buildFromResultMap (QueryResult rs) throws SQLException
    {

    }

    @Override
    public boolean save ()
    {
        return false;
    }

    @Override
    public String toString ()
    {
        return "Customer{" + "id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", staffId=" + staffId
                + ", balance=" + balance + ", staff=" + staff + ", orders=" + orders + '}';
    }
}
