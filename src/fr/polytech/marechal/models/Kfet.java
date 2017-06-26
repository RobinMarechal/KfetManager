package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Kfet extends Model<Kfet>
{
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double balance;
    private int reasonId;
    private String reasonTable;
    private String reasonType;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

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
        return "Kfet{" + "id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", balance=" + balance + ", reasonId=" +
                reasonId + ", reasonTable='" + reasonTable + '\'' + ", reasonType='" + reasonType + '\'' + '}';
    }
}
