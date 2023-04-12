/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.prefs.Preferences;

/**
 *
 * @author yacin
 */
public class SessionManager {
    //public static Preferences pref;
    private static int id;
    private static String Name;
    private static String email;
    private static String passowrd;
    private static String role;

    public static int getId() {
        return SessionManager.id;
    }

    public static String getName() {
        return SessionManager.Name;
    }

    public static String getEmail() {
        return SessionManager.email;
    }

   
    public static String getRole() {
        return SessionManager.role;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static void setName(String Name) {
        SessionManager.Name = Name;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static void setPassowrd(String passowrd) {
        SessionManager.passowrd = passowrd;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }
    
    

}
