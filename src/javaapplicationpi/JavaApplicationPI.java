/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationpi;

import java.util.ArrayList;
import models.Disponibility;
import services.DisponibilityService;
import services.OrderLineService;
import services.OrderService;
import services.SubServices;
import util.MyConnection;
import models.Order;
import models.OrderLine;
import models.Subscription;
import models.Ticket;
import models.User;
import services.TicketService;
import services.UserService;

/**
 *
 * @author yacin
 */
public class JavaApplicationPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MyConnection cnx = MyConnection.getInstance();
            /**
         * ************************************************
         * *************** Fedi  WORK ******************
         * ***********************************************
         */
        /**
         * ************************************************
         * *************** User MANAGMENT ***********
         * ***********************************************
         */
       UserService userserv = new UserService();

        /**
         * ************************************************
         * *************** User LISTE ***********
         * ***********************************************
         */
        //System.out.println(userserv.userListe());/*DONE*/
        /**
         * ************************************************
         * *************** User  CREATION ***********
         * ***********************************************
         */

        
        userserv.BannedUtilisateur(71); 


      

       
      
    }

}
