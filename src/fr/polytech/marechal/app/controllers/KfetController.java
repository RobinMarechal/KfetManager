package fr.polytech.marechal.app.controllers;

import fr.polytech.marechal.app.models.*;
import fr.polytech.marechal.app.models.managers.*;
import fr.polytech.marechal.app.views.kfet.KfetHome;
import fr.polytech.marechal.app.views.kfet.KfetLowStocks;
import fr.polytech.marechal.app.views.kfet.KfetSummary;
import fr.polytech.marechal.configs.Settings;
import fr.polytech.marechal.libs.api.ApiQueryBuilder;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.controllers.Controller;
import fr.polytech.marechal.libs.mvc.views.ViewController;
import fr.polytech.marechal.libs.ui.managers.ViewsManager;
import fr.polytech.marechal.libs.ui.template.Template;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class KfetController extends Controller
{
    @Override
    public void home ()
    {
        double                   balance = Double.MIN_VALUE;
        Kfet                     last    = new KfetManager().getLast();
        HashMap<String, Integer> summary = new HashMap<>();

        if (last != null)
        {
            balance = last.getBalance();
        }

        ArrayList<Order> orders = new ApiQueryBuilder<Order>(new OrdersManager()).setUrlParams(new UrlParametersMap().setDate(LocalDate.now()))
                                                                                 .executeQuery();
        if (orders != null)
        {
            summary.put("sales", orders.size());
        }


        UrlParametersMap params = new UrlParametersMap().only("id");

        ArrayList<Menu> menus = new MenusManager().all(params);
        if (orders != null)
        {
            summary.put("menus", menus.size());
        }


        ArrayList<Category> categories = new CategoriesManager().all(params);
        if (categories != null)
        {
            summary.put("categories", categories.size());
        }


        ArrayList<Subcategory> subcategories = new SubcategoriesManager().all(params);
        if (subcategories != null)
        {
            summary.put("subcategories", subcategories.size());
        }


        ArrayList<Product> products = new ProductsManager().all(params);
        if (products != null)
        {
            summary.put("products", products.size());
        }


        ArrayList<Customer> customers = new CustomersManager().all(params);
        if (customers != null)
        {
            summary.put("customers", customers.size());
        }


        ArrayList<Staff> staffMembers = new StaffsManager().all(params);
        if (staffMembers != null)
        {
            summary.put("staff", staffMembers.size());
        }


        ArrayList<Product> lowStockProducts = new ProductsManager().all(new UrlParametersMap().only("name", "quantity")
                                                                                              .where("quantity", "<=", Settings
                                                                                                      .getCriticalStockValue()));
        Restocking lastRestocking = new RestockingsManager().getLast(new UrlParametersMap().only("date", "cost"));


        ViewController lowStocksView = ViewsManager.load(new KfetLowStocks(lowStockProducts, lastRestocking));
        ViewController summaryView   = ViewsManager.load(new KfetSummary(summary));
        KfetHome       view          = (KfetHome) ViewsManager.load(new KfetHome(balance, summaryView, lowStocksView));

        Template.getInstance()
                .setView(view);
    }
}
