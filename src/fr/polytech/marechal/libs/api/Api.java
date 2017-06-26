package fr.polytech.marechal.libs.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Robin
 * @date 19/06/2017
 */
public class Api
{
    private static final String USER_AGENT = "Chrome/58.0.3029.110";

    public static ApiResponse get (String url) throws IOException, ParseException
    {
        return sendRequest(url, null, "GET");
    }

    public static ApiResponse post (String url, HashMap<String, Object> data) throws IOException, ParseException
    {
        return sendRequest(url, data, "POST");
    }

    public static ApiResponse put (String url, HashMap<String, Object> data) throws IOException, ParseException
    {
        return sendRequest(url, data, "PUT");
    }

    public static ApiResponse delete (String url) throws IOException, ParseException
    {
        return sendRequest(url, null, "DELETE");
    }

    public static ApiResponse sendRequest (String url, HashMap<String, Object> data, String httpMethod) throws ParseException, IOException
    {
        URL urlObj = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        // headers
        connection.setRequestMethod(httpMethod);
        connection.setRequestProperty("User-Agent", "java client");
        connection.setInstanceFollowRedirects(false);

        if (!httpMethod.equals("GET") && !httpMethod.equals("DELETE"))
        {
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            JSONObject dataJson = new JSONObject(data);

            wr.writeBytes(dataJson.toJSONString());
            wr.flush();
            wr.close();
        }

        int responseCode = connection.getResponseCode();

        StringBuffer buffer = new StringBuffer();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())))
        {
            String line;

            while ((line = in.readLine()) != null)
            {
                buffer.append(line);
            }
        }

        String jsonStr = buffer.toString();

        if (jsonStr.charAt(0) == '{' || jsonStr.isEmpty())
        {
            jsonStr = "[" + jsonStr + "]";
        }

        JSONParser parser = new JSONParser();
        JSONArray  json   = (JSONArray) parser.parse(jsonStr);

        return new ApiResponse(responseCode, json);
    }

}
