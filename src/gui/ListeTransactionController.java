/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import chat1.ClientController;
import chat1.SampleController;
import entities.Transaction;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
    @FXML
    private TableColumn<Transaction, String> commentairelt;
    @FXML
    private Button chatlt;
    @FXML
    private Button validerlt;
    @FXML
    private Button annulerlt;

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
        etatlt.setCellValueFactory(new PropertyValueFactory<>("etat"));
        commentairelt.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

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

        Transaction transaction = tablelt.getSelectionModel().getSelectedItem();
        Context.getInstance().addContextObject("Transaction", transaction);
        if (transaction != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Feedbackadd.fxml"));
            try {
                Parent root = loader.load();
                FeedbackaddController dc = loader.getController();

                // Pass the transactionId to FeedbackaddController constructor
                // dc.setId(transaction.getId());
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Transaction Selected");
            alert.setHeaderText("Please select a transaction in the table.");
            alert.showAndWait();
        }
    }
@FXML

public void openChat(ActionEvent event) throws IOException {

         Parent page2 = FXMLLoader.load(getClass().getResource("../chat1/client.fxml"));
         Scene scene2 = new Scene(page2);

         Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         app_stage.setScene(scene2);
         app_stage.show();

   }





    @FXML
    private void validerTransaction(ActionEvent event) {
        // Get the selected transaction
        Transaction selectedTransaction = tablelt.getSelectionModel().getSelectedItem();

        // Check if a transaction is selected
        if (selectedTransaction == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez sélectionner une transaction à valider.");
            alert.showAndWait();
            return;
        }

        // Check if the transaction is already completed
        if (selectedTransaction.getEtat().equals("Effectuée")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("La transaction sélectionnée est déjà effectuée.");
            alert.showAndWait();
            return;
        }
                TransactionCRUD ppcd = new TransactionCRUD();
        int countJetonsFrom = ppcd.getCountJetonsFrom(1); //nbre de jetons de from_user
        int selectedID = tablelt.getSelectionModel().getSelectedItem().getId(); //id de la transaction selectionnée
        int toId= ppcd.getToUserIdByTransId(selectedID); //id de to_user
        String ToUser = ppcd.getToUsernameById(toId); // get_user_nom
        int countJetonsTo = ppcd.getCountJetonsTo(toId); // nbre de jetons de to_user
        
        int selectedFromJetons = tablelt.getSelectionModel().getSelectedItem().getJetons_prop(); //jetons_prop
              int newCountJetons = countJetonsFrom - selectedFromJetons; //nouveau count de from_user
              int newCountJetons2 = countJetonsTo + selectedFromJetons; // nouveau count de to_user
              ppcd.updCountJetonsProp(1, newCountJetons); // mise a jour des jetons de from_user
              ppcd.updCountJetonsProp(toId, newCountJetons2); //mise a jour des jetons de to_user
              System.out.println("Jetons mis à jour");
        
       int selectedToJetons = tablelt.getSelectionModel().getSelectedItem().getJetons_dem(); // jetons_dem
             int newCountJetons3 = countJetonsTo - selectedToJetons; //nouveau count de to_user
             int newCountJetons4 = countJetonsFrom + selectedToJetons;
            ppcd.updCountJetonsProp(toId, newCountJetons3); //mise a jour des jetons de to_user
            ppcd.updCountJetonsProp(1, newCountJetons4);
             System.out.println("Jetons mis à jour 2");

        // Ask for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Valider la transaction");
        alert.setContentText("Êtes-vous sûr de vouloir valider cette transaction ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Update the transaction
            selectedTransaction.setEtat("Effectuée");
            TransactionCRUD transactionCRUD = new TransactionCRUD();
            transactionCRUD.updEntity(selectedTransaction);

            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setContentText("La transaction a été validée avec succès.");
            successAlert.showAndWait();

            // Refresh the table
            tablelt.getItems().clear();
            List<Transaction> transaction = transactionCRUD.entitiesList();
            tablelt.getItems().addAll(transaction);
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Transaction t = tablelt.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void annulerTransaction(ActionEvent event) {
        Transaction selectedTransaction = tablelt.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null && selectedTransaction.getEtat().equals("En cours")) {
            // Ask for confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Annuler la transaction");
            alert.setContentText("Voulez-vous vraiment annuler cette transaction ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User confirmed, delete the transaction
                TransactionCRUD transactionCRUD = new TransactionCRUD();
                transactionCRUD.delEntity(selectedTransaction.getId());

                // Remove the transaction from the table
                tablelt.getItems().remove(selectedTransaction);

                // Show a success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Transaction annulée avec succès !");
                successAlert.showAndWait();
            }
        } else {
            // Show an error message
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez sélectionner une transaction en cours !");
            errorAlert.showAndWait();
        }
    }

}
