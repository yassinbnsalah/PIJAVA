/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.users.pharmacien;

import controllers.OrderListeController;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class PharmacienListController implements Initializable {
ObservableList<User> PharmacienList = FXCollections.observableArrayList();
   
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
    private PasswordField txtPassword;
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
    private int IDPharmacienToUpdate;
    @FXML
    private TableView<User> PharmacienTable;
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
    @FXML
    private Button btnBan;
    @FXML
    private TextField searchfld;
    @FXML
    private Label userName;
    public int getIDPharmacienToUpdate() {
        return IDPharmacienToUpdate;
    }
    
    public void setIDPharmacienToUpdate(int IDPharmacienToUpdate) {
        this.IDPharmacienToUpdate = IDPharmacienToUpdate;
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
        UserService pharmacienserv = new UserService();
        PharmacienList.clear();
        PharmacienList.addAll(pharmacienserv.pharmacienListe());
        PharmacienTable.setItems(PharmacienList);
        ColCin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
  
        
        PharmacienTable.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = PharmacienTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(PharmacienTable.getItems().get(myIndex).getId()));
                    setIDPharmacienToUpdate(id);
                    cinFld.setText(PharmacienTable.getItems().get(myIndex).getCIN().toString());
                     NameFld.setText(PharmacienTable.getItems().get(myIndex).getName().toString());
                      NumeroTelFld.setText(PharmacienTable.getItems().get(myIndex).getNumero());
                       AdresseFld.setText(PharmacienTable.getItems().get(myIndex).getAdresse().toString());
                        EmailFld.setText(PharmacienTable.getItems().get(myIndex).getEmail().toString());
                        
                   
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
    private void CreatePharmacien(ActionEvent event) {
    UserService rs = new UserService();
        String CIN = txtCin.getText();
        String Email = txtEmail.getText();
        String Adresse = txtAdresse.getText();

         String Numberr = txtNumero.getText();
        String Numero = txtNumero.getText();
        String Name = txtName.getText();
        String Password = txtPassword.getText();
      
        int h = rs.existeMail2(Email);
        if (txtCin.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()|| txtNumero.getText().isEmpty() || txtName.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } 
        /*else if (rs.existeNumm(Numero) != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, " Le Numero deja exist", ButtonType.OK);
            a.showAndWait();
            txtNumero.setText("");

            txtNumero.requestFocus();
        } */else if (rs.isNumeric(Numberr) == false) {
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
            rs.AddPharmacien(u);
            clearForms2();
            refreshTable();
        }
    }

    @FXML
    private void updatePharmacien(ActionEvent event) {
        
        int numero = Integer.parseInt(NumeroTelFld.getText()) ;
        String cin=cinFld.getText();
        String name =NameFld.getText();
        String adresse =AdresseFld.getText();
        String email=EmailFld.getText();
           
        User u = new User();
        u.setNumero(NumeroTelFld.getText());
        u.setCIN(cinFld.getText());
        u.setName(NameFld.getText());
        u.setEmail(EmailFld.getText());
        u.setAdresse(AdresseFld.getText());
        u.setId(IDPharmacienToUpdate);
        

        User user = PharmacienTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();

           UserService sc=new UserService();
          sc.modifierUtilisateur(u);
  
            refreshTable();
        System.out.println(u);
        System.out.println(IDPharmacienToUpdate);

        
        
    
    }

    @FXML
    private void deleteClient(ActionEvent event) {
         UserService userserv = new UserService();
        userserv.BannedUtilisateur(IDPharmacienToUpdate);
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
    private void clearForms() {
        cinFld.setText("");
        NameFld.setText("");
        NumeroTelFld.setText("");
        AdresseFld.setText("");
        EmailFld.setText("");

    }
    @FXML
    private void Ticket(ActionEvent event) throws IOException {
               Routage.getInstance().GOTO(btnOrders, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        // clear user session data
        SessionManager.getInstance().Logout();
           Routage.getInstance().GOTO(btnTicket, "/view/LoginPage.fxml");
      
    }

    @FXML
    private void Client(ActionEvent event) throws IOException {
         Routage.getInstance().GOTO(btnTicket, "/view/users/client/ClientListe.fxml");
    }

    private void Pharmacien(ActionEvent event) {
           Routage.getInstance().GOTO(btnOrders, "/view/users/coach/CoachList.fxml");
    }

    @FXML
    private void medcin(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void coach(ActionEvent event) {
        Routage.getInstance().GOTO(btnOrders, "/view/users/coach/CoachList.fxml");
    }

    @FXML
    private void ban(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/banliste/BanList.fxml");
    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }

      @FXML
    private void search(KeyEvent event) {
        PharmacienTable.setItems(FXCollections.observableArrayList(searchList(searchfld.getText(), PharmacienList)));
    }

    private List<User> searchList(String searchWords, List<User> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getName().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getEmail().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}
