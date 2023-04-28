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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.OrderHolder;
import models.OrderLine;
import services.OrderLineService;
import services.OrderService;
import models.Order ; 
import services.GenerateInvoice;
import util.Routage;
import util.SessionManager;
/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderDetailsController implements Initializable {
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
    Order order;
    @FXML
    private Label statelbl;
    @FXML
    private Button adminDash;

    @FXML
    private Button updateState;
    @FXML
    private ComboBox<String> orderStateCB;
    @FXML
    private Label informationlbl;
    @FXML
    private AnchorPane bx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hello");
        orderStateCB.getItems().add("Cancel");
        orderStateCB.getItems().add("Confirmed");
        orderStateCB.getItems().add("Shipped");
        orderStateCB.getItems().add("ready To Shipp");
        informationlbl.setVisible(false);
        if (SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_ADMIN\"]")) {
            adminDash.setVisible(true);

        } else {
            adminDash.setVisible(false);
            orderStateCB.setVisible(false);
            updateState.setVisible(false);
        }
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
        statelbl.setText(order.getState());
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
        if (SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_ADMIN\"]")) {
            Routage rtg = Routage.getInstance();
            rtg.GOTO(btnOrders, "/view/admin/order/OrderListe.fxml");
        } else {
            Routage rtg = Routage.getInstance();
            rtg.GOTO(btnOrders, "/view/client/order/orderHistory.fxml");
        }

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
    private void DownloadInvoice(ActionEvent event) {
        System.out.println("GGG");
        // informationlbl.setVisible(true);
        GenerateInvoice gn = new GenerateInvoice();
        gn.createPDF(order);

    }

    @FXML
    private void GoToadminDash(ActionEvent event) {
        if (SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_ADMIN\"]")) {

            Routage.getInstance().GOTO(adminDash, "/view/admin/subscription/subscriptionListe.fxml");
        }
    }

 

    @FXML
    private void logoutIng(ActionEvent event) {
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnTicket, "/view/LoginPage.fxml");
    }

    
}
