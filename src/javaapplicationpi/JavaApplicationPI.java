/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationpi;

import services.DisponibilityService;
import services.SubServices;
import util.MyConnection;

/**
 *
 * @author yacin
 */
public class JavaApplicationPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MyConnection cnx = MyConnection.getInstance() ; 
        SubServices subserv = new SubServices(); 
        //System.out.println(subserv.subListe());
        //subserv.UpdateStateSub("Cancel", 101);
        DisponibilityService dispoService = new DisponibilityService() ;
        System.out.println(dispoService.disponibilityListe(1));
        dispoService.deleteDisponibility(49);
    }
    
}
