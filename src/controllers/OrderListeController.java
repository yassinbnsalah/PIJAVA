/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Disponibility;
import models.Order;
import models.OrderLine;
import models.Subscription;
import services.GenerateInvoice;
import services.OrderLineService;
import services.OrderService;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderListeController implements Initializable {

    private int IDOrderToUpdate;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSubscription;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnCreateOrder;
    @FXML
    private Label Referencelbl;
    @FXML
    private Label errorlbl;

    public int getIDOrderToUpdate() {
        return IDOrderToUpdate;
    }

    public void setIDOrderToUpdate(int IDOrderToUpdate) {
        this.IDOrderToUpdate = IDOrderToUpdate;
    }

    @FXML
    private TableView<Order> OrderTable;
    @FXML
    private TableColumn<Order, String> ColOrderReference;
    @FXML
    private TableColumn<Order, String> ColOrderOwner;
    @FXML
    private TableColumn<Order, String> ColOrderDate;
    @FXML
    private TableColumn<Order, String> ColShippingAdress;
    @FXML
    private TableColumn<Order, String> ColPaiementMethod;
    @FXML
    private TableColumn<Order, String> ColPrice;
    @FXML
    private TableColumn<Order, String> ColState;
    @FXML
    private TableView<OrderLine> orderlineTable;
    @FXML
    private TableColumn<OrderLine, String> productNameCol;
    @FXML
    private TableColumn<OrderLine, String> productPricecol;
    @FXML
    private TableColumn<OrderLine, String> quantitycol;
    @FXML
    private TableColumn<OrderLine, String> pricelinecol;
    @FXML
    private ComboBox<String> stateCombo;

    ObservableList<Order> OrderList = FXCollections.observableArrayList();
    ObservableList<OrderLine> OrderLineList = FXCollections.observableArrayList();
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField shippingtxt;
    @FXML
    private TextField amounttxt;
    @FXML
    private TextField paiementtxt;
    @FXML
    private TextField orderDatetxt;
    @FXML
    private TextField orderStatetxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refereshTable();
    }

    private void refereshTable() {
        OrderService orderService = new OrderService();
        OrderLineService orderlineService = new OrderLineService();
        OrderList.clear();
        OrderList.addAll(orderService.OrderListe());
        OrderTable.setItems(OrderList);
        ColOrderReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        ColOrderOwner.setCellValueFactory(new PropertyValueFactory<>("OwnerEmail"));
        ColOrderDate.setCellValueFactory(new PropertyValueFactory<>("dateOrder"));
        ColShippingAdress.setCellValueFactory(new PropertyValueFactory<>("shippingadress"));
        ColPaiementMethod.setCellValueFactory(new PropertyValueFactory<>("paiementmethod"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ColState.setCellValueFactory(new PropertyValueFactory<>("state"));
        OrderTable.setRowFactory(tv -> {
            TableRow<Order> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    stateCombo.getItems().clear();
                    orderlineTable.getItems().clear();
                    int myIndex = OrderTable.getSelectionModel().getSelectedIndex();
                    emailTxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getOwnerEmail()));
                    nameTxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getOwnerEmail()));
                    shippingtxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getShippingadress()));
                    amounttxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getPrice()));
                    paiementtxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getPaiementmethod()));
                    orderDatetxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getDateOrder()));
                    orderStatetxt.setText(String.valueOf(OrderTable.getItems().get(myIndex).getState()));
                    stateCombo.getItems().add("Confirmed");
                    stateCombo.getItems().add("Inconfirmed");
                    stateCombo.getItems().add("Ready To Ship");
                    stateCombo.getItems().add("Shipped");
                    stateCombo.getItems().add("Canceled");
                    stateCombo.getItems().add("Done");
                    Referencelbl.setText(OrderTable.getItems().get(myIndex).getReference());
                    this.setIDOrderToUpdate(OrderTable.getItems().get(myIndex).getId());
                    OrderLineList.addAll(orderlineService.orderlineListe(this.getIDOrderToUpdate()));
                    orderlineTable.setItems(OrderLineList);
                    productNameCol.setCellValueFactory(new PropertyValueFactory<>("product_id"));
                    productPricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                    quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    pricelinecol.setCellValueFactory(new PropertyValueFactory<>("price"));

                }
            });
            return myRow;
        });

    }

    @FXML
    private void updateOrderState(ActionEvent event) {
        if(this.getIDOrderToUpdate() != 0){
              System.out.println("order state Updated");
        OrderService orderService = new OrderService();
        orderService.updateStateOrder(this.getIDOrderToUpdate(), stateCombo.getValue());
        clearForm();
        refereshTable();
        }else{
           
            errorlbl.setText("No Order Clicked");
       }
      

    }

    public void clearForm() {
        emailTxt.setText("");
        nameTxt.setText("");
        shippingtxt.setText("");
        amounttxt.setText("");
        paiementtxt.setText("");
        orderDatetxt.setText("");
        orderStatetxt.setText("");
        orderlineTable.getItems().clear();
    }

    @FXML
    private void GenerateInvoicehere(ActionEvent event) {
         if(this.getIDOrderToUpdate() != 0){
        GenerateInvoice GI = new GenerateInvoice() ; 
        OrderService orderservice = new OrderService() ;
        Order order = orderservice.orderByID(IDOrderToUpdate); 
        GI.createPDF(order);
         }else{
           
            errorlbl.setText("No Order Clicked");
       }
    }

   

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/subscription/subscriptionListe.fxml"));
            Parent root = loader.load();
            btnSubscription.getScene().setRoot(root);  
        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void GoToCreateOrder(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/order/CreateOrder.fxml"));
            Parent root = loader.load();
            btnCreateOrder.getScene().setRoot(root);  
        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleClicks(ActionEvent event) {
    }

}
