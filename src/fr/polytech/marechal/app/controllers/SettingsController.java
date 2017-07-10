package fr.polytech.marechal.app.controllers;

import fr.polytech.marechal.app.views.dialogs.ApiSettingsDialog;
import fr.polytech.marechal.app.views.dialogs.PreferencesDialog;
import fr.polytech.marechal.configs.ApiConfig;
import fr.polytech.marechal.configs.Settings;
import fr.polytech.marechal.libs.mvc.controllers.Controller;
import fr.polytech.marechal.libs.ui.managers.DialogsManager;
import fr.polytech.marechal.libs.ui.custom.Dialog;
import org.jetbrains.annotations.NotNull;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class SettingsController extends Controller
{
    @Override
    public void home ()
    {
    }

    public void openSettingsDialog ()
    {
        Dialog dialog = DialogsManager.load(new PreferencesDialog());
        dialog.show();
    }

    public void openApiSettingsDialog ()
    {
        Dialog dialog = DialogsManager.load(new ApiSettingsDialog());
        dialog.show();
    }


    public boolean saveApiConfigs (@NotNull String apiUrl, @NotNull String appKey)
    {
        ApiConfig.setApiUrl(apiUrl);
        ApiConfig.setAppKey(appKey);
        return true;
    }

    public boolean saveSettings (double maxDept, int lowStockValue)
    {
        Settings.setCustomerMaxDept(maxDept);
        return true;
    }
}
