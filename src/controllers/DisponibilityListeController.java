/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.time.ZoneId;
import javafx.util.Callback;
import models.Disponibility;
import services.DisponibilityService;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class DisponibilityListeController implements Initializable {

    private int IDToUpdate;
    @FXML
    private Button updatebtn;
    @FXML
    private Button updatebtn2;

    public int getIDToUpdate() {
        return IDToUpdate;
    }

    public void setIDToUpdate(int IDToUpdate) {
        this.IDToUpdate = IDToUpdate;
    }

    @FXML
    private Button btnAddDisponnibility;
    @FXML
    private TableColumn<Disponibility, String> colReference;
    @FXML
    private TableColumn<Disponibility, String> colDateDispo;
    @FXML
    private TableColumn<Disponibility, String> ColHeureStart;
    @FXML
    private TableColumn<Disponibility, String> ColHeureEnd;

    ObservableList<Disponibility> DispoList = FXCollections.observableArrayList();
    @FXML
    private TableView<Disponibility> TableDispo;
    @FXML
    private TableColumn<Disponibility, String> ColState;
    @FXML
    private DatePicker DatePickfld;
    @FXML
    private TextField heureStartTxt;
    @FXML
    private TextField heureEndTxt;
    @FXML
    private TextArea NoteTXT;
    @FXML
    private TextField oldDateTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.refreshTable();
    }

    @FXML
    private void GoToDisponnibility(ActionEvent event) {
        System.out.println("disponinbility Liste");
    }

    @FXML
    private void CreateNewDIsponnibility(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Medecin/AddDisponnibility.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() {
        DisponibilityService dispservice = new DisponibilityService();
        DispoList.clear();
        DispoList.addAll(dispservice.disponibilityListe(1));
        TableDispo.setItems(DispoList);
        colReference.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDateDispo.setCellValueFactory(new PropertyValueFactory<>("dateDispo"));
        ColHeureStart.setCellValueFactory(new PropertyValueFactory<>("heureStart"));
        ColHeureEnd.setCellValueFactory(new PropertyValueFactory<>("heureEnd"));
        ColState.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableDispo.setRowFactory(tv -> {
            TableRow<Disponibility> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = TableDispo.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(TableDispo.getItems().get(myIndex).getId()));
                    oldDateTxt.setText(TableDispo.getItems().get(myIndex).getDateDispo().toString());
                    this.setIDToUpdate(TableDispo.getItems().get(myIndex).getId());
                    heureStartTxt.setText(String.valueOf(TableDispo.getItems().get(myIndex).getHeureStart()));
                    heureEndTxt.setText(String.valueOf(TableDispo.getItems().get(myIndex).getHeureEnd()));
                    NoteTXT.setText(String.valueOf(TableDispo.getItems().get(myIndex).getNote()));
                }
            });
            return myRow;
        });

    }

    @FXML
    private void UpdateDispo(ActionEvent event) {
        DisponibilityService dispservice = new DisponibilityService();
        Disponibility dispo = new Disponibility();
        if (DatePickfld.valueProperty().getValue() != null) {
            System.out.println("date updated");
            dispo.setDateDispo(Date.valueOf(DatePickfld.valueProperty().getValue()));
        } else {
            System.out.println("date not updated");
            dispo.setDateDispo(Date.valueOf(oldDateTxt.getText()));

        }
        dispo.setHeureStart(heureStartTxt.getText());
        dispo.setHeureEnd(heureEndTxt.getText());
        dispo.setNote(NoteTXT.getText());
        dispo.setId(IDToUpdate);
        dispservice.updateDisponibilityData(dispo);
        oldDateTxt.setText("");
        DatePickfld.setValue(null);
        this.setIDToUpdate(0);
        heureStartTxt.setText("");
        heureEndTxt.setText("");
        NoteTXT.setText("");
        refreshTable();
    }

    @FXML
    private void updateStateDispo(ActionEvent event) {
        DisponibilityService dispservice = new DisponibilityService();
        dispservice.updateStateDisponibility(IDToUpdate);
        oldDateTxt.setText("");
        DatePickfld.setValue(null);
        this.setIDToUpdate(0);
        heureStartTxt.setText("");
        heureEndTxt.setText("");
        NoteTXT.setText("");
        refreshTable();
    }

    @FXML
    private void DeleteDisponibility(ActionEvent event) {
        DisponibilityService dispservice = new DisponibilityService();
        dispservice.deleteDisponibility(IDToUpdate);
        oldDateTxt.setText("");
        DatePickfld.setValue(null);
        this.setIDToUpdate(0);
        heureStartTxt.setText("");
        heureEndTxt.setText("");
        NoteTXT.setText("");
        refreshTable();
    }
}
