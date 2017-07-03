package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.models.managers.PurchasesManager;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Purchase extends Model<Purchase>
{
    private double cost;
    private int quantity;
    private LocalDate date;
    private String description;


    public double getCost ()
    {
        return cost;
    }

    public void setCost (double cost)
    {
        this.cost = cost;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    public LocalDate getDate ()
    {
        return date;
    }

    public void setDate (LocalDate date)
    {
        this.date = date;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    protected void recopy (Purchase obj)
    {

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
    public Purchase loadAll ()
    {
        return this;
    }

    @Override
    public Purchase loadAll (UrlParametersMap parameters)
    {
        return this;
    }

    @Override
    public ModelManager<Purchase> getManagerInstance ()
    {
        return new PurchasesManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        return null;
    }

    @Override
    public String toString ()
    {
        return "Purchase{" + "id=" + getId() + ", cost=" + cost + ", quantity=" + quantity + ", date=" + date + ", description='" +
                description + '\'' + '}';
    }
}
