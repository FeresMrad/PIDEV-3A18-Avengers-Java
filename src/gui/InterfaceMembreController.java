/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class InterfaceMembreController implements Initializable {

    @FXML
    private AnchorPane rootPane3;
    @FXML
    private Pane paneId;
    @FXML
    private Button profilbtn;
    @FXML
    private Button matchbtn;
    @FXML
    private Button tournoisbtn;
    @FXML
    private Button mescommandesbtn;
    @FXML
    private Button magasinbtn;
    @FXML
    private Button reclamationbtn;
    @FXML
    private Button logoffbtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionProfilbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/ProfilMembre.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    
    

    @FXML
    private void ActionLogOffbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/SignInMembre.fxml"));
        rootPane3.getChildren().setAll(pane);
    }
    
    
    
}
