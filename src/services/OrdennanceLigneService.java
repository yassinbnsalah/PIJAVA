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
import models.OrdennanceLigne;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class OrdennanceLigneService {
     public void addOrdennanceLigne(OrdennanceLigne ordennanceLigne) {
        try {
            String req = "INSERT INTO `ordennance_ligne`(`medicament_id`, `ordennance_id`, `qunatite`) VALUES (?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, ordennanceLigne.getMedicament_id());
            pst.setInt(2, ordennanceLigne.getOrdennance_id());
            pst.setInt(3, (int) ordennanceLigne.getQuantite());
            pst.executeUpdate();
            System.out.println("ordennanceligne created successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<OrdennanceLigne> ordennanceligneListe(int id) {
        ArrayList<OrdennanceLigne> listeOrdennanceLigne = new ArrayList<>();
        try {
            String req = "SELECT * FROM `ordennance_ligne` inner join medicament on medicament.id = ordennance_ligne.medicament_id  WHERE `ordennance_id` = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrdennanceLigne ordennanceLigne = new OrdennanceLigne();
                ordennanceLigne.setId(rs.getInt(1));
                ordennanceLigne.setMedicament_id(rs.getInt(1));
                ordennanceLigne.setOrdennance_id(rs.getInt(1));
                ordennanceLigne.setQuantite(rs.getInt("qunatite"));
                ordennanceLigne.setNomMedicament(rs.getString("nom"));
                listeOrdennanceLigne.add(ordennanceLigne);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeOrdennanceLigne;
    }

    public void deleteMedicamment(int id) {
        try {
            String req = "DELETE FROM `ordennance_ligne` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println(" ligne Deleted !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
