/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Activity;
import Services.CRUD_Activity;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author djoum
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import utils.MyConnection;

public class ActivityController {
    @FXML
    private TextField nomField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TableView<Activity> tableView;

    @FXML
    private TableColumn<Activity, String> nomColumn;

    @FXML
    private TableColumn<Activity, String> descriptionColumn;
    

    private final CRUD_Activity database;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button ActualiserButton;
    @FXML
    private TextField tf_search;
    @FXML
    private Button bt_search;
    
    @FXML
    private void handleSearch() {
    String searchQuery = tf_search.getText();
    List<Activity> searchResult = database.searchActivityByName(searchQuery);
    tableView.getItems().setAll(searchResult);
}

    public ActivityController() {
        Connection conn = MyConnection.getConnection();
        database = new CRUD_Activity(conn);
        //initialize();
    }

    private void initialize() {
    
}

    @FXML
    private void refresh()
    {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        List<Activity> activities = database.getAllActivities();
    ObservableList<Activity> activityList = FXCollections.observableList(activities);
    tableView.setItems(activityList);

        tableView.getItems().setAll(database.getAllActivities());
    }
    @FXML
    private void addActivity() {
        String nom = nomField.getText();
        String description = descriptionArea.getText();
        Activity activity = new Activity(nom, description);
        if (database.getAllActivities().contains(activity)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("L'activité existe déjà");
            alert.showAndWait();
        } else {
            database.addActivity(activity);
            tableView.getItems().add(activity);
            clearFields();
        }
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ObservableList<Activity> activityList = (ObservableList<Activity>) database.getAllActivities();
        tableView.getItems().setAll(database.getAllActivities());
    if (activityList.isEmpty()) {
        System.out.println("La liste d'activités est vide.");
    } else {
        tableView.setItems(activityList);
        System.out.println("Nombre d'activités récupérées : " + activityList.size());
    }
        System.out.println("initialize() a été exécuté.");
    }

    @FXML
    private void updateActivity() {
        Activity selectedActivity = tableView.getSelectionModel().getSelectedItem();
        if (selectedActivity == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner une activité");
            alert.showAndWait();
        } else {
            Dialog<Activity> dialog = new Dialog<>();
            dialog.setTitle("Modifier l'activité");
            dialog.setHeaderText("Modifier les informations de l'activité");
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
            TextField nomField = new TextField(selectedActivity.getNom());
            TextArea descriptionArea = new TextArea(selectedActivity.getDescription());
            dialog.getDialogPane().setContent(new VBox(new Label("Nom :"), nomField, new Label("Description :"), descriptionArea));
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    Activity updatedActivity = new Activity(nomField.getText(), descriptionArea.getText());
                    database.updateActivity(selectedActivity, updatedActivity);
                    return updatedActivity;
                }
                return null;
            });
            dialog.showAndWait();
            tableView.getItems().setAll(database.getAllActivities());
        }
    }

    @FXML
    @SuppressWarnings("empty-statement")
    
    private void deleteActivity() {
        Activity selectedActivity = tableView.getSelectionModel().getSelectedItem();
        if (selectedActivity == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner une activité");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette activité ?");
            alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
        database.deleteActivity(selectedActivity);
        tableView.getItems().remove(selectedActivity);
        }
    }   
   }
    
    private void clearFields() {
    nomField.setText("");
    descriptionArea.setText("");
    }
 }



