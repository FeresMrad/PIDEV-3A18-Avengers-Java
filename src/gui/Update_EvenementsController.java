/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class Update_EvenementsController implements Initializable {

    @FXML
    private AnchorPane a1;
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
    @FXML
    private AnchorPane a2;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AnchorPane anchorPane = (AnchorPane) a1.lookup("#AnchorPane"); // Replace 'scene' with your scene object
anchorPane.setStyle("-fx-background-color: linear-gradient(from bottom right, #14B1A1, #CCF5F1);");
     AnchorPane anchorPane2 = (AnchorPane) a2.lookup("#AnchorPane"); // Replace 'scene' with your scene object
anchorPane.setStyle("-fx-background-color: linear-gradient(from bottom right, #14B1A1, #CCF5F1);");
    }    

    @FXML
    private void updEvent(ActionEvent event) {
    }

}
