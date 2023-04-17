/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controllers.OrderListeController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.OrderHolder;

/**
 *
 * @author yacin
 */
public final class Routage {

    private final static Routage INSTANCE = new Routage();

    private Routage() {
    }

    public static Routage getInstance() {
        return INSTANCE;
    }

    public void GOTO(Button btn , String url) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();
            btn.getScene().setRoot(root);  
        } catch (IOException ex) {
            Logger.getLogger(OrderListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
