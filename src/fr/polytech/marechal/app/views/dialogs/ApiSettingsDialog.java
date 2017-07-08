package fr.polytech.marechal.app.views.dialogs;

import fr.polytech.marecal.FieldValueType;
import fr.polytech.marecal.FormMap;
import fr.polytech.marechal.app.controllers.SettingsController;
import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.libs.ui.custom.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class ApiSettingsDialog extends Dialog
{
    private FormMap form;

    @FXML private TextField fieldUrl;
    @FXML private TextField fieldKey;
    @FXML private Button submit;
    @FXML private Button reset;

    @Override
    public void initialize ()
    {
        setTitle("Modifier les paramètres de l'API");

        form = new FormMap();
        form.add("api_url", FieldValueType.URL, fieldUrl, true);
        form.add("app_key", FieldValueType.UNDEFINED, fieldKey, true);
        form.setSubmitButton(submit);

        submit.setOnAction(event -> save());
        reset.setOnAction(event -> reset());

        reset();
    }

    public void save ()
    {
        new SettingsController().saveApiConfigs(fieldUrl.getText(), fieldKey.getText());
        this.close();
    }

    public void reset ()
    {
        fieldUrl.setText(ApiConfig.getApiUrl());
        fieldKey.setText(ApiConfig.getAppKey());
    }
}
