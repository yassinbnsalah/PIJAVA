/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.User;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fedi
 */
public class UserService {

    Connection cnx2;

    public UserService() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void AddUser(User user) {
        try {
            String req = "INSERT INTO `user`(`cin`, `name`, `numero`, `email`, `adresse`, `password`)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst.setString(1, user.getCIN());
            pst.setString(2, user.getName());
            pst.setInt(3, user.getNumero());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getAdresse());
            pst.setString(6, user.getPassword());
            pst.executeUpdate();
            System.out.println("Client created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<User> userListe() {
        ArrayList<User> liste = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` ";
            PreparedStatement pst = cnx2.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setCIN(rs.getString("CIN"));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setAdresse(rs.getString("Adresse"));
                user.setPassword(rs.getString("Password"));
                liste.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }

    public void supprimerUtilisateur(User user) {
        try {
            String requete = "delete from user where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.executeUpdate();

            System.out.println("Utlisateur est supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierUtilisateur(User user) {
        try {
            String requete2 = "update user set cin=?,name=?,numero=?,email=?,adresse=? where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, user.getCIN());
            pst.setString(2, user.getName());
            pst.setInt(3, user.getNumero());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getAdresse());
            pst.setInt(6, user.getId());
            pst.executeUpdate();

            System.out.println("Utlisateur est modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User userById(int id) {
        User usr = new User();
        try {

            String req = "SELECT * FROM `user` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                usr.setId(rs.getInt(1));
                usr.setName(rs.getString("name"));
                usr.setEmail(rs.getString("email"));
                usr.setAdresse(rs.getString("adresse"));
                usr.setCIN(rs.getString("cin"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usr;
    }
}
