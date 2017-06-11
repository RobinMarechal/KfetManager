package fr.polytech.marechal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Robin
 * @date 10/06/2017
 */
public class Main
{
    public static void main (String[] args)
    {
        try
        {
            Connection driver = DriverManager.getConnection("jdbc:mysql://localhost/kfetmanager", "root", "");

            PreparedStatement statement = driver.prepareStatement("INSERT INTO categories (name) VALUES('def'),  ('ghi')");
            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
