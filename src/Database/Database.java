/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Database {
    
       private final String url = "jdbc:mysql://localhost:3306/codeofdutypi";
    private final String login = "root";
    private final String pwd = "";

    private Connection cnx;

    private static Database instance;

    private Database(){
        try {
            cnx = (Connection) DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion !");
            System.out.println(e.getMessage());
        }
    }

    public static Database getInstance(){
        if (instance == null) instance = new Database();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

  
    
}
