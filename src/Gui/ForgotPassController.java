/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.Mail;
import util.SharedData;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class ForgotPassController implements Initializable {

    @FXML
    private PasswordField newpass;
    @FXML
    private Button save;

    @FXML
    private TextField mail;
    @FXML
    private Button sendmail;
    @FXML
    private TextField mailcode;
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
    private void save(ActionEvent event) throws IOException {
        SharedData da = new SharedData();

        int code = Integer.parseInt(mailcode.getText());
        
        UserService us = new UserService();
        String mailin = mail.getText();
        String pass = newpass.getText();
        if (newpass.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } else if (us.existeMail2(mailin) == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Email", ButtonType.OK);
            a.showAndWait();
            mail.setText("");

            mail.requestFocus();

        } else if (code != da.GenratedCode) {

            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Code de verification", ButtonType.OK);
            a.showAndWait();
            mail.setText("");

            mail.requestFocus();
        }else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Comfirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de modifier ?");
            Optional<ButtonType> action = alert.showAndWait();
            System.out.println("User Updated");
            User U = new User();
            String mailinE = mail.getText();
            U = us.getByEmail(mailinE);
            System.out.println(U);
            U.setPassword(pass);
            System.out.println(U.getId());
            System.out.println(U.getPassword());
            us.modifier2(U.getId(), U);
            String tilte = "Resset Password Success ";
            String message = "un message a etes envoyer ";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            Mail m = new Mail();

            String sub = "Reset-Password Affected ";
            String sub2 = " Votre nouveaux Mot de pass est: " + U.getPassword();
            String content = "Bonjour Mr/Mme " + U.getName() + ". Au nom de tous les membres du FITHAELTH, je vous informer que le mot de passe est change avec succes ";
            String finalcontant = sub + content + sub2;
            m.sendEmail(U.getEmail(), finalcontant);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"))));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();

        }

    }

   
    @FXML
    private void sendmail(ActionEvent event) throws IOException {

        Random rand = new Random();
        int upperbound = 25555;
        int numer = rand.nextInt(upperbound);

        String mailin = mail.getText();
        Mail m = new Mail();
        String content = "Bonjour Mr/Mme " + ". Votre code est = " + numer;
        String finalcontant = content;
        System.out.println(numer);
        m.sendEmail(mailin, finalcontant);
        SharedData da = new SharedData();
        da.GenratedCode = numer;
        System.out.println(da.GenratedCode);

    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Login.fxml"));
            Parent root = loader.load();
            back.getScene().setRoot(root);
    }
    
    
}
