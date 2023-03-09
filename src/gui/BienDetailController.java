/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Bien;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Nouhe
 */
public class BienDetailController implements Initializable {

    @FXML
    private Label lblNom;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblCategorie;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
     private Bien bien;
    
    public void setBien(Bien bien) {
        this.bien = bien;
        afficherBien();
    }
    
    private void afficherBien() {
        lblNom.setText(bien.getNom());
        lblDescription.setText(bien.getDescription());
        imageView.setImage(bien.getImage().getImage());
        lblCategorie.setText(bien.getCategorie());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
