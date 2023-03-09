/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Transaction;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.TransactionCRUD;

/**
 * FXML Controller class
 *
 * @author feres
 */
public class ListeTransactionController implements Initializable {

    @FXML
    private TableView<Transaction> tablelt;
    @FXML
    private TableColumn<Transaction, Integer> idlt;
    @FXML
    private TableColumn<Transaction, String> from_userlt;
    @FXML
    private TableColumn<Transaction, String> to_userlt;
    @FXML
    private TableColumn<Transaction, String> from_user_itemlt;
    @FXML
    private TableColumn<Transaction, String> to_user_itemlt;
    @FXML
    private TableColumn<Transaction, Integer> jetons_proplt;
    @FXML
    private TableColumn<Transaction, Integer> jetons_demlt;
    @FXML
    private TableColumn<Transaction, String> etatlt;
    @FXML
    private Button feedbacklt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idlt.setCellValueFactory(new PropertyValueFactory<>("id"));
        from_userlt.setCellValueFactory(new PropertyValueFactory<>("from_user_id"));
        to_userlt.setCellValueFactory(new PropertyValueFactory<>("to_user_id"));
        from_user_itemlt.setCellValueFactory(new PropertyValueFactory<>("from_user_item"));
        to_user_itemlt.setCellValueFactory(new PropertyValueFactory<>("to_user_item"));
        jetons_proplt.setCellValueFactory(new PropertyValueFactory<>("jetons_prop"));
        jetons_demlt.setCellValueFactory(new PropertyValueFactory<>("jetons_dem"));
        etatlt.setCellFactory(new PropertyValueFactory<>("etat"));
        
        // Load data from database
        TransactionCRUD pcd = new TransactionCRUD();
        List<Transaction> transaction = pcd.entitiesList();

        // Add transactions to table
        tablelt.getItems().addAll(transaction);
        
    // Set the value of the from_userlt column to the username of the from_user_id
        from_userlt.setCellValueFactory(cellData -> {
            int userId = cellData.getValue().getFrom_user_id();
            TransactionCRUD transactionCRUD = new TransactionCRUD();
            String username = transactionCRUD.getFromUsernameById(userId);
            return new SimpleStringProperty(username);
    });
    
    // Set the value of the to_userlt column to the username of the to_user_id
        to_userlt.setCellValueFactory(cellData -> {
            int userId = cellData.getValue().getTo_user_id();
            TransactionCRUD transactionCRUD = new TransactionCRUD();
            String username = transactionCRUD.getToUsernameById(userId);
            return new SimpleStringProperty(username);
    });      
        
    }    

    @FXML
    private void giveFeedback(ActionEvent event) {
    }
    
}
