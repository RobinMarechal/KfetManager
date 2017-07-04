package fr.polytech.marechal.libs;

/**
 * @author Robin
 * @date 14/06/2017
 */
public class Helpers
{
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

    public static String plural (String str)
    {
        if (str.endsWith("y"))
        {
            str = str.substring(0, str.length() - 1) + "ies";
        }
        else
        {
            str += "s";
        }

        return str;
    }

    public static String getManagerOfModel (String modelName) // path/of/the/class
    {
        String modelsPackage  = modelName.substring(0, modelName.lastIndexOf('.'));
        String modelClassName = modelName.substring(modelName.lastIndexOf('.') + 1);

        modelClassName = plural(modelClassName);
        String managerClassname = modelClassName + "Manager";

        String managerPath = modelsPackage + ".managers." + managerClassname;

        return managerPath;
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
