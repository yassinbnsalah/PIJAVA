/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Esprit extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      try {
            Parent root = FXMLLoader.load(getClass(). getResource("Produit.fxml"));
            Scene scene = new Scene(root);
          
            
            
            primaryStage.setTitle("category");
            primaryStage.setScene(scene);
            primaryStage.show();         
            
        } catch (IOException ex) {
            Logger.getLogger(Esprit.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
