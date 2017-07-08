package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.managers.KfetManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Kfet extends Model<Kfet>
{
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double balance;
    private int reasonId;
    private String reasonTable;
    private String reasonType;

    public LocalDateTime getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public double getBalance ()
    {
        return balance;
    }

    public void setBalance (double balance)
    {
        this.balance = balance;
    }

    public int getReasonId ()
    {
        return reasonId;
    }

    public void setReasonId (int reasonId)
    {
        this.reasonId = reasonId;
    }

    public String getReasonTable ()
    {
        return reasonTable;
    }

    public void setReasonTable (String reasonTable)
    {
        this.reasonTable = reasonTable;
    }

    public String getReasonType ()
    {
        return reasonType;
    }

    public void setReasonType (String reasonType)
    {
        this.reasonType = reasonType;
    }

    @Override
    protected void recopy (Kfet obj)
    {
        createdAt = obj.createdAt;
        updatedAt = obj.updatedAt;
        balance = obj.balance;
        reasonId = obj.reasonId;
        reasonTable = obj.reasonTable;
        reasonType = obj.reasonType;
    }

    @Override
    public boolean saveAll ()
    {
        return false;
    }

    @Override
    public boolean save () throws IOException
    {
        return false;
    }

    @Override
    public Kfet loadAll ()
    {
        // Nothing
        return this;
    }

    @Override
    public Kfet loadAll (UrlParametersMap parameters)
    {
        // Nothin
        return this;
    }

    @Override
    public ModelManager<Kfet> getManagerInstance ()
    {
        return new KfetManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        return null;
    }

    @Override
    public String toString ()
    {
        return "Kfet{" + "id=" + getId() + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", balance=" + balance + ", reasonId=" +
                reasonId + ", reasonTable='" + reasonTable + '\'' + ", reasonType='" + reasonType + '\'' + '}';
    }
}
