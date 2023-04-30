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
public interface IServiceCategory<Category> {
    
        public void ajouter(Category c) throws SQLException ;
    public boolean delete(Category c )throws SQLException;
    public boolean update(Category c) throws SQLException;
     public boolean search(Category c) throws SQLException;
    public List<Category> read_all() throws SQLException;
    
}

