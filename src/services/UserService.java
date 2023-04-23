/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import interfaces.UserInterface;
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
import java.util.regex.Pattern;
import util.BCrypt;

/**
 *
 * @author Fedi
 */
public class UserService implements UserInterface{
    
    Connection cnx2;
    public UserService(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
     public int existeMail2(String email) {
        try {
            Statement s = cnx2.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) from user WHERE email ='" + email + "'");
            int size = 0;

            rs.next();

            size = rs.getInt(1);

            return size;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return 0;
    }
     public int existeNumm(int email) {
        try {
            Statement s = cnx2.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) from user WHERE numero ='" + email + "'");
            int size = 0;

            rs.next();

            size = rs.getInt(1);

            return size;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return 0;
    }
     
    public void AddUser(User user) {
        try {
            String req = "INSERT INTO `user`(`cin`, `name`, `numero`, `email`, `adresse`, `password`,`roles`)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst.setString(1, user.getCIN());
            pst.setString(2, user.getName());
            pst.setInt(3, user.getNumero());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getAdresse());
            pst.setString(6, user.getPassword());
            pst.setString(7, "[\"ROLE_CLIENT\"]");
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
                user.setNumero(rs.getInt("Numero"));
                user.setAdresse(rs.getString("Adresse"));
                user.setPassword(rs.getString("Password"));
                liste.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }
   public void supprimerUtilisateur(int id) {
           try {
            String req = "DELETE FROM `user` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" user  Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   public void modifierUtilisateur(int id,User user) {
        try {
            String requete2="update user set cin=?,name=?,numero=?,email=?,adresse=? where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, user.getCIN());
            pst.setString(2, user.getName());
            pst.setInt(3, user.getNumero());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getAdresse());
            pst.setInt(6,id);
            pst.executeUpdate();
           
            System.out.println("Utlisateur est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    
   
    public String LoginUser(String txtUsername , String txtPassword ) {
        String status = "";
        String email = txtUsername;
        String password = txtPassword;
        System.out.println("email is " + email);
        System.out.println("password is " + password);
        if (email.isEmpty() || password.isEmpty()) {
           
            status = "Empty Credits";
        } else {
            //query
            String sql = "SELECT * FROM user Where email = ? and password = ?";
            try {
               PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    status = "email/password false";
                } else {
                
                       String Role = resultSet.getString("roles");
                       System.out.println("Role is "+Role);
                 
                    status = "success";
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }

        return status;
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
     public static boolean isValidPhoneNumber(String phone_number) {
        boolean isValid = phone_number.matches("\\d{8}");
        System.out.println(phone_number + " : " + isValid);
        return isValid;
    }
    public boolean isValidString(String email) {
        boolean result = true;

        if (Pattern.matches("[a-zA-Z]+", email)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    public String checkRole(String email) {
        String default_return = "roles not found";
        try {
            String req;
            req = "select roles from client where email=?";
            PreparedStatement st = cnx2.prepareStatement(req);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString("roles").equals("[\"ROLE_ADMIN\"]")) {

                    return "ADMIN";
                } else if (rs.getString("roles").equals("[\"ROLE_USER\"]")) {
                    System.out.println("third");
                    return "USER";

                }

            }

        } catch (SQLException ex) {
        }
        return default_return;
    }
   public void modifier2(int id, User r) {
        try {
        
            String req = "UPDATE `user` SET email = '" + r.getEmail()
                    + "', password = '" + r.getPassword()
                    + "' WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);

            System.out.println("User Modifieé avec succées ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   public User getByEmail(String email) {
        User t = new User();

        try {

           String req = ("SELECT * FROM `user` WHERE email = '" + email + "'");
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);

            rs.next();
            t.setId(rs.getInt("id"));
            t.setCIN(rs.getString("cin"));
            t.setName(rs.getString("name"));
            t.setEmail(rs.getString("email"));
            t.setPassword(rs.getString("password"));
            t.setNumero(rs.getInt("numero"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
   public boolean verifPassword(String email, String password) {
        try {
            Statement ste = cnx2.createStatement();

            ResultSet rs = ste.executeQuery("select e.* from user e where email='" + email + "'");

            while (rs.next()) {
                String passBase = rs.getString("password");
                if (BCrypt.checkpw(password, passBase)) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (SQLException sq) {
            return false;
        }
        return false;
    }

}
