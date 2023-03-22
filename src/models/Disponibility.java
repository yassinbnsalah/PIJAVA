/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author yacin
 */
public class Disponibility {
    private int id ; 
    private String heureStart  ; 
    private String heureEnd ; 
    private Date dateDispo ; 
    private String note ; 
    private String state ; 
    private User doctor ; 
    private int id_doctor ; 

    public Disponibility() {
    }

    public Disponibility(int id, String heureStart, String heureEnd, Date dateDispo, String note, String state, int id_doctor) {
        this.id = id;
        this.heureStart = heureStart;
        this.heureEnd = heureEnd;
        this.dateDispo = dateDispo;
        this.note = note;
        this.state = state;
        this.id_doctor = id_doctor;
    }

    public int getId() {
        return id;
    }

    public String getHeureStart() {
        return heureStart;
    }

    public String getHeureEnd() {
        return heureEnd;
    }

    public Date getDateDispo() {
        return dateDispo;
    }

    public String getNote() {
        return note;
    }

    public String getState() {
        return state;
    }

    public User getDoctor() {
        return doctor;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeureStart(String heureStart) {
        this.heureStart = heureStart;
    }

    public void setHeureEnd(String heureEnd) {
        this.heureEnd = heureEnd;
    }

    public void setDateDispo(Date dateDispo) {
        this.dateDispo = dateDispo;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    @Override
    public String toString() {
        return "Disponibility{" + "id=" + id + ","
                + " heureStart=" + heureStart + ","
                + "heureEnd=" + heureEnd + ", "
                + "dateDispo=" + dateDispo + ", "
                + "note=" + note + ", state=" + state + ", "
                + "doctor=" + doctor + ", id_doctor=" + id_doctor + "}\n";
    }
    
        
}
