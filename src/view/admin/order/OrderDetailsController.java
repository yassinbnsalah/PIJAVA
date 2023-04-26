/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.order;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.OrderHolder;
import models.OrderLine;
import services.OrderLineService;
import services.OrderService;
import models.Order ; 
/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderDetailsController implements Initializable {
ObservableList<OrderLine> OrderLineList = FXCollections.observableArrayList();
    @FXML
    private Button btnClient;
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
    private TableColumn<?, ?> productnameCol;
    @FXML
    private TableColumn<?, ?> productPricecol;
    @FXML
    private TableColumn<?, ?> quantitycol;
    @FXML
    private TableColumn<?, ?> pricelinecol;
    @FXML
    private Button createOrderbtn;
    @FXML
    private Button backtoOrderListe;
    @FXML
    private Label reference;
    private Order order  ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
    }

    @FXML
    private void DownloadInvoice(ActionEvent event) {
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
    }
    
}
