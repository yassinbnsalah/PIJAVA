/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.client.order;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class OrderdetailsController implements Initializable {

    @FXML
    private AnchorPane bx;
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
    @FXML
    private Button adminDash;
    @FXML
    private Label emaillbl;
    @FXML
    private Label namelbl;
    @FXML
    private Label phonelbl;
    @FXML
    private Label amountlbl;
    @FXML
    private Label statelbl;
    @FXML
    private Label paimentmethodlbl;
    @FXML
    private TableView<?> orderlineTable;
    @FXML
    private TableColumn<?, ?> productnameCol;
    @FXML
    private TableColumn<?, ?> productPricecol;
    @FXML
    private TableColumn<?, ?> quantitycol;
    @FXML
    private TableColumn<?, ?> pricelinecol;
    @FXML
    private Button createOrderbtn;
    @FXML
    private Label informationlbl;
    @FXML
    private Label reference;
    @FXML
    private Button backtoOrderListe;
    @FXML
    private ComboBox<?> orderStateCB;
    @FXML
    private Button updateState;
    @FXML
    private Label userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToOrderListe(ActionEvent event) {
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void logoutIng(ActionEvent event) {
    }

    @FXML
    private void GoToadminDash(ActionEvent event) {
    }

    @FXML
    private void DownloadInvoice(ActionEvent event) {
    }

    @FXML
    private void updateStateOrder(ActionEvent event) {
    }
    
}
