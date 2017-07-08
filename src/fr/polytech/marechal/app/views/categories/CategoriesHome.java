package fr.polytech.marechal.app.views.categories;

import fr.polytech.marechal.libs.mvc.views.ViewController;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class CategoriesHome extends ViewController
{

    public CategoriesHome()
    {
        getChildren().add(new Label("Categories Home"));
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {

    }
}
