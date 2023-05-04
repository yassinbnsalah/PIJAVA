/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.Seance;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class CRUD_Seance {
      private Connection connection;

    public CRUD_Seance(Connection connection) {
        this.connection = connection;
    }

    public CRUD_Seance() {
      //To change body of generated methods, choose Tools | Templates.
    }
    
    // Méthode pour ajouter une nouvelle séance
    public void addSeance(Seance seance) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        connection = MyConnection.getInstance().getCnx();
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM seance WHERE name=?  ");
        preparedStatement.setString(1, seance.getNom());
       
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) > 0) {
            throw new SQLException("La séance existe déjà !");
        }
        preparedStatement = connection.prepareStatement("INSERT INTO seance(name, description, duree, niveau) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, seance.getNom());
        preparedStatement.setString(2, seance.getDescription());
        preparedStatement.setInt(3, seance.getDuree());
        preparedStatement.setString(4, seance.getNiveau());
         
        preparedStatement.executeUpdate();
    } finally {
        if (resultSet != null) {
            //resultSet.close();
        }
        if (preparedStatement != null) {
            //preparedStatement.close();
        }
        if (connection != null) {
            //connection.close();
        }
    }
}

    
    // Méthode pour mettre à jour une séance existante
    public void updateSeance(Seance seance, Seance newSeance) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE seance SET name=?, description=?, duree=?, niveau=?  WHERE id=?");
            preparedStatement.setString(1, newSeance.getNom());
            preparedStatement.setString(2, newSeance.getDescription());
            preparedStatement.setInt(3, newSeance.getDuree());
            preparedStatement.setString(4, newSeance.getNiveau());
          
            preparedStatement.setInt(5, seance.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    // Méthode pour supprimer une séance existante
    public void deleteSeance(int seanceId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM seance WHERE id=?");
            preparedStatement.setInt(1, seanceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode pour récupérer une séance à partir de son id
    public Seance getSeanceById(int seanceId) {
        Seance seance = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM seance WHERE id=?");
            preparedStatement.setInt(1, seanceId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                seance = new Seance();
                seance.setId(rs.getInt("id"));
                seance.setNom(rs.getString("nom"));
                seance.setDescription(rs.getString("description"));
                seance.setDuree(rs.getInt("duree"));
                seance.setNiveau(rs.getString("niveau"));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance;
    }
    
    // Méthode pour récupérer toutes les séances de la base de données
public List<Seance> getAllSeances() {
    List<Seance> seances = new ArrayList<>();
    try {
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM seance");
            while(rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("name");
                String description = rs.getString("description");
                int duree = rs.getInt("duree");
                String niveau = rs.getString("niveau");
               // Date date = rs.getDate("date");
                Seance s = new Seance(id, nom, description, duree, niveau);
                seances.add(s);
            }
        } else {
            System.out.println("Connection to database failed!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return seances;
}


    public List<Seance> searchSeanceByName(String nom) {
    try {
        String sql = "SELECT * FROM seance WHERE name LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + nom + "%");
        ResultSet rs = stmt.executeQuery();

        List<Seance> seances = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nomSeance = rs.getString("name");
            String description = rs.getString("description");
            int duree = rs.getInt("duree");
            String niveau = rs.getString("niveau");
            
            
            Seance seance = new Seance(id, nomSeance, description, duree, niveau);
            seances.add(seance);
        }

        return seances;

    } catch (SQLException e) {
        System.out.println("Error searching seance by name: " + e.getMessage());
        return null;
    }
}


}
