package fr.polytech.marechal.app.controllers;

import fr.polytech.marechal.app.views.kfet.KfetHome;
import fr.polytech.marechal.libs.mvc.controllers.Controller;
import fr.polytech.marechal.libs.ui.template.Template;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class KfetController extends Controller
{
    @Override
    public void home ()
    {
        KfetHome view = new KfetHome();
        Template.getInstance().setView(view);
    }
}
