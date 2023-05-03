/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author yacin
 */
public class Rendezvous {
    int id;
    Timestamp date_rv, date_passage_rv;
    String hour_rv, hour_passage_rv;
    String state = "inconfirmed";
    int fromuser_id, todoctor_id, ordennance_id;
    String note;

    User user;
    User doctor;

    public Rendezvous() {
    }

    public Rendezvous(int id, Timestamp date_rv, Timestamp date_passage_rv, String hour_rv, String hour_passage_rv, String state, int fromuser_id, int todoctor_id, int ordennance_id, String note) {
        this.id = id;
        this.date_rv = date_rv;
        this.date_passage_rv = date_passage_rv;
        this.hour_rv = hour_rv;
        this.hour_passage_rv = hour_passage_rv;
        this.state = state;
        this.fromuser_id = fromuser_id;
        this.todoctor_id = todoctor_id;
        this.ordennance_id = ordennance_id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate_rv() {
        return date_rv;
    }

    public void setDate_rv(Timestamp date_rv) {
        this.date_rv = date_rv;
    }

    public Timestamp getDate_passage_rv() {
        return date_passage_rv;
    }

    public void setDate_passage_rv(Timestamp date_passage_rv) {
        this.date_passage_rv = date_passage_rv;
    }

    public String getHour_rv() {
        return hour_rv;
    }

    public void setHour_rv(String hour_rv) {
        this.hour_rv = hour_rv;
    }

    public String getHour_passage_rv() {
        return hour_passage_rv;
    }

    public void setHour_passage_rv(String hour_passage_rv) {
        this.hour_passage_rv = hour_passage_rv;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFromuser_id() {
        return fromuser_id;
    }

    public void setFromuser_id(int fromuser_id) {
        this.fromuser_id = fromuser_id;
    }

    public int getTodoctor_id() {
        return todoctor_id;
    }

    public void setTodoctor_id(int todoctor_id) {
        this.todoctor_id = todoctor_id;
    }

    public int getOrdennance_id() {
        return ordennance_id;
    }

    public void setOrdennance_id(int ordennance_id) {
        this.ordennance_id = ordennance_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return date_rv + "";
    }
}
