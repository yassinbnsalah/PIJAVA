/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import services.SubServices;
import util.EmailManager;
import util.MyConnection;
import models.Subscription;
import models.Disponibility;
import models.OrderLine;
import models.Order;
import services.DisponibilityService;
import services.OrderService;
import services.TicketService;
import services.UserService;
//import util.EmailManager;

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
        /**
         * ************************************************
         * *************** CONNECTION DATA BASE **************
         * ************************************************
         */
        MyConnection cnx = MyConnection.getInstance();
        /**
         * ************************************************
         * *************** YESSINE IS WORK ******************
         * ***********************************************
         */
        /**
         * ************************************************
         * *************** SUBSCRIPTION MANAGMENT ***********
         * ***********************************************
         */
        SubServices subserv = new SubServices();
        /**
         * ************************************************
         * *************** SUBSCRIPTION LISTE ***********
         * ***********************************************
         */
        //System.out.println(subserv.subListe());/*DONE*/
        /**
         * ************************************************
         * *************** SUBSCRIPTION CREATION ***********
         * ***********************************************
         */
        Subscription sub = new Subscription();
        sub.setId_user(1);
        sub.setAmount(150);
        sub.setDatesub(new java.sql.Date(System.currentTimeMillis()));
        sub.setPaiementMethod("cash fel salle");
        sub.setReference("SUBFROMJAVAFXGG");
        sub.setState("confirmed");
        sub.setType("2");
        /**
         * *****************************************************
         * ************** subserv.AddSubscription(sub); *********
         * ******************************************************
         */

        /**
         * ************************************************
         * *************** SUBSCRIPTION UPDATE STATE ***********
         * ***********************************************
         */
        //subserv.UpdateStateSub("Cancel", 106);
        /**
         * ************************************************
         * *************** DISPONIBILITY MANAGMENT ***********
         * ***********************************************
         */
        /**
         * ****** CREATE DISPONIBILITY SERVICE INSTANCE ******
         */
        DisponibilityService dispoService = new DisponibilityService();

        /**
         * ****** DISPLAY DISPONIBILITY LISTE BY USER ******
         */
        // System.out.println(dispoService.disponibilityListe(1));
        /**
         * ****** CREATE DISPONIBILITY ******
         */
        Disponibility dispo = new Disponibility();
        dispo.setDateDispo(new java.sql.Date(System.currentTimeMillis()));
        dispo.setHeureEnd("14:00");
        dispo.setHeureStart("08:00");
        dispo.setId_doctor(1);
        dispo.setNote("daily shift");
        dispo.setState("available");
        // dispoService.AddDisponibility(dispo); /**DONE**/

        /**
         * ****** DELETE DISPONIBILITY ******
         */
        //  dispoService.deleteDisponibility(48);/**DONE**/
        /**
         * ****** UPDATE STATE DISPONIBILITY ******
         */
        //  dispoService.updateStateDisponibility(50);
        /**
         * ************************************************
         * *************** ORDER MANAGMENT ***********
         * ***********************************************
         */
        /**
         * ************************************************
         * *************** ORDER SERVICE INSTANCE *********
         * ***********************************************
         */
        OrderService orderservice = new OrderService();

        /**
         * ****** DISPLAY ORDER LISTE ******
         */
        //System.out.println(orderservice.OrderListe());
        /**
         * ****** CREATE ORDER ************
         */
        Order order = new Order();
        order.setReference("ORDERFROMJAVAFX23");
        order.setDateOrder(new java.sql.Date(System.currentTimeMillis()));
        order.setNote("urgent");
        order.setPaiementmethod("cash en delivrey");
        order.setPrice(150);
        order.setShippingadress("Ariana Tunis");
        order.setState("inconfirmed");
        OrderLine orderline1 = new OrderLine();
        orderline1.setPrice(150);
        orderline1.setQuantity(2);
        orderline1.setProduct_id(2);
        orderline1.setRelatedOrder(order);
        ArrayList<OrderLine> orderlines = new ArrayList<>();
        orderlines.add(orderline1);
        //orderservice.AddOrder(order, orderlines);
        /**
         * ****** ORDER DETAILS ************
         */
        // System.out.println(orderservice.orderByID(64));
        /**
         * ****** UPDATE ORDER STATE ************
         */
        //orderservice.updateStateOrder(64, "Confirmed");

        /**
         * ************************************************
         * *************** FEDI IS WORK ******************
         * ***********************************************
         */
       /* UserService userserv = new UserService();
        System.out.println(userserv.userListe());
        TicketService ticketserv = new TicketService();
        System.out.println(ticketserv.listTicket());*/
      
    }

}
