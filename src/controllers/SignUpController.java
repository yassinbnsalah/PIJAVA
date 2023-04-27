/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.EmailManager;


/**
 * FXML Controller class
 *
 * @author 21693
 */
public class SignUpController implements Initializable {

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
    private PasswordField tfMdp2;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

       @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
            Parent root = loader.load();
            back.getScene().setRoot(root);
    }

    @FXML
    private void CreateClient(ActionEvent event) {
        UserService rs = new UserService();
        String CIN = txtCin.getText();
        String Email = txtEmail.getText();
        String Adresse = txtAdresse.getText();

         String Numberr = txtNumero.getText();
        String Numero =txtNumero.getText();
        String Name = txtName.getText();
        String Password = txtPassword.getText();
        String mdp2 = tfMdp2.getText();
      
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
        }*/ else if (rs.isNumeric(Numberr) == false) {
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

        } else if (!(mdp2.equals(Password))) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Password", ButtonType.OK);
            a.showAndWait();
            txtPassword.setText("");
            tfMdp2.setText("");
            txtPassword.requestFocus();}
           else {

            User u = new User(CIN ,Name, Adresse, Password, Email, Numero);
            System.out.println(u);
            rs.AddUser(u);
    
            if ((validateEmaill())) {
                String tilte = "Inscription  Success ";
                String message = "un message a etes envoyer ";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
                EmailManager m = new EmailManager();
                String sub = "Bienvenue sur Notre Application";
                String sub2 = " Votre Mot de pass est: " + u.getPassword();
                String content = "Bonjour Mr/Mme " + u.getName() + ". Au nom de tous les membres du Fithaelth, je vous souhaite la bienvenue.";
                String finalcontant = sub + content + sub2;
                m.sendEmail(u.getEmail(), finalcontant);

                navigateToLogin(event);

            }
        }
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

    private void navigateToLogin(ActionEvent actionEvent) {
        navigateTo(actionEvent, "/view/LoginPage.fxml");
    }
    private void navigateTo(ActionEvent actionEvent, String path) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource(path));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }
}
