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
import java.util.Timer;
import java.util.TimerTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private ComboBox<String> CBType;
    @FXML
    private ComboBox<String> CBPaiType;
    @FXML
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
    @FXML
    private AnchorPane subscriptionDetailslbl;
    @FXML
    private AnchorPane createSubLabel;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> clientNameCol1;
    @FXML
    private TableColumn<User, String> clientEmailCol1;
    @FXML
    private TextField clientNamelbl;
    @FXML
    private TextField clientEmaillbl;
    @FXML
    private TextField clientphonelbl;
    @FXML
    private Button btnsub;
    ObservableList<User> UserList = FXCollections.observableArrayList();
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
    @FXML
    private Label userName;
    @FXML
    private Button btnBan;

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
        userName.setText(SessionManager.getInstance().getUser().getEmail());
        subscriptionDetailslbl.setVisible(false);
        createSubLabel.setVisible(false);
        this.LoadDataForAddSubscription();
        this.refreshTable();
        loadTableUser();
    }

    private void LoadDataForAddSubscription() {
        UserService userservice = new UserService();
        /*ArrayList<User> userListe = userservice.userListe();
        for (User user : userListe) {
            CBClEmail.getItems().add(user.getEmail());
        }*/
        CBType.getItems().add("1 Month");
        CBType.getItems().add("3 Months");
        CBType.getItems().add("6 Months");

        CBPaiType.getItems().add("Cash");
        CBPaiType.getItems().add("Cheque");
    }
    private User SubOwner;

    public User getSubOwner() {
        return SubOwner;
    }

    public void setSubOwner(User SubOwner) {
        this.SubOwner = SubOwner;
    }

    public void loadTableUser() {
        UserService userservice = new UserService();
        UserList.clear();
        UserList.addAll(userservice.userListe());
        userTable.setItems(UserList);
        clientNameCol1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clientEmailCol1.setCellValueFactory(new PropertyValueFactory<>("Email"));
        //ClientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        userTable.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = userTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(userTable.getItems().get(myIndex).getId()));

                    clientNamelbl.setText(String.valueOf(userTable.getItems().get(myIndex).getName()));

                    clientEmaillbl.setText(String.valueOf(userTable.getItems().get(myIndex).getEmail()));
                    clientphonelbl.setText(String.valueOf(userTable.getItems().get(myIndex).getNumero()));
                    User user = userservice.userById(id);
                    //this.setOwnerOrder(user);
                    setSubOwner(user);
                }
            });
            return myRow;
        });
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
                    subscriptionDetailslbl.setVisible(true);
                    createSubLabel.setVisible(false);
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
        //  Routage.getInstance().GOTO(btnsubscription, "/view/admin/subscription/createSubscription.fxml");
        subscriptionDetailslbl.setVisible(false);
        createSubLabel.setVisible(true);
    }

    @FXML
    private void updateSubscription(ActionEvent event) {
        SubServices subservice = new SubServices();
        subservice.UpdateStateSub(CBType1.getValue(), this.getIDsubscriptionToUpdate());
        clearForms();
        refreshTable();
        subscriptionDetailslbl.setVisible(false);

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
        subscriptionDetailslbl.setVisible(false);
        createSubLabel.setVisible(false);
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/order/OrderListe.fxml"));
            Parent root = loader.load();
            btnSubscription.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    private List<Subscription> searchList(String searchWords, List<Subscription> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getReference().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getPaiementMethod().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getState().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getDatesub().toString().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getDateExpire().toString().toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    private void InstantSet(KeyEvent event) {
        System.out.println(searchTxt.getText());
        searchList(searchTxt.getText(), SubsList);
        SubTable.setItems(FXCollections.observableArrayList(searchList(searchTxt.getText(), SubsList)));
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

    @FXML
    private void addSubscriptio(ActionEvent event) {
        SubServices subservice = new SubServices();
        UserService userservice = new UserService();
        System.out.println("email" + clientEmaillbl.getText());
        User user = userservice.userByEmail(clientEmaillbl.getText());
        Subscription sub = new Subscription();
        sub.setId_user(getSubOwner().getId());
        sub.setDatesub(Date.valueOf(DatePick.valueProperty().getValue()));
        String type = CBType.getValue();
        if (type.equals("1 Month")) {
            sub.setType("1");
        } else if (type.equals("3 Months")) {
            sub.setType("2");
        } else {
            sub.setType("3");
        }
        sub.setPaiementMethod(CBPaiType.getValue());
        subservice.AddSubscription(sub);
        clearForms();
        refreshTable();
        subscriptionDetailslbl.setVisible(false);
        createSubLabel.setVisible(false);
    }

    @FXML
    private void Ticket(ActionEvent event) {
        Routage.getInstance().GOTO(btnOrders, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    private void Pharmacien(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/users/pharmacien/PharmacienList.fxml");
    }

    @FXML
    private void medcin(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void coach(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void ban(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/banliste/BanList.fxml");
    }

}
