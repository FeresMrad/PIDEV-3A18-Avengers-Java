/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Reclamation;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class StatController implements Initializable {

  @FXML
    private Button btnload;
    @FXML
    private PieChart idpie;
    ObservableList <PieChart.Data> ol = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
@FXML


private void loadChart(ActionEvent event) {
    try {
       String requete=" SELECT reclamations.subject, COUNT(DISTINCT reclamations.user_id) AS nb_reclamations FROM reclamations GROUP BY reclamations.subject";

        
   // String requete = "SELECT reclamations.subject, COUNT(*) AS nb_reclamations FROM reclamations GROUP BY reclamations.user_id";

    // execute query and store the result in rs
    PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
    ResultSet rs = pst.executeQuery();

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalReclamations = 0;

    while (rs.next()) {
        String subject = rs.getString("subject");
        int nbReclamations = rs.getInt("nb_reclamations");
        totalReclamations += nbReclamations;

        PieChart.Data data = new PieChart.Data(subject + " (" + nbReclamations + ")", nbReclamations);
        pieChartData.add(data);
    }

    // add total to the piechart
    PieChart.Data totalData = new PieChart.Data("Total (" + totalReclamations + ")", totalReclamations);
    pieChartData.add(totalData);

    idpie.setData(pieChartData);
    idpie.setLegendSide(Side.LEFT);

    FadeTransition f = new FadeTransition(Duration.seconds(4), idpie);
    f.setFromValue(0);
    f.setToValue(1);
    f.play();

} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}

 //try {
          /*Reclamation r  = new Reclamation();
            String requete="SELECT reclamations.subject, COUNT(*) AS nb_reclamations FROM reclamations GROUP BY reclamations.user_id";
       
            //execute querry and store it in resulttest
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
             ResultSet rs =  pst.executeQuery();
             ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
          
             int totalreclamations = 0;  
             while(rs.next()){
                 String subject = rs.getString(2);
                 int nb_reclamations = rs.getInt("nb_reclamations");
                 totalreclamations +=nb_reclamations;
                 pieChartData.add(new PieChart.Data(totalreclamations+"("+ ")"+ totalreclamations, totalreclamations));
                 ol.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                    idpie.setData(ol);
                    
                    idpie.setLegendSide(Side.LEFT);
                    
                    FadeTransition f = new FadeTransition(Duration.seconds(4),idpie);
                    f.setFromValue(0);
                    f.setToValue(1);
                    f.play();
             }
     } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
 for (PieChart.Data data : idpie.getData())
                     {
                         data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
                             @Override
                             public void handle(MouseEvent event) {
                               JOptionPane.showMessageDialog(null,"subject   -- "+ data.getName()+ "Total --" +(int)data.getPieValue());   
                             }
                         });
                     }*/
 
 
 
 
 
 
 }
             
 
 
 
 
 
 
 
 
   
}
 
         
       
   



            
        

    
    










