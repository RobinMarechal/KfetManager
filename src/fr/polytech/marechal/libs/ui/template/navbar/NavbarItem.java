package fr.polytech.marechal.libs.ui.template.navbar;

import fr.polytech.marechal.libs.mvc.controllers.Controller;
import fr.polytech.marechal.libs.ui.template.Template;
import javafx.scene.control.Button;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class NavbarItem extends Button
{
    private Controller controller;

    /**
     * Creates a button with an empty string for its label.
     */
    public NavbarItem (String text)
    {
        this(text, null);
    }

    /**
     * Creates a button with the specified text as its label.
     *
     * @param text A text string for its label.
     */
    public NavbarItem (String text, Controller controller)
    {
        super(text);
        this.controller = controller;

        setMaxWidth(Double.MAX_VALUE);

        getStyleClass().add("nav-btn");
        prepareClickAction();
    }

    public Controller getController ()
    {
        return controller;
    }

    public void setController (Controller controller)
    {
        this.controller = controller;
        prepareClickAction();
    }

    private void prepareClickAction ()
    {
        setOnAction(event ->
        {
            if (Template.getInstance()
                        .getSelectedNavbarItem() != this)
            {
                Template.getInstance()
                        .setSelectedNavbarItem(this);

                if (controller != null)
                {
                    controller.home();
                    System.out.println("Controller '" + controller.getClass()
                                                                  .getSimpleName() + "'");
                }
                else
                {
                    System.out.println("No controller defined...");
                }
            }
        });
    }
}
