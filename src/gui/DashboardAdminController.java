/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class DashboardAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button logobnt1;
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
    private void Utilisateur(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/Gestion_user.fxml"));
        rootPane.getChildren().setAll(pane);  
    }

    @FXML
    private void Actionlog(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/SignInAdmin.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void goevent(ActionEvent event) throws IOException {
    
    Parent page2 = FXMLLoader.load(getClass().getResource("ListeEvenements.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
    
}
