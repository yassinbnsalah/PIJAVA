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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Disponibility;
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
    @FXML
    private Label lblerrortxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblerrortxt.setVisible(false);
    }

    public boolean verifyHoure(String ch) {
        int ind = ch.indexOf(":");
        String heure = ch.substring(0, ind);
        String minute = ch.substring(ind + 1, ch.length());
        int heurestart;
        int minuteStart;
        try {
            heurestart = Integer.parseInt(heure);
            minuteStart = Integer.parseInt(minute);
        } catch (Exception ex) {
            return false;
        }
        if (heurestart > 23 || minuteStart > 60) {
            return false;
        }
        return true;
    }

    @FXML
    private void OnHandleClickCreate(ActionEvent event) {
        verifyHoure(txtHeureStart.getText());
        if (txtHeureStart.getText().length() != 0 && txtHeureEnd.getText().length() != 0 && txtNode.getText().length() != 0
                && verifyHoure(txtHeureStart.getText()) && verifyHoure(txtHeureEnd.getText())) {

            Disponibility dispo = new Disponibility(txtHeureStart.getText(), txtHeureEnd.getText(), txtNode.getText(), "Available", 1);
            dispo.setDateDispo(Date.valueOf(DateDispo.valueProperty().getValue()));
            DisponibilityService dispoService = new DisponibilityService();
            dispoService.AddDisponibility(dispo);
           // DisponibilityListeController adc = new DisponibilityListeController();
            //adc.refreshTable();
            System.out.println("Disponnibility created succeffuly");
            Stage stage = (Stage) btnCreate.getScene().getWindow();
            // do what you have to do
            stage.close();
        } else {
            lblerrortxt.setVisible(true);
            lblerrortxt.setText("No Data Founded");
        }

    }

}
