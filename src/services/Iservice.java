/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import models.Disponibility;
import models.Order;
import models.OrderLine;
import models.Subscription;
import models.Ticket;
import models.User;

/**
 *
 * @author yacin
 * @param <T>
 */
public interface Iservice<T> {

    /**
     * USER SERVICES *
     */
    public void AddUser(User user);

    public ArrayList<User> userListe();

    public void supprimerUtilisateur(User user);

    /**
     * DISPONIBILITY SERVICES *
     */
    public void AddDisponibility(Disponibility p);

    public void deleteDisponibility(int id);

    public void updateStateDisponibility(int id);

    public ArrayList<Disponibility> disponibilityListe(int id);

    /**
     * ORDER SERVICES *
     */
    public void AddOrder(Order order, ArrayList<OrderLine> orderlines);

    public ArrayList<Order> OrderListe();

    public int OrderIDByReference(String reference);

    public Order orderByID(int id);

    public void updateStateOrder(int id, String state);

    /**
     * ORDERLINE SERVICES *
     */
    public void addOrderline(OrderLine orderline);

    public ArrayList<OrderLine> orderlineListe(int id);

    /**
     * SUBSCRIPTION SERVICES *
     */
    public void AddSubscription(Subscription sub);

    public ArrayList<Subscription> subListe();

    public void UpdateStateSub(String State, int id);

    public void DeleteSub(int id);

    /**
     * TICKET SERVICES *
     */
    public void ajouterTicket(Ticket t);

    public void UpdateStateTicket(String State, int id);

    public void SupprimerTicket(int id);

    public List<Ticket> listTicket();

    public List<Ticket> recupererUser(int ident);

    public ObservableList<Ticket> getall();

}
