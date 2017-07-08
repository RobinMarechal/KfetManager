package fr.polytech.marechal.app.controllers;

import fr.polytech.marechal.app.views.categories.CategoriesHome;
import fr.polytech.marechal.libs.mvc.controllers.Controller;
import fr.polytech.marechal.libs.ui.template.Template;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class CategoriesController extends Controller
{
    @Override
    public void home ()
    {
        Template.getInstance().setView(new CategoriesHome());
    }
}
