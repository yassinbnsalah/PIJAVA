/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.store;

import controllers.OrderListeController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import models.Category;
import models.Produit;
import models.User;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import services.ProductService;
import services.ServiceCategory;

import services.ServiceProduit;
import util.MyConnection;
import util.Routage;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author yacin
 */
public class ProduitController implements Initializable {

    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, String> cat;
    @FXML
    private TableColumn<Produit, String> name;
    @FXML
    private TableColumn<Produit, String> desc;
    @FXML
    private TableColumn<Produit, Integer> prix;
    @FXML
    private TableColumn<Produit, Integer> prix2;
    @FXML
    private TableColumn<Produit, Integer> quant;
    @FXML
    private TableColumn<Produit, Integer> rate;
    @FXML
    private TableView<Produit> tab;

    @FXML
    private TextField n;
    @FXML
    private TextField d;
    @FXML
    private TextField p;
    @FXML
    private TextField p2;
    @FXML
    private TextField q;
    @FXML
    private TextField urlx;
    @FXML
    private ComboBox<String> comm;
    @FXML
    private TextField recherche;

    @FXML
    private Rating stars;

    ObservableList<Produit> Chercheprod;
    private ImageView img;
    @FXML
    private Label userName;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnPharmacien;
    @FXML
    private Button btnMedcin;
    @FXML
    private Button btnCoach;
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
    private Button btnBan;
    @FXML
    private Button btnSignout;
    @FXML
    private Button clDash;
    @FXML
    private AnchorPane RatingAna;
    @FXML
    private Button statistique;
    @FXML
    private TextField CategoryOld;
    private int IDProduct;
    @FXML
    private Button btnUpdate;

    public int getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(int IDProduct) {
        this.IDProduct = IDProduct;
    }

    public ProduitController() {
        Connection cnx = MyConnection.getInstance().getCnx();
    }

    ServiceProduit prod = new ServiceProduit();

    @FXML
    private void Ajoutefichier(ActionEvent event) {
        FileChooser f = new FileChooser();

        File S = f.showOpenDialog(null);
        if (S != null) {

            String n = S.getAbsolutePath();
            urlx.setText(S.getAbsolutePath());

        }
    }

