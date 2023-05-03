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
import models.Disponibility;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class DisponibilityService {

    public void AddDisponibility(Disponibility dispo) {
        try {
            String req = "INSERT INTO `disponibility`(`doctor_id`, `date_dispo`, `heure_start`, `heure_end`, `note`, `state`)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, dispo.getId_doctor());
            pst.setDate(2, dispo.getDateDispo());
            pst.setString(3, dispo.getHeureStart());
            pst.setString(4, dispo.getHeureEnd());
            pst.setString(5, dispo.getNote());
            pst.setString(6, dispo.getState());
            pst.executeUpdate();
            System.out.println("disponibility created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Disponibility> disponibilityListe(int id) {
        ArrayList<Disponibility> liste = new ArrayList<>();
        try {
            String req = "SELECT * FROM `disponibility` WHERE doctor_id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Disponibility dispo = new Disponibility();
                dispo.setId(rs.getInt(1));
                dispo.setDateDispo(rs.getDate("date_dispo"));
                dispo.setHeureEnd(rs.getString("heure_end"));
                dispo.setHeureStart(rs.getString("heure_start"));
                dispo.setNote(rs.getString("note"));
                dispo.setState(rs.getString("state"));

                /*p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setAge(rs.getInt("age"));
                p.setCin(rs.getString("cin"));*/
                liste.add(dispo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }

    public void updateStateDisponibility(int id) {
        try {
            String req = "SELECT * FROM `disponibility` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String state = "clear";
                if (rs.getString("state").equals("full")) {
                    state = "available";
                } else {
                    state = "full";
                }
                String reqUpdate = "UPDATE `disponibility` SET"
                        + "`state` = ? WHERE id = ?";
                PreparedStatement pstUpdate = MyConnection.getInstance().getCnx().prepareStatement(reqUpdate);
                pstUpdate.setInt(2, id);
                pstUpdate.setString(1, state);
                pstUpdate.executeUpdate();
                System.out.println("disponibility updated successfully bros ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteDisponibility(int id) {
        try {
            String req = "DELETE FROM `disponibility` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println(" disponibility Deleted !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
