/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Subscription;
import util.MyConnection;
import models.User;
import util.EmailManager;

/**
 *
 * @author yacin
 */
public class SubServices {

    public void AddSubscription(Subscription sub) {
        try {
            String req = "INSERT INTO `subscription`( `user_id`, `date_sub`, `date_expire`, `type`,"
                    + " `paiement_type`, `amount`, `state`, `reference`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, sub.getId_user());
            pst.setDate(2, sub.getDatesub());
            Date dateExpire = new java.sql.Date(System.currentTimeMillis());
            if (sub.getType().equals("1")) {
                pst.setDate(3, java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(30)));
                sub.setDateExpire(java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(30)));
            } else if (sub.getType().equals("2")) {
                pst.setDate(3, java.sql.Date.valueOf(
                        new java.sql.Date(System.currentTimeMillis()).toLocalDate().plusDays(90)));
                sub.setDateExpire(java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(90)));
            } else {
                pst.setDate(3, java.sql.Date.valueOf(
                        new java.sql.Date(System.currentTimeMillis()).toLocalDate().plusDays(120)));
                sub.setDateExpire(java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(120)));
            }
            pst.setString(4, sub.getType());
            pst.setString(5, sub.getPaiementMethod());
            pst.setInt(6, sub.getAmount());
            pst.setString(7, sub.getState());
            pst.setString(8, sub.getReference());
            pst.executeUpdate();
            UserService userService = new UserService();
            User user = userService.userById(sub.getId_user());
            EmailManager em = new EmailManager();
            String message = "<h3> you received new sub with reference " + sub.getReference() + " and your "
                    + "sub will expire at " + sub.getDateExpire() + " thank you </h3>";
            String subject = "Subscription Information";
            em.SendMail(user.getEmail(), message, subject);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Subscription> subListe() {
        String req = "SELECT * FROM `subscription` ";
        ArrayList<Subscription> subs = new ArrayList<>();
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Subscription sub = new Subscription();
                sub.setId(rs.getInt(1));
                sub.setDatesub(rs.getDate("date_sub"));
                sub.setAmount(rs.getInt("amount"));
                sub.setDateExpire(rs.getDate("date_expire"));
                sub.setPaiementMethod(rs.getString("paiement_type"));
                sub.setType(rs.getString("type"));
                sub.setReference(rs.getString("reference"));
                sub.setState(rs.getString("state"));
                sub.setId_user(rs.getInt("user_id"));
                // add user by id here from FEDI 
                subs.add(sub);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return subs;
    }

    public void UpdateStateSub(String State, int id) {
        try {
            String req = "UPDATE `subscription` SET `state`= ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(2, id);

            pst.setString(1, State);
            pst.executeUpdate();
            System.out.println(" Subscription State updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteSub(int id) {
        try {
            String req = "DELETE FROM `subscription` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println(" Subscription State Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
