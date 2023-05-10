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

    public int Rating(int note, int IDUser, int IDProduct) {
        ArrayList<Product> listeProduit = new ArrayList<>();
        int avrage =0 ; 
        try {
            String req = "SELECT * FROM `rating` WHERE user_id  = ? and produit_id  = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareCall(req);
            pst.setInt(1, IDUser);
            pst.setInt(2, IDProduct);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String reqU = "UPDATE `rating` SET `avis`= ? WHERE `user_id`= ? and `produit_id`= ?";
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareCall(reqU);
                pst1.setInt(1, note);
                pst1.setInt(2, IDUser);
                pst1.setInt(3, IDProduct);
                pst1.executeUpdate();
                System.out.println("Rating Existe and UPDATED");
            } else {
                String req1 = "INSERT INTO `rating`(`user_id`, `produit_id`, `avis`) VALUES (? , ? , ?)";
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareCall(req1);
                pst1.setInt(1, IDUser);
                pst1.setInt(2, IDProduct);
                pst1.setInt(3, note);
                pst1.executeUpdate();
                System.out.println("Rating created");
            }
            System.out.println("HERE WE GOOOOOOOOOO ");
             String req2 = "SELECT  AVG(avis)  as Avis   FROM `rating` WHERE produit_id  = ?";
            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareCall(req2 );
            pst3.setInt(1, IDProduct);
            ResultSet rs3 = pst3.executeQuery();
            while(rs3.next()){
                System.out.println("Somme Avis "+rs3.getInt("Avis"));
           avrage = rs3.getInt("Avis") ;
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return avrage ;
    }
}
