package fr.polytech.marechal.app.views.kfet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.HashMap;

/**
 * Created by Utilisateur on 09/07/2017.
 */
public class KfetSummary extends KfetViewController
{
    @FXML private Label sales;
    @FXML private Label menus;
    @FXML private Label categories;
    @FXML private Label subcategories;
    @FXML private Label products;
    @FXML private Label customers;
    @FXML private Label staffMembers;

    private final HashMap<String, Integer> summary;

    public KfetSummary (HashMap<String, Integer> summary)
    {
        this.summary = summary;
    }

    @Override
    public void initialize ()
    {
        sales.setText(summary.getOrDefault("sales", 0) + "");
        menus.setText(summary.getOrDefault("menus", 0) + "");
        categories.setText(summary.getOrDefault("categories", 0) + "");
        subcategories.setText(summary.getOrDefault("subcategories", 0) + "");
        products.setText(summary.getOrDefault("products", 0) + "");
        customers.setText(summary.getOrDefault("customers", 0) + "");
        staffMembers.setText(summary.getOrDefault("staffMembers", 0) + "");
    }
}
