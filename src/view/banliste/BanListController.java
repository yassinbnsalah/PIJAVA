/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.banliste;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.User;
import services.TicketService;
import services.UserService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class BanListController implements Initializable {

    ObservableList<User> BanList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> BanTable;
    @FXML
    private TableColumn<User, String> ColCin;
    @FXML
    private TableColumn<User, String> ColName;
    @FXML
    private TableColumn<User, String> ColNumero;
    @FXML
    private TableColumn<User, String> ColEmail;
    @FXML
    private TableColumn<User, String> ColAdresse;
    @FXML
    private TextField cinFld;
    @FXML
    private TextField NameFld;
    @FXML
    private TextField NumeroTelFld;
    @FXML
    private TextField AdresseFld;
    @FXML
    private TextField EmailFld;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
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
    private Button btnBan;
    @FXML
    private Button logoutButton;
    private int IDBanToUpdate;
    @FXML
    private TextField searchfld;
    @FXML
    private Label userName;
    @FXML
    private Button clDash;

    public int getIDBanToUpdate() {
        return IDBanToUpdate;
    }

    public void setIDBanToUpdate(int IDBanToUpdate) {
        this.IDBanToUpdate = IDBanToUpdate;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName.setText(SessionManager.getInstance().getUser().getEmail());
        this.refreshTable();
    }

    private void refreshTable() {
        UserService coachserv = new UserService();
        BanList.clear();
        BanList.addAll(coachserv.BanListe());
        BanTable.setItems(BanList);
        ColCin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        BanTable.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = BanTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(BanTable.getItems().get(myIndex).getId()));
                    setIDBanToUpdate(id);
                    cinFld.setText(BanTable.getItems().get(myIndex).getCIN().toString());
                    NameFld.setText(BanTable.getItems().get(myIndex).getName().toString());
                    NumeroTelFld.setText(BanTable.getItems().get(myIndex).getNumero());
                    AdresseFld.setText(BanTable.getItems().get(myIndex).getAdresse().toString());
                    EmailFld.setText(BanTable.getItems().get(myIndex).getEmail().toString());

                }
            });
            return myRow;
        });

    }

  

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
        Routage rtg = Routage.getInstance();
        rtg.GOTO(btnSubscription, "/view/admin/subscription/subscriptionListe.fxml");
    }

    @FXML
    private void roleMedcin(ActionEvent event) {
        UserService userserv = new UserService();
        userserv.DebanMedcin(IDBanToUpdate);
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");
        this.setIDBanToUpdate(0);
        refreshTable();
    }

    @FXML
    private void roleClient(ActionEvent event) {
        UserService userserv = new UserService();
        userserv.DebanClient(IDBanToUpdate);
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");
        this.setIDBanToUpdate(0);
        refreshTable();
    }

    @FXML
    private void roleCoach(ActionEvent event) {
        UserService userserv = new UserService();
        userserv.DebanCoach(IDBanToUpdate);
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");
        this.setIDBanToUpdate(0);
        refreshTable();
    }

    @FXML
    private void rolePharmacien(ActionEvent event) {
        UserService userserv = new UserService();
        userserv.DebanPharmacien(IDBanToUpdate);
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");
        this.setIDBanToUpdate(0);
        refreshTable();
    }

    @FXML
    private void search(KeyEvent event) {

        BanTable.setItems(FXCollections.observableArrayList(searchList(searchfld.getText(), BanList)));
    }

    private List<User> searchList(String searchWords, List<User> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getName().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getEmail().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    private void Ticket(ActionEvent event) throws IOException {
        Routage.getInstance().GOTO(btnTicket, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        // clear user session data
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(logoutButton, "/view/LoginPage.fxml");

    }

   

    @FXML
    private void Pharmacien(ActionEvent event) {
        Routage.getInstance().GOTO(btnPharmacien, "/view/users/pharmacien/PharmacienList.fxml");
    }

    @FXML
    private void medcin(ActionEvent event) {
        Routage.getInstance().GOTO(btnMedcin, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void coach(ActionEvent event) {
        Routage.getInstance().GOTO(btnCoach, "/view/users/coach/CoachList.fxml");
    }

    @FXML
    private void ban(ActionEvent event) {
        Routage.getInstance().GOTO(btnBan, "/view/banliste/BanList.fxml");
    }

    @FXML
    private void client(ActionEvent event) {
          Routage.getInstance().GOTO(btnClient, "/view/users/client/ClientListe.fxml");
    }

    @FXML
    private void Order(ActionEvent event) {
          Routage rtg = Routage.getInstance();
        rtg.GOTO(btnOrders, "/view/admin/order/OrderListe.fxml");
    }

    @FXML
    private void Product(ActionEvent event) {
         Routage.getInstance().GOTO(btnProduct, "/view/admin/store/Produit.fxml");
    }

    @FXML
    private void category(ActionEvent event) {
          Routage rtg = Routage.getInstance();
        rtg.GOTO(btnCategory, "/view/admin/store/categoryPage.fxml");
    }

    @FXML
    private void GoToClientDash(ActionEvent event) {
        Routage.getInstance().GOTO(clDash, "/view/client/subscription/subscriptionhistory.fxml");
    }

}
