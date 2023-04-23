/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.User;
import services.UserService;
import util.BCrypt;
import util.MyConnection;
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
    private Button btnSignin1;
    @FXML
    private Button login_btn;
    private Hyperlink login_acc;
    private AnchorPane signup_form;
    private AnchorPane login_form;

    @FXML
    private Hyperlink create_acc;
 private Connection cnx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblErrors.setVisible(false);
        // TODO
    }

    private void LoginBTN(ActionEvent event) {
        
  

    }

    private void setLblError(Color color, String text) {
        lblErrors.setVisible(true);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    private void login(ActionEvent event) throws IOException{
        UserService us = new UserService();
        if(txtUsername.getText().equals("ADMIN@gmail.com") && txtPassword.getText().equals("ADMIN123:") )
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("FITHAELTH CENTER :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Bienvenu Admin");
                     alert.showAndWait();
                     
                  Parent root = FXMLLoader.load(getClass().getResource("/Gui/Admin.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }else {
            
            String query2="select * from user where email=?  and password=?";
            cnx = MyConnection.getInstance().getCnx();
                String email = txtUsername.getText();
                 String pass = txtPassword.getText();
           try{
              PreparedStatement smt = cnx.prepareStatement(query2);
       
               smt.setString(1,txtUsername.getText());
               smt.setString(2,txtPassword.getText());
              
               ResultSet rs= smt.executeQuery();
               User p;
                if(rs.next() ){
                     p=new User(rs.getString("cin"),rs.getString("name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("password"));
                     User.setCurrent_User(p);
                     SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
                     System.out.println(User.Current_User.getEmail());
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("FITHAELTH CENTER :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Vous etes connecté");
                     alert.showAndWait();
                    login_btn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/Gui/ClientListe.fxml"));
                     Scene scene;
                    scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
                
                    
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FITHAELTH CENTER :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();  
                }
          
      }catch(Exception ex){
           System.out.println(ex.getMessage());
      }

            
        }
         
    }

    

    @FXML
    private void changeForm(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/SignUp.fxml"));
            Parent root = loader.load();
            login_btn.getScene().setRoot(root);
    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
         if (txtUsername.isFocused()){
            txtUsername.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            txtPassword.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
            
        }else if (txtPassword.isFocused()){
            txtUsername.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            txtPassword.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
        } 
    }
    

    @FXML
    private void openForget(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/ForgotPass.fxml"));
            Parent root = loader.load();
            login_btn.getScene().setRoot(root);

    }
     
}
