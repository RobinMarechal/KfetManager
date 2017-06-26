package fr.polytech.marechal.libs.api;


import java.text.ParseException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 26/06/2017
 */
public class UrlParametersMap extends HashMap<String, Object>
{

    public UrlParametersMap ()
    {

    }

    public UrlParametersMap (String params) throws ParseException
    {
        // ?field=value&field2=value&... || field=value&field2=value || field=value
        if (!params.matches("\\??([a-zA-Z0-9]+=[a-zA-Z0-9]+&)*([a-zA-Z0-9]+=[a-zA-Z0-9]+)"))
        {
            throw new ParseException("The parameters' string format is invalid", 0);
        }

        if (params.charAt(0) == '?')
        {
            params = params.substring(1);
        }

        String array[] = params.split("&");

        for (String param : array)
        {
            String split[] = param.split("=");
            put(split[0], split[1]);
        }
    }

    public UrlParametersMap setOrderBy (String field)
    {
        set("orderby", field);
        return this;
    }


    public UrlParametersMap setOrderBy (String field, OrderBy order)
    {
        setOrderBy(field);
        setOrder(order);
        return this;
    }

    public UrlParametersMap setOrder (OrderBy order)
    {
        set("order", order);
        return this;
    }

    public UrlParametersMap setLimit (int limit, int offset)
    {
        setLimit(limit);
        setOffset(offset);
        return this;
    }

    public UrlParametersMap setLimit (int limit)
    {
        set("limit", limit);
        return this;
    }

    public UrlParametersMap setOffset (int offset)
    {
        set("offset", offset);
        return this;
    }

    public UrlParametersMap setRelations (String relations) throws ParseException
    {
        if (relations.isEmpty())
        {
            return this;
        }

        // rel1,rel2,...,reln
        if (!relations.matches("([.a-zA-Z0-9]+,)*([.a-zA-Z0-9]+)"))
        {
            throw new ParseException("The parameter value of 'with' format is invalid", 0);
        }


        set("with", relations);

        return this;
    }

    public UrlParametersMap setRelations (String... relations)
    {
        String str = "";
        for (String r : relations)
        {
            str += r + ",";
        }

        if (!str.isEmpty())
        {
            str = str.substring(0, str.length() - 1);
        }

        try
        {
            return setRelations(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public UrlParametersMap set (String param, Object value)
    {
        put(param, value);
        return this;
    }

    @Override
    public String toString ()
    {
        if (isEmpty())
        {
            return "";
        }

        String str = "";

        for (Entry<String, Object> entry : this.entrySet())
        {
            str += entry.getKey() + "=" + entry.getValue()
                                               .toString() + "&";
        }

        return str.substring(0, str.length() - 1);
    }
}
