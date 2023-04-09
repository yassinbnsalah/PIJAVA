/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label btnForgot;
    @FXML
    private Label lblErrors;
    @FXML
    private Button btnSignin1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LoginBTN(ActionEvent event) {
        UserService usrservice = new UserService();
        String state = usrservice.LoginUser(txtUsername.getText(), txtPassword.getText());
        System.out.println("state : " + state);
        if (state.equals("success")) {
            try {
                //setLblError(Color.GREEN, "Login Successful..Redirecting..");
                
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/subscription/subscriptionListe.fxml"));
            Parent root = loader.load();
            btnSignin1.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (state.equals("Empty Credits")) {
            setLblError(Color.TOMATO, "Empty credentials");
        } else {
            setLblError(Color.TOMATO, "Enter Correct Email/Password");
        }

    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

}
