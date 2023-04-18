/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Api.GmailMailer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import models.Subscription;
import models.User;
import services.SubServices;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class ClientListeController implements Initializable {
 ObservableList<User> ClientList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> ClientTable;
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
    private TextField txtEmail;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtName;
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
    private Button btnSubscription;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnSignout;
    private int IDClientToUpdate;
    
    public int getIDClientToUpdate() {
        return IDClientToUpdate;
    }
    
    public void setIDClientToUpdate(int IDClientToUpdate) {
        this.IDClientToUpdate = IDClientToUpdate;
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
        UserService clientserv = new UserService();
        ClientList.clear();
        ClientList.addAll(clientserv.userListe());
        ClientTable.setItems(ClientList);
        ColCin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
  
        
        ClientTable.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = ClientTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(ClientTable.getItems().get(myIndex).getId()));
                    setIDClientToUpdate(id);
                    cinFld.setText(ClientTable.getItems().get(myIndex).getCIN().toString());
                     NameFld.setText(ClientTable.getItems().get(myIndex).getName().toString());
                      NumeroTelFld.setText(Integer.toString(ClientTable.getItems().get(myIndex).getNumero()));
                       AdresseFld.setText(ClientTable.getItems().get(myIndex).getAdresse().toString());
                        EmailFld.setText(ClientTable.getItems().get(myIndex).getEmail().toString());
                        
                   
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
    private void CreateClient(ActionEvent event) {UserService rs = new UserService();
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
            rs.AddUser(u);
            clearForms2();
            refreshTable();
        }
    }
    @FXML
    private void deleteClient(ActionEvent event) {
        UserService userserv = new UserService();
        userserv.supprimerUtilisateur(IDClientToUpdate);
        clearForms();
        refreshTable();
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
    private void updateClient(ActionEvent event) {
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
        
        

        User user = ClientTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();

           UserService sc=new UserService();
          sc.modifierUtilisateur(IDClientToUpdate,u);
  
            refreshTable();
System.out.println(u);
System.out.println(IDClientToUpdate);

        
        
    
    }
   
    private void clearForms() {
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");

    }


    @FXML
    private void handleClicks(ActionEvent event) {
    }


 @FXML
    private void Ticket(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/TicketListe.fxml"));
            Parent root = loader.load();
            btnTicket.getScene().setRoot(root);
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
    }

    @FXML
    private void textfieldDesign1(MouseEvent event) {
    }

    @FXML
    private void textfieldDesign1(KeyEvent event) {
    }
    
}
