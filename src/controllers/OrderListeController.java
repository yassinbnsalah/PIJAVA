/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderListeController implements Initializable {

    @FXML
    private TableView<?> SubTable;
    @FXML
    private TableColumn<?, ?> ColSubReference;
    @FXML
    private TableColumn<?, ?> ColDatesubscription;
    @FXML
    private TableColumn<?, ?> Coldateexpiration;
    @FXML
    private TableColumn<?, ?> Coltype;
    @FXML
    private TableColumn<?, ?> ColpaiementType;
    @FXML
    private TableColumn<?, ?> Colamount;
    @FXML
    private TableColumn<?, ?> Colstate;
    @FXML
    private TableView<?> orderlineTable;
    @FXML
    private TableColumn<?, ?> productNameCol;
    @FXML
    private TableColumn<?, ?> productPricecol;
    @FXML
    private TableColumn<?, ?> quantitycol;
    @FXML
    private TableColumn<?, ?> pricelinecol;
    @FXML
    private ComboBox<?> stateCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateOrderState(ActionEvent event) {
    }

    @FXML
    private void GenerateInvoicehere(ActionEvent event) {
    }
    
}
