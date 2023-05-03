/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author AMIRA
 */
public class OrdennanceLigne {

    private int id;
    private int medicament_id;
    private int ordennance_id;
    private float quantite;

    private String nomMedicament;

    public OrdennanceLigne() {
    }

    public OrdennanceLigne(int id, int medicament_id, int ordennance_id, float quantite) {
        this.id = id;
        this.medicament_id = medicament_id;
        this.ordennance_id = ordennance_id;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicament_id() {
        return medicament_id;
    }

    public void setMedicament_id(int medicament_id) {
        this.medicament_id = medicament_id;
    }

    public int getOrdennance_id() {
        return ordennance_id;
    }

    public void setOrdennance_id(int ordennance_id) {
        this.ordennance_id = ordennance_id;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    @Override
    public String toString() {
        return "OrdennanceLigne{" + "id=" + id + ", medicament_id=" + medicament_id + ", ordennance_id=" + ordennance_id + ", quantite=" + quantite + '}';
    }

}
