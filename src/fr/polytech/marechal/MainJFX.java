package fr.polytech.marechal; /**
 * @author Utilisateur
 * @date 06/07/2017
 */

import fr.polytech.marechal.libs.App;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainJFX extends Application
{
    public static void main (String[] args)
    {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage)
    {
        App.start(primaryStage);
    }
}
