package configs;

/**
 * @author Robin
 * @date 22/06/2017
 */
public class ApiConfig
{
    private static String apiUrl = "http://kfetmanager.api/api/";

    public static String getApiUrl ()
    {
        return apiUrl;
    }

    public static void setApiUrl (String apiUrl)
    {
        ApiConfig.apiUrl = apiUrl;
    }
}
