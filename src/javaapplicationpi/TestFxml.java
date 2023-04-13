/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationpi;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SubServices;
import util.MyConnection;
import models.Subscription;
import models.Disponibility;
import models.OrderLine;
import models.Order;
import services.DisponibilityService;

import services.OrderService;
//import util.EmailManager;

/**
 *
 * @author yacin
 */
public class TestFxml extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       Parent rootD= FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
      //  Parent rootD = FXMLLoader.load(getClass().getResource("/view/Medecin/disponibilityListe.fxml"));
     //  Parent rootD = FXMLLoader.load(getClass().getResource("/view/admin/subscription/subscriptionListe.fxml"));
        Scene scene = new Scene(rootD);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /**
         * ************************************************
         * *************** CONNECTION DATA BASE **************
         * ************************************************
         */
        MyConnection cnx = MyConnection.getInstance();
        launch(args);

    }

}
