/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Produit {
     private int id;
   private Category category_id ;

    private String name ;
    private String description ; 
    private int buyprice ;
    private int sellprice ;
    private int quantity ;
    private String image ;
    private double Rate;

    public Produit(int sellprice) {
        this.sellprice = sellprice;
    }

    public Produit(int id, int sellprice, double Rate) {
        this.id = id;
        this.sellprice = sellprice;
        this.Rate = Rate;
    }
    
    

    public Produit(int id, Category category_id, String name, String description, int buyprice, int sellprice, int quantity, String image, double Rate) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.quantity = quantity;
        this.image = image;
        this.Rate = Rate;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double Rate) {
        this.Rate = Rate;
    }

  
 
    

    public Produit(int id, Category category_id, String name, String description, int buyprice, int sellprice, int quantity, String image) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.quantity = quantity;
        this.image = image;
    }

   
    public Produit() {
      
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getBuyprice() {
        return buyprice;
    }

    public int getSellprice() {
        return sellprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyprice(int buyprice) {
        this.buyprice = buyprice;
    }

    public void setSellprice(int sellprice) {
        this.sellprice = sellprice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description + ", buyprice=" + buyprice + ", sellprice=" + sellprice + ", quantity=" + quantity + ", image=" + image + ", Rate=" + Rate + '}';
    }

   
    
    
    
    
    
 
    
}
