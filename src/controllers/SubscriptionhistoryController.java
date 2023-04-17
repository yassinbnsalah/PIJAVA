/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Subscription; 
import services.SubServices;
import util.Routage;
/**
 * FXML Controller class
 *
 * @author yacin
 */
public class SubscriptionhistoryController implements Initializable {

      ObservableList<Subscription> SubsList = FXCollections.observableArrayList();
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSubscription;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnSignout;
    @FXML
    private TableView<Subscription> subscriptionTable;
    @FXML
    private TableColumn<Subscription, String> referenceCol;
    @FXML
    private TableColumn<Subscription, String> dateSubscriptionCol;
    @FXML
    private TableColumn<Subscription, String> DateexpirationCol;
    @FXML
    private TableColumn<Subscription, String> typeCol;
    @FXML
    private TableColumn<Subscription, String> paiementTypeCol;
    @FXML
    private TableColumn<Subscription, String> amountType;
    @FXML
    private TableColumn<Subscription, String> stateCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshtable();
    }  
    
    public void refreshtable(){
         SubServices subservice = new SubServices();
        SubsList.clear();
        SubsList.addAll(subservice.subscriptionByUser(1));
        subscriptionTable.setItems(SubsList);
        referenceCol.setCellValueFactory(new PropertyValueFactory<>("reference"));
        dateSubscriptionCol.setCellValueFactory(new PropertyValueFactory<>("datesub"));
        DateexpirationCol.setCellValueFactory(new PropertyValueFactory<>("dateExpire"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        paiementTypeCol.setCellValueFactory(new PropertyValueFactory<>("paiementMethod"));
        amountType.setCellValueFactory(new PropertyValueFactory<>("amount"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
        Routage rtg = Routage.getInstance(); 
        rtg.GOTO(btnOrders, "/view/client/order/orderHistory.fxml");
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
