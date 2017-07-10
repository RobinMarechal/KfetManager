package fr.polytech.marechal.libs.api;


import java.time.LocalDate;
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

    //    public UrlParametersMap (String params) throws ParseException
    //    {
    //        // ?field=value&field2=value&... || field=value&field2=value || field=value
    //        if (!params.matches("\\??([a-zA-Z0-9]+=[a-zA-Z0-9]+&)*([a-zA-Z0-9]+=[a-zA-Z0-9]+)"))
    //        {
    //            throw new ParseException("The parameters' string format is invalid", 0);
    //        }
    //
    //        if (params.charAt(0) == '?')
    //        {
    //            params = params.substring(1);
    //        }
    //
    //        String array[] = params.split("&");
    //
    //        for (String param : array)
    //        {
    //            String split[] = param.split("=");
    //            put(split[0], split[1]);
    //        }
    //    }

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

    public UrlParametersMap setFrom (LocalDate from)
    {
        set("from", from);
        return this;
    }

    public UrlParametersMap setTo (LocalDate to)
    {
        set("to", to);
        return this;
    }

    public UrlParametersMap setDate (LocalDate date)
    {
        setFrom(date);
        setTo(date.plusDays(1));
        return this;
    }

    public UrlParametersMap only (String... fields)
    {
        String fieldsStr = "";
        for (String f : fields)
        {
            fieldsStr += "," + f;
        }

        if (!fieldsStr.isEmpty())
        {
            fieldsStr = fieldsStr.substring(1);
        }

        set("fields", fieldsStr);
        return this;
    }

    public UrlParametersMap where (String field, String operator, Object value)
    {
        String where = "";

        if (containsKey("where"))
        {
            where = get("where").toString() + ";";
        }

        where += field + "," + operator + "," + value.toString();

        set("where", where);
        return this;
    }

    public UrlParametersMap with (String relation)
    {
        String with = "";

        if (containsKey("with"))
        {
            with = get("with").toString() + ",";
        }

        with += relation;

        set("with", with);
        return this;
    }


    public UrlParametersMap withAllRelations ()
    {
        return with("*");
    }

    public UrlParametersMap setRelations (String... relations)
    {
        for (String r : relations)
        {
            with(r);
        }
        return this;
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
