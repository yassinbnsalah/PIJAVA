/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.User;

/**
 *
 * @author 21693
 */
public interface UserInterface {
    public void AddUser(User user);
    public ArrayList<User> userListe();
    public void BannedUtilisateur(int id);
    public void modifierUtilisateur(int id,User user);
    public boolean verifPassword(String nom, String password);
}
