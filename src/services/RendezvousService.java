/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import util.MyConnection;
import models.Rendezvous;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.User;

/**
 *
 * @author yacin
 */
public class RendezvousService {

    public void AddRendezvous(Rendezvous rdv) {
        try {
            String req = "INSERT INTO `rendez_vous`(`date_rv`,  `date_passage_rv`,`state`,`note`, hour_rv,hour_passage_rv, fromuser_id, todoctor_id)"
                    + " VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setTimestamp(1, rdv.getDate_rv());
            pst.setTimestamp(2, rdv.getDate_passage_rv());
            pst.setString(3, rdv.getState());
            pst.setString(4, rdv.getNote());
            pst.setString(5, rdv.getHour_rv());
            pst.setString(6, rdv.getHour_passage_rv());
            pst.setInt(7, rdv.getUser().getId());
            pst.setInt(8, rdv.getDoctor().getId());

            pst.executeUpdate();
            System.out.println("rendez_vous created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Rendezvous> RendezvousListe() {
        ArrayList<Rendezvous> liste = new ArrayList<>();
        OrdennanceLigneService ordennanceligneservice = new OrdennanceLigneService();
        try {
            String req = "SELECT * FROM `rendez_vous` INNER JOIN user u1 on u1.id = rendez_vous.fromuser_id INNER JOIN user u2 on u2.id = rendez_vous.todoctor_id;";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                User u = new User();
                u.setId(11);
                u.setNumero(rs.getString("numero"));

                User u2 = new User();
                u2.setId(20);

                u2.setName(rs.getString(25));

                Rendezvous rdv = new Rendezvous();
                rdv.setUser(u);
                rdv.setDoctor(u2);
                rdv.setId(rs.getInt(1));
                rdv.setDate_rv(rs.getTimestamp("date_rv"));
                rdv.setNote(rs.getString("note"));
                rdv.setDate_passage_rv(rs.getTimestamp("date_passage_rv"));
                rdv.setState(rs.getString("state"));
                rdv.setHour_passage_rv(rs.getString("hour_passage_rv"));
                rdv.setHour_rv(rs.getString("hour_rv"));

                liste.add(rdv);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }

    public void delete(int id) {
        try {
            String req = "DELETE FROM `rendez_vous` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println(" rendez vous Deleted !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(int id, Rendezvous rdv) {
        String sql = "UPDATE `rendez_vous` SET `date_rv`=?,`date_passage_rv`=?,hour_rv=?, `hour_passage_rv`=? WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setTimestamp(1, rdv.getDate_rv());
            pst.setTimestamp(2, rdv.getDate_passage_rv());
            pst.setString(4, rdv.getNote());
            pst.setString(5, rdv.getHour_rv());
            pst.setString(6, rdv.getHour_passage_rv());
            pst.setInt(7, id);
            System.out.println(pst);
            pst.executeUpdate();

            System.out.println("Medicamment est modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void confirmRendzeVous(Rendezvous rv) {
        String sql = "UPDATE `rendez_vous` SET state='confirm' WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);

            pst.setInt(1, rv.getId());
            System.out.println(pst);
            pst.executeUpdate();
            String message = "rendze vous à" + rv.getDate_rv() + " est confirmer";
            sendSMS(message, "+216" + rv.getUser().getNumero());

            System.out.println("rendze vous confirmé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cancelRendzeVous(Rendezvous rv) {
        String sql = "UPDATE `rendez_vous` SET state='Cancel' WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);

            pst.setInt(1, rv.getId());
            pst.executeUpdate();

            String message = "rendze vous à" + rv.getDate_rv() + " est annullé";

            sendSMS(message, "+216" + rv.getUser().getNumero());
            System.out.println("rendze vous confirmé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendSMS(String message, String num) {
        String accountSid = "AC632ccc77cdafb44168dff95021697262";
        String authToken = "eb5d6acb7726d9303c9a625c21328556";
        Twilio.init(accountSid, authToken);

        String from = "+16205079158"; // your Twilio phone number
        String to = "+21652793877"; // recipient phone number
        Message m = Message.creator(new PhoneNumber(to), new PhoneNumber(from), message).create();

        System.out.println(m.getSid()); // print the message SID to the console

    }

    public List<Rendezvous> rendezVousDateYear(int year, int month, int idDocteur) {
        ArrayList<Rendezvous> liste = new ArrayList<>();
        List<Rendezvous> l = new ArrayList<>();
        try {
            if (idDocteur != 0) {
                String req = "SELECT * FROM `rendez_vous` INNER JOIN user u1 on u1.id = rendez_vous.fromuser_id INNER JOIN user u2 on u2.id = rendez_vous.todoctor_id where state='confirm' and todoctor_id = " + idDocteur;
                Statement st = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(req);

                while (rs.next()) {
                    User u = new User();
                    u.setId(11);
                    u.setNumero(rs.getString("numero"));

                    User u2 = new User();
                    u2.setId(20);

                    u2.setName(rs.getString(25));

                    Rendezvous rdv = new Rendezvous();
                    rdv.setUser(u);
                    rdv.setDoctor(u2);
                    rdv.setId(rs.getInt(1));
                    rdv.setDate_rv(rs.getTimestamp("date_rv"));
                    rdv.setNote(rs.getString("note"));
                    rdv.setDate_passage_rv(rs.getTimestamp("date_passage_rv"));
                    rdv.setState(rs.getString("state"));
                    rdv.setHour_passage_rv(rs.getString("hour_passage_rv"));
                    rdv.setHour_rv(rs.getString("hour_rv"));

                    liste.add(rdv);
                }

                l = liste.stream().filter(rv -> {
                    System.out.println(rv.getDate_rv().toLocalDateTime().toLocalDate());
                    return rv.getDate_rv().toLocalDateTime().toLocalDate().getYear() == year && rv.getDate_rv().toLocalDateTime().toLocalDate().getMonthValue() == month;
                }).collect(Collectors.toList());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l;
    }
}
