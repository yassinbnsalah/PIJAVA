/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Medcecin;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Ordennance;
import models.Rendezvous;
import services.OrdennanceService;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddOrdennanceController implements Initializable {

    OrdennanceService ordennanceService;
    ObservableList<Ordennance> listOrdennances = FXCollections.observableArrayList();
    Ordennance ordennance;

    RendezvousService rendezvousService;
    @FXML
    private Button bttnajoutermedic;
    @FXML
    private Button editbttn;
    @FXML
    private Button supbttn;
    @FXML
    private TableView<Ordennance> ordonnaceTableView;
    @FXML
    private TableColumn<Ordennance, String> datecol;
    @FXML
    private TableColumn<Ordennance, String> ammountCol;
    @FXML
    private TableColumn<Ordennance, String> doctorcell;
    @FXML
    private TableColumn<Ordennance, String> rendezvous;
    @FXML
    private ComboBox<Rendezvous> RVCB;
    @FXML
    private TextField amountF;
    @FXML
    private DatePicker dateF;
    @FXML
    private TableColumn<Ordennance, String> ActionButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ordennanceService = new OrdennanceService();
        rendezvousService = new RendezvousService();

        LoadData();

        ObservableList<Rendezvous> list = FXCollections.observableArrayList();
        list.addAll(rendezvousService.RendezvousListe());
        RVCB.setItems(list);
    }

    public void refreshTable() {

        listOrdennances.clear();
        listOrdennances.addAll(ordennanceService.OrdennanceListe());
        ordonnaceTableView.setItems(listOrdennances);

    }

    private void LoadData() {

        refreshTable();
        datecol.setCellValueFactory(new PropertyValueFactory<>("dateordenance"));
        ammountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
//        doctorcell.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        rendezvous.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getRendezvous().getDate_rv().toString());
        });;
        ActionButton.setCellFactory(createActionButton());

    }

    private Callback<TableColumn<Ordennance, String>, TableCell<Ordennance, String>> createActionButton() {
        Callback<TableColumn<Ordennance, String>, TableCell<Ordennance, String>> cellFoctory = (TableColumn<Ordennance, String> param) -> {
            // make cell containing buttons
            final TableCell<Ordennance, String> cell = new TableCell<Ordennance, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
                        logoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                ordennance = ordonnaceTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddLigneOrdonance.fxml"));

                                Parent root;
                                root = loader.load();

                                AddLigneOrdonanceController addLigneOrdonanceController = loader.getController();
                                addLigneOrdonanceController.initOrder(ordennance);
                                addLigneOrdonanceController.LoadData();

                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AddOrdennanceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        HBox managebtn = new HBox(logoIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        return cellFoctory;
    }

    @FXML
    private void createMedicament(ActionEvent event) {
        if (controleSaisie()) {

            ordennance = new Ordennance();
            ordennance.setAmount(Float.parseFloat(amountF.getText()));
            ordennance.setRendezvous(RVCB.getSelectionModel().getSelectedItem());
            ordennance.setDateordenance(Timestamp.valueOf(dateF.getValue().atStartOfDay()));
            ordennanceService.AddOrdennance(ordennance);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ajout suuccess");
            alert.setTitle("success");
            alert.show();

            refreshTable();

            amountF.setText("");
            RVCB.getSelectionModel().select(null);
            dateF.setValue(null);

            ordennance = null;

        }
    }

    @FXML
    private void edit(ActionEvent event) {

        ordennance = ordonnaceTableView.getSelectionModel().getSelectedItem();

        if (RVCB.getSelectionModel().getSelectedItem() == null && amountF.getText().equals("")) {
            if (ordennance == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("selecet ordennace");
                alert.setTitle("fail");
                alert.show();
                return;
            }
            dateF.setValue(ordennance.getDateordenance().toLocalDateTime().toLocalDate());

            RVCB.getSelectionModel().select(ordennance.getRendezvous());
            amountF.setText(ordennance.getAmount() + "");

        } else {

            ordennance.setAmount(Float.parseFloat(amountF.getText()));
            ordennance.setRendezvous(RVCB.getSelectionModel().getSelectedItem());
            ordennance.setDateordenance(Timestamp.valueOf(dateF.getValue().atStartOfDay()));

            if (controleSaisie()) {
                ordennanceService.modifier(ordennance.getId(), ordennance);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("modif suuccess");
                alert.setTitle("success");
                alert.show();

                refreshTable();
                amountF.setText("");
                RVCB.getSelectionModel().select(null);
                dateF.setValue(null);

                ordennance = null;
            }
        }
    }

    @FXML
    private void suprimer(ActionEvent event) {
        ordennance = ordonnaceTableView.getSelectionModel().getSelectedItem();
        if (ordennance == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("selecet ordence");
            alert.setTitle("fail");
            alert.show();
            return;
        }

        ordennanceService.delete(ordennance.getId());
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("delete avec succès");
        alert.setTitle("Succès");
        alert.show();

        ordennance = null;
    }

    private boolean controleSaisie() {

        if (dateF.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le date");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (RVCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le randez vous");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        try {
            Float.parseFloat(amountF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(amountF.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        return true;
    }

}
