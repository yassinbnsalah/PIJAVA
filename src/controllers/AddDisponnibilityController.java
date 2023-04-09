/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import models.Disponibility ; 
import services.DisponibilityService;
/**
 * FXML Controller class
 *
 * @author yacin
 */
public class AddDisponnibilityController implements Initializable {

    @FXML
    private DatePicker DateDispo;
    @FXML
    private TextField txtHeureStart;
    @FXML
    private TextField txtHeureEnd;
    @FXML
    private TextArea txtNode;
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
    private void OnHandleClickCreate(ActionEvent event) {
        Disponibility dispo = new Disponibility(txtHeureStart.getText(),  txtHeureEnd.getText(),  txtNode.getText(),  "Available",  1); 
        dispo.setDateDispo(Date.valueOf(DateDispo.valueProperty().getValue()));
        DisponibilityService dispoService = new DisponibilityService(); 
        dispoService.AddDisponibility(dispo);
        System.out.println("Disponnibility created succeffuly");
    }
    
}
