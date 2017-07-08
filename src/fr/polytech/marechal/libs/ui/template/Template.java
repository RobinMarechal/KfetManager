package fr.polytech.marechal.libs.ui.template;

import fr.polytech.marechal.app.controllers.*;
import fr.polytech.marechal.app.views.kfet.KfetHome;
import fr.polytech.marechal.libs.mvc.views.ViewController;
import fr.polytech.marechal.libs.ui.template.navbar.NavbarItem;
import fr.polytech.marechal.libs.ui.template.navbar.NavbarItemList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class Template extends Scene
{
    private static Template instance = new Template();

    public static Template getInstance ()
    {
        return instance;
    }

    private BorderPane layout;

    private VBox navbar;
    private ScrollPane contentPane;
    private MenuBar menubar;

    private NavbarItemList navbarItems;
    private NavbarItem selectedNavbarItem;

    private Template ()
    {
        super(new Pane());

        layout = new BorderPane();
        layout.setPrefWidth(1400);
        layout.setPrefHeight(700);
        this.setRoot(layout);

        displayNavbar();
        displayContentPane();
        displayMenubar();

        URL jbootx = Template.class.getResource("/css/jbootx.css");
        if (jbootx != null)
        {
            getStylesheets().add(jbootx.toString());
        }

        URL style = Template.class.getResource("/css/style.css");
        if (style != null)
        {
            getStylesheets().add(style.toString());
        }
    }

    private void displayNavbar ()
    {
        navbar = new VBox();
        navbar.setPrefWidth(230);
        navbar.getStyleClass()
              .add("navbar");

        navbarItems = new NavbarItemList();

        navbarItems.add(new NavbarItem("Accueil", new KfetController()));
        navbarItems.add(new NavbarItem("Catégories", new CategoriesController()));
        navbarItems.add(new NavbarItem("Achats de la Kfet", new PurchasesController()));
        navbarItems.add(new NavbarItem("Ajouts d'argent", new MoneyAddingsController()));
        navbarItems.add(new NavbarItem("Clients", new CustomersController()));
        navbarItems.add(new NavbarItem("Commandes", new OrdersController()));
        navbarItems.add(new NavbarItem("Événements", new EventsController()));
        navbarItems.add(new NavbarItem("Menus", new MenusController()));
        //        navbarItems.add(new NavbarItem("Sous-Catégories"));
        navbarItems.add(new NavbarItem("Staff", new StaffController()));
        navbarItems.add(new NavbarItem("Produits", new ProductsController()));
        navbarItems.add(new NavbarItem("Réapprovisionnements", new RestockingsController()));

        navbar.getChildren()
              .addAll(navbarItems);

        navbar.getStyleClass().add("bg-almost-white");

        layout.setLeft(navbar);
    }

    private void displayContentPane ()
    {
        contentPane = new ScrollPane();
        layout.setCenter(contentPane);

        setView(new KfetHome());
        setSelectedNavbarItem(navbarItems.get(0));
    }

    private void displayMenubar ()
    {
        SettingsController menubarController = new SettingsController();

        MenuItem saveOrder = new MenuItem("Une commande");

        MenuItem prefs = new MenuItem("Préférences");
        prefs.setOnAction(event -> menubarController.openSettingsDialog());
        MenuItem paramAPI = new MenuItem("API");
        paramAPI.setOnAction(event -> menubarController.openApiSettingsDialog());

        Menu menuSave   = new Menu("Enregistrer...");
        Menu menuParams = new Menu("Paramètres...");

        menuSave.getItems()
                .add(saveOrder);

        menuParams.getItems()
                  .addAll(prefs, paramAPI);

        menubar = new MenuBar(menuSave, menuParams);

        layout.setTop(menubar);
    }

    public void setView (ViewController view)
    {
        contentPane.setContent(view);
    }

    public void setSelectedNavbarItem (@NotNull NavbarItem selectedNavbarItem)
    {
        if (this.selectedNavbarItem != null)
        {
            this.selectedNavbarItem.getStyleClass()
                                   .remove("navbar-item-selected");
        }

        this.selectedNavbarItem = selectedNavbarItem;
        this.selectedNavbarItem.getStyleClass()
                               .add("navbar-item-selected");
    }

    public NavbarItem getSelectedNavbarItem ()
    {
        return selectedNavbarItem;
    }
}
