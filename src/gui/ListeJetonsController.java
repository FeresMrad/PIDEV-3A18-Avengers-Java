/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entities.Jetons;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.JetonsCRUD;

/**
 * FXML Controller class
 *
 * @author feres
 */
public class ListeJetonsController implements Initializable {
    
    @FXML
    private TableView<Jetons> tablelj;
    @FXML
    private TableColumn<Jetons, Integer> user_idlj;
    @FXML
    private TableColumn<Jetons, String> usernamelj;
    @FXML
    private TableColumn<Jetons, Integer> countlj;
    @FXML
    private TextField addtextlj;
    @FXML
    private TextField subtextlj;
    @FXML
    private Button subbtnlj;
    @FXML 
    private Button addbtnlj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configure columns
        user_idlj.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        usernamelj.setCellValueFactory(new PropertyValueFactory<>("username"));
        countlj.setCellValueFactory(new PropertyValueFactory<>("count"));
        
                // Load data from database
        JetonsCRUD pcd = new JetonsCRUD();
        List<Jetons> Jetons = pcd.entitiesList();

        // Add transactions to table
       // tablelj.getItems().clear();
        tablelj.getItems().addAll(Jetons);
    }   
    
//    @FXML
//    private void handleAddJetonButtonAction(ActionEvent event) {
//        try {
//            // Get the current selected jeton object
//            Jetons selectedJeton = tablelj.getSelectionModel().getSelectedItem();
//
//            // Get the new count value
//            int newCount = selectedJeton.getCount() + Integer.parseInt(addtextlj.getText());
//
//            // Update the count value in the database
//            JetonsCRUD pcd = new JetonsCRUD();
//            pcd.addCount(selectedJeton.getUser_id(), newCount);
//
//            // Update the selected jeton object
//            selectedJeton.setCount(newCount);
//
//            // Update the count column in the table
//            countlj.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCount()).asObject());
//
//            // Refresh the table
//            tablelj.refresh();
//        } catch (NumberFormatException e) {
//            // Handle invalid input in jetonCountField
//            System.out.println("Invalid input: " + e.getMessage());
//        }
//    }
//    @FXML
//    private void handleAddJetonButtonAction(ActionEvent event) {
//        Jetons selectedJetons = tablelj.getSelectionModel().getSelectedItem();
//        if (selectedJetons != null) {
//            try {
//                int amount = Integer.parseInt(addtextlj.getText());
//                JetonsCRUD jetonsCRUD = new JetonsCRUD();
//                jetonsCRUD.addCount(selectedJetons.getUser_id(), amount);
//                refreshTable();
//            } catch (NumberFormatException ex) {
//                System.out.println("Invalid amount: " + addtextlj.getText());
//            }
//        } else {
//            System.out.println("No jetons selected.");
//        }
//    }
    
    @FXML
    private void handleAddJetonButtonAction(ActionEvent event) {
        Jetons selectedJetons = tablelj.getSelectionModel().getSelectedItem();
        if (selectedJetons != null) {
            try {
                int amount = Integer.parseInt(addtextlj.getText());
                if (amount <= 0) {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Erreur");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Le montant doit être positif.");
                    errorAlert.showAndWait();
                } else {
                    Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirmation");
                    confirmAlert.setHeaderText("Ajouter " + amount + " jetons?");
                    confirmAlert.setContentText("Cliquez sur Oui pour ajouter les jetons, ou sur Non pour annuler.");

                    Optional<ButtonType> result = confirmAlert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        JetonsCRUD jetonsCRUD = new JetonsCRUD();
                        jetonsCRUD.addCount(selectedJetons.getUser_id(), amount);
                        refreshTable();
                    }
                }
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Ajoutez un montant: " + addtextlj.getText());
                errorAlert.showAndWait();
            }
        } else {
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Aucun jeton sélectionné.");
            infoAlert.showAndWait();
        }
    }
    
//    @FXML
//    private void handleSubstractJetonButtonAction(ActionEvent event) {
//        Jetons selectedJetons = tablelj.getSelectionModel().getSelectedItem();
//        if (selectedJetons != null) {
//            try {
//                int amount = Integer.parseInt(subtextlj.getText());
//                JetonsCRUD jetonsCRUD = new JetonsCRUD();
//                jetonsCRUD.substractCount(selectedJetons.getUser_id(), amount);
//                refreshTable();
//            } catch (NumberFormatException ex) {
//                System.out.println("Invalid amount: " + subtextlj.getText());
//            }
//        } else {
//            System.out.println("No jetons selected.");
//        }
//    }
    
    @FXML
    private void handleSubstractJetonButtonAction(ActionEvent event) {
        Jetons selectedJetons = tablelj.getSelectionModel().getSelectedItem();
        if (selectedJetons != null) {
            try {
                int amount = Integer.parseInt(subtextlj.getText());
                if (amount <= 0) {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Erreur");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Le montant doit être positif.");
                    errorAlert.showAndWait();
                } else if (amount > selectedJetons.getCount()) {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Erreur");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Le montant ne peut pas être supérieur à " + selectedJetons.getCount());
                    errorAlert.showAndWait();
                } else {
                    Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirmation");
                    confirmAlert.setHeaderText("Soustracter " + amount + " jetons?");
                    confirmAlert.setContentText("Cliquez sur Oui pour soustracter les jetons, ou sur Non pour annuler.");

                    Optional<ButtonType> result = confirmAlert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        JetonsCRUD jetonsCRUD = new JetonsCRUD();
                        jetonsCRUD.substractCount(selectedJetons.getUser_id(), amount);
                        refreshTable();
                    }
                }
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Ajoutez un montant: " + subtextlj.getText());
                errorAlert.showAndWait();
            }
        } else {
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Aucun jeton sélectionné.");
            infoAlert.showAndWait();
        }
    }
    
    private void refreshTable() {
    tablelj.getItems().clear();
    JetonsCRUD pcd = new JetonsCRUD();
    List<Jetons> Jetons = pcd.entitiesList();
    tablelj.getItems().addAll(Jetons);
}
    
}
