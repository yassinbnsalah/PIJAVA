/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit;

import Database.Database;
import Entity.Category;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.ServiceCategory;
import services.ServiceProduit;


public class CategoryController implements Initializable {

    @FXML
    private TableColumn<Category, Integer> id;
    @FXML
    private TableColumn<Category, String> name;
    @FXML
    private TableColumn<Category, String> slug;
    @FXML
    private TableView<Category> tab;
    @FXML
    private TextField nom;
    @FXML
    private TextField sluug;
    @FXML
    private Button add;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private TextField idx;

    /**
     * Initializes the controller class.
     */
    
     public CategoryController(){
        Connection cnx = Database.getInstance().getCnx();
    }
      private Connection cnx;
      
             ServiceCategory sr = new ServiceCategory();

     public void table(){
         
        id.setCellValueFactory( new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory <>("name"));
         slug.setCellValueFactory(new PropertyValueFactory <>("slug"));



        tab.setItems(sr.RecupBase()); 
         System.out.println(sr.RecupBase());

     }
             
             
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        
            tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
        
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
            Category d = new Category();
             d.setName(nom.getText());
             d.setSlug(sluug.getText());
            
            
             
               try {
                   sr.ajouter(d);
                   
//                   pagination.setPageFactory(this::createPage);

               } catch (SQLException ex) {
                   Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
               }

           table();
           
           nom.clear();
           sluug.clear();

       
           }
        
         
           public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Category f = tab.getSelectionModel().getSelectedItem();
      String id=String.valueOf(f.getId());
      idx.setText(id);
          nom.setText(f.getName());
          sluug.setText(f.getSlug());
  
     
    }
}
   

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
         
          String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Category r = new Category();
        
        r.setId(i);
         
              System.out.println(r);
              sr.delete(r);
        
                         table();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le client a été supprimer avec succés");
        
    }

    @FXML
    private void modiff(ActionEvent event) throws SQLException {
        
        
           int i = Integer.valueOf(idx.getText());
    String name = nom.getText();
    String s = sluug.getText();
        
        Category d = new Category();
        d.setId(i);
        d.setName(name);
        d.setSlug(s);
        
        
sr.Update2(d);
table();
        
        
    }
    
}
