/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Medcecin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class BaseController implements Initializable {

    public static BaseController baseController;

    @FXML
    private HBox gestionRVButton;
    @FXML
    private HBox GestionMedicamentButon;
    @FXML
    private HBox GestionOrdonnanceButon1;
    @FXML
    private HBox GestionOrdonnanceButon11;
    @FXML
    private Label calaendarButton;
    @FXML
    private StackPane AnchorePaneLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        baseController = this;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddRendez-vous.fxml"));
            removeSelectedStyle();
            gestionRVButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionRV(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddRendez-vous.fxml"));
            removeSelectedStyle();
            gestionRVButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionMedicament(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddMedicamment.fxml"));
            removeSelectedStyle();
            GestionMedicamentButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionOrdeonance(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddOrdennance.fxml"));
            removeSelectedStyle();
            GestionOrdonnanceButon1.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadCalendar(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            removeSelectedStyle();
            GestionOrdonnanceButon11.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeSelectedStyle() {
        GestionMedicamentButon.getStyleClass().clear();
        gestionRVButton.getStyleClass().clear();
        GestionOrdonnanceButon1.getStyleClass().clear();
        GestionOrdonnanceButon11.getStyleClass().clear();

        GestionMedicamentButon.getStyleClass().add("btns");
        gestionRVButton.getStyleClass().add("btns");
        GestionOrdonnanceButon1.getStyleClass().add("btns");
        GestionOrdonnanceButon11.getStyleClass().add("btns");

    }

}
