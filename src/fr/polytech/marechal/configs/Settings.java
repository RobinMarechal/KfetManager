package fr.polytech.marechal.configs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class Settings
{
    private static int customerMaxDept;

    private static String filePath = "/configs/configs.properties";

    public static int getCustomerMaxDept ()
    {
        return customerMaxDept;
    }

    public static void setCustomerMaxDept (int customerMaxDept)
    {
        Settings.customerMaxDept = customerMaxDept;
    }

    public static void load ()
    {
        Properties p = new Properties();
        try
        {
            String path = new URI(ApiConfig.class.getResource(filePath)
                                                 .toString()).getPath();
            FileInputStream in = new FileInputStream(path);

            p.load(in);
            customerMaxDept = Integer.parseInt(p.getProperty("CUSTOMER_MAX_DEPT"));

            in.close();
            System.out.println("Settings loaded");
        }
        catch (IOException | NullPointerException | URISyntaxException e)
        {
            System.err.println("CONFIG ERROR >>> Settings properties file not found...");
            e.printStackTrace();
        }
    }

    public static void save()
    {
        Properties p = new Properties();
        try
        {
            String path = new URI(ApiConfig.class.getResource(filePath)
                                                 .toString()).getPath();
            FileOutputStream out = new FileOutputStream(path);

            p.setProperty("CUSTOMER_MAX_DEPT", String.valueOf(customerMaxDept));

            p.store(out, null);

            p.store(out, null);
            out.close();
            System.out.println("Settings saved");
        }
        catch (IOException | NullPointerException | URISyntaxException e)
        {
            System.err.println("CONFIG ERROR >>> Settings properties file not found...");
            e.printStackTrace();
        }
    }
}
