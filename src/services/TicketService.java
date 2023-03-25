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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Ticket;
import util.MyConnection;

/**
 *
 * @author Fedi
 */
public class TicketService {
    
   Connection cnx;

    public TicketService() {
        cnx = MyConnection.getInstance().getCnx();
    }


    public void ajouterTicket(Ticket t) {
        try {
            String req = "insert into ticket(owner_id,message,titre,date_ticket,state) values( " +t.getId_user()+",' " + t.getMessage()+ "','" + t.getTitre()+ "','"
                    + t.getDateticket()+"','on Pending');";
          
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ticket ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      public void UpdateStateTicket(String State, int id) {
        try {
            String req = "UPDATE `ticket` SET `state`= ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(2, id);
            
            pst.setString(1, State);
            pst.executeUpdate();
            System.out.println(" Ticket State updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void SupprimerTicket(int id) {
    try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM ticket WHERE id = "+id+"";
                st.executeUpdate(req);      
      System.out.println("Ticket supprimer avec succès...");
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());        
              }
    }


    public List<Ticket> listTicket() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            String req = "select * from ticket order by date_ticket DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt(1));
                t.setMessage(rs.getString("message"));
                t.setTitre(rs.getString("titre"));
                t.setDateticket(rs.getDate("date_ticket"));
                t.setState(rs.getString("state"));
               tickets.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tickets;
    }
    
      public List<Ticket> recupererUser(int ident) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            String req = "select * from ticket where owner_id='"+ident+"' order by date_ticket DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt(1));
                t.setMessage(rs.getString("message"));
                t.setTitre(rs.getString("titre"));
                t.setDateticket(rs.getDate("date_ticket"));
                t.setState(rs.getString("state"));
               tickets.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tickets;
    }
      
public ObservableList<Ticket> getall() {
        ObservableList<Ticket> tickets = FXCollections.observableArrayList();
        try {
            String req = "select * from ticket";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt(1));
                t.setMessage(rs.getString("message"));
                t.setTitre(rs.getString("titre"));
                t.setDateticket(rs.getDate("date_ticket"));
                t.setState(rs.getString("state"));
               tickets.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tickets;
    }
}
