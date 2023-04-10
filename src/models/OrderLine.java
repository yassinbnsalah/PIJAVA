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

    private int id;
    private Order relatedOrder;
    private int quantity;
    private float price;
    private int product_id;
    private String product_name ; 
    private int product_price ; 

    public OrderLine() {
    }

    public OrderLine(int id, int quantity, float price, int product_id) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product_id = product_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_price() {
        return product_price;
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

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "id=" + id + ", relatedOrder=" + relatedOrder + ", quantity=" + quantity + ", price=" + price + ", product_id=" + product_id + '}';
    }

}