    public void onEdit() {

        java.sql.Connection cnx;
        cnx = MyConnection.getInstance().getCnx();

        if (tab.getSelectionModel().getSelectedItem() != null) {
            Produit f = tab.getSelectionModel().getSelectedItem();
            String id = String.valueOf(f.getId());
            String prix = String.valueOf(f.getBuyprice());
            String prix2 = String.valueOf(f.getSellprice());
            String quntite = String.valueOf(f.getQuantity());

            n.setText(f.getName());
            d.setText(f.getDescription());
            p.setText(prix);
            p2.setText(prix2);
            q.setText(quntite);
            CategoryOld.setText(String.valueOf(f.getCategory_id()));
            urlx.setText(f.getImage());
            System.out.println(f.getImage());

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
        comm.setItems(prod.RecupCombo());
        userName.setText(SessionManager.getInstance().getUser().getEmail());

        urlx.setVisible(false);

        ChercheFichier();

    }

    public void table() {

//        r.setCellValueFactory( new PropertyValueFactory<>("Role"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        cat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new SimpleStringProperty(param.getValue().getCategory_id().getName());
            }
        });
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("buyprice"));
        prix2.setCellValueFactory(new PropertyValueFactory<>("sellprice"));
        quant.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));

        tab.setItems(prod.RecupBase2());
        tab.setRowFactory(tv -> {
            TableRow<Produit> myRow = new TableRow<>();

            myRow.setOnMouseClicked(event
                    -> {

                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {

                    Produit f = tab.getSelectionModel().getSelectedItem();
                    String id = String.valueOf(f.getId());
                    String prix = String.valueOf(f.getBuyprice());
                    String prix2 = String.valueOf(f.getSellprice());
                    String quntite = String.valueOf(f.getQuantity());
                    setIDProduct(f.getId());
                    n.setText(f.getName());
                    d.setText(f.getDescription());
                    p.setText(prix);
                    p2.setText(prix2);
                    q.setText(quntite);
                    CategoryOld.setText(String.valueOf(f.getCategory_id().getName()));
                    urlx.setText(f.getImage());
                }
            });
            return myRow;
        });
        //System.out.println(prod.RecupBase2());

    }

    public void read() throws SQLException {

        java.sql.Connection cnx;
        cnx = MyConnection.getInstance().getCnx();

        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from produit");
        while (rs.next()) {
            int id = rs.getInt(1);
            int sellprice = rs.getInt(6);
            double rate = rs.getDouble(9);
            Produit p = new Produit(id, sellprice, rate);
            if (p.getRate() == 1.0) {

                prod.reduction(p, p.getSellprice());

            }
            if (p.getRate() == 1.0) {

                prod.reduction1(p, p.getSellprice());

            }

        }
    }

    private void EnregistrerVersBase() {
        java.sql.Connection cnx;
        cnx = MyConnection.getInstance().getCnx();

        try {
            String sql = "INSERT INTO produit (category_id,name,description,buyprice,sellprice,quantity,image) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            int prix1 = Integer.valueOf(p.getText());
            int prix2 = Integer.valueOf(p2.getText());

            int quantitée = Integer.valueOf(q.getText());

            String i = comm.getValue();
            System.out.println(i);
            // st.setInt(1, i);
            st.setString(2, name.getText());
            st.setString(3, d.getText());
            st.setInt(4, prix1);
            st.setInt(5, prix2);
            st.setInt(6, quantitée);
            st.setString(7, urlx.getText());
            //  st.setString(8, date.getText());

            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @FXML
    private void Add(ActionEvent event) {
        if (name.getText().isEmpty() || n.getText().isEmpty() || p.getText().isEmpty() || p2.getText().isEmpty() || q.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");

            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (urlx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");

            alert.setContentText("ajouter une image");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            EnregistrerVersBase();
            table();

        }
        Notifications.create()
                .title("Notification")
                .text("produit ajoutè.")
                .position(Pos.BOTTOM_RIGHT)
                .showInformation();

    }

    public void ChercheFichier() {
        Produit f = new Produit();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        Chercheprod = prod.RecupBase2();
        tab.setItems(prod.RecupBase2());
        FilteredList<Produit> filtreddata;
        filtreddata = new FilteredList<>(Chercheprod, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filtreddata.setPredicate((u -> {

                if ((newValue == null) || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (u.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (u.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            }));
        });

        SortedList<Produit> srt = new SortedList<>(filtreddata);
        srt.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(srt);
    }

    @FXML
    private void print(ActionEvent event) {

        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tab;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow());
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }

    }

    @FXML
    private void Supp(ActionEvent event) throws SQLException {

        int i = Integer.valueOf(getIDProduct());
        Produit r = new Produit();

        r.setId(i);

        System.out.println(r);
        prod.delete(r);

        table();

//        SuppRole(i);
        JOptionPane.showMessageDialog(null, "Le client a été supprimer avec succés");
    }

    @FXML
    private void Rate(ActionEvent event) throws SQLException {

        Produit p = new Produit();

        int id = getIDProduct();
        String nn = p2.getText();
        int prix = Integer.valueOf(nn);
        p.setSellprice(prix);
        p.setId(id);
        p.setRate(stars.getRating());
        System.out.println(p);

        prod.updaterating(p);
        ProductService ps = new ProductService();
        int avg = ps.Rating((int) p.getRate(), SessionManager.getInstance().getUser().getId(), p.getId());
        table();
        if (avg <= 1.0) {

            prod.reduction(p, p.getSellprice());
            table();

        }

        if (avg <= 2.0) {

            prod.reduction1(p, p.getSellprice());
            table();

        }

        System.out.println(stars.getRating());
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
    private void GoToOrderListe(ActionEvent event) {
        Routage rtg = Routage.getInstance();
        rtg.GOTO(btnOrders, "/view/admin/order/OrderListe.fxml");
    }

    @FXML
    private void Ticket(ActionEvent event) {
        Routage.getInstance().GOTO(btnTicket, "/view/Ticket/TicketListe.fxml");
    }

    @FXML
    private void ban(ActionEvent event) {
        Routage.getInstance().GOTO(btnBan, "/view/banliste/BanList.fxml");

    }

    @FXML
    private void signout(ActionEvent event) {
        SessionManager.getInstance().Logout();
        Routage.getInstance().GOTO(btnSignout, "/view/LoginPage.fxml");
    }

    @FXML
    private void GoToClientDash(ActionEvent event) {
        Routage.getInstance().GOTO(clDash, "/view/client/subscription/subscriptionhistory.fxml");

    }

    @FXML
    private void GoToState(ActionEvent event) {
        Routage.getInstance().GOTO(statistique, "/view/admin/store/Statistique.fxml");
    }

    @FXML
    private void client(ActionEvent event) {
        Routage.getInstance().GOTO(btnClient, "/view/users/client/ClientListe.fxml");
    }

    @FXML
    private void Subscription(ActionEvent event) {
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
    private void updateProduct(ActionEvent event) {

        Produit p1 = new Produit();
        p1.setName(n.getText());
        p1.setQuantity(Integer.parseInt(q.getText()));
        p1.setBuyprice(Integer.parseInt(p.getText()));
        p1.setSellprice(Integer.parseInt(p2.getText()));
        p1.setDescription(d.getText());
        ServiceCategory c = new ServiceCategory();
        System.out.println("UPDATING TO " + comm.getValue());
        Category ca = c.SelectCategoryByName(comm.getValue());
        System.out.println("CATEGORY NAMEEE " + ca.getName());
        p1.setCategory_id(ca);
        n.setText("");
        d.setText("");
        p.setText("");
        p2.setText("");
        q.setText("");
        ServiceProduit s = new ServiceProduit();
        s.updateProduct(p1, getIDProduct());

        table();
    }

}
