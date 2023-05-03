/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Medecin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Medicamment;
import models.Ordennance;
import models.OrdennanceLigne;
import services.MedicamantService;
import services.OrdennanceLigneService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class AddLigneOrdonanceController implements Initializable {

    Ordennance ordennance;
    MedicamantService medicamantService;
    OrdennanceLigne ordennanceLigne;
    ObservableList<OrdennanceLigne> listOrdennances = FXCollections.observableArrayList();
    OrdennanceLigneService ordennanceLigneService;
    @FXML
    private AnchorPane rechercheField;
    @FXML
    private Button bttnajoutermedic;
    @FXML
    private TextField qttext;
    @FXML
    private Button editbttn;
    @FXML
    private Button supbttn;
    @FXML
    private ComboBox<Medicamment> medicamentCB;
    @FXML
    private TableView<OrdennanceLigne> ordreLigneTableView;
    @FXML
    private TableColumn<OrdennanceLigne, String> namecol;
    @FXML
    private TableColumn<OrdennanceLigne, String> Qtcol;
    @FXML
    private Label userEmaillbl;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Button ClientDashboard;
    @FXML
    private Button RendezVousbtn;
    @FXML
    private Button btnOrdennance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicamantService = new MedicamantService();
        ordennanceLigneService = new OrdennanceLigneService();

        ObservableList<Medicamment> list = FXCollections.observableArrayList();
        list.addAll(medicamantService.medicammentList());
        medicamentCB.setItems(list);
    }

    public void refreshTable() {
        listOrdennances.clear();
        listOrdennances.addAll(ordennanceLigneService.ordennanceligneListe(ordennance.getId()));
        ordreLigneTableView.setItems(listOrdennances);

    }

    public void LoadData() {
        refreshTable();
        namecol.setCellValueFactory(new PropertyValueFactory<>("nomMedicament"));
        Qtcol.setCellValueFactory(new PropertyValueFactory<>("quantite"));

    }

    @FXML
    private void createMedicament(ActionEvent event) {
        if (controleSaisie()) {

            ordennanceLigne = new OrdennanceLigne();
            ordennanceLigne.setOrdennance_id(ordennance.getId());
            ordennanceLigne.setQuantite((int) Float.parseFloat(qttext.getText()));
            ordennanceLigne.setMedicament_id(medicamentCB.getSelectionModel().getSelectedItem().getId());

            ordennanceLigneService.addOrdennanceLigne(ordennanceLigne);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ajout suuccess");
            alert.setTitle("success");
            alert.show();

            refreshTable();

            qttext.setText("");
            medicamentCB.getSelectionModel().select(null);

            ordennanceLigne = null;

        }
    }

    @FXML
    private void edit(ActionEvent event) {
    }

    @FXML
    private void suprimer(ActionEvent event) {
        ordennanceLigne = ordreLigneTableView.getSelectionModel().getSelectedItem();
        if (ordennanceLigne == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("selecet ordennace ligne");
            alert.setTitle("fail");
            alert.show();
            return;
        }

        ordennanceLigneService.deleteMedicamment(ordennanceLigne.getId());
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("delete avec succès");
        alert.setTitle("Succès");
        alert.show();

        ordennance = null;

    }

    public void initOrder(Ordennance o) {
        this.ordennance = o;
    }

    private boolean controleSaisie() {
        if (medicamentCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le medicament");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        try {
            Float.parseFloat(qttext.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(qttext.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        return true;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
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
    private void GoToClientListe(ActionEvent event) {
    }

    @FXML
    private void GoToRendezVous(ActionEvent event) {
        Routage.getInstance().GOTO(supbttn, "/view/Medecin/AddRendezvous.fxml");
    }

    @FXML
    private void GoToOrdennanceListe(ActionEvent event) {
             Routage.getInstance().GOTO(supbttn, "/view/Medecin/AddOrdennance.fxml");
    }
}
