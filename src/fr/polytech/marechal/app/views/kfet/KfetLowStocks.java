package fr.polytech.marechal.app.views.kfet;

import fr.polytech.marechal.app.models.Product;
import fr.polytech.marechal.app.models.Restocking;
import fr.polytech.marechal.libs.Helpers;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Utilisateur on 09/07/2017.
 */
public class KfetLowStocks extends KfetViewController
{
    private final ArrayList<Product> products;
    private final Restocking lastRestocking;

    @FXML private TableView<Product> table;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, Integer> colStock;

    @FXML private Label restockingDate;
    @FXML private Label restockingCost;

    public KfetLowStocks (ArrayList<Product> products, Restocking lastRestocking)
    {
        this.products = products;
        this.lastRestocking = lastRestocking;
    }

    @Override
    public void initialize ()
    {
        products.sort(Comparator.comparing(o -> ((Integer) o.getQuantity())));
        
        table.setItems(FXCollections.observableArrayList(products));

        colName.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getName()));
        colStock.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getQuantity()));

        restockingCost.setText(lastRestocking.getCost()+ "");
        restockingDate.setText(lastRestocking.getDate().format(Helpers.getDateFormatter()));

    }
}
