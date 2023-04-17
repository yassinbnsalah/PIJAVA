/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.OrderService;
import models.Order;
import models.OrderHolder;
import services.OrderLineService;
import models.OrderLine;
import services.GenerateInvoice;
import util.Routage;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderdetailsController implements Initializable {

    ObservableList<OrderLine> OrderLineList = FXCollections.observableArrayList();
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
    private Label emaillbl;
    @FXML
    private Label namelbl;
    @FXML
    private Label phonelbl;
    @FXML
    private Label amountlbl;
    @FXML
    private Label paimentmethodlbl;
    @FXML
    private TableView<OrderLine> orderlineTable;
    @FXML
    private TableColumn<OrderLine, String> productnameCol;
    @FXML
    private TableColumn<OrderLine, String> productPricecol;
    @FXML
    private TableColumn<OrderLine, String> quantitycol;
    @FXML
    private TableColumn<OrderLine, String> pricelinecol;

    private int IDOrder;
    @FXML
    private Label reference;
    @FXML
    private Button createOrderbtn;
    @FXML
    private Button backtoOrderListe;
 Order order ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LoadData();
    }

    public void LoadData() {
        OrderHolder orderh = OrderHolder.getInstance();

        OrderService orderservice = new OrderService();
        System.out.println("ID ORDER : " + orderh.getIdOrder());
         order = orderservice.orderByID(orderh.getIdOrder());
        reference.setText(order.getReference());
        emaillbl.setText(order.getOwner().getEmail());
        namelbl.setText(order.getOwner().getName());
        phonelbl.setText(order.getOwner().getNumero());
        amountlbl.setText(String.valueOf(order.getPrice()));
        paimentmethodlbl.setText(order.getPaiementmethod());
        OrderLineService orderlineservice = new OrderLineService();
        ArrayList<OrderLine> orderlineListe = orderlineservice.orderlineListe(orderh.getIdOrder());
        OrderLineList.addAll(orderlineListe);
        orderlineTable.setItems(OrderLineList);
        productnameCol.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        productPricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricelinecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
        Routage rtg = Routage.getInstance(); 
        rtg.GOTO(btnOrders, "/view/client/order/orderHistory.fxml");
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
          Routage rtg = Routage.getInstance(); 
        rtg.GOTO(btnOrders, "/view/client/subscription/subscriptionhis.fxml");
    }

    @FXML
    private void DownloadInvoice(ActionEvent event) {
        GenerateInvoice gn = new GenerateInvoice(); 
        gn.createPDF(order);
    }

}
