package fr.polytech.marechal.libs;

import fr.polytech.marechal.libs.database.query.results.QueryResultList;

import java.sql.SQLException;

/**
 * @author Robin
 * @date 14/06/2017
 */
public class Helpers
{
    public static int numbersOfResultFromQuery (QueryResultList qrl) throws SQLException
    {
        return qrl.size();
    }

    public static String snakeCaseToCamelCase (String s)
    {
        String res = "";

        String[] split = s.split("_");

        res += split[0];
        for (int i = 1; i < split.length; i++)
        {
            res += split[i].substring(0, 1)
                           .toUpperCase() + split[i].substring(1);
        }

        return res;
    }

    public static String getFactoryOfModel (String modelName) // path/of/the/class
    {
        String modelsPackage    = modelName.substring(0, modelName.lastIndexOf('.'));
        String modelClassName   = modelName.substring(modelName.lastIndexOf('.') + 1);
        String factoryClassName = modelClassName + "Factory";

        String factoryPath = modelsPackage + ".factories." + factoryClassName;

        return factoryPath;
    }

    public static String getAddingMethodName (String str)
    {
        str = "add_" + str;
        str = snakeCaseToCamelCase(str);

        if (str.endsWith("ies"))
        {
            String beg = str.split("ies")[0];
            return beg + "y";
        }

        return str.substring(0, str.length() - 1);
    }
}
