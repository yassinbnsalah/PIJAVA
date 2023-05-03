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
import models.Ordennance;
import models.Rendezvous;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class OrdennanceService {
     public void modifier(int id, Ordennance ordennance) {
        try {
            String requete2 = "UPDATE `ordennance` SET `dateordenance`=?,`amount`=?,`rendez_vous_id`=? WHERE `id`=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst.setTimestamp(1, ordennance.getDateordenance());
            pst.setFloat(2, ordennance.getAmount());
            pst.setInt(3, ordennance.getRendezvous().getId());
            pst.setInt(4, id);
            pst.executeUpdate();

            System.out.println("ordonace est modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AddOrdennance(Ordennance orden) {
        try {
            String req = "INSERT INTO `ordennance`( `dateordenance`, `amount`, `rendez_vous_id`)"
                    + " VALUES (?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setTimestamp(1, orden.getDateordenance());
            pst.setFloat(2, orden.getAmount());
            pst.setInt(3, orden.getRendezvous().getId());
            pst.executeUpdate();
            System.out.println("ordennance created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void delete(int id) {
        try {
            String req = "DELETE FROM `ordennance` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println(" medicamment Deleted !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Ordennance> OrdennanceListe() {
        ArrayList<Ordennance> liste = new ArrayList<>();
        OrdennanceLigneService ordennanceligneservice = new OrdennanceLigneService();
        try {
            String req = "SELECT * FROM `ordennance` inner join rendez_vous on rendez_vous.id = ordennance.rendez_vous_id; ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Ordennance ordennance = new Ordennance();
                ordennance.setId(rs.getInt(1));
                ordennance.setDateordenance(rs.getTimestamp("dateordenance"));
                ordennance.setAmount(rs.getInt("amount"));
                Rendezvous r = new Rendezvous();
                r.setDate_rv(rs.getTimestamp("date_rv"));
                r.setId(rs.getInt(5));
                ordennance.setRendezvous(r);
//                ordennance. setOrdennanceligne(ordennanceligneservice.ordennanceligneListe(rs.getInt(1)));
                liste.add(ordennance);
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

    public int OrderIDByRendez_VOus_Id(String rende_vous_id) {
        int id = 0;
        try {
            String req = "SELECT * FROM `ordennance` WHERE rendez_vous_id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, rende_vous_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                id = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public Ordennance ordennanceByID(int id) {
        OrdennanceLigneService ordennanceligneservice = new OrdennanceLigneService();
        Ordennance ordennance = new Ordennance();
        try {
            String req = "SELECT * FROM `ordenance` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                ordennance.setId(rs.getInt(1));
                ordennance.setDateordenance(rs.getTimestamp("date_ordennance"));
                ordennance.setAmount(rs.getInt("amount"));
//                ordennance.setRendez_vous_id(rs.getInt("1"));
                ordennance.setOrdennanceligne(ordennanceligneservice.ordennanceligneListe(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ordennance;
    }

    public void updateStateOrdennance(int id, String state) {

        try {
            String req = "UPDATE `ordennance` SET `state`= ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(2, id);
            pst.setString(1, state);
            pst.executeUpdate();
            System.out.println("Ordennance Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
