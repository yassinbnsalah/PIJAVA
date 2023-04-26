/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.User;
import services.TicketService;
import services.UserService;
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
    private TableColumn<User,String> ColCin;
    @FXML
    private TableColumn<User,String> ColName;
    @FXML
    private TableColumn<User,String> ColNumero;
    @FXML
    private TableColumn<User,String> ColEmail;
    @FXML
    private TableColumn<User,String> ColAdresse;
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
                      NumeroTelFld.setText(Integer.toString(BanTable.getItems().get(myIndex).getNumero()));
                       AdresseFld.setText(BanTable.getItems().get(myIndex).getAdresse().toString());
                        EmailFld.setText(BanTable.getItems().get(myIndex).getEmail().toString());
                        
                   
                }
            });
            return myRow;
        });
        
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

  @FXML
    void handleLogout(ActionEvent event) throws IOException {
        // clear user session data
        SessionManager.cleanUserSession();
        
        // load the login FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Login.fxml"));
        Parent root = loader.load();
        
        // get the stage and show the login scene
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
    private void GoToSubscriptionListe(ActionEvent event) {
    }

    @FXML
    private void Ticket(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/TicketListe.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void ban(ActionEvent event) {
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
    
}
