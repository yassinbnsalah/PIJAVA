/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author djoum
 */
public class CRUD_Activity {

    private Connection connection;

    public CRUD_Activity(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS activities (nom VARCHAR(255) PRIMARY KEY, description VARCHAR(255) NOT NULL)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            System.out.println("Table 'activity' created");
        } catch (SQLException e) {
            System.err.println("Cannot create table: " + e.getMessage());
        }
    }

    public boolean addActivity(Activity activity) {
        try {
            if (activityExists(activity.getNom())) {
                return false; // activity already exists, cannot add
            }
            String query = "INSERT INTO activity (nom, description) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, activity.getNom());
            stmt.setString(2, activity.getDescription());
            stmt.executeUpdate();
            System.out.println("Activity added to the database");
            return true;
        } catch (SQLException e) {
            System.err.println("Cannot add activity: " + e.getMessage());
            return false;
        }
    }

    public boolean updateActivity(Activity oldActivity, Activity newActivity) {
        try {
            if (!activityExists(oldActivity.getNom())) {
                return false; // activity does not exist, cannot update
            }
            String query = "UPDATE activity SET nom = ?, description = ? WHERE nom = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newActivity.getNom());
            stmt.setString(2, newActivity.getDescription());
            stmt.setString(3, oldActivity.getNom());
            stmt.executeUpdate();
            System.out.println("Activity updated in the database");
            return true;
        } catch (SQLException e) {
            System.err.println("Cannot update activity: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteActivity(Activity activity) {
        try {
            if (!activityExists(activity.getNom())) {
                return false; // activity does not exist, cannot delete
            }
            String query = "DELETE FROM activity WHERE nom = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, activity.getNom());
            stmt.executeUpdate();
            System.out.println("Activity deleted from the database");
            return true;
        } catch (SQLException e) {
            System.err.println("Cannot delete activity: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Activity> getAllActivities() {
        ArrayList<Activity> activities = new ArrayList<>();
        try {
            String query = "SELECT * FROM activity";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                activities.add(new Activity(nom, description));
            }
        } catch (SQLException e) {
            System.err.println("Cannot get activities: " + e.getMessage());
        }
        return activities;
    }

    private boolean activityExists(String nom) throws SQLException {
        String query = "SELECT COUNT(*) FROM activity WHERE nom = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, nom);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }
    
    public List<Activity> searchActivityByName(String name) {
    try {
        String sql = "SELECT * FROM activity WHERE nom LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + name + "%");
        ResultSet rs = stmt.executeQuery();

        List<Activity> activities = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String activityName = rs.getString("nom");
            String description = rs.getString("description");
            Activity activity = new Activity(activityName, description);
            activities.add(activity);
        }

        return activities;

    } catch (SQLException e) {
        System.out.println("Error searching activity by name: " + e.getMessage());
        return null;
    }
}


}
