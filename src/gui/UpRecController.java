/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Reclamation;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.Rcrud;

/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class UpRecController implements Initializable {
  @FXML
    private TextField iduser;
   
    @FXML
    private TextArea iddesc;

    @FXML
    private Button btnsave;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getIduser() {
        return iduser;
    }

    public TextArea getIddesc() {
        return iddesc;
    }

 

    public Button getBtnsave() {
        return btnsave;
    }

    public void setIduser(TextField iduser) {
        this.iduser = iduser;
    }

    public void setIddesc(TextArea iddesc) {
        this.iddesc = iddesc;
    }

  

    public void setBtnsave(Button btnsave) {
        this.btnsave = btnsave;
    }


    
    private void updReclam(ActionEvent event) {
  
    
    }

    @FXML
    private void uprec(ActionEvent event) {
          int  resNom = Integer.parseInt(iduser.getText());
          String resDesc = iddesc.getText();
    
             Rcrud pcd = new Rcrud();
         Reclamation r = new Reclamation(resNom, resDesc);
        System.out.println( pcd.FindReclamationById(resNom));
      
        
        System.out.println("Done!!");
       // pcd.FindReclamationById();
        
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
        
        try {
            Parent root = loader.load();
           ReclamationController dc = loader.getController();
            dc.setTfnom(iduser);
           dc.setTfdescrip(iddesc);
            iduser.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
}
    
    
    
    
    
    
    
    
    
    

