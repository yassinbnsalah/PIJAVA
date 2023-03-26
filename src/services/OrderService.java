/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Order;
import models.OrderLine;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class OrderService {

    public void AddOrder(Order order, ArrayList<OrderLine> orderlines) {
        OrderLineService orderlineService = new OrderLineService();
        try {
            String req = "INSERT INTO `order`(`client_id`, `reference`, `state`, `price`, `shippingadress`, `date_order`, `note`,"
                    + " `paiementmethod`, `invoiced`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, 1);
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
        try {
            String req = "SELECT * FROM `order` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                order.setId(rs.getInt(1));
                order.setDateOrder(rs.getDate("date_order"));
                order.setNote(rs.getString("note"));
                order.setPaiementmethod(rs.getString("paiementmethod"));
                order.setPrice(rs.getInt("price"));
                order.setReference(rs.getString("reference"));
                order.setShippingadress(rs.getString("shippingadress"));
                order.setState(rs.getString("state"));
                order.setOrderline(orderlineservice.orderlineListe(id));
                /*order.setNom(rs.getString("nom"));
                order.setPrenom(rs.getString("prenom"));
                order.setAge(rs.getInt("age"));
                order.setCin(rs.getString("cin"));*/

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return order;
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

}
