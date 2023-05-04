/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coach;

import services.ExcelExporter;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Seance;
import services.CRUD_Seance;
import services.CsvExporter;
import util.MyConnection;
import java.io.File;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class SeanceController implements Initializable {

    // Table view et colonnes
    @FXML
    private TableView<Seance> tableView;
    private TableColumn<Seance, Integer> col_id;
    @FXML
    private TableColumn<Seance, String> col_nom;
    @FXML
    private TableColumn<Seance, String> col_description;
    @FXML
    private TableColumn<Seance, Integer> col_duree;
    @FXML
    private TableColumn<Seance, String> col_niveau;
    @FXML
    private TableColumn<Seance, String> col_date;

    // Champs de texte
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_description;
    @FXML
    private ComboBox<String> cb_niveau;
    @FXML
    private DatePicker dp_date;
    @FXML
    private TextField tf_duree;
    // Boutons
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;

    private int duree;

    // Liste des séances
    private ObservableList<Seance> seanceList;

    // Instance du modèle CRUD
    private CRUD_Seance crud;
    @FXML
    private Button bt_act;
    @FXML
    private TextField tf_search;
    @FXML
    private Button bt_search;

    @FXML
    private Button bt_exp;
    private Connection connection;

    public void handleExportSeances(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Seances");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File file = fileChooser.showSaveDialog(bt_exp.getScene().getWindow());

        if (file != null) {
            CsvExporter exporter = new CsvExporter();
            CRUD_Seance seanceService = new CRUD_Seance();
            try {
                exporter.exportSeances(seanceService.getAllSeances(), file.getPath());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SeanceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleExportExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter les séances");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier Excel (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            String filename = file.getAbsolutePath();
            if (!filename.endsWith(".xlsx")) {
                filename += ".xlsx";
            }
            List<Seance> seances = crud.getAllSeances();
            ExcelExporter.exportSeancesToExcel(seances, filename);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Exportation terminée");
            alert.setHeaderText(null);
            alert.setContentText("Les séances ont été exportées dans le fichier " + filename);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearch() {
        String searchQuery = tf_search.getText();
        List<Seance> searchResult = crud.searchSeanceByName(searchQuery);
        tableView.getItems().setAll(searchResult);
    }

    public SeanceController() {
        Connection conn = MyConnection.getInstance().getCnx();
        crud = new CRUD_Seance(conn);
        //initialize();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> listNiveau = FXCollections.observableArrayList("Débutant", "Intermédiaire", "Avancé", "Expert");
        cb_niveau.setItems(listNiveau);

        // Configuration des colonnes du table view
       // col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        col_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Chargement des séances dans le table view
        seanceList = FXCollections.observableArrayList(crud.getAllSeances());
        tableView.setItems(seanceList);

        // Configuration des listes déroulantes
        //cb_duree.getItems().addAll(1, 2, 3, 4);
        //cb_niveau.getItems().addAll("Débutant", "Intermédiaire", "Avancé", "Expert");
    }

    @FXML
    void refresh() {
        // Configuration des colonnes du table view
//       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        col_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Chargement des séances dans le table view
        seanceList = FXCollections.observableArrayList(crud.getAllSeances());
        tableView.setItems(seanceList);
    }

    @FXML
    void comboxN() {
        ObservableList<String> listNiveau = FXCollections.observableArrayList("Débutant", "Intermédiaire", "Avancé", "Expert");
        cb_niveau.setItems(listNiveau);
    }

    @FXML
    void handleAjouter(ActionEvent event) throws SQLException {
        String nom = tf_nom.getText();
        String description = tf_description.getText();
        try {
            duree = Integer.parseInt(tf_duree.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La durée doit être un entier !");
            alert.showAndWait();
            return;
        }
        String niveau = cb_niveau.getValue().toString();
        LocalDateTime date = dp_date.getValue().atStartOfDay();

        if (nom.isEmpty() || description.isEmpty() || date == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        Seance seance = new Seance(nom, description, duree, niveau);
        crud.addSeance(seance);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("La séance a été ajoutée avec succès !");
        alert.showAndWait();

        clearFields();
    }

    @FXML
    private void handleUpdate() {
        Seance selectedSeance = tableView.getSelectionModel().getSelectedItem();
        if (selectedSeance == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner une séance");
            alert.showAndWait();
        } else {
            Dialog<Seance> dialog = new Dialog<>();
            dialog.setTitle("Modifier la séance");
            dialog.setHeaderText("Modifier les informations de la séance");
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
            TextField nomField = new TextField(selectedSeance.getNom());
            TextArea descriptionArea = new TextArea(selectedSeance.getDescription());
            TextField dureeField = new TextField(Integer.toString(selectedSeance.getDuree()));
            ComboBox<String> niveauComboBox = new ComboBox<String>();
            niveauComboBox.getItems().addAll("Débutant", "Intermédiaire", "Avancé", "Expert");
            niveauComboBox.setValue(selectedSeance.getNiveau());
         
            dialog.getDialogPane().setContent(new VBox(new Label("Nom :"), nomField, new Label("Description :"), descriptionArea, new Label("Durée :"), dureeField, new Label("Niveau :"), niveauComboBox));
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    int duree = 0;
                    try {
                        duree = Integer.parseInt(dureeField.getText());
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("La durée doit être un entier !");
                        alert.showAndWait();
                        return null;
                    }
                  
                    Seance updatedSeance = new Seance(nomField.getText(), descriptionArea.getText(), duree, niveauComboBox.getValue().toString() );

                    crud.updateSeance(selectedSeance, updatedSeance);
                    return updatedSeance;
                }
                return null;
            });
            dialog.showAndWait();
            tableView.getItems().setAll(crud.getAllSeances());
        }

    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void handleSupprimer(ActionEvent event) {
        Seance selectedSeance = tableView.getSelectionModel().getSelectedItem();
        if (selectedSeance != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Supprimer la séance " + selectedSeance.getNom() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                crud.deleteSeance(selectedSeance.getId());
                showSeanceList(); // mettre à jour l'affichage
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune séance n'a été sélectionnée");
            alert.setContentText("Veuillez sélectionner une séance dans la liste");
            alert.showAndWait();
        }
    }

    private void showSeanceList() {
        List<Seance> seances = crud.getAllSeances();
        ObservableList<Seance> seanceList = FXCollections.observableArrayList(seances);
        tableView.setItems(seanceList);
    }

    private void clearFields() {
        tf_nom.setText("");
        tf_description.setText("");
        tf_duree.setText("");
        cb_niveau.setValue("");
        dp_date.setValue(null);
    }

}
