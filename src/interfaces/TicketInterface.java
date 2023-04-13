/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import models.Ticket;

/**
 *
 * @author 21693
 */
public interface TicketInterface {
     public void ajouterTicket(Ticket t);
     public void UpdateStateTicket(int id);
     public void SupprimerTicket(int id);
     public List<Ticket> listTicket();
     public List<Ticket> recupererUser(int ident);
     public ObservableList<Ticket> getall();
    
}
