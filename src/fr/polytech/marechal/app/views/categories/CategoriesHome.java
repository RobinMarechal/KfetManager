package fr.polytech.marechal.app.views.categories;

import fr.polytech.marechal.libs.mvc.views.ViewController;
import javafx.scene.control.Label;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class CategoriesHome extends ViewController
{

    public CategoriesHome()
    {
        getChildren().add(new Label("Categories Home"));
    }

    @Override
    public void initialize ()
    {

    }
}
