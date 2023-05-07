/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import models.Subscription; 
import services.SubServices;
import util.Routage;
import util.SessionManager;
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
    @FXML
    private Button adminDash;
    @FXML
    private TextField searchsubfld;
    @FXML
    private Label userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName.setText(SessionManager.getInstance().getUser().getEmail());
        if( SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_CLIENT\"]")){
       
            
            adminDash.setVisible(false);
        }else if( SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_ADMIN\"]")){
            adminDash.setVisible(true);
            adminDash.setText("Admin Dashboard");
        }else if(SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_MEDCIN\"]")){
             adminDash.setVisible(true);
            adminDash.setText("Doctor Dashboard");
        }
        refreshtable();
    }  
    
    public void refreshtable(){
         SubServices subservice = new SubServices();
        SubsList.clear();
        SubsList.addAll(subservice.subscriptionByUser(SessionManager.getInstance().getUser().getId()));
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
    private void GoToadminDash(ActionEvent event) {
          if(SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_ADMIN\"]")){
                     Routage.getInstance().GOTO(adminDash, "/view/admin/subscription/subscriptionListe.fxml");
                }else if(SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_MEDCIN\"]")){
                     Routage.getInstance().GOTO(adminDash, "/view/Medecin/disponibilityListe.fxml");
                }else if(SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_PHARMACIEN\"]")){
                     Routage.getInstance().GOTO(adminDash, "/view/Pharmacien/AddMedicamment.fxml");
                }else if(SessionManager.getInstance().getUser().getRoles().equals("[\"ROLE_COACH\"]")){
                     Routage.getInstance().GOTO(adminDash, "/view/coach/activity.fxml");
                }
        
    
    }

    @FXML
    private void logout(ActionEvent event) {
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    @FXML
    private void searchSub(KeyEvent event) {
           System.out.println(searchsubfld.getText());
        searchList(searchsubfld.getText() ,SubsList );
         subscriptionTable.setItems(FXCollections.observableArrayList(searchList(searchsubfld.getText() ,SubsList )));
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
    private void subscription(ActionEvent event) {
    }


    @FXML
    private void ticketCreate(ActionEvent event) {
         Routage.getInstance().GOTO(btnTicket, "/view/Ticket/TicketCreate.fxml");
    }
}
