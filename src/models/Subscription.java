/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author yacin
 */
public class Subscription {
    private int id ; 
    private Date datesub ; 
    private Date dateExpire ; 
    private String type ; 
    private String paiementMethod ; 
    private int amount ; 
    private String state ; 
    private String reference ; 
    private User user ; 
    private int id_user ; 
    public Subscription() {
    }

    public Subscription(int id, Date datesub, Date dateExpire, String type, String paiementMethod, int amount, String state, String reference, User user) {
        this.id = id;
        this.datesub = datesub;
        this.dateExpire = dateExpire;
        this.type = type;
        this.paiementMethod = paiementMethod;
        this.amount = amount;
        this.state = state;
        this.reference = reference;
        this.user = user;
    }

    public Subscription(Date datesub, Date dateExpire, String type, String paiementMethod, int amount, String state, String reference, User user) {
        this.datesub = datesub;
        this.dateExpire = dateExpire;
        this.type = type;
        this.paiementMethod = paiementMethod;
        this.amount = amount;
        this.state = state;
        this.reference = reference;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Date getDatesub() {
        return datesub;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public String getType() {
        return type;
    }

    public String getPaiementMethod() {
        return paiementMethod;
    }

    public int getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }

    public String getReference() {
        return reference;
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

    public void setDatesub(Date datesub) {
        this.datesub = datesub;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPaiementMethod(String paiementMethod) {
        this.paiementMethod = paiementMethod;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Subscription{" + "id=" + id + ", datesub=" + datesub + ","
                + " dateExpire=" + dateExpire + ", type=" + type + ","
                + " paiementMethod=" + paiementMethod + ","
                + " amount=" + amount + ", state=" + state + ","
                + " reference=" + reference + ", user=" + id_user + "}\n";
    }
    
    
    
    

}
