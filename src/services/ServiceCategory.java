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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Category;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class ServiceCategory {

    private Connection cnx;

    public ServiceCategory() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public static ObservableList<Category> RecupBase() {

        ObservableList<Category> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = MyConnection.getInstance().getCnx();
        String sql = "select *from category";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();
            while (R.next()) {
                Category r = new Category();
                r.setId((R.getInt(1)));
                r.setName(R.getString(2));
                r.setSlug(R.getString(3));

                list.add(r);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return list;
    }

    public Category SelectCategory(int id) {
        Category r = new Category();
        String req = "SELECT * FROM category where id =" + id + "";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);

            while (rs.next()) {

                r = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("slug"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public List<Category> read_all() throws SQLException {
        List<Category> arr = new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from category");
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String slug = rs.getString(3);

            Category d = new Category(id, name, slug);
            arr.add(d);
        }
        return arr;
    }

   
    public void ajouter(Category c) throws SQLException {
        Statement ste = cnx.createStatement();

        String requeteInsert = "INSERT INTO category (id,name,slug ) VALUES (NULL, '" + c.getName() + "','" + c.getSlug() + "');";
        ste.executeUpdate(requeteInsert);
    }

  
    public boolean delete(Category c) throws SQLException {
        Statement ste = cnx.createStatement();
        if (search(c) == true) {
            ste = cnx.createStatement();
            String requeteDelete = "DELETE FROM category WHERE id=" + c.getId() + "";
            ste.executeUpdate(requeteDelete);
        } else {
            System.out.println("Le Role n'existe pas");
        }
        return true;
    }

    public boolean update(Category c) throws SQLException {
        if (search(c) == true) {
            PreparedStatement pre = cnx.prepareStatement("UPDATE category SET id ='" + c.getId() + "' , type = '" + c.getName() + "', slug = '" + c.getSlug() + "' WHERE `id`= '" + c.getId());
            pre.setInt(1, c.getId());
            pre.setString(2, c.getName());
            pre.setString(3, c.getSlug());
            pre.executeUpdate();
            return true;
        } else {
            System.out.println("La category  n'existe pas");
            return true;
        }
    }

  
    public boolean search(Category c) throws SQLException {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from category");
        boolean ok = false;
        while (rs.next() && (ok == false)) {
            if (rs.getInt(1) == c.getId()) {
                ok = true;
            }
        }
        return ok;
    }

    public boolean Update2(Category d) throws SQLException {
        if (search(d) == true) {
            PreparedStatement pre = cnx.prepareStatement("UPDATE category SET name ='" + d.getName() + "',slug ='" + d.getSlug() + "'  WHERE `id`='" + d.getId() + "' ");

            pre.executeUpdate();
            return true;
        } else {
            System.out.println("La division n'existe pas");
            return true;
        }

    }

    public int RecupTotal() {

        int total = 0;

        java.sql.Connection cnx;
        cnx = MyConnection.getInstance().getCnx();
        String sql = "SELECT COUNT(*) AS total_clent FROM category";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();
            while (R.next()) {

                total = R.getInt(1);

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return total;
    }
}
