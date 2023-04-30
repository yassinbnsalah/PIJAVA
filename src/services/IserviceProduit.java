/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MediaCenter Zaghouan
 */
public interface IserviceProduit<Produit> {
    public void ajouter(Produit p) throws SQLException ;
    public boolean delete(Produit p )throws SQLException;
    public boolean update(Produit p) throws SQLException;
     public boolean search(Produit p) throws SQLException;
    public List<Produit> read_all() throws SQLException;
}
