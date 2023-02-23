/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dechets;

import entities.Evenements;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class Update_EvenementsController implements Initializable {

   
    @FXML
    private TextField resultatnom;
    @FXML
    private TextField resultatlieu;
    @FXML
    private TextArea resultatdesc;
    @FXML
    private DatePicker resultatdatedeb;
    @FXML
    private DatePicker resultatdatefin;
    @FXML
    private Button btnupdevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getResultatnom() {
        return resultatnom;
    }

    public void setResultatnom(String message) {
        this.resultatnom.setText(message);
    }

    public TextField getResultatlieu() {
        return resultatlieu;
    }

    public void setResultatlieu(String message) {
        this.resultatlieu.setText(message);
    }

    public TextArea getResultatdesc() {
        return resultatdesc;
    }

    public void setResultatdesc(String message) {
        this.resultatdesc.setText(message);
    }

    public DatePicker getResultatdatedeb() {
        return resultatdatedeb;
    }

    public void setResultatdatedeb(DatePicker resultatdatedeb) {
        this.resultatdatedeb = resultatdatedeb;
    }

    public DatePicker getResultatdatefin() {
        return resultatdatefin;
    }

    public void setResultatdatefin(DatePicker resultatdatefin) {
        this.resultatdatefin = resultatdatefin;
    }



     
    @FXML
    private void updEvent(ActionEvent event) {
     String resNom = resultatnom.getText();
     String resDesc = resultatdesc.getText();
     Date resDate_deb = java.sql.Date.valueOf(resultatdatedeb.getValue());
     Date resDate_fin = java.sql.Date.valueOf(resultatdatefin.getValue());
     String resLieu = resultatlieu.getText();
     
     
        EvenementsCRUD pcd = new EvenementsCRUD();
        Evenements t = new Evenements(resNom,resDesc,resDate_deb,resDate_fin,resLieu);
      //  pcd.updEvenements(t,x);
        
        System.out.println("Done!!");
        
        
     /// redirection
     
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenements.fxml"));
//        
//        try {
//            Parent root = loader.load();
//            EvenementsController dc = loader.getController();
//            dc.setResultatNom(resNom);
//            dc.setResultatDes(resDesc);
//            tfnom.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());  
//        }
    }
    
}
    

