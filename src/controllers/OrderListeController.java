/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Disponibility;
import models.Order;
import models.OrderHolder;
import models.OrderLine;
import models.Subscription;
import services.GenerateInvoice;
import services.OrderLineService;
import services.OrderService;
import util.Routage;

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
    private Label errorlbl;
    @FXML
    private Button clientDash;
    @FXML
    private TextField searhOrderfld;
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;

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
    private TableView<OrderLine> orderlineTable;
    private ComboBox<String> stateCombo;

    ObservableList<Order> OrderList = FXCollections.observableArrayList();
    ObservableList<OrderLine> OrderLineList = FXCollections.observableArrayList();
    private TextField emailTxt;
    private TextField nameTxt;
    private TextField shippingtxt;
    private TextField amounttxt;
    private TextField paiementtxt;
    private TextField orderDatetxt;
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
                    System.out.println("REDIRECTING");
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    // stage.close();
                    //try {

                        OrderHolder orderh = OrderHolder.getInstance();
                        int myIndex = OrderTable.getSelectionModel().getSelectedIndex();

                        orderh.setIdOrder(OrderTable.getItems().get(myIndex).getId());
                        Routage.getInstance().GOTO(btnOrders, "/view/admin/order/OrderDetails.fxml");
                       /* Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/admin/order/OrderDetails.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);*/
                        //stage.show();

                 //   } catch (IOException ex) {
                   //     Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
                   // }

                }
            });
            return myRow;
        });

    }

    private void updateOrderState(ActionEvent event) {
        if (this.getIDOrderToUpdate() != 0) {
            System.out.println("order state Updated");
            OrderService orderService = new OrderService();
            orderService.updateStateOrder(this.getIDOrderToUpdate(), stateCombo.getValue());
            clearForm();
            refereshTable();
        } else {

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

    private void GenerateInvoicehere(ActionEvent event) {
        if (this.getIDOrderToUpdate() != 0) {
            GenerateInvoice GI = new GenerateInvoice();
            OrderService orderservice = new OrderService();
            Order order = orderservice.orderByID(IDOrderToUpdate);
            GI.createPDF(order);
        } else {

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
        System.out.println("GOING");
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
         Routage.getInstance().GOTO(clientDash, "/view/users/client/ClientListe.fxml");
    }
    

    @FXML
    private void GoToclientDash(ActionEvent event) {

        Routage.getInstance().GOTO(clientDash, "/view/client/order/orderHistory.fxml");
    }

    @FXML
    private void searchOrder(KeyEvent event) {
         OrderTable.setItems(FXCollections.observableArrayList(searchList(searhOrderfld.getText(), OrderList)));
    }
   private List<Order> searchList(String searchWords, List<Order> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getReference().toLowerCase().contains(word.toLowerCase())) ||searchWordsArray.stream().allMatch(word
                    -> input.getState().toLowerCase().contains(word.toLowerCase())) ||searchWordsArray.stream().allMatch(word
                    -> String.valueOf(input.getDateOrder()).toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getOwnerEmail().toLowerCase().contains(word.toLowerCase())) ;
        }).collect(Collectors.toList());
    }

    @FXML
    private void GOTOTICKET(ActionEvent event) {
        Routage.getInstance().GOTO(btnOrders, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    private void Pharmacien(ActionEvent event) {
        Routage.getInstance().GOTO(clientDash, "/view/users/pharmacien/PharmacienList.fxml");
    }

    @FXML
    private void medcin(ActionEvent event) {
        Routage.getInstance().GOTO(clientDash, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void coach(ActionEvent event) {
         Routage.getInstance().GOTO(clientDash, "/view/users/medecin/MedcinList.fxml");
    }
}
