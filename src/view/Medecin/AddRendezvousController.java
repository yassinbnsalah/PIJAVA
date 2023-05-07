/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Medecin;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Rendezvous;
import models.User;
import services.RendezvousService;
import services.UserService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddRendezvousController implements Initializable {

    RendezvousService rendezvousService;
    ObservableList<Rendezvous> listRendezvouses = FXCollections.observableArrayList();
    Rendezvous rv;
    UserService userService;

    @FXML
    private Button ajout;
    @FXML
    private Button editbttn;
    @FXML
    private Button supbttn;
    @FXML
    private DatePicker daterendzeVousF;
    @FXML
    private DatePicker datePassageField;
    @FXML
    private TextField noteF;
    @FXML
    private TableView<Rendezvous> rendezVousTableView;
    @FXML
    private TableColumn<Rendezvous, String> datecol;
    @FXML
    private TableColumn<Rendezvous, String> datePassagecoll;
    @FXML
    private TableColumn<Rendezvous, String> stateCol;
    @FXML
    private TableColumn<Rendezvous, String> noteCol;
    @FXML
    private TextField heureRVF;
    @FXML
    private TextField HeurePassageF;
    @FXML
    private TableColumn<Rendezvous, String> HeureRCol;
    @FXML
    private TableColumn<Rendezvous, String> heurePCol;
    @FXML
    private ComboBox<User> userCB;
    @FXML
    private ComboBox<User> userCB1;
    @FXML
    private TableColumn<Rendezvous, String> patientCell;
    @FXML
    private TableColumn<Rendezvous, String> DocteurCell;
    @FXML
    private Label userEmaillbl;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Button ClientDashboard;
    @FXML
    private Button btnRendezVous;
    @FXML
    private Button btnOrdennance;
    @FXML
    private Button Calanderbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userEmaillbl.setText(SessionManager.getInstance().getUser().getEmail());
        rendezvousService = new RendezvousService();
        userService = new UserService();
        LoadData();

        ObservableList<User> list = FXCollections.observableArrayList();
        list.addAll(userService.userListe());
        userCB.setItems(list);

        userCB1.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.addAll("confirm", "Cancel", "inconfirmed");
    }

    public void refreshTable() {

        listRendezvouses.clear();
        listRendezvouses.addAll(rendezvousService.RendezvousListe());
        rendezVousTableView.setItems(listRendezvouses);

    }

    private void LoadData() {

        refreshTable();
        datecol.setCellValueFactory(new PropertyValueFactory<>("date_rv"));
        datePassagecoll.setCellValueFactory(new PropertyValueFactory<>("date_passage_rv"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        heurePCol.setCellValueFactory(new PropertyValueFactory<>("hour_passage_rv"));
        HeureRCol.setCellValueFactory(new PropertyValueFactory<>("hour_rv"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        patientCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getUser().getNumero() + "");
        });;

        DocteurCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getDoctor().getName());
        });;

    }

    @FXML
    private void create(ActionEvent event) {
        if (controleSaisie()) {
            rv = new Rendezvous();
            rv.setDate_passage_rv(Timestamp.valueOf(datePassageField.getValue().atStartOfDay()));
            rv.setDate_rv(Timestamp.valueOf(daterendzeVousF.getValue().atStartOfDay()));
            rv.setNote(noteF.getText());
            rv.setHour_passage_rv(HeurePassageF.getText());
            rv.setHour_rv(heureRVF.getText());
            rv.setUser(userCB.getSelectionModel().getSelectedItem());
            rv.setDoctor(userCB1.getSelectionModel().getSelectedItem());

            rendezvousService.AddRendezvous(rv);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ajout suuccess");
            alert.setTitle("success");
            alert.show();

            refreshTable();

            noteF.setText("");
            HeurePassageF.setText("");
            heureRVF.setText("");
            datePassageField.setValue(null);
            daterendzeVousF.setValue(null);

        }
        rv = null;

    }

    @FXML
    private void edit(ActionEvent event) {

        if (datePassageField.getValue() == null && daterendzeVousF.getValue() == null && HeurePassageF.getText().equals("") && heureRVF.getText().equals("")) {
            rv = rendezVousTableView.getSelectionModel().getSelectedItem();

            if (rv == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("selecet rendez vous");
                alert.setTitle("fail");
                alert.show();
                return;
            }
            datePassageField.setValue(rv.getDate_passage_rv().toLocalDateTime().toLocalDate());
            daterendzeVousF.setValue(rv.getDate_rv().toLocalDateTime().toLocalDate());
            HeurePassageF.setText(rv.getHour_passage_rv());
            heureRVF.setText(rv.getHour_rv());
            noteF.setText(rv.getNote());

        } else {
            rv.setDate_passage_rv(Timestamp.valueOf(datePassageField.getValue().atStartOfDay()));
            rv.setDate_rv(Timestamp.valueOf(daterendzeVousF.getValue().atStartOfDay()));
            rv.setNote(noteF.getText());
            rv.setHour_passage_rv(HeurePassageF.getText());
            rv.setHour_rv(heureRVF.getText());
            rv.setUser(userCB.getSelectionModel().getSelectedItem());
            rv.setDoctor(userCB1.getSelectionModel().getSelectedItem());

            if (controleSaisie()) {
                rendezvousService.update(rv.getId(), rv);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("modif suuccess");
                alert.setTitle("success");
                alert.show();

                refreshTable();
                noteF.setText("");
                HeurePassageF.setText("");
                heureRVF.setText("");
                datePassageField.setValue(null);
                daterendzeVousF.setValue(null);
            }
        }
    }

    @FXML
    private void suprimer(ActionEvent event) {

        rv = rendezVousTableView.getSelectionModel().getSelectedItem();
        if (rv == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("selecet rendez vous");
            alert.setTitle("fail");
            alert.show();
            return;
        }

        rendezvousService.delete(rv.getId());
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("delete avec succès");
        alert.setTitle("Succès");
        alert.show();

        rv = null;
    }

    private boolean controleSaisie() {
        if (HeurePassageF.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le heure passage");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (heureRVF.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le heure render vous");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (datePassageField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir date passage");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    @FXML
    private void confirme(ActionEvent event) {
        rv = rendezVousTableView.getSelectionModel().getSelectedItem();

        if (rv.getState().equals("confirm")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("rendeze vous deja confirmer");
            alert.setTitle("fail");
            alert.show();
        } else {
            rendezvousService.confirmRendzeVous(rv);
            refreshTable();

        }

        rv = null;
    }

    @FXML
    private void cancel(ActionEvent event) {
        rv = rendezVousTableView.getSelectionModel().getSelectedItem();

        if (rv.getState().equals("Cancel")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("rendeze vous deja cancled");
            alert.setTitle("fail");
            alert.show();
        } else {
            rendezvousService.cancelRendzeVous(rv);
            refreshTable();
        }

        rv = null;
    }


    @FXML
    private void logout(ActionEvent event) {
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

  

  @FXML
    private void GoToClientDashboard(ActionEvent event) {
        Routage.getInstance().GOTO(ClientDashboard, "/view/client/subscription/subscriptionhistory.fxml");

    }
    @FXML
    private void GoToRendezVous(ActionEvent event) {
        Routage.getInstance().GOTO(btnRendezVous, "/view/Medecin/AddRendezvous.fxml");
    }

    @FXML
    private void GoToOrdennanceListe(ActionEvent event) {
           Routage.getInstance().GOTO(btnOrdennance, "/view/Medecin/AddOrdennance.fxml");
    }

  

    @FXML
    private void Availability(ActionEvent event) {
        Routage.getInstance().GOTO(btnOrders, "/view/Medecin/Calendar.fxml");
    }

    @FXML
    private void DisplayCalender(ActionEvent event) {
    }

}
