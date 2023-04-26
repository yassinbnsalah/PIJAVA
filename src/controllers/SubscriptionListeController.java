/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import models.Disponibility;
import models.Order;
import models.User;
import models.Subscription;
import services.SubServices;
import services.UserService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class SubscriptionListeController implements Initializable {
    
    ArrayList<Subscription> SubsList = new ArrayList();
    /* @FXML
    private TableColumn<Subscription, String> SubReference;*/
    // private TableColumn<Subscription, String> Datesubscription;
    @FXML
    private TableView<Subscription> SubTable;
    @FXML
    private TableColumn<Subscription, String> ColSubReference;
    @FXML
    private TableColumn<Subscription, String> ColDatesubscription;
    @FXML
    private TableColumn<Subscription, String> Coldateexpiration;
    @FXML
    private TableColumn<Subscription, String> Coltype;
    @FXML
    private TableColumn<Subscription, String> ColpaiementType;
    @FXML
    private TableColumn<Subscription, String> Colamount;
    @FXML
    private TableColumn<Subscription, String> Colstate;
    private ComboBox<String> CBClEmail;
    private ComboBox<String> CBType;
    private ComboBox<String> CBPaiType;
    private DatePicker DatePick;
    @FXML
    private TextField emailFld;
    @FXML
    private TextField paiementTypeFld;
    @FXML
    private TextField DateSubsc;
    @FXML
    private TextField DateExpirationfld;
    @FXML
    private ComboBox<String> CBType1;
    
    private int IDsubscriptionToUpdate;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnSubscription;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnsubscription;
    @FXML
    private TextField searchTxt;
    @FXML
    private Button clDash;
    
    public int getIDsubscriptionToUpdate() {
        return IDsubscriptionToUpdate;
    }
    
    public void setIDsubscriptionToUpdate(int IDsubscriptionToUpdate) {
        this.IDsubscriptionToUpdate = IDsubscriptionToUpdate;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadDataForAddSubscription();
        this.refreshTable();
    }
    
    private void LoadDataForAddSubscription() {
        /*UserService userservice = new UserService();
        rrayList<User> userListe = userservice.userListe();
        for (User user : userListe) {
            CBClEmail.getItems().add(user.getEmail());
        }
        CBType.getItems().add("1 Month");
        CBType.getItems().add("3 Months");
        CBType.getItems().add("6 Months");
        
        CBPaiType.getItems().add("Cash");
        CBPaiType.getItems().add("Cheque");*/
    }
    
    private void refreshTable() {
        SubServices subservice = new SubServices();
        SubsList.clear();
        SubsList.addAll(subservice.subListe());
        SubTable.setItems(FXCollections.observableArrayList(SubsList));
        ColSubReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        ColDatesubscription.setCellValueFactory(new PropertyValueFactory<>("datesub"));
        Coldateexpiration.setCellValueFactory(new PropertyValueFactory<>("dateExpire"));
        Coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColpaiementType.setCellValueFactory(new PropertyValueFactory<>("paiementMethod"));
        Colamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Colstate.setCellValueFactory(new PropertyValueFactory<>("state"));
        
        SubTable.setRowFactory(tv -> {
            TableRow<Subscription> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    CBType1.getItems().clear();
                    int myIndex = SubTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(SubTable.getItems().get(myIndex).getId()));
                    this.setIDsubscriptionToUpdate(id);
                    System.out.println("Email is " + String.valueOf(SubTable.getItems().get(myIndex).getUser().getEmail()));
                    emailFld.setText(String.valueOf(SubTable.getItems().get(myIndex).getUser().getEmail()));
                    paiementTypeFld.setText(String.valueOf(SubTable.getItems().get(myIndex).getPaiementMethod()));
                    DateSubsc.setText(String.valueOf(SubTable.getItems().get(myIndex).getDatesub()));
                    DateExpirationfld.setText(String.valueOf(SubTable.getItems().get(myIndex).getDateExpire()));
                    
                    CBType1.getItems().add("Confirmed");
                    if (String.valueOf(SubTable.getItems().get(myIndex).getState()).equals("Suspend")) {
                        CBType1.getItems().add("Insuspend");
                    } else {
                        CBType1.getItems().add("Suspend");
                    }
                    CBType1.getItems().add("Cancel");
                    
                }
            });
            return myRow;
        });
        
    }
    
    @FXML
    private void CreateSubscription(ActionEvent event) {
        Routage.getInstance().GOTO(btnsubscription, "/view/admin/subscription/createSubscription.fxml");
     
    }
    
    @FXML
    private void updateSubscription(ActionEvent event) {
        SubServices subservice = new SubServices();
        subservice.UpdateStateSub(CBType1.getValue(), this.getIDsubscriptionToUpdate());
        clearForms();
        refreshTable();
    }
    
    private void clearForms() {
        emailFld.setText("");
        paiementTypeFld.setText("");
        DateSubsc.setText("");
        DateExpirationfld.setText("");
        CBType1.getItems().clear();
        //zDatePick.setValue(null);
        this.setIDsubscriptionToUpdate(0);
    }
    
    @FXML
    private void deleteSubscription(ActionEvent event) {
        SubServices subservice = new SubServices();
        subservice.DeleteSub(IDsubscriptionToUpdate);
        clearForms();
        refreshTable();
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/order/OrderListe.fxml"));
            Parent root = loader.load();
            btnSubscription.getScene().setRoot(root);  
        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
      private List<Subscription> searchList(String searchWords, List<Subscription> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getReference().toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getPaiementMethod().toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getState().toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getDatesub().toString().toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getDateExpire().toString().toLowerCase().contains(word.toLowerCase()))  ;
        }).collect(Collectors.toList());
    }

    @FXML
    private void InstantSet(KeyEvent event) {
        System.out.println(searchTxt.getText());
        searchList(searchTxt.getText() ,SubsList );
         SubTable.setItems(FXCollections.observableArrayList(searchList(searchTxt.getText() ,SubsList )));
    }

    @FXML
    private void signout(ActionEvent event) {
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    @FXML
    private void GoToClientDash(ActionEvent event) {
         Routage.getInstance().GOTO(clDash, "/view/client/order/orderHistory.fxml");
    }
}
