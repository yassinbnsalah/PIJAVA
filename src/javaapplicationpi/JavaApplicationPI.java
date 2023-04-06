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
        User user = new User();
        user.setCIN(14326640);
        user.setName("Fedi");
        user.setNumero(93293311);
        user.setEmail("Fedi@gmail.com");
        user.setAdresse("Ariana");
        user.setPassword("Fedi123:");
        
        /**
         * *****************************************************
         * ************** userserv.AddUser(user); *********
         * ******************************************************
         */
/**
         * ************************************************
         * *************** Ticket MANAGMENT ***********
         * ***********************************************
         */
         TicketService ticketserv = new TicketService();
       
         /**
         * ************************************************
         * *************** Ticket LISTE ***********
         * ***********************************************
         */
        // System.out.println(ticketserv.listTicket());*/
      
        /**
         * ****** CREATE Ticket ******
         */
        Ticket ticket = new Ticket();
        ticket.setDateticket(new java.sql.Date(System.currentTimeMillis()));
        ticket.setMessage("i cant log my acount");
        ticket.setTitre("Password Problem");
        ticket.setId_user(1);
         /**
         * *****************************************************
         * **************   ticketserv.ajouterTicket(ticket);  *********
         * ******************************************************
         */
      
        /**
         * ****** DELETE Ticket ******
         */
        // ticketserv.SupprimerTicket(15);/**DONE**/
        /**
         * ****** UPDATE STATE Ticket ******
         */
        // ticketserv.UpdateStateTicket("Confirmed",16);

      

       
      
    }

}
