package fr.polytech.marechal.libs.ui.custom;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Utilisateur on 10/07/2017.
 */
public class LoadingWindow extends Stage
{
    private Label labTitle;
    private Label labLoading;
    private ImageView loadingImageView;

    /**
     * Creates a new instance of decorated {@code Stage}.
     *
     * @throws IllegalStateException if this constructor is called on a thread
     *                               other than the JavaFX Application Thread.
     */
    public LoadingWindow ()
    {
//        super(StageStyle.UNDECORATED);
        labTitle = new Label("Logiciel de gestion de la Kfet");
        labLoading = new Label("Chargement...");
        loadingImageView = new ImageView();

        initModality(Modality.APPLICATION_MODAL);

        display();
    }

    private void display ()
    {
        setWidth(500);
        setHeight(300);
        VBox vbox = new VBox();
        setScene(new Scene(vbox));

        Image loading = new Image("/media/loading.gif");
        loadingImageView.setImage(loading);

        vbox.getChildren().addAll(labTitle, loadingImageView, labLoading);
    }
}
