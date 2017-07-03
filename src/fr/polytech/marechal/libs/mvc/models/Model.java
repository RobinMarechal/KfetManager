package fr.polytech.marechal.libs.mvc.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 12/06/2017
 */
public abstract class Model<T extends Model> implements Comparable<Model<T>>
{
    protected int id;

    @Override
    public int compareTo (@NotNull Model<T> model)
    {
        return new Integer(id).compareTo(new Integer(model.id));
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }


    protected abstract void recopy (T obj);

    // ABSTRACT

    public boolean existsInDatabase ()
    {
        if (getId() < 1)
        {
            return false;
        }

        Model m = getManagerInstance().find(getId());

        return m != null;
    }

    public abstract boolean save ();

    public boolean saveWithoutRelations () throws IOException
    {
        Model tmp;

        if (existsInDatabase()) // PUT
        {
            tmp = getManagerInstance().update(this);
        }
        else // POST
        {
            tmp = getManagerInstance().create(this);
        }

        if(tmp == null)
            return false;

        setId(tmp.getId());
        return true;
    }

    public boolean delete () throws IOException
    {
        return getManagerInstance().delete(this);
    }

    public abstract T loadAll ();

    public abstract T loadAll (UrlParametersMap parameters);

    public abstract ModelManager<T> getManagerInstance ();

    public abstract HashMap<String, Object> toHashMap ();
}
