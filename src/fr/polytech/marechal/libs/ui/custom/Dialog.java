package fr.polytech.marechal.libs.ui.custom;

import fr.polytech.marechal.libs.Helpers;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public abstract class Dialog extends Stage implements Initializable
{
    private String fxmlFileName = null;

    /**
     * Default constructor
     */
    public Dialog ()
    {
//        initStyle(StageStyle.UTILITY);
        setResizable(false);
        prepareDialogEventListeners();
    }

    public String getFxmlFileName ()
    {
        if (fxmlFileName != null)
        {
            return fxmlFileName;
        }

        String className = this.getClass()
                               .getSimpleName();
        String dialogName = className.endsWith("Dialog") ? className.replace("Dialog", "") : className;
        String filename   = Helpers.snakeCase(dialogName);

        return filename + ".fxml";
    }

    /**
     * Prepare dialog events listener like close on espace pressed, or close on focus loss
     */
    public void prepareDialogEventListeners ()
    {
        addEventFilter(KeyEvent.KEY_RELEASED, event ->
        {
            if (event.getCode() == KeyCode.ESCAPE)
            {
                close();
            }
        });

//        focusedProperty().addListener((observable, oldValue, newValue) ->
//        {
//            if (!newValue)
//            {
//                close();
//            }
//        });
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

    /**
     * set the content of the dialog
     *
     * @param content the inside pane
     */
    public void setContent (Pane content)
    {
        setScene(new Scene(content));
    }

    public abstract void initialize ();

    public void setFxmlFileName (String fxmlFileName)
    {
        this.fxmlFileName = fxmlFileName;
    }
}
