/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author yacin
 */
public class OrderLine {
    
    private int id ; 
    private Order relatedOrder ; 
    private int quantity ; 
    private float price ; 

    public OrderLine() {
    }

    public OrderLine(int id, Order relatedOrder, int quantity, float price) {
        this.id = id;
        this.relatedOrder = relatedOrder;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Order getRelatedOrder() {
        return relatedOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRelatedOrder(Order relatedOrder) {
        this.relatedOrder = relatedOrder;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "id=" + id + ", relatedOrder=" + relatedOrder + ", quantity=" + quantity + ", price=" + price + "}\n";
    }
    
    
}