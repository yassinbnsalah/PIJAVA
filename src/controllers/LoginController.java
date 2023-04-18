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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    @FXML
    private Hyperlink login_acc;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private AnchorPane login_form;
    @FXML
    private Hyperlink mdp_oub;
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
        if(txtUsername.getText().equals("Fedi@gmail.com") && txtPassword.getText().equals("Fedi123:") )
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("Travel Me :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Bienvenu Admin");
                     alert.showAndWait();
                     
                  Parent root = FXMLLoader.load(getClass().getResource("/Gui/ClientList.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }else {
            
            String query2="select * from user where email=?  and password=?";
            cnx = MyConnection.getInstance().getCnx();
           try{
              PreparedStatement smt = cnx.prepareStatement(query2);
       
               smt.setString(1,txtUsername.getText());
               smt.setString(2,txtPassword.getText());
               ResultSet rs= smt.executeQuery();
               User p;
                if(rs.next()){
                     p=new User(rs.getString("cin"),rs.getString("name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("password"));
                     User.setCurrent_User(p);
                     SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
                     System.out.println(User.Current_User.getEmail());
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("Travel Me :: Success Message");
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
                alert.setTitle("Travel Me :: Error Message");
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
    private void sendPaswword_btn(ActionEvent event) {
         sendPassword();
    }

    @FXML
    private void changeForm(ActionEvent event) {
          if(event.getSource() == create_acc){
            signup_form.setVisible(true);
             login_form.setVisible(false);
        }else if(event.getSource()==login_acc){
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }
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
     void sendPassword(){
        System.out.println("cxcccccccccccccccccc");
                String query2="select * from user where email=? ";
                String email1="empty";
                 try {
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, txtUsername.getText());
             ResultSet rs= smt.executeQuery();
                if(rs.next()){
                   email1=rs.getString("email");
                     System.out.println(email1);
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
                 sendMail(email1);
    }
    public void sendMail(String recepient){
        String host = "smtp.gmail.com";
        System.out.println("Preparing to send email");
           Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        String myAccountEmail = "contact.fithealth23@gmail.com";
        String passwordd = "qavkrnciihzjmtkp@";
       
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,passwordd);
            }
        });
        Message message =preparedMessage(session,myAccountEmail,recepient);
        try{
            Transport.send(message);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("TravelMe :: Boite Mail");
                alert.setHeaderText(null);
                alert.setContentText("consulter votre boite mail !!");
                alert.showAndWait();  
           
        }catch(Exception ex){
            ex.printStackTrace();
           
        }
               
    }
     private Message preparedMessage(Session session, String myAccountEmail, String recepient){
         String query2="select * from user where email=?";
         String userEmail="null" ;
         String pass="empty";
        try {
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, txtUsername.getText());
             ResultSet rs= smt.executeQuery();
             System.out.println(rs);
                if(rs.next()){
                   pass=rs.getString("password");
                   userEmail=rs.getString("email");                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.print("c est en cours");
        String text="Votre mot de pass est :"+pass+"";
        String object ="Recupération de mot de passe";
        Message message = new MimeMessage(session);
        try{
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject(object);
        String htmlcode ="<h1> "+text+" </h1> <h2> <b> </b2> </h2> ";
        message.setContent(htmlcode, "text/html");
         System.out.println("Message envoyeer");
         
           System.out.println(pass);
           
        return message;
       
        }catch(MessagingException ex){
            ex.printStackTrace();
        }
    return null;
    }

}
