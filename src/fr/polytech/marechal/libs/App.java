package fr.polytech.marechal.libs;

import fr.polytech.marecal.validator.FormValidator;
import fr.polytech.marecal.validator.InvalidationReason;
import fr.polytech.marecal.validator.InvalidationReasonMessageMap;
import fr.polytech.marechal.app.controllers.KfetController;
import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.configs.Settings;
import fr.polytech.marechal.libs.ui.template.Template;
import javafx.stage.Stage;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class App
{
    public static void loadConfigs ()
    {
        ApiConfig.load();
        Settings.load();
    }

    public static void start (Stage window)
    {
        App.loadConfigs();

        //      95
        //  3       3
        //      3
        window.setResizable(false);
        window.setTitle("KfetManager");
        window.setOnCloseRequest(event -> App.closeApplication());
        window.setScene(Template.getInstance());
        window.show();

        window.centerOnScreen();

        new KfetController().home();
        Template.getInstance()
                .selectFirstNavbarItem();

        InvalidationReasonMessageMap reasons = FormValidator.getInvalidationReasonMessageMap();
        reasons.setMessage(InvalidationReason.UNKNOWN, "Erreur inconnue");
        reasons.setMessage(InvalidationReason.REQUIRED_FIELD, "Le champs est requis, il ne devrait pas être vide");
        reasons.setMessage(InvalidationReason.INCORRECT_VALUE, "La valeur du champs est incorrecte");
    }

    public static void closeApplication ()
    {
        ApiConfig.save();
        Settings.save();

        System.exit(0);
    }
}
