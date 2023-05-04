/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author djoum
 */
public class MyConnection {
    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/codeofdutypi";
    String user = "root";
    String pwd = "";
    private static MyConnection instance;

    private MyConnection() {

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("Echec de la connexion");
        }
     }

    public static MyConnection getInstance() {
        if (MyConnection.instance == null) {
            MyConnection.instance = new MyConnection();
        }
        return MyConnection.instance;

    }

    public static Connection getConnection() {
        return MyConnection.getInstance().conn;
    }
}


