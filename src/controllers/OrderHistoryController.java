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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Order;
import models.OrderHolder;
import services.OrderLineService;
import services.OrderService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderHistoryController implements Initializable {

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
    private TableView<Order> OrderTable;
    @FXML
    private TableColumn<Order, String> referenceCol;
    @FXML
    private TableColumn<Order, String> DateOrderCol;
    @FXML
    private TableColumn<Order, String> ShippingAdressCol;
    @FXML
    private TableColumn<Order, String> PaiementMethodCol;
    @FXML
    private TableColumn<Order, String> priceCol;
    @FXML
    private TableColumn<Order, String> stateCOl;
    ObservableList<Order> OrderList = FXCollections.observableArrayList();
    @FXML
    private Button createOrderbtn;
    @FXML
    private Label usernamelbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usernamelbl.setText(SessionManager.getInstance().getUser().getEmail());
        refereshTable();
    }

    private void refereshTable() {
        OrderService orderService = new OrderService();
        OrderLineService orderlineService = new OrderLineService();
        OrderList.clear();
        OrderList.addAll(orderService.OrderByCLient(SessionManager.getInstance().getUser().getId()));
        OrderTable.setItems(OrderList);
        referenceCol.setCellValueFactory(new PropertyValueFactory<>("reference"));

        DateOrderCol.setCellValueFactory(new PropertyValueFactory<>("dateOrder"));
        ShippingAdressCol.setCellValueFactory(new PropertyValueFactory<>("shippingadress"));
        PaiementMethodCol.setCellValueFactory(new PropertyValueFactory<>("paiementmethod"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stateCOl.setCellValueFactory(new PropertyValueFactory<>("state"));

        OrderTable.setRowFactory(tv -> {
            TableRow<Order> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                   
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    // stage.close();
                    try {
                       
                        OrderHolder orderh = OrderHolder.getInstance();
                        int myIndex = OrderTable.getSelectionModel().getSelectedIndex();
                     
                        orderh.setIdOrder(OrderTable.getItems().get(myIndex).getId());
                         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client/order/orderdetails.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        //stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return myRow;
        });
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
           Routage rtg = Routage.getInstance(); 
        rtg.GOTO(btnOrders, "/view/client/subscription/subscriptionhistory.fxml");

    }

    @FXML
    private void GoToCreateOrder(ActionEvent event) {
           Routage rtg = Routage.getInstance(); 
        rtg.GOTO(createOrderbtn, "/view/client/order/createOrder.fxml");
    }

}
