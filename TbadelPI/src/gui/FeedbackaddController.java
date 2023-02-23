/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Feedback;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.FeedbackCRUD;

/**
 * FXML Controller class
 *
 * @author Feriel
 */
public class FeedbackaddController implements Initializable {

    @FXML
    private TextField ratingTextField;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    //Ajouter feedback
   @FXML
private void handleAddButtonAction(ActionEvent event) {
    //controle de saisie
    if (ratingTextField.getText().isEmpty() || commentTextArea.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.showAndWait();
        return;
    }

    int rating;
    try {
        rating = Integer.parseInt(ratingTextField.getText());
        if (rating < 1 || rating > 10) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La note doit être un entier entre 1 et 10!");
        alert.showAndWait();
        return;
    }

    String comment = commentTextArea.getText();

    FeedbackCRUD fcd = new FeedbackCRUD();
    Feedback f = new Feedback(1, rating, comment, new java.sql.Date(new Date().getTime()));

    //Alert de confirmation
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous ajouter ce feedback?");
    alert.showAndWait();
    if (alert.getResult() == ButtonType.OK) {
        fcd.add(f);
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Succès");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Feedback ajouté avec succès.");
        successAlert.showAndWait();
    }

    //redirection
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
    try {
        Parent root = loader.load();
        btn.getScene().setRoot(root);
    } catch (IOException ex) {
        Logger.getLogger(FeedbackaddController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    /*  public static String getRatingStars(int rating) {
        String stars = "";
        for (int i = 0; i < rating; i++) {
            stars += "★";
        }
        return stars;
    }*/
   
}
