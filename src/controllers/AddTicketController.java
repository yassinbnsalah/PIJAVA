/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Ticket;
import services.TicketService;
import util.SessionManager;
import util.SharedData;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class AddTicketController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtSubject;
    @FXML
    private Button btnCreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnHandleClickCreate(ActionEvent event) throws IOException {
        if (txtTitle.getText().length() <= 5) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le Titre doit contenir au moins 10 caractéres!", ButtonType.APPLY.OK);
            a.setHeaderText("Sujet Invalide");
            a.setTitle("Error");
            a.showAndWait();
        } else if (txtSubject.getText().length() <= 10) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le contenu doit contenir au moins 20 caractéres!", ButtonType.APPLY.OK);
            a.setHeaderText("Contenu Invalide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            Date d = new Date(System.currentTimeMillis());
            Ticket ticket = new Ticket(d,txtTitle.getText(), txtSubject.getText(),SharedData.currentUser);
            TicketService tk = new TicketService();
            SessionManager session = SessionManager.getInstance();
            int ownerId = session.getId();
            tk.ajouterTicket(ownerId, ticket);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/TicketListe.fxml"));
            Parent root = loader.load();
            btnCreate.getScene().setRoot(root);
        }
    }



    
}
