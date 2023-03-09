/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.Ncrud;

/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class UpdateNotifController implements Initializable {

    @FXML
    private Button btnadd;
    @FXML
    private TextField tfnom;
    @FXML
    private TextArea tftext;
    @FXML
    private TextField  tftype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Button getBtnadd() {
        return btnadd;
    }

    public TextField getTfnom() {
        return tfnom;
    }

    public TextArea getTftext() {
        return tftext;
    }

    public TextField getTftype() {
        return tftype;
    }

    public void setBtnadd(Button btnadd) {
        this.btnadd = btnadd;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    public void setTftext(TextArea tftext) {
        this.tftext = tftext;
    }

    public void setTftype(TextField tftype) {
        this.tftype = tftype;
    }
           @FXML
           
    private void ajouterNotification(ActionEvent event) {
if (tfnom.getText().isEmpty() || tftext.getText().isEmpty() || tftext.getText().isEmpty()) {
    Notifications notificationBuilder = Notifications.create()
            .title("ERREUR")
            .text("Veuillez remplir les champs")
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT);

    notificationBuilder.show();
} else {
 
    Ncrud rec = new Ncrud();
    
    //SAUVEGARDE BASE DE DONNEES
    //int nom =  Integer.parseInt(tfnom.getText());
     int userId =  Integer.parseInt(tfnom.getText());
          String desc = tftext.getText();
          String type = tftype.getText();
         
        //System.out.println(nom);
       Notification n = new Notification(userId, desc, type);
       Ncrud pc = new Ncrud();   
     pc.ajouternotification(n); 
     System.out.println("Notification ajoutée");
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      //  sendMail();
                alert.setTitle("Success");
                
                

        
        
}}}
    
    
    
    







