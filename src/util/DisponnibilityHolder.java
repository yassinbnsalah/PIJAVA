/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controllers.DisponibilityListeController;

/**
 *
 * @author yacin
 */
public  final class DisponnibilityHolder {
    private final static DisponnibilityHolder INSTANCE = new DisponnibilityHolder();

    private DisponnibilityHolder() {
    }

    public static DisponnibilityHolder getInstance() {
       // DisponibilityListeController ds = new DisponibilityListeController(); 
      //  ds.initialize(url, rb);
        return INSTANCE;
        
        
    }
}
