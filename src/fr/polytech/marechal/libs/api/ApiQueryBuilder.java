package fr.polytech.marechal.libs.api;

import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 22/06/2017
 */
public class ApiQueryBuilder<T extends Model>
{
    private String url;

    private ModelManager<T> modelManager;

    private ApiQuery query;

    private Http httpMethod;
    private HashMap<String, Object> data;
    private UrlParametersMap urlParams;

    public ApiQueryBuilder (ModelManager modelManager)
    {
        this(modelManager.getBaseUrl());
        this.modelManager = modelManager;
    }

    public ApiQueryBuilder (String url)
    {
        this(url, Http.GET);
    }

    public ApiQueryBuilder (String url, Http httpMethod)
    {
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public ApiQueryBuilder setModelManager (ModelManager<T> modelManager)
    {
        this.modelManager = modelManager;
        return this;
    }

    public static ApiQueryBuilder create (String url)
    {
        return new ApiQueryBuilder(url);
    }

    public static ApiQueryBuilder create (String url, Http httpMethod)
    {
        return new ApiQueryBuilder(url, httpMethod);
    }

    public ApiQueryBuilder atUrl (String url)
    {
        return atUrl(url, Http.GET);
    }

    public ApiQueryBuilder atUrl (String url, Http httpMethod)
    {
        this.url = url;
        this.httpMethod = httpMethod;
        return this;
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

    public ApiQuery buildQuery ()
    {
        this.query = new ApiQuery(url, httpMethod, data);
        query.setUrlParams(urlParams);
        return query;
    }

    public ApiQuery getQuery ()
    {
        if (query == null)
        {
            buildQuery();
        }

        return query;
    }

    public ArrayList<T> executeQuery ()
    {
        try
        {
            JSONArray json = this.getQuery()
                                 .execute()
                                 .getJson();

            ArrayList<T> list = new ArrayList<>();
            for (Object obj : json)
            {
                try
                {
                    list.add(modelManager.buildFromJson(((JSONObject) obj)));
                }
                catch (ReflectiveOperationException e)
                {
                    list.add(null);
                    e.printStackTrace();
                }
            }

            return list;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString ()
    {
        return query.toString();
    }

    public ApiQueryBuilder setUrlParams (UrlParametersMap urlParams)
    {
        this.urlParams = urlParams;
        return this;
    }
}
