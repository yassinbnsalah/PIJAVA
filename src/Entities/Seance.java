package Entities;

import java.time.LocalDateTime;

public class Seance {
    
    private int id;
    private String nom;
    private String description;
    private int duree;
    private String niveau;
    private LocalDateTime date;

    public Seance() {
    }

    public Seance(int id, String nom, String description, int duree, String niveau, LocalDateTime date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.niveau = niveau;
        this.date = date;
    }

    public Seance(String nom, String description, int duree, String niveau, LocalDateTime date) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.niveau = niveau;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", duree=" + duree +
                ", niveau='" + niveau + '\'' +
                ", date=" + date +
                '}';
    }
}
