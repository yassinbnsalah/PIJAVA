/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Rendezvous;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author AMIRA
 */
public class Addrendezvous implements Initializable {

    @FXML
    private DatePicker Date_rv;
    @FXML
    private TextField rdhe;
    @FXML
    private TextField rdpsg;
    @FXML
    private TextField rdnt;
    @FXML
    private Button rdbttn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 /*Rendezvous rdv = new Rendezvous(rdhe.getText(), rdpsg.getText(), rdnt.getText(),rdbttn.getText(),  "Available",  1); 
        rdv.setDate_rv(Date.valueOf(Date_rv.valueProperty().getValue()));
        RendezvousService rdvService = new RendezvousService(); 
        rdvService.AddRendezvous(rdv);
        System.out.println("Disponnibility created succeffuly");*/
    }}
        
    

