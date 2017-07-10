package fr.polytech.marechal.libs.api;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 22/06/2017
 */
public class ApiQuery
{
    private final String completeUrl;
    private final Http httpMethod;
    private HashMap<String, Object> data;
    private UrlParametersMap urlParams;
    private String suffix;

    public ApiQuery (String completeUrl, Http method, HashMap<String, Object> data)
    {
        this.httpMethod = method;
        this.completeUrl = completeUrl;
        this.data = data;
    }

    public ApiResponse execute () throws IOException, ParseException
    {
        String url = completeUrl;
        if (suffix != null && !suffix.isEmpty())
        {
            url += "?" + suffix;
        }
        
        return Api.sendRequest(url, data, httpMethod.toString());
    }

    @Override
    public String toString ()
    {
        return "[" + httpMethod + "] " + completeUrl;
    }

    public ApiQuery build ()
    {
        suffix = "";
        if (urlParams != null)
        {
            suffix = urlParams.toString();
        }

        return this;
    }

    public void setUrlParams (UrlParametersMap urlParams)
    {
        this.urlParams = urlParams;
        this.suffix = urlParams.toString();
    }
}
