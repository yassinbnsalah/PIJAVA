/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationpi;

import services.DisponibilityService;
import services.OrderLineService;
import services.OrderService;
import services.SubServices;
import util.MyConnection;
import models.Order;
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
       // SubServices subserv = new SubServices();
        //System.out.println(subserv.subListe());
        //subserv.UpdateStateSub("Cancel", 101);
        /*DisponibilityService dispoService = new DisponibilityService() ;
        System.out.println(dispoService.disponibilityListe(1));
        dispoService.deleteDisponibility(49);*/
       // OrderService orderservice = new OrderService();
       // System.out.println(orderservice.OrderListe());
       // Order order = new Order();
       // order.setReference("ORDERFROMJAVAFX");
       // order.setDateOrder(new java.sql.Date(System.currentTimeMillis()));
      //  order.setNote("urgent");
       // order.setPaiementmethod("cash en delivrey");
        //order.setPrice(150);
       // order.setShippingadress("Ariana Tunis");
        //order.setState("inconfirmed");
        //update State ORder tekhdem jawha 6 ZIT 
        // orderservice.updateStateOrder(59, "Confirmed");
        // order ajoute tekhdem jawha behy ne9sa just orderlines 
        //   orderservice.AddOrder(order);

        /*OrderLineService orderlineservice = new OrderLineService(); 
        System.out.println(orderlineservice.orderlineListe(15));*/
           UserService userserv =new UserService();
       System.out.println(userserv.userListe());
       TicketService ticketserv =new TicketService();
       System.out.println(ticketserv.listTicket());
    }

}
