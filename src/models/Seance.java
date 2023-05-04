/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author yacin
 */
public class Seance {
      
    private int id;
    private String nom;
    private String description;
    private int duree;
    private String niveau;
   

    public Seance() {
    }

    public Seance(int id, String nom, String description, int duree, String niveau ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.niveau = niveau;
      
    }

    public Seance(String nom, String description, int duree, String niveau ) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.niveau = niveau;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", duree=" + duree +
                ", niveau='" + niveau + '\'' +
               
                '}';
    }
}
