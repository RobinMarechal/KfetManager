package fr.polytech.marechal.libs.ui.managers;

import fr.polytech.marechal.libs.mvc.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Utilisateur on 09/07/2017.
 */
public class ViewsManager
{
    public static ViewController load (@NotNull ViewController view)
    {
        String path = view.getFxmlFileName();
        Pane   root;

        try
        {
            FXMLLoader loader = new FXMLLoader(ViewController.class.getResource(path));
            loader.setController(view);

            root = loader.load();
        }
        catch (Exception e)
        {
            root = new Pane();
            //            view.setTitle("Error");
            System.err.println("Failed to the view of fxml file '" + path + "'...");
            e.printStackTrace();
        }

        view.getChildren().add(root);

        return view;
    }
}
