package fr.polytech.marechal.libs;

import fr.polytech.marecal.validator.FormValidator;
import fr.polytech.marecal.validator.InvalidationReason;
import fr.polytech.marecal.validator.InvalidationReasonMessageMap;
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
    public static void loadConfigs()
    {
        ApiConfig.load();
        Settings.load();
    }

    public static void start (Stage window)
    {
        App.loadConfigs();

        window.setScene(Template.getInstance());
        window.setResizable(false);
        window.show();
        window.setTitle("KfetManager");
        window.setOnCloseRequest(event -> App.closeApplication());

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
