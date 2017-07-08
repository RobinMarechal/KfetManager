package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.managers.MoneyAddingsManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class MoneyAdding extends Model<MoneyAdding>
{
    private LocalDate date;
    private double amount;
    private String reason;
    private String description;


    public LocalDate getDate ()
    {
        return date;
    }

    public void setDate (LocalDate date)
    {
        this.date = date;
    }

    public double getAmount ()
    {
        return amount;
    }

    public void setAmount (double amount)
    {
        this.amount = amount;
    }

    public String getReason ()
    {
        return reason;
    }

    public void setReason (String reason)
    {
        this.reason = reason;
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
    protected void recopy (MoneyAdding obj)
    {

        date = obj.date;
        amount = obj.amount;
        description = obj.description;
        reason = obj.reason;
    }

    @Override
    public boolean saveAll () throws IOException
    {
        return save();
    }

    @Override
    public MoneyAdding loadAll ()
    {
        return this;
    }

    @Override
    public MoneyAdding loadAll (UrlParametersMap parameters)
    {
        return this;
    }

    @Override
    public ModelManager<MoneyAdding> getManagerInstance ()
    {
        return new MoneyAddingsManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("date", date);
        map.put("description", description);
        map.put("amount", amount);
        map.put("reason", reason);

        return map;
    }

    @Override
    public String toString ()
    {
        return "MoneyAdding{" + "id=" + getId() + ", date=" + date + ", amount=" + amount + ", reason='" + reason + '\'' + ", description='" +
                description + '\'' + '}';
    }
}
