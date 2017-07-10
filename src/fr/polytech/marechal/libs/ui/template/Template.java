package fr.polytech.marechal.libs.ui.template;

import fr.polytech.marechal.app.controllers.*;
import fr.polytech.marechal.libs.ui.template.navbar.NavbarItem;
import fr.polytech.marechal.libs.ui.template.navbar.NavbarItemList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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

    public final static int CONTENT_WIDTH = 1200;
    public final static int NAVBAR_WIDTH = 230;
    public final static int HEIGHT = 700;

    private Template ()
    {
        super(new Pane());

        layout = new BorderPane();
        layout.setPrefWidth(CONTENT_WIDTH + NAVBAR_WIDTH);
        layout.setPrefHeight(HEIGHT + 18.33);
        layout.getStyleClass()
              .add("bg-almost-white");
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
        navbar.setPrefWidth(NAVBAR_WIDTH);
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

        navbar.getStyleClass()
              .add("bg-almost-white");

        layout.setLeft(navbar);
    }

    private void displayContentPane ()
    {
        contentPane = new ScrollPane();
        layout.setCenter(contentPane);

        contentPane.setFitToWidth(true);

        Pane p = new Pane();
        p.getStyleClass()
         .add("bg-almost-white");
        setView(p);
    }

    private void displayMenubar ()
    {
        SettingsController menubarController = new SettingsController();

        MenuItem addOrder = new MenuItem("Une commande");

        MenuItem prefs = new MenuItem("Préférences");
        prefs.setOnAction(event -> menubarController.openSettingsDialog());
        MenuItem paramAPI = new MenuItem("API");
        paramAPI.setOnAction(event -> menubarController.openApiSettingsDialog());

        Menu menuAdd    = new Menu("Ajouter...");
        Menu menuParams = new Menu("Paramètres...");

        menuAdd.getItems()
               .add(addOrder);

        menuParams.getItems()
                  .addAll(prefs, paramAPI);

        menubar = new MenuBar(menuAdd, menuParams);

        layout.setTop(menubar);
    }

    public void setView (Pane view)
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

    public void selectFirstNavbarItem ()
    {
        setSelectedNavbarItem(navbarItems.get(0));
    }
}
