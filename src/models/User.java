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
public class User {
    private int id ; 
    private String cin ; 

    public User() {
    }

    public User(int id, String cin) {
        this.id = id;
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + '}';
    }
    
}
