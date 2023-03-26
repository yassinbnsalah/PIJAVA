/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yacin
 */
public class MyConnection {
     final String URL = "jdbc:mysql://localhost:3306/codeofdutypi"; 
    final String USR = "root" ; 
    final String PWD ="" ; 
    Connection cnx ; 
    
   public static MyConnection instance;
    
    private MyConnection(){
        try {
           cnx = DriverManager.getConnection(URL ,USR ,PWD);
           System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
           System.err.print(ex.getMessage());
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
        instance = new MyConnection();
        }
        return instance;
    }
}