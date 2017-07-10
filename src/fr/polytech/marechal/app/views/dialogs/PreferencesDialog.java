package fr.polytech.marechal.app.views.dialogs;

import fr.polytech.marecal.FieldValueType;
import fr.polytech.marecal.FormMap;
import fr.polytech.marechal.app.controllers.SettingsController;
import fr.polytech.marechal.configs.Settings;
import fr.polytech.marechal.libs.ui.custom.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Utilisateur on 08/07/2017.
 */
public class PreferencesDialog extends Dialog
{
    private FormMap form;

    @FXML private TextField fieldDept;
    @FXML private TextField fieldLowStockValue;
    @FXML private Button submit;
    @FXML private Button reset;

    @Override
    public void initialize ()
    {
        setTitle("Modifier les préférences");

        form = new FormMap();
        form.add("max_dept", FieldValueType.NUMBERS_DOUBLE_UNSIGNED, fieldDept, true);
        form.add("low_stock_value", FieldValueType.NUMBERS_INT_UNSIGNED, fieldLowStockValue, true);
        form.setSubmitButton(submit);

        submit.setOnAction(event -> save());
        reset.setOnAction(event -> reset());

        reset();
    }

    public void save ()
    {
        new SettingsController().saveSettings(Double.parseDouble(fieldDept.getText()), Integer.parseInt(fieldLowStockValue.getText()));
        this.close();
    }

    public void reset ()
    {
        fieldDept.setText(Settings.getCustomerMaxDept() + "");
        fieldLowStockValue.setText(Settings.getCriticalStockValue() + "");
    }
}
