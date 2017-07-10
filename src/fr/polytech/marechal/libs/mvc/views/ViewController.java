package fr.polytech.marechal.libs.mvc.views;

import fr.polytech.marechal.libs.Helpers;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Utilisateur
 * @date 04/07/2017
 */
public abstract class ViewController extends Pane implements Initializable
{
    private String fxmlFileName = null;

    public ViewController ()
    {
        String partials[] = this.getClass()
                                .getName()
                                .split("\\.");
        String pathPrefix = "/fxml";

        String path = "";
        path += pathPrefix + "/";
        path += Helpers.snakeCase(partials[partials.length - 2]); // package
        path += "/" + Helpers.snakeCase(partials[partials.length - 1]); // filename
        path += ".fxml";

        this.fxmlFileName = path;
    }

    public String getFxmlFileName ()
    {
        return fxmlFileName;
    }

    public void setFxmlFileName (String fxmlFileName)
    {
        this.fxmlFileName = fxmlFileName;
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
        initialize();
    }

    public abstract void initialize ();
}
