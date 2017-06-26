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

    public ApiQuery (String completeUrl, Http method)
    {
        this.httpMethod = method;
        this.completeUrl = completeUrl;
    }

    public ApiResponse execute () throws IOException, ParseException
    {
        return Api.sendRequest(completeUrl, data, httpMethod.toString());
    }

    @Override
    public String toString ()
    {
        return "[" + httpMethod + "] " + completeUrl;
    }
}
