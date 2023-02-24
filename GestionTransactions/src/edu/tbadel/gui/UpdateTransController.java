/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tbadel.gui;

import edu.tbadel.entities.Transaction;
import edu.tbadel.services.TransactionCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author feres
 */
public class UpdateTransController implements Initializable {

    private Transaction transaction;
    @FXML
    private TextField from_item_nomtf;
    @FXML
    private TextField from_imagetf;
    @FXML
    private TextField commentaire_transtf;
    @FXML
    private TextField offre_jetonstf;
    @FXML
    private Button confirmbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    private void updateTransaction(ActionEvent event) {
    String fromitemnomtf = from_item_nomtf.getText();
    String fromimagetf = from_imagetf.getText();
    String commentairetranstf = commentaire_transtf.getText();
    String offrejetonstf = offre_jetonstf.getText();

//
}
//    public void loadTransaction(Transaction transaction) {
//    from_item_nomtf.setText(transaction.getFrom_item_nom());
//    from_imagetf.setText(transaction.getFrom_image());
//    offre_jetonstf.setText(String.valueOf(transaction.getOffre_jetons()));
//    commentaire_transtf.setText(transaction.getCommentaire_trans())
//};


//    void setTransaction(Transaction transaction) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    // Declare a public method to set the feedback
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        from_item_nomtf.setText(transaction.getFrom_item_nom());
        commentaire_transtf.setText(transaction.getCommentaire_trans());
        from_imagetf.setText(transaction.getFrom_image());
        offre_jetonstf.setText(Integer.toString(transaction.getOffre_jetons()));
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public TextField getFrom_itemnomtf() {
        return from_item_nomtf;
    }

    public TextField getFrom_imagetf() {
        return from_imagetf;
    }

    public TextField getCommentaire_transtf() {
        return commentaire_transtf;
    }

    public TextField getOffre_jetonstf() {
        return offre_jetonstf;
    }

    public Button getConfirmbtn() {
        return confirmbtn;
    }

    public void setFrom_item_nomtf(TextField from_item_nomtf) {
        this.from_item_nomtf = from_item_nomtf;
    }

    public void setFrom_imagetf(TextField from_imagetf) {
        this.from_imagetf = from_imagetf;
    }

    public void setCommentaire_transtf(TextField commentaire_transtf) {
        this.commentaire_transtf = commentaire_transtf;
    }

    public void setOffre_jetonstf(TextField offre_jetonstf) {
        this.offre_jetonstf = offre_jetonstf;
    }

    public void setConfirmbtn(Button confirmbtn) {
        this.confirmbtn = confirmbtn;
    }
    
    }
    
