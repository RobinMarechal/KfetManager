package fr.polytech.marechal.app.views.kfet;

import fr.polytech.marechal.libs.mvc.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * @author Utilisateur
 * @date 06/07/2017
 */
public class KfetHome extends KfetViewController
{
    private final double balance;
    private final ViewController summary;
    private final ViewController lowStocks;

    @FXML private Label labBalance;
    @FXML private Pane paneSummary;
    @FXML private Pane paneLowStocks;

    public KfetHome (double balance, ViewController summary, ViewController lowStocks)
    {
        super();
        this.balance = balance;
        this.summary = summary;
        this.lowStocks = lowStocks;


    }

    @Override
    public void initialize ()
    {
        labBalance.setText(balance + "");
        paneSummary.getChildren().add(summary);
        paneLowStocks.getChildren().add(lowStocks);
    }
}
