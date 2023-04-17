/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Product;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class ProductService {

    Connection cnx2;

    public ProductService() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public Product productBYID(int id) {
        Product product = new Product();
        try {
            String req = "SELECT * FROM produit WHERE ID = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareCall(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                 product.setId(id);
                 product.setName(rs.getString("name"));
                 product.setSellprice(rs.getInt("sellprice"));
             
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return product;
    }

    public ArrayList<Product> listeProduct() {

        ArrayList<Product> listeProduit = new ArrayList<>();
        try {
            String req = "SELECT * FROM `produit` ";

            PreparedStatement pst = cnx2.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSellprice(rs.getFloat("sellprice"));
                p.setBuyprice(rs.getFloat("buyprice"));
                p.setDescription(rs.getString("description"));
                p.setId(rs.getInt("id"));
                listeProduit.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listeProduit;
    }
}
