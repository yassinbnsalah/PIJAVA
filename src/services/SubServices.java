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
import java.util.concurrent.TimeUnit;
import models.Subscription;
import util.MyConnection;
import models.User;
import util.EmailManager;
import util.HtmlMessages;

/**
 *
 * @author yacin
 */
public class SubServices {

    public void AddSubscription(Subscription sub) {
        try {

            UserService userService = new UserService();
            User user = userService.userById(sub.getId_user());
            String reference = "SUB-" + user.getName().toUpperCase() + "-" + sub.getDatesub().toString();
            sub.setReference(reference);

            String req = "INSERT INTO `subscription`( `user_id`, `date_sub`, `date_expire`, `type`,"
                    + " `paiement_type`, `amount`, `state`, `reference`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, sub.getId_user());
            pst.setDate(2, sub.getDatesub());
            Date dateExpire = sub.getDatesub();
            if (sub.getType().equals("1")) {
                pst.setDate(3, java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(30)));
                sub.setDateExpire(java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(30)));
                sub.setAmount(80);
            } else if (sub.getType().equals("2")) {
                pst.setDate(3, java.sql.Date.valueOf(
                        new java.sql.Date(System.currentTimeMillis()).toLocalDate().plusDays(90)));
                sub.setDateExpire(java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(90)));
                sub.setAmount(180);
            } else {
                pst.setDate(3, java.sql.Date.valueOf(
                        new java.sql.Date(System.currentTimeMillis()).toLocalDate().plusDays(120)));
                sub.setDateExpire(java.sql.Date.valueOf(
                        dateExpire.toLocalDate().plusDays(120)));
                sub.setAmount(360);
            }

            EmailManager em = new EmailManager();
            HtmlMessages ht = new HtmlMessages();
            System.out.println("LOAD TEMPLATE");
            ht.setMessage(sub, user);
            ht.setMessage2(user, sub);
            String subject = "Subscription Information";
            System.out.println("sending mail .... ");
            em.SendMail(user.getEmail(), ht.getMessage(), subject);
            System.out.println("sended mail .... ");
            // GENERATE REFERENCE 

            sub.setState("Confirmed");

            pst.setString(4, sub.getType());
            pst.setString(5, sub.getPaiementMethod());
            pst.setInt(6, sub.getAmount());
            pst.setString(7, sub.getState());
            pst.setString(8, sub.getReference());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Subscription> subListe() {
        String req = "SELECT * FROM `subscription`   ORDER BY date_sub DESC";
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
                UserService userservice = new UserService();
                User userOwner = userservice.userById(rs.getInt("user_id"));
                sub.setUser(userOwner);
                // add user by id here from FEDI 
                subs.add(sub);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return subs;
    }

    public ArrayList<Subscription> subscriptionByUser(int id) {

        ArrayList<Subscription> subs = new ArrayList<>();

        try {
            String req = "SELECT * FROM `subscription` where user_id = ? ORDER BY date_sub DESC";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
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
                UserService userservice = new UserService();
                User userOwner = userservice.userById(rs.getInt("user_id"));
                sub.setUser(userOwner);

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
            if (State.equals("Suspend")) {
                System.out.println("update to Suspend");
                String req1 = "UPDATE `subscription` SET `date_expire`= ? WHERE  id = ?";
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement(req1);
                pst1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                pst1.setInt(2, id);
                pst1.executeUpdate();
            } else if (State.equals("Insuspend")) {
                System.out.println("update to Confirmed");
                String reqSelect = "SELECT * FROM `subscription` WHERE id = ?";
                PreparedStatement pst12 = MyConnection.getInstance().getCnx().prepareStatement(reqSelect);
                pst12.setInt(1, id);
                ResultSet rs = pst12.executeQuery();
                Subscription subs = new Subscription();
                while (rs.next()) {
                    subs.setId(rs.getInt(1));
                    subs.setDatesub(rs.getDate("date_sub"));
                    subs.setAmount(rs.getInt("amount"));
                    subs.setDateExpire(rs.getDate("date_expire"));
                    subs.setPaiementMethod(rs.getString("paiement_type"));
                    subs.setType(rs.getString("type"));
                    subs.setReference(rs.getString("reference"));
                    subs.setState(rs.getString("state"));
                }

                long days = TimeUnit.DAYS.convert(Math.abs(subs.getDatesub().getTime() - subs.getDateExpire().getTime()), TimeUnit.MILLISECONDS);
                long daysPeriod;
                if (subs.getType().equals("1")) {
                    daysPeriod = 30;
                } else if (subs.getType().equals("2")) {
                    daysPeriod = 90;
                } else {
                    daysPeriod = 120;
                }

                long restDays = daysPeriod - days;

                Date newExpirationDate = java.sql.Date.valueOf(
                        new java.sql.Date(System.currentTimeMillis()).toLocalDate().plusDays(restDays));

                String req1 = "UPDATE `subscription` SET `date_expire`= ? WHERE  id = ?";
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement(req1);
                pst1.setDate(1, newExpirationDate);
                pst1.setInt(2, id);
                pst1.executeUpdate();

            }
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
