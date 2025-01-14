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
import java.sql.Date;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Disponibility;
import models.Ticket;
import models.User;
import services.DisponibilityService;
import services.TicketService;
import util.Routage;
import util.SessionManager;
import util.SharedData;

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
    @FXML
    private Button btnBan;
    @FXML
    private TextField searchfld;
    @FXML
    private AnchorPane ticketDetaillbl;
    @FXML
    private AnchorPane createTicketlbl;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtTitle;
    @FXML
    private Button btnCreate;

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
    private TableColumn<Ticket, String> colTitle;
    @FXML
    private TableColumn<Ticket, String> colSubjectTicket;
    @FXML
    private TableColumn<Ticket, String> ColDateTicket;
    @FXML
    private TableColumn<Ticket, Integer> ColOwner;
    @FXML
    private TableColumn<Ticket, String> ColState;
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
        ticketDetaillbl.setVisible(false);
        createTicketlbl.setVisible(false);
        this.refreshTable();
    }

    @FXML
    private void CreateNewTicket(ActionEvent event) {
        /*try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/AddTicket.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ticketDetaillbl.setVisible(false);
        createTicketlbl.setVisible(true);
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
        TicketList.addAll(ticketserv.listTicket());
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
                    ticketDetaillbl.setVisible(true);
                    createTicketlbl.setVisible(false);
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
    private void search(KeyEvent event) {
        TableTicket.setItems(FXCollections.observableArrayList(searchList(searchfld.getText(), TicketList)));
    }

    private List<Ticket> searchList(String searchWords, List<Ticket> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getTitre().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getTitre().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    private void OnHandleClickCreate(ActionEvent event) throws IOException {
        if (txtTitle.getText().length() <= 5) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le Titre doit contenir au moins 5 caractéres!", ButtonType.APPLY.OK);
            a.setHeaderText("Sujet Invalide");
            a.setTitle("Error");
            a.showAndWait();
        } else if (txtSubject.getText().length() <= 10) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le contenu doit contenir au moins 10 caractéres!", ButtonType.APPLY.OK);
            a.setHeaderText("Contenu Invalide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            Date d = new Date(System.currentTimeMillis());
            Ticket ticket = new Ticket(d, txtTitle.getText(), txtSubject.getText(), SharedData.currentUser);
            TicketService tk = new TicketService();
            SessionManager session = SessionManager.getInstance();
            int ownerId = session.getUser().getId();
            tk.ajouterTicket(ownerId, ticket);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            // The phone number you want to send the message to
            String toNumber = "+21693293311";

            // The phone number you want to send the message from (must be a Twilio phone number)
            String fromNumber = "+15673343714";

            // The message you want to send
            String messageText = "Hello, Your Ticket are saved!!";

            // Send the message using the Twilio library
            Message message = Message.creator(
                    new PhoneNumber(toNumber),
                    new PhoneNumber(fromNumber),
                    messageText)
                    .create();

            System.out.println("Message SID: " + message.getSid());
            refreshTable();
            ticketDetaillbl.setVisible(false);
            createTicketlbl.setVisible(false);
        }
    }

    @FXML
    private void Ticket(ActionEvent event) throws IOException {
        Routage.getInstance().GOTO(btnOrders, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event)  {
        // clear user session data
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnTicket, "/view/LoginPage.fxml");

    }

    private void Client(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/users/client/ClientListe.fxml");
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

    @FXML
    private void handleClicks(ActionEvent event) {
        Routage rtg = Routage.getInstance();
        rtg.GOTO(btnOrders, "/view/admin/order/OrderListe.fxml");
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
    private void textfieldDesign1(MouseEvent event) {
    }

    @FXML
    private void textfieldDesign1(KeyEvent event) {
    }
    

}
