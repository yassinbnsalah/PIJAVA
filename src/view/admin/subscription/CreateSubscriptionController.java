/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.subscription;

import controllers.OrderListeController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Subscription;
import models.User;
import services.SubServices;
import services.UserService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class CreateSubscriptionController implements Initializable {

    @FXML
    private Button btnClient;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSubscription;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnSignout;
    private ComboBox<String> CBClEmail;
    @FXML
    private ComboBox<String> CBPaiType;
    @FXML
    private ComboBox<String> CBType;
    @FXML
    private DatePicker DatePick;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> clientNameCol;
    @FXML
    private TableColumn<User, String> clientEmailCol;
    @FXML
    private TableColumn<User, String> ClientPhoneCol;
    @FXML
    private TextField clientNamelbl;
    @FXML
    private TextField clientEmaillbl;
    @FXML
    private TextField clientphonelbl;
    ObservableList<User> UserList = FXCollections.observableArrayList();
   
    @FXML
    private Button btnsub;
    @FXML
    private Button clDash;
 private User SubOwner ; 
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
    @FXML
    private Button btnBan;
    public User getSubOwner() {
        return SubOwner;
    }

    public void setSubOwner(User SubOwner) {
        this.SubOwner = SubOwner;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableUser();
        this.LoadDataForAddSubscription();
    }

    private void LoadDataForAddSubscription() {
        UserService userservice = new UserService();
        /*ArrayList<User> userListe = userservice.userListe();
        for (User user : userListe) {
            CBClEmail.getItems().add(user.getEmail());
        }*/
        CBType.getItems().add("1 Month");
        CBType.getItems().add("3 Months");
        CBType.getItems().add("6 Months");

        CBPaiType.getItems().add("Cash");
        CBPaiType.getItems().add("Cheque");
    }

    public void loadTableUser() {
        UserService userservice = new UserService();
        UserList.clear();
        UserList.addAll(userservice.userListe());
        userTable.setItems(UserList);
        clientNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clientEmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ClientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        userTable.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = userTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(userTable.getItems().get(myIndex).getId()));

                    clientNamelbl.setText(String.valueOf(userTable.getItems().get(myIndex).getName()));

                    clientEmaillbl.setText(String.valueOf(userTable.getItems().get(myIndex).getEmail()));
                    clientphonelbl.setText(String.valueOf(userTable.getItems().get(myIndex).getNumero()));
                    User user = userservice.userById(id);
                    //this.setOwnerOrder(user);
                    setSubOwner(user) ; 
                }
            });
            return myRow;
        });
    }


    @FXML
    private void GoToOrderListe(ActionEvent event) {
         Routage rtg = Routage.getInstance();
        rtg.GOTO(btnOrders, "/view/admin/order/OrderListe.fxml");
        
    }

    @FXML
    private void CreateSubscription(ActionEvent event) {
        SubServices subservice = new SubServices();
        UserService userservice = new UserService();
        System.out.println("email"+clientEmaillbl.getText());
        User user = userservice.userByEmail(clientEmaillbl.getText());        
        Subscription sub = new Subscription();        
        sub.setId_user(getSubOwner().getId());
        sub.setDatesub(Date.valueOf(DatePick.valueProperty().getValue()));
        String type = CBType.getValue();
        if (type.equals("1 Month")) {
            sub.setType("1");
        } else if (type.equals("3 Months")) {
            sub.setType("2");            
        } else {
            sub.setType("3");
        }
        sub.setPaiementMethod(CBPaiType.getValue());
        subservice.AddSubscription(sub);
        Routage.getInstance().GOTO(btnsub, "/view/admin/subscription/subscriptionListe.fxml");
    }

    @FXML
    private void GoToClientDash(ActionEvent event) {
         Routage.getInstance().GOTO(clDash, "/view/client/order/orderHistory.fxml");
    }

    @FXML
    private void client(ActionEvent event) {
        Routage rtg = Routage.getInstance();
        rtg.GOTO(btnClient, "/view/users/client/ClientListe.fxml");
    }

    @FXML
    private void Pharmacien(ActionEvent event) {
         Routage.getInstance().GOTO(btnPharmacien, "/view/users/pharmacien/PharmacienList.fxml");
    }

    @FXML
    private void medcin(ActionEvent event) {
         Routage.getInstance().GOTO(btnMedcin, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void coach(ActionEvent event) {
        Routage.getInstance().GOTO(btnCoach, "/view/users/coach/CoachList.fxml");
    }

    @FXML
    private void subscription(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/subscription/subscriptionListe.fxml"));
            Parent root = loader.load();
            btnSubscription.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void product(ActionEvent event) {
         Routage.getInstance().GOTO(btnProduct, "/view/admin/store/Produit.fxml");
    }

    @FXML
    private void category(ActionEvent event) {
         Routage rtg = Routage.getInstance();
        rtg.GOTO(btnCategory, "/view/admin/store/categoryPage.fxml");
    }

    @FXML
    private void ticket(ActionEvent event) {
         Routage.getInstance().GOTO(btnTicket, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    private void ban(ActionEvent event) {
        Routage.getInstance().GOTO(btnBan, "/view/banliste/BanList.fxml");
    }

    @FXML
    private void Logout(ActionEvent event) {
         SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

}
