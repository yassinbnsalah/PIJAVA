/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.OrderLine;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class OrderLineService {

    public void addOrderline(OrderLine orderline) {
        try {
            String req = "INSERT INTO `order_line`(`product_id`, `related_order_id`, `quantity`, `price`) VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, orderline.getProduct_id());
            pst.setInt(2, orderline.getRelatedOrder().getId());
            pst.setInt(3, orderline.getQuantity());
            pst.setFloat(4, orderline.getPrice());
            pst.executeUpdate();
            System.out.println("orderline created successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<OrderLine> orderlineListe(int id) {
        ArrayList<OrderLine> listeOrderLine = new ArrayList<>();
        try {
            String req = "SELECT * FROM `order_line` WHERE `related_order_id` = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderLine orderLine = new OrderLine();
                orderLine.setId(rs.getInt(1));
                orderLine.setPrice(rs.getFloat("price"));
                orderLine.setQuantity(rs.getInt("quantity"));
                listeOrderLine.add(orderLine);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeOrderLine;
    }

}
