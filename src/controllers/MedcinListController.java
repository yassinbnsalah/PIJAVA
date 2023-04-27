/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class MedcinListController implements Initializable {
ObservableList<User> MedcinList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> MedcinTable;
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
    private TextField txtCin;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
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
    private Button logoutButton;
    private int IDMedcinToUpdate;
    @FXML
    private Button btnCoach;
    @FXML
    private Button btnBan;
    public int getIDMedcinToUpdate() {
        return IDMedcinToUpdate;
    }
    
    public void setIDMedcinToUpdate(int IDMedcinToUpdate) {
        this.IDMedcinToUpdate = IDMedcinToUpdate;
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
        UserService medcinserv = new UserService();
        MedcinList.clear();
        MedcinList.addAll(medcinserv.medcinListe());
        MedcinTable.setItems(MedcinList);
        ColCin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
  
        
        MedcinTable.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = MedcinTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(MedcinTable.getItems().get(myIndex).getId()));
                    setIDMedcinToUpdate(id);
                    cinFld.setText(MedcinTable.getItems().get(myIndex).getCIN().toString());
                     NameFld.setText(MedcinTable.getItems().get(myIndex).getName().toString());
                      NumeroTelFld.setText(Integer.toString(MedcinTable.getItems().get(myIndex).getNumero()));
                       AdresseFld.setText(MedcinTable.getItems().get(myIndex).getAdresse().toString());
                        EmailFld.setText(MedcinTable.getItems().get(myIndex).getEmail().toString());
                        
                   
                }
            });
            return myRow;
        });
        
    }
     private boolean validateEmaill() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(txtEmail.getText());
        if (m.find() && m.group().equals(txtEmail.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }
    @FXML
    private void CreateMedcin(ActionEvent event) {
    UserService rs = new UserService();
        String CIN = txtCin.getText();
        String Email = txtEmail.getText();
        String Adresse = txtAdresse.getText();

         String Numberr = txtNumero.getText();
        int Numero = Integer.parseInt(txtNumero.getText());
        String Name = txtName.getText();
        String Password = txtPassword.getText();
      
        int h = rs.existeMail2(Email);
        if (txtCin.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()|| txtNumero.getText().isEmpty() || txtName.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } else if (rs.existeNumm(Numero) != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, " Le Numero deja exist", ButtonType.OK);
            a.showAndWait();
            txtNumero.setText("");

            txtNumero.requestFocus();
        } else if (rs.isNumeric(Numberr) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, " Verifyer Numero deja exist", ButtonType.OK);
            a.showAndWait();
            txtNumero.setText("");

            txtNumero.requestFocus();
        } else if (rs.isValidPhoneNumber(Numberr) != true) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Numero", ButtonType.OK);
            a.showAndWait();
            txtNumero.setText("");

            txtNumero.requestFocus();
        } else if (rs.isValidString(Name) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre nom", ButtonType.OK);
            a.showAndWait();
            txtName.setText("");

            txtName.requestFocus();
        } else if (rs.isValidString(Adresse) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Prenom", ButtonType.OK);
            a.showAndWait();
            txtAdresse.setText("");

            txtAdresse.requestFocus();
        } else if (h != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le Email est deja utuliser", ButtonType.OK);
            a.showAndWait();
            txtEmail.setText("");

            txtEmail.requestFocus();

        }  else {

            User u = new User(CIN ,Name, Adresse, Password, Email, Numero);
            System.out.println(u);
            rs.AddMedcin(u);
            clearForms2();
            refreshTable();
        }
     }
    private void clearForms2() {
        txtCin.setText("");
        txtNumero.setText("");
        txtName.setText("");
        txtAdresse.setText("");
        txtEmail.setText("");
        txtPassword.setText("");

    }
    @FXML
    private void updateMedcin(ActionEvent event) {
         int numero = Integer.parseInt(NumeroTelFld.getText()) ;
        String cin=cinFld.getText();
        String name =NameFld.getText();
        String adresse =AdresseFld.getText();
        String email=EmailFld.getText();
           
        User u = new User();
        u.setNumero(Integer.parseInt(NumeroTelFld.getText()));
        u.setCIN(cinFld.getText());
        u.setName(NameFld.getText());
        u.setEmail(EmailFld.getText());
        u.setAdresse(AdresseFld.getText());
        
        

        User user = MedcinTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();

           UserService sc=new UserService();
          sc.modifierUtilisateur(IDMedcinToUpdate,u);
  
            refreshTable();
        System.out.println(u);
        System.out.println(IDMedcinToUpdate);
    }

    @FXML
    private void deleteClient(ActionEvent event) {
          UserService userserv = new UserService();
        userserv.BannedUtilisateur(IDMedcinToUpdate);
        clearForms();
        refreshTable();
    }
    private void clearForms() {
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");

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
    private void Client(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/ClientListe.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void pharmacien(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/PharmacienList.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void medcin(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
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
    private void coach(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/CoachList.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void ban(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/BanList.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

  
}
