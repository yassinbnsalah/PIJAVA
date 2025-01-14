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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.UserService;
import util.Routage;
import util.SessionManager;

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
    private Label lblErrors;
    @FXML
    private Button btnSignin1;
    @FXML
    private Hyperlink create_acc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblErrors.setVisible(false);
        // TODO
    }

    @FXML
    private void LoginBTN(ActionEvent event) {
        UserService usrservice = new UserService();
        String state = usrservice.LoginUser(txtUsername.getText(), txtPassword.getText());
        System.out.println("state : " + state);
        if (state.equals("success")) {
          
                //setLblError(Color.GREEN, "Login Successful..Redirecting..");
                SessionManager sm = SessionManager.getInstance() ; 
                if(sm.getUser().getRoles().equals("[\"ROLE_CLIENT\"]")){
                    Routage.getInstance().GOTO(btnSignin1, "/view/client/order/orderHistory.fxml");
                }else if(sm.getUser().getRoles().equals("[\"ROLE_ADMIN\"]")){
                     Routage.getInstance().GOTO(btnSignin1, "/view/admin/subscription/subscriptionListe.fxml");
                }else if(sm.getUser().getRoles().equals("[\"ROLE_MEDCIN\"]")){
                     Routage.getInstance().GOTO(btnSignin1, "/view/Medecin/disponibilityListe.fxml");
                }else if(sm.getUser().getRoles().equals("[\"ROLE_PHARMACIEN\"]")){
                     Routage.getInstance().GOTO(btnSignin1, "/view/Pharmacien/AddMedicamment.fxml");
                }else if(sm.getUser().getRoles().equals("[\"ROLE_COACH\"]")){
                     Routage.getInstance().GOTO(btnSignin1, "/view/coach/activity.fxml");
                }
                  
        } else if (state.equals("Empty Credits")) {
            setLblError(Color.TOMATO, "Empty credentials");
        } else {
            setLblError(Color.TOMATO, "Enter Correct Email/Password");
        }

    }

    private void setLblError(Color color, String text) {
        lblErrors.setVisible(true);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    private void openForget(ActionEvent event) {
        Routage.getInstance().GOTO(btnSignin1, "/view/ForgotPass.fxml");
          
    }

    @FXML
    private void changeForm(ActionEvent event) {
         Routage.getInstance().GOTO(btnSignin1, "/view/Signup.fxml");
    }


}
