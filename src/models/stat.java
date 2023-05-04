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
public class stat {
     
private int stat    ;
private String stat2;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getStat2() {
        return stat2;
    }

    public void setStat2(String stat2) {
        this.stat2 = stat2;
    }

    public stat(int stat, String stat2) {
        this.stat = stat;
        this.stat2 = stat2;
    }

}
