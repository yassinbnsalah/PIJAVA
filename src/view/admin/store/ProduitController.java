/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.store;


import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
import models.Produit;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import services.ServiceProduit;
import util.MyConnection;


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
    private TextField idx;
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
    private ComboBox<Integer> comm;
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

    public ProduitController() {
        Connection cnx = MyConnection.getInstance().getCnx();
    }

    ServiceProduit prod = new ServiceProduit();

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
        System.out.println(prod.RecupBase2());

    }

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

            idx.setText(id);
            n.setText(f.getName());
            d.setText(f.getDescription());
            p.setText(prix);
            p2.setText(prix2);
            q.setText(quntite);
            urlx.setText(f.getImage());
            System.out.println(f.getImage());

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        urlx.setVisible(false);
        table();
        ChercheFichier();
        comm.setItems(prod.RecupCombo());
        idx.setVisible(false);

//        try {
//            read();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        tab.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                try {
                    onEdit();
                    String s = urlx.getText();

                    File file = new File(s);
                    System.out.println(s);
                    Image image = new Image(file.toURI().toURL().toExternalForm());
                    img.setImage(image);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
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

            Integer i = comm.getValue();
            System.out.println(i);
            st.setInt(1, i);
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
        String idf = idx.getText();
        int i = Integer.valueOf(idf);
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

        String n = idx.getText();
        int id = Integer.valueOf(n);
        String nn = p2.getText();
        int prix = Integer.valueOf(nn);
        p.setSellprice(prix);
        p.setId(id);
       p.setRate(stars.getRating());
        System.out.println(p);

        prod.updaterating(p);

        table();
      if (p.getRate() == 1.0) {

            prod.reduction(p, p.getSellprice());
            table();

        }

        if (p.getRate() == 2.0) {

            prod.reduction1(p, p.getSellprice());
            table();

        }

        System.out.println(stars.getRating());
    }


    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void Pharmacien(ActionEvent event) {
    }

    @FXML
    private void medcin(ActionEvent event) {
    }

    @FXML
    private void coach(ActionEvent event) {
    }

    @FXML
    private void GoToOrderListe(ActionEvent event) {
    }

    @FXML
    private void Ticket(ActionEvent event) {
    }

    @FXML
    private void ban(ActionEvent event) {
    }

    @FXML
    private void signout(ActionEvent event) {
    }

    @FXML
    private void GoToClientDash(ActionEvent event) {
    }

}
