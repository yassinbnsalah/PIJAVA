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
public class Product {
    private int id ;
    private String name ; 
    private float sellprice ; 
    private float buyprice ; 
    private int quantity ; 
    private String description ; 

    public Product() {
    }

    public Product(int id, String name, float sellprice, float buyprice, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.sellprice = sellprice;
        this.buyprice = buyprice;
        this.quantity = quantity;
        this.description = description;
    }

    public Product(String name, float sellprice, float buyprice, int quantity, String description) {
        this.name = name;
        this.sellprice = sellprice;
        this.buyprice = buyprice;
        this.quantity = quantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getSellprice() {
        return sellprice;
    }

    public float getBuyprice() {
        return buyprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSellprice(float sellprice) {
        this.sellprice = sellprice;
    }

    public void setBuyprice(float buyprice) {
        this.buyprice = buyprice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", sellprice=" + sellprice + ", buyprice=" + buyprice + ", quantity=" + quantity + ", description=" + description + '}';
    }
    
}
