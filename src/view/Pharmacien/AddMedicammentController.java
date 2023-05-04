/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Pharmacien;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Medicamment;
import services.MedicamantService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class AddMedicammentController implements Initializable {

    MedicamantService medicammentService;
    ObservableList<Medicamment> listMedicamments = FXCollections.observableArrayList();
    Medicamment medicamment;

    @FXML
    private TextField desctxt;
    @FXML
    private Button bttnajoutermedic;
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField qttext;
    @FXML
    private TextField prixtxt;
    @FXML
    private Button editbttn;
    @FXML
    private Button supbttn;
    @FXML
    private TableView<Medicamment> medicamentTableView;
    @FXML
    private TableColumn<Medicamment, String> namecol;
    @FXML
    private TableColumn<Medicamment, String> descCol;
    @FXML
    private TableColumn<Medicamment, String> Qtcol;
    @FXML
    private TableColumn<Medicamment, String> prixcol;
    @FXML
    private AnchorPane rechercheField;
    @FXML
    private TextField searchInput;
    @FXML
    private Label userEmaillbl;
    @FXML
    private Button btnMedicament;
    @FXML
    private Button btnSignout;
    @FXML
    private Button ClientDashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicammentService = new MedicamantService();
        LoadData();
    }

    public void refreshTable() {

        listMedicamments.clear();
        listMedicamments.addAll(medicammentService.medicammentList());

        FilteredList<Medicamment> filterData = recherche(listMedicamments);
        String a = searchInput.getText();
        medicamentTableView.setItems(filterData);

    }

    private void LoadData() {

        refreshTable();
        namecol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        Qtcol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));

    }

    @FXML
    private void createMedicament(ActionEvent event) {

        if (controleSaisie()) {
            medicamment = new Medicamment();
            medicamment.setDescription(desctxt.getText());
            medicamment.setNom(nomtxt.getText());
            medicamment.setPrix(Float.parseFloat(prixtxt.getText()));
            medicamment.setQuantite(Float.parseFloat(qttext.getText()));
            medicammentService.AddMedicamment(medicamment);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ajout suuccess");
            alert.setTitle("success");
            alert.show();

            refreshTable();

            desctxt.setText("");
            nomtxt.setText("");
            prixtxt.setText("");
            qttext.setText("");
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        medicamment = medicamentTableView.getSelectionModel().getSelectedItem();

        if (desctxt.getText().equals("") && nomtxt.getText().equals("") && prixtxt.getText().equals("") && qttext.getText().equals("")) {
            if (medicamment == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("selecet medicament");
                alert.setTitle("fail");
                alert.show();
                return;
            }
            desctxt.setText(medicamment.getDescription());
            nomtxt.setText(medicamment.getNom());
            prixtxt.setText(medicamment.getPrix() + "");
            qttext.setText(medicamment.getQuantite() + "");

        } else {
            medicamment.setDescription(desctxt.getText());
            medicamment.setNom(nomtxt.getText());
            medicamment.setPrix(Float.parseFloat(prixtxt.getText()));
            medicamment.setQuantite(Float.parseFloat(qttext.getText()));

            if (controleSaisie()) {
                medicammentService.modifierMedicamment(medicamment.getId(), medicamment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("modif suuccess");
                alert.setTitle("success");
                alert.show();

                refreshTable();
                desctxt.setText("");
                nomtxt.setText("");
                prixtxt.setText("");
                qttext.setText("");
            }
        }

    }

    @FXML
    private void suprimer(ActionEvent event) {
        medicamment = medicamentTableView.getSelectionModel().getSelectedItem();
        if (medicamment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("selecet medicament");
            alert.setTitle("fail");
            alert.show();
            return;
        }

        medicammentService.deleteMedicamment(medicamment.getId());
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("delete avec succès");
        alert.setTitle("Succès");
        alert.show();

        medicamment = null;

    }

    private boolean controleSaisie() {

        if (nomtxt.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le nom");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (desctxt.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le description");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        try {
            Float.parseFloat(prixtxt.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(prixtxt.getText() + " n'est pas un nombre valide (nombre)");
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

        if (Float.parseFloat(qttext.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(qttext.getText() + " doit etre > 0");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (Float.parseFloat(prixtxt.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(qttext.getText() + " doit etre > 0");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    private FilteredList<Medicamment> recherche(ObservableList matchList) {
        FilteredList<Medicamment> filterData = new FilteredList<Medicamment>(matchList, b -> true);
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(matchSearchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String serachKeeyword = newValue.toLowerCase();
                if (((Medicamment) matchSearchModel).getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Medicamment) matchSearchModel).getDescription() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                }

                return false;
            });

        });

        return filterData;
    }

    @FXML
    private void GoToMedicamentListe(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
         SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    @FXML
    private void GoToClientDashboard(ActionEvent event) {
             Routage.getInstance().GOTO(btnSignout, "/view/client/subscription/subscriptionhistory.fxml");
    }

    
}
