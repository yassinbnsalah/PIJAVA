/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author yacin
 */
public class Order {
    private int id ; 
    private String reference ; 
    private Date dateOrder ; 
    private String state ; 
    private int price ; 
    private String shippingadress ; 
    private String note ;
    private String paiementmethod ; 
    private ArrayList<OrderLine> orderline ; 
    
    public Order() {
    }

    public Order(int id, String reference, Date dateOrder, String state, int price, String shippingadress, String note, String paiementmethod) {
        this.id = id;
        this.reference = reference;
        this.dateOrder = dateOrder;
        this.state = state;
        this.price = price;
        this.shippingadress = shippingadress;
        this.note = note;
        this.paiementmethod = paiementmethod;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public String getState() {
        return state;
    }

    public int getPrice() {
        return price;
    }

    public String getShippingadress() {
        return shippingadress;
    }

    public String getNote() {
        return note;
    }

    public String getPaiementmethod() {
        return paiementmethod;
    }

    public ArrayList<OrderLine> getOrderline() {
        return orderline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setShippingadress(String shippingadress) {
        this.shippingadress = shippingadress;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPaiementmethod(String paiementmethod) {
        this.paiementmethod = paiementmethod;
    }

    public void setOrderline(ArrayList<OrderLine> orderline) {
        this.orderline = orderline;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", reference=" + reference + ","
                + " dateOrder=" + dateOrder + ", state=" + state + ", "
                + "price=" + price + ", shippingadress=" + shippingadress + ", note=" + note + ","
                + " paiementmethod=" + paiementmethod + ",\n orderlines=" + orderline + "}\n";
    }

  
    
    
    
}
