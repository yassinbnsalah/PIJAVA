/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author AMIRA
 */
public class Ordennance {

    private int id;
    private Timestamp dateordenance;
    private float amount;
    private User doctor;
    private Rendezvous rendezvous;
    private ArrayList<OrdennanceLigne> ordennanceligne;

    public Ordennance() {
    }

    public Ordennance(int id, Timestamp dateordenance, float amount, User doctor, ArrayList<OrdennanceLigne> ordennanceligne) {
        this.id = id;
        this.dateordenance = dateordenance;
        this.amount = amount;
        this.doctor = doctor;
        this.ordennanceligne = ordennanceligne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateordenance() {
        return dateordenance;
    }

    public void setDateordenance(Timestamp dateordenance) {
        this.dateordenance = dateordenance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public ArrayList<OrdennanceLigne> getOrdennanceligne() {
        return ordennanceligne;
    }

    public void setOrdennanceligne(ArrayList<OrdennanceLigne> ordennanceligne) {
        this.ordennanceligne = ordennanceligne;
    }

    public Rendezvous getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
    }

    @Override
    public String toString() {
        return "Ordennance{" + "id=" + id + ", dateordenance=" + dateordenance + ", amount=" + amount + ", doctor=" + doctor + ", rendezvous=" + rendezvous + ", ordennanceligne=" + ordennanceligne + '}';
    }

}
