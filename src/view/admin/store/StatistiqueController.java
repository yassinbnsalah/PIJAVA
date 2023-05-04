/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.store;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import models.stat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import models.stat ; 
import services.ServiceProduit;
import services.ServiceCategory;
import util.MyConnection;



public class StatistiqueController implements Initializable {

 @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private PieChart pie;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
     public StatistiqueController(){
        Connection cnx = MyConnection.getInstance().getCnx();
    }
       private ObservableList data;
              private ObservableList data2;

       
       ServiceProduit sf = new ServiceProduit();
       ServiceCategory df = new ServiceCategory();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
              label1.setText("Nombre de produit Total :   "+String.valueOf(sf.RecupTotal()));
//            label2.setText("Nombre de Division Total :   "+String.valueOf(df.RecupTotal()));
//            
            buildData();
            buildData2();
            pie.getData().addAll(data);
            this.generateBarChart();
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     public void generateBarChart() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<stat> produits = data2;

        for (stat produit : produits) {
            dataset.setValue(produit.getStat(), "Nombre d'achats", produit.getStat2());
        }

        JFreeChart chart = ChartFactory.createBarChart("Les 10 livres les plus achetÃ©s", "sellprice", "Nombre d'achats", dataset);

        javafx.scene.chart.Axis<String> xAxis = barChart.getXAxis();
        javafx.scene.chart.Axis<Number> yAxis = barChart.getYAxis();
        xAxis.setLabel("prix de vente");
        xAxis.setTickLabelsVisible(false);
        barChart.getXAxis().setTickLabelRotation(90);
        yAxis.setLabel("Nombre de produit par prix de vente ");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Object columnKey : dataset.getColumnKeys()) {
            String label = (String) columnKey;
            XYChart.Data<String, Number> data = new XYChart.Data<>(label, dataset.getValue("Nombre d'achats", (Comparable<?>) columnKey));
            // Set the label for the data
           data.setNode(new HoveredThresholdNode((int) (double) dataset.getValue("Nombre d'achats", (Comparable<?>) columnKey), label));
            series.getData().add(data);
        }

        barChart.getData().add(series);

        barChart.setAnimated(true);
        barChart.setBarGap(0);
        barChart.setCategoryGap(10);
        barChart.setAnimated(false);
        barChart.setLegendVisible(false);
        barChart.setLegendSide(Side.RIGHT.RIGHT);

    }
     
     public void buildData(){
          java.sql.Connection cnx;
     cnx = MyConnection.getInstance().getCnx();
          data = FXCollections.observableArrayList();
          try{
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL =" SELECT COUNT(*), type FROM produit u,category r where u.id = r.id GROUP BY u.id";

            ResultSet rs = cnx.createStatement().executeQuery(SQL);
            while(rs.next()){
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2),rs.getInt(1)));
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }

      }
     
           public void buildData2(){
               
            
             
          data2 = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MyConnection.getInstance().getCnx();
          String sql = "SELECT COUNT(*), sellprice FROM produit GROUP BY sellprice ";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
                     data2.add(new stat(R.getInt(1),R.getString(2)));

    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    }
     
     
     class HoveredThresholdNode extends StackPane {

    HoveredThresholdNode(int value, String label) {
        setPrefSize(15, 15);
        final Label valueLabel = new Label(String.valueOf(value));
        valueLabel.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        valueLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        valueLabel.setVisible(false);
        final Label dataLabel = new Label(label);
        dataLabel.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        dataLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        dataLabel.setVisible(false);
        setOnMouseEntered(mouseEvent -> {
            valueLabel.setVisible(true);
            dataLabel.setVisible(true);
        });
        setOnMouseExited(mouseEvent -> {
            valueLabel.setVisible(false);
            dataLabel.setVisible(false);
        });
        getChildren().setAll(valueLabel, dataLabel);
    }
     }
}
