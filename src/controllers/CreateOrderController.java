/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import models.Disponibility;
import services.UserService;
import models.User;
import services.ProductService;
import models.Product;
import models.OrderLine;
import services.OrderService;
import models.Order;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class CreateOrderController implements Initializable {

    HashSet<OrderLine> TreeOrderLine = new HashSet<>();
    private OrderLine orderline;
    @FXML
    private TextField nameuserTxt;
    @FXML
    private TextField phoneusertxt;
    @FXML
    private TextField adressUserTxt;
    @FXML
    private TextField emailUserTxt;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> colNameUser;
    @FXML
    private TableColumn<User, String> ColEmailUser;
    private TableColumn<User, String> ColPhoneUser;

    List<User> UserList = new ArrayList<>();
    List<Product> ProductList = new ArrayList<>();
    ObservableList<OrderLine> OrderLineListe = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> ProductNameCol;
    @FXML
    private TableColumn<Product, String> descriptionCol;
    @FXML
    private TableColumn<Product, String> BuyPriceCol;
    @FXML
    private TableColumn<Product, String> SellPriceColl;
    @FXML
    private TableColumn<Product, String> quantityCol;
    @FXML
    private TableColumn<Product, String> categoryCol;
    @FXML
    private TextField productNametxt;
    @FXML
    private TextField productSellPricetxt;
    @FXML
    private TextField productBuyPricetxt;
    @FXML
    private TextField qte;
    @FXML
    private Button updateOrderLine;
    @FXML
    private TableView<OrderLine> orderlinetable;
    @FXML
    private TableColumn<OrderLine, String> productnamecol;
    @FXML
    private TableColumn<OrderLine, String> sellpriceordercol;
    @FXML
    private TableColumn<OrderLine, String> quantityorderCol;
    @FXML
    private TableColumn<OrderLine, String> priceColl;
    @FXML
    private TextField shppingtxt;
    @FXML
    private TextArea NoteTxt;

    private User ownerOrder;
    @FXML
    private Button btnorderCreate;
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
    private Label lblerror;
    @FXML
    private TextField txtsearshUser;
    @FXML
    private Button btnClient;
    @FXML
    private Label Pricelbl;
    @FXML
    private Label ProductOrderError;
    @FXML
    private Label ClientErrorlbl;
    @FXML
    private Button adminDash;
    @FXML
    private TextField searchfld;
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
    @FXML
    private Button btnBan;
    @FXML
    private Label userName;

    public User getOwnerOrder() {
        return ownerOrder;
    }

    public void setOwnerOrder(User ownerOrder) {
        this.ownerOrder = ownerOrder;
    }

    public OrderLine getOrderline() {
        return orderline;
    }

    public void setOrderline(OrderLine orderline) {
        this.orderline = orderline;
    }
    private int IDPRODUCT;

    public int getIDPRODUCT() {
        return IDPRODUCT;
    }

    public void setIDPRODUCT(int IDPRODUCT) {
        this.IDPRODUCT = IDPRODUCT;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName.setText(SessionManager.getInstance().getUser().getEmail());
        lblerror.setVisible(false);
        ClientErrorlbl.setVisible(false);
        ProductOrderError.setVisible(false);
        loadTableUser();
        loadTableProduct();
    }

    public void loadTableUser() {
        UserService userservice = new UserService();
        UserList.clear();
        UserList.addAll(userservice.userListe());
        tableUser.setItems(FXCollections.observableArrayList(UserList));
        colNameUser.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColEmailUser.setCellValueFactory(new PropertyValueFactory<>("Email"));
        // ColPhoneUser.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        tableUser.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = tableUser.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(tableUser.getItems().get(myIndex).getId()));

                    nameuserTxt.setText(String.valueOf(tableUser.getItems().get(myIndex).getName()));
                    adressUserTxt.setText(String.valueOf(tableUser.getItems().get(myIndex).getAdresse()));
                    emailUserTxt.setText(String.valueOf(tableUser.getItems().get(myIndex).getEmail()));
                    phoneusertxt.setText(String.valueOf(tableUser.getItems().get(myIndex).getNumero()));
                    User user = userservice.userById(id);
                    this.setOwnerOrder(user);
                }
            });
            return myRow;
        });
    }

    public void CalculePriceOrder() {
        float price = 0;
        for (OrderLine orderline : TreeOrderLine) {
            price = price + orderline.getPrice();
        }
        Pricelbl.setText(String.valueOf(price));
    }

    public void loadTableProduct() {
        ProductService productService = new ProductService();
        ProductList.clear();
        ProductList.addAll(productService.listeProduct());
        productTable.setItems(FXCollections.observableArrayList(ProductList));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        BuyPriceCol.setCellValueFactory(new PropertyValueFactory<>("buyprice"));
        SellPriceColl.setCellValueFactory(new PropertyValueFactory<>("sellprice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTable.setRowFactory(tv -> {
            TableRow<Product> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = productTable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(productTable.getItems().get(myIndex).getId()));
                    setIDPRODUCT(id);
                    productNametxt.setText(String.valueOf(productTable.getItems().get(myIndex).getName()));
                    productSellPricetxt.setText(String.valueOf(productTable.getItems().get(myIndex).getSellprice()));
                    productBuyPricetxt.setText(String.valueOf(productTable.getItems().get(myIndex).getBuyprice()));
                    Boolean test = false;
                    Iterator iterator = TreeOrderLine.iterator();
                    while (iterator.hasNext()) {
                        OrderLine obj = (OrderLine) iterator.next();
                        if (obj.getProduct_name().equals(productTable.getItems().get(myIndex).getName())) {
                            test = true;
                            qte.setText(String.valueOf(obj.getQuantity()));
                        }
                    }
                    if (test == false) {
                        qte.setText("0");
                    }
                }
            });
            return myRow;
        }
        );
    }

    @FXML
    private void updateOrderLineitem(ActionEvent event) {
        System.out.println("create orderline");
        OrderLine orderline2 = new OrderLine();
        orderline2.setProduct_id(IDPRODUCT);
        orderline2.setProduct_name(productNametxt.getText());
        orderline2.setProduct_price(Float.parseFloat(productSellPricetxt.getText()));
        orderline2.setQuantity(Integer.parseInt(qte.getText()));
        orderline2.setPrice(orderline2.getQuantity() * orderline2.getProduct_price());
        if (TreeOrderLine.contains(orderline2)) {
            TreeOrderLine.remove(orderline2);
        }
        TreeOrderLine.add(orderline2);
        System.out.println(TreeOrderLine);
        OrderLineListe.clear();
        OrderLineListe.addAll(TreeOrderLine);
        LoadOrderLine();
    }

    public void LoadOrderLine() {
        orderlinetable.setItems(OrderLineListe);
        productnamecol.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        sellpriceordercol.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        quantityorderCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColl.setCellValueFactory(new PropertyValueFactory<>("price"));
        orderlinetable.setRowFactory(tv -> {
            TableRow<OrderLine> myRow2 = new TableRow<>();
            myRow2.setOnMouseClicked(event2
                    -> {
                if (event2.getClickCount() == 1 && (!myRow2.isEmpty())) {
                    int myIndex = orderlinetable.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(orderlinetable.getItems().get(myIndex).getProduct_id()));
                    setIDPRODUCT(id);
                   
                    productNametxt.setText(String.valueOf(orderlinetable.getItems().get(myIndex).getProduct_name()));
                    productSellPricetxt.setText(String.valueOf(orderlinetable.getItems().get(myIndex).getProduct_price()));
                    productBuyPricetxt.setText(String.valueOf(orderlinetable.getItems().get(myIndex).getProduct_price()));
                    qte.setText(String.valueOf(orderlinetable.getItems().get(myIndex).getQuantity()));

                }
            });
            return myRow2;
        });
        CalculePriceOrder();
    }

    @FXML
    private void deleteOrderLine(ActionEvent event) {
        OrderLine orderline2 = new OrderLine();

        orderline2.setProduct_name(productNametxt.getText());
        orderline2.setProduct_price(Float.parseFloat(productSellPricetxt.getText()));
        orderline2.setQuantity(Integer.parseInt(qte.getText()));
        orderline2.setPrice(orderline2.getQuantity() * orderline2.getProduct_price());
        if (TreeOrderLine.contains(orderline2)) {
            TreeOrderLine.remove(orderline2);
        }
        OrderLineListe.clear();
        OrderLineListe.addAll(TreeOrderLine);
        LoadOrderLine();
    }

    @FXML
    private void createOrder(ActionEvent event) {
        int leng = TreeOrderLine.size();
        int lengShipp = shppingtxt.getText().length();
        if (leng == 0) {
            ProductOrderError.setText("No Product Checked For OrderLine");
            ProductOrderError.setVisible(true);
        }
        if (this.getOwnerOrder() != null) {
            ClientErrorlbl.setText("no Client Check For");
        }
        if (leng != 0 && this.getOwnerOrder() != null && lengShipp != 0) {
            OrderService orderservice = new OrderService();
            Order order = new Order();
            String reference = "#" + this.getOwnerOrder().getName().toUpperCase() + String.valueOf(new java.sql.Date(System.currentTimeMillis()));
            order.setReference(reference);
            order.setState("Confirmed");
            order.setShippingadress(shppingtxt.getText());
            order.setDateOrder(new java.sql.Date(System.currentTimeMillis()));
            order.setNote(NoteTxt.getText());
            order.setPaiementmethod("Cash");
            order.setOwner(ownerOrder);
            int price = 0;
            for (OrderLine orderline : TreeOrderLine) {
                price = price + (int) orderline.getPrice();
            }
            order.setPrice(price);
            orderservice.AddOrder(order, TreeOrderLine);
            System.out.println("SUIIII");
            System.out.println("TreeOrder"+TreeOrderLine);
            Routage.getInstance().GOTO(btnorderCreate, "/view/admin/order/OrderListe.fxml");
       
        } else {
            lblerror.setVisible(true);
            lblerror.setText("Please check your info before you create Order");
        }

    }


    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/subscription/subscriptionListe.fxml"));
            Parent root = loader.load();
            btnSubscription.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToadminDash(ActionEvent event) {
        Routage.getInstance().GOTO(adminDash, "/view/client/order/orderHistory.fxml");
    }

    @FXML
    private void searchproduct(KeyEvent event) {

        productTable.setItems(FXCollections.observableArrayList(searchList(searchfld.getText(), ProductList)));
    }

    private List<Product> searchList(String searchWords, List<Product> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getName().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getDescription().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    private void searchUser(KeyEvent event) {
        tableUser.setItems(FXCollections.observableArrayList(searchListUser(txtsearshUser.getText(), UserList)));
    }

    private List<User> searchListUser(String searchWords, List<User> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word
                    -> input.getName().toLowerCase().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getEmail().contains(word.toLowerCase())) || searchWordsArray.stream().allMatch(word
                    -> input.getNumero().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    private void Pharmacien(ActionEvent event) throws IOException {
     
                  Routage.getInstance().GOTO(btnPharmacien,"/view/users/pharmacien/PharmacienList.fxml");
    }

    @FXML
    private void medcin(ActionEvent event) throws IOException {
          
                  Routage.getInstance().GOTO(btnMedcin, "/view/users/medecin/MedcinList.fxml");
    }

    @FXML
    private void coach(ActionEvent event) throws IOException {
             
               Routage.getInstance().GOTO(btnCoach, "/view/users/coach/CoachList.fxml");
    }

    @FXML
    private void ban(ActionEvent event) throws IOException {
         
            
             Routage.getInstance().GOTO(btnBan, "/view/banliste/BanList.fxml");
    }

   

    @FXML
    private void Ticket(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    private void logoutIng(ActionEvent event) {
        SessionManager.getInstance().Logout();
         Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    @FXML
    private void client(ActionEvent event) {
          Routage.getInstance().GOTO(btnClient, "/view/users/client/ClientListe.fxml");
    }

    @FXML
    private void Order(ActionEvent event) {
         Routage rtg = Routage.getInstance();
        rtg.GOTO(btnOrders, "/view/admin/order/OrderListe.fxml");
    }

    @FXML
    private void Product(ActionEvent event) {
         Routage.getInstance().GOTO(btnProduct, "/view/admin/store/Produit.fxml");
    }

    @FXML
    private void Category(ActionEvent event) {
         Routage rtg = Routage.getInstance();
        rtg.GOTO(btnCategory, "/view/admin/store/categoryPage.fxml");
    }
}
