/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Disponibility;
import models.Ticket;
import services.DisponibilityService;
import services.TicketService;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class TicketListeController implements Initializable {
    private int IDToUpdate;
    @FXML
    private Button updatebtn2;
    @FXML
    private TextField titleTxt;
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
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
     public static final String ACCOUNT_SID = "AC861f71d7aac3551d77d339a99370346e";
     public static final String AUTH_TOKEN = "9977080a69c468ee2434850e50eb58c5";
    public int getIDToUpdate() {
        return IDToUpdate;
    }

    public void setIDToUpdate(int IDToUpdate) {
        this.IDToUpdate = IDToUpdate;
    }
    ObservableList<Ticket> TicketList = FXCollections.observableArrayList();
    @FXML
    private Button btnAddTicket;
    @FXML
    private TableColumn<Ticket,String> colTitle;
    @FXML
    private TableColumn<Ticket,String> colSubjectTicket;
    @FXML
    private TableColumn<Ticket,String> ColDateTicket;
    @FXML
    private TableColumn<Ticket,Integer> ColOwner;
    @FXML
    private TableColumn<Ticket,String> ColState;
    @FXML
    private TextField SubjectTXT;

    @FXML
    private TableView<Ticket> TableTicket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.refreshTable();
    }    

    @FXML
    private void CreateNewTicket(ActionEvent event) {
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/AddTicket.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void SupprimerTicket(ActionEvent event) {
        TicketService ticketserv = new TicketService();
        ticketserv.SupprimerTicket(IDToUpdate);
        titleTxt.setText("");
       
        this.setIDToUpdate(0);
       
        SubjectTXT.setText("");
        refreshTable();
    }

    @FXML
    private void UpdateStateTicket(ActionEvent event) {
        TicketService ticketserv = new TicketService();
        ticketserv.UpdateStateTicket(IDToUpdate);
        titleTxt.setText("");
        this.setIDToUpdate(0);
        SubjectTXT.setText("");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // The phone number you want to send the message to
        String toNumber = "+21693293311";

        // The phone number you want to send the message from (must be a Twilio phone number)
        String fromNumber = "+15673343714";

        // The message you want to send
        String messageText = "Hello, Your Ticket Confirmed!";

        // Send the message using the Twilio library
        Message message = Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromNumber),
                messageText)
            .create();

        System.out.println("Message SID: " + message.getSid());
    
        refreshTable();
    }
    private void refreshTable() {
        TicketService ticketserv = new TicketService();
        TicketList.clear();
        TicketList.addAll(ticketserv.recupererUser(1));
        TableTicket.setItems(TicketList);
        colTitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColDateTicket.setCellValueFactory(new PropertyValueFactory<>("dateticket"));
        ColOwner.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        colSubjectTicket.setCellValueFactory(new PropertyValueFactory<>("message"));
        ColState.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableTicket.setRowFactory(tv -> {
            TableRow<Ticket> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = TableTicket.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(TableTicket.getItems().get(myIndex).getId()));
                    SubjectTXT.setText(TableTicket.getItems().get(myIndex).getMessage().toString());
                    this.setIDToUpdate(TableTicket.getItems().get(myIndex).getId());
                    titleTxt.setText(String.valueOf(TableTicket.getItems().get(myIndex).getTitre()));
                }
            });
            return myRow;
        });

    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/ClientListe.fxml"));
          Parent root = loader.load();
          btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
    }

    @FXML
    private void Ticket(ActionEvent event) {
    }

    @FXML
    private void textfieldDesign1(MouseEvent event) {
    }

    @FXML
    private void textfieldDesign1(KeyEvent event) {
    }

     @FXML
    private void Pharmacien(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/PharmacienList.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void medcin(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/MedcinList.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void coach(ActionEvent event) throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/CoachList.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

       @FXML
    void handleLogout(ActionEvent event) throws IOException {
        // clear user session data
        SessionManager.cleanUserSession();
        
        // load the login FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Login.fxml"));
        Parent root = loader.load();
        
        // get the stage and show the login scene
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
