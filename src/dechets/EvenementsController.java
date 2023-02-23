/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dechets;

import dechets.Update_EvenementsController;
import entities.Evenements;
import services.EvenementsCRUD;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.EvenementsCRUD;

/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class EvenementsController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflieu;
    @FXML
    private TextArea tfdesc;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button btnevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void saveEvent(ActionEvent event) {
     String resNom = tfnom.getText();
     String resDesc = tfdesc.getText();
     Date resDate_deb = java.sql.Date.valueOf(datedeb.getValue());
     Date resDate_fin = java.sql.Date.valueOf(datefin.getValue());
     String resLieu = tflieu.getText();
     
     
        EvenementsCRUD pcd = new EvenementsCRUD();
        Evenements t = new Evenements(resNom,resDesc,resDate_deb,resDate_fin,resLieu);
        pcd.addEntity(t);
        System.out.println("Done!!");
        
        
     /// redirection
     
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_Evenements.fxml"));
        
        try {
            Parent root = loader.load();
            Update_EvenementsController dc = loader.getController();
            dc.setResultatnom(resNom);
            dc.setResultatdesc(resDesc);
            dc.setResultatdatedeb(datedeb);
            dc.setResultatdatefin(datefin);
            dc.setResultatlieu(resLieu);
            tfnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
}
