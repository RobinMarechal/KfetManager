package fr.polytech.marechal.libs.api;

import fr.polytech.marechal.libs.mvc.models.ModelFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Robin
 * @date 22/06/2017
 */
public class ApiQueryBuilder
{
    private String url;
    private int limit = -1;
    private int offset = -1;
    private String orderBy;
    private String order;
    private List<String> relations = new ArrayList<>();

    private ApiQuery query;

    private Http httpMethod;
    private HashMap<String, Object> data;

    public ApiQueryBuilder (String url)
    {
        this(url, Http.GET);
    }

    public ApiQueryBuilder (String url, Http httpMethod)
    {
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public static <T extends ModelFactory> ApiQueryBuilder forModelFactory (T modelFactory)
    {
        return forModelFactory(modelFactory, Http.GET);
    }

    public static <T extends ModelFactory> ApiQueryBuilder forModelFactory (T modelFactory, Http httpMethod)
    {
        String url = modelFactory.getBaseUrl();
        return create(url, httpMethod);
    }

    public static ApiQueryBuilder create (String url)
    {
        return new ApiQueryBuilder(url);
    }

    public static ApiQueryBuilder create (String url, Http httpMethod)
    {
        return new ApiQueryBuilder(url, httpMethod);
    }

    public ApiQueryBuilder setHttpMethod (Http method)
    {
        this.httpMethod = method;
        return this;
    }

    public ApiQueryBuilder setData (HashMap<String, Object> data)
    {
        this.data = data;
        return this;
    }

    public ApiQueryBuilder limit (int limit)
    {
        return limit(limit, 0);
    }

    public ApiQueryBuilder limit (int limit, int offset)
    {
        this.limit = limit;
        this.offset = offset;
        return this;
    }

    public ApiQueryBuilder orderBy (String field)
    {
        return orderBy(field, "ASC");
    }

    public ApiQueryBuilder orderBy (String field, String order)
    {
        this.orderBy = field;
        this.order = order;
        return this;
    }

    @SafeVarargs
    public final ApiQueryBuilder with (String... relations) throws ReflectiveOperationException
    {
        return relations(relations);
    }

    @SafeVarargs
    public final ApiQueryBuilder relations (String... relations) throws ReflectiveOperationException
    {
        for (String r : relations)
        {
            if (!this.relations.contains(r))
            {
                this.relations.add(r);
            }
        }

        return this;
    }

    public ApiQuery buildQuery ()
    {
        String url = this.url;

        if (url.charAt(url.length() - 1) == '/')
        {
            url = url.substring(0, url.length() - 1);
        }

        if (orderBy == null && limit == -1 && relations.isEmpty())
        {
            this.query = new ApiQuery(url, httpMethod);
            return this.query;
        }

        url += "?";

        if (orderBy != null)
        {
            url += "orderby=" + orderBy + "&";
            if (order != null)
            {
                url += "order=" + order + "&";
            }
        }

        if (limit != -1)
        {
            url += "limit=" + limit + "&";
            if (offset != -1)
            {
                url += "offset=" + offset + "&";
            }
        }

        if (!relations.isEmpty())
        {
            String with = "with=";
            for (String r : relations)
            {
                with += r + ",";
            }

            with = with.substring(0, with.length() - 1);
            url += with;
        }


        if (url.charAt(url.length() - 1) == '&')
        {
            url = url.substring(0, url.length() - 1);
        }

        this.query = new ApiQuery(url, httpMethod);
        return this.query;
    }

    public ApiQuery getQuery ()
    {
        if (query == null)
        {
            buildQuery();
        }

        return query;
    }

    @Override
    public String toString ()
    {
        return query.toString();
    }

    public ApiQueryBuilder setUrlParams (UrlParametersMap m)
    {
        limit = (int) m.getOrDefault("limit", -1);
        offset = (int) m.getOrDefault("offset", -1);

        orderBy = m.containsKey("orderby") ? m.get("orderby")
                                              .toString() : null;
        order = m.containsKey("order") ? m.getOrDefault("order", OrderBy.ASC)
                                          .toString() : null;

        if (m.containsKey("with"))
        {
            String with = m.get("with")
                           .toString();
            String[] split = with.split(",");
            relations.addAll(Arrays.asList(split));
        }

        return this;
    }
}
