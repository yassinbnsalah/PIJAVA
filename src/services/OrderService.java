/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplicationpi.JavaApplicationPI;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import models.Order;
import models.OrderLine;
import models.User;
import util.EmailManager;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class OrderService {

    public void AddOrder(Order order, HashSet<OrderLine> orderlines) {
        OrderLineService orderlineService = new OrderLineService();
        try {
            String req = "INSERT INTO `order`(`client_id`, `reference`, `state`, `price`, `shippingadress`, `date_order`, `note`,"
                    + " `paiementmethod`, `invoiced`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, order.getOwner().getId());
            pst.setString(2, order.getReference());
            pst.setString(3, order.getState());
            pst.setInt(4, order.getPrice());
            pst.setString(5, order.getShippingadress());
            pst.setDate(6, order.getDateOrder());
            pst.setString(7, order.getNote());
            pst.setString(8, order.getPaiementmethod());
            pst.setBoolean(9, true);
            pst.executeUpdate();
            int idOrder = this.OrderIDByReference(order.getReference());
            order.setId(idOrder);
            for (OrderLine orderline : orderlines) {
                orderline.setRelatedOrder(order);
                orderlineService.addOrderline(orderline);
            }
            System.out.println(" Order ajouté !");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Order> OrderListe() {
        ArrayList<Order> liste = new ArrayList<>();
        OrderLineService orderlineservice = new OrderLineService();
        UserService userservice = new UserService(); 
        User user ; 
        try {
            String req = "SELECT * FROM `order` ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setDateOrder(rs.getDate("date_order"));
                order.setNote(rs.getString("note"));
                order.setPaiementmethod(rs.getString("paiementmethod"));
                order.setPrice(rs.getInt("price"));
                order.setReference(rs.getString("reference"));
                order.setShippingadress(rs.getString("shippingadress"));
                order.setState(rs.getString("state"));
                user = userservice.userById(rs.getInt("client_id"));
                order.setOwnerEmail(user.getEmail());
                order.setOrderline(orderlineservice.orderlineListe(rs.getInt(1)));
                liste.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }

    public int OrderIDByReference(String reference) {
        int id = 0;
        try {
            String req = "SELECT * FROM `order` WHERE reference = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, reference);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                id = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public Order orderByID(int id) {
        OrderLineService orderlineservice = new OrderLineService();
        Order order = new Order();
        UserService userservice = new UserService();
        try {
            String req = "SELECT * FROM `order` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("client_id"));
                order.setId(rs.getInt(1));
                order.setDateOrder(rs.getDate("date_order"));
                order.setNote(rs.getString("note"));
                order.setPaiementmethod(rs.getString("paiementmethod"));
                order.setPrice(rs.getInt("price"));
                order.setReference(rs.getString("reference"));
                order.setShippingadress(rs.getString("shippingadress"));
                order.setState(rs.getString("state"));
                User user = userservice.userById(Integer.parseInt(rs.getString("client_id")));
                order.setOwner(user);
                order.setOrderline(orderlineservice.orderlineListe(id));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Order : " + order);
        return order;
    }
    
       public ArrayList<Order> OrderByCLient(int id) {
        OrderLineService orderlineservice = new OrderLineService();
        
        ArrayList<Order> orderliste = new ArrayList<>() ; 
        UserService userservice = new UserService();
        try {
            String req = "SELECT * FROM `order` WHERE client_id = ? ORDER BY date_order DESC";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               Order order = new Order();
                order.setId(rs.getInt(1));
                order.setDateOrder(rs.getDate("date_order"));
                order.setNote(rs.getString("note"));
                order.setPaiementmethod(rs.getString("paiementmethod"));
                order.setPrice(rs.getInt("price"));
                order.setReference(rs.getString("reference"));
                order.setShippingadress(rs.getString("shippingadress"));
                order.setState(rs.getString("state"));
                User user = userservice.userById(Integer.parseInt(rs.getString("client_id")));
                order.setOwner(user);
                order.setOrderline(orderlineservice.orderlineListe(id));
                orderliste.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return orderliste;
    }

    public void updateStateOrder(int id, String state) {

        try {
            String req = "UPDATE `order` SET `state`= ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(2, id);
            pst.setString(1, state);
            pst.executeUpdate();
            System.out.println("Order Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void GenerateInvoice(Order order) {

        try {
            String reference = order.getReference() + ".pdf";

            String filePath = "D:\\yessine\\esprit\\3A47\\PI\\projetpi\\Invoices\\" + reference;
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph pra = new Paragraph("Invoice Reference " + reference);
            document.add(pra);
            // add table 

            PdfPTable table = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("Product"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Price Product"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Quantity"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Price Slote"));
            table.addCell(c1);

            table.setHeaderRows(1);

            table.addCell("X1");
            table.addCell("250DT");
            table.addCell("2");
            table.addCell("500");

            document.add(table);
            // create Image 
            document.add(Image.getInstance("D:\\telechargement\\pilogo.png"));
            document.close();

            System.out.println("finished");
        } catch (Exception ex) {
            Logger.getLogger(JavaApplicationPI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
