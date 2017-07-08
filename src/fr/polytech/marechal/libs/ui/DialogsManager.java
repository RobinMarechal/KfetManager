package fr.polytech.marechal.libs.ui;

import fr.polytech.marechal.libs.ui.custom.Dialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

/**
 * @author Utilisateur
 * @date 07/07/2017
 */
public class DialogsManager
{
    public static Dialog load (@NotNull Dialog dialog)
    {
        String path = "/fxml/dialogs/" + dialog.getFxmlFileName();
        Pane   root;

        try
        {
            FXMLLoader loader = new FXMLLoader(DialogsManager.class.getResource(path));
            loader.setController(dialog);

            root = loader.load();
        }
        catch (Exception e)
        {
            root = new VBox();
//            dialog.setTitle("Error");
            System.err.println("Failed to the dialog of fxml file '" + path + "'...");
            e.printStackTrace();
        }

        dialog.setContent(root);

        return dialog;
    }

}
