/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.client.order;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
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
import models.Order;
import models.OrderLine;
import models.Product;
import models.User;
import services.OrderService;
import services.ProductService;
import services.UserService;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class CreateOrderController implements Initializable {

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
    private TextField nameuserTxt;
    @FXML
    private TextField phoneusertxt;
    @FXML
    private TextField emailUserTxt;
    @FXML
    private TextField shppingtxt;
    @FXML
    private TextArea NoteTxt;
    private User ownerOrder;
    @FXML
    private Button btnorderCreate;
    @FXML
    private Label lblerror;
    @FXML
    private Label Pricelbl;
    @FXML
    private TextField searchfld;

    public User getOwnerOrder() {
        return ownerOrder;
    }

    public void setOwnerOrder(User ownerOrder) {
        this.ownerOrder = ownerOrder;
    }

    HashSet<OrderLine> TreeOrderLine = new HashSet<>();
    List<Product> ProductList = new ArrayList<>();
    ObservableList<OrderLine> OrderLineListe = FXCollections.observableArrayList();
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
        loadTableUser();
        loadTableProduct();
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

    public void CalculePriceOrder() {
        float price = 0;
        for (OrderLine orderline : TreeOrderLine) {
            price = price + orderline.getPrice();
        }
        Pricelbl.setText(String.valueOf(price));
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
        Routage.getInstance().GOTO(btnOrders, "/view/client/order/orderHistory.fxml");
    }

    @FXML
    private void GoToSubscriptionListe(ActionEvent event) {
         Routage.getInstance().GOTO(btnOrders, "/view/client/subscription/subscriptionhistory.fxml");
    }

    @FXML
    private void handleClicks(ActionEvent event) {
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
                    int id = Integer.parseInt(String.valueOf(orderlinetable.getItems().get(myIndex).getId()));
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

    public void loadTableUser() {
        UserService userservice = new UserService();
        int id = SessionManager.getInstance().getUser().getId();
        System.out.println("IDENT" + id);
        User user = userservice.userById(id);
        nameuserTxt.setText(user.getName());
        emailUserTxt.setText(user.getEmail());
        phoneusertxt.setText(user.getNumero());

        this.setOwnerOrder(user);
    }

    @FXML
    private void createOrder(ActionEvent event) {
        int leng = TreeOrderLine.size();
        int lengShipp = shppingtxt.getText().length();
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
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/client/order/orderHistory.fxml"));
                Parent root = loader.load();
                btnorderCreate.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            lblerror.setVisible(true);
            lblerror.setText("Please check your info before you create Order");
        }

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
    private void Logout(ActionEvent event) {
        SessionManager.getInstance().Logout();
         Routage.getInstance().GOTO(btnOrders, "/view/LoginPage.fxml");
    }

    @FXML
    private void Ticket(ActionEvent event) {
          Routage.getInstance().GOTO(btnOrders, "/view/Ticket/TicketCreate.fxml");
    }
}
