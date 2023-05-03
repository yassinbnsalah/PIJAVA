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
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Disponibility;
import models.Subscription;
import services.DisponibilityService;
import util.MyConnection;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class DisponibilityListeController implements Initializable {

    @FXML
    private AnchorPane updateForm;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Label userEmaillbl;
    @FXML
    private AnchorPane addDispoForm;
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
    @FXML
    private Button btncancel;
    @FXML
    private Button ClientDashboard;
    @FXML
    private TextField SearchDispo;
    @FXML
    private Button btnRendezVous;
    @FXML
    private Button btnOrdennance;

    public DisponibilityListeController() {

    }

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
        addDispoForm.setVisible(false);
        this.refreshTable();
        lblerrortxt.setVisible(false);
        userEmaillbl.setText(SessionManager.getInstance().getUser().getEmail());
        updateForm.setVisible(false);

    }

    private void GoToDisponnibility(ActionEvent event) {
        System.out.println("disponinbility Liste");
    }

    @FXML
    private void CreateNewDIsponnibility(ActionEvent event) {
        addDispoForm.setVisible(true);
        updateForm.setVisible(false);
        /*  try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Medecin/AddDisponnibility.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void refreshTable() {
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
                    addDispoForm.setVisible(false);
                    updateForm.setVisible(true);
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
        addDispoForm.setVisible(false);
        updateForm.setVisible(false);
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
        addDispoForm.setVisible(false);
        updateForm.setVisible(false);
        refreshTable();
        updateForm.setVisible(false);
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
        updateForm.setVisible(false);
    }

    @FXML
    private void handleClicks(ActionEvent event) {
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
            lblerrortxt.setVisible(true);
            refreshTable();
            lblerrortxt.setText("Disponnibility created Succesfully");
            setTimeout(() -> addDispoForm.setVisible(false), 1000);
            // Stage stage = (Stage) btnCreate.getScene().getWindow();
            // do what you have to do
            //stage.close();
        } else {
            lblerrortxt.setVisible(true);
            lblerrortxt.setText("No Data Founded");
        }
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    @FXML
    private void cancelAdd(ActionEvent event) {
        /*  TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Checking local files...");
                
            }
           
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(
                task,
                0,
                1000L);*/

        addDispoForm.setVisible(false);
        updateForm.setVisible(false);
    }

    @FXML
    private void GoToClientDashboard(ActionEvent event) {

        Routage.getInstance().GOTO(ClientDashboard, "/view/client/subscription/subscriptionhistory.fxml");
    }

    @FXML
    private void logout(ActionEvent event) {
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    
     private List<Disponibility> searchList(String searchWords, List<Disponibility> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getHeureEnd().toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getHeureStart().toLowerCase().contains(word.toLowerCase()))||searchWordsArray.stream().allMatch(word
                    -> input.getDateDispo().toString().toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    private void searching(KeyEvent event) {
         TableDispo.setItems(FXCollections.observableArrayList(searchList(SearchDispo.getText(), DispoList)));
    }

    @FXML
    private void GoToRendezVous(ActionEvent event) {
        Routage.getInstance().GOTO(btnClient, "/view/Medecin/AddRendez-vous.fxml");
    }

    @FXML
    private void GoToOrdennanceListe(ActionEvent event) {
           Routage.getInstance().GOTO(btnClient, "/view/Medecin/AddOrdennance.fxml");
    }
}
