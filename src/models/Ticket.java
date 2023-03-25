/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import models.User;

/**
 *
 * @author Fedi
 */
public class Ticket {
    private int id ; 
    private User user ; 
    private int id_user ;
    private String message ; 
    private String titre ; 
    private Date dateticket; 
    private String state ; 
  public Ticket() {
    }

    public Ticket(int id, Date dateticket, String message, String titre,  String state, User user) {
        this.id = id;
        this.dateticket = dateticket;
        this.message = message;
        this.titre = titre;
        this.state = state;
        this.user = user;
    }

    public Ticket(Date dateticket, String message, String titre,  String state, User user) {
        this.dateticket = dateticket;
        this.message = message;
        this.titre = titre;
        this.state = state;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Date getDateticket() {
        return dateticket;
    }


    public String getMessage() {
        return message;
    }

    public String getTitre() {
        return titre;
    }

  

    public String getState() {
        return state;
    }

  

    public User getUser() {
        return user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateticket(Date dateticket) {
        this.dateticket = dateticket;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



    public void setState(String state) {
        this.state = state;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", user=" + user + ", id_user=" + id_user + ", message=" + message + ", titre=" + titre + ", dateticket=" + dateticket + ", state=" + state + '}';
    }

}