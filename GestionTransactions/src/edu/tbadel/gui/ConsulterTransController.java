package edu.tbadel.gui;

import edu.tbadel.entities.Transaction;
import edu.tbadel.services.TransactionCRUD;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsulterTransController implements Initializable {

    @FXML
    private TableView<Transaction> table;
    @FXML
    private TableColumn<Transaction, String> from_item_nomtb;
    @FXML
    private TableColumn<Transaction, String> from_imagetb;
    @FXML
    private TableColumn<Transaction, Integer> offre_jetonstb;
    @FXML
    private TableColumn<Transaction, String> commentaire_transtb;
    @FXML
    private Button canceltb;
    @FXML
    private Button updatetb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configure columns
        from_item_nomtb.setCellValueFactory(new PropertyValueFactory<>("from_item_nom"));
        from_imagetb.setCellValueFactory(new PropertyValueFactory<>("from_image"));
        offre_jetonstb.setCellValueFactory(new PropertyValueFactory<>("offre_jetons"));
        commentaire_transtb.setCellValueFactory(new PropertyValueFactory<>("commentaire_trans"));

        // Load data from database
        TransactionCRUD pcd = new TransactionCRUD();
        List<Transaction> transaction = pcd.entitiesList();

        // Add transactions to table
        table.getItems().addAll(transaction);
        
        
    }
    
//    @FXML
//        private void deleteEntity(ActionEvent event) {
//    //          int x = EventSelectionner();
//    //
//    //          EvenementsCRUD pcd=new EvenementsCRUD();
//    //          pcd.delEvenements(x);
//    //
//    //  tableevent.getItems().clear();
//    //            showEvent();
//
//
//        if(table.getSelectionModel().getSelectedItems().isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Aucune ligne sélectionnée");
//            alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
//            alert.showAndWait();
//            return;
//        }
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Supprimer l'événement");
//        alert.setHeaderText(null);
//        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet événement ?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//            int selectedItem = table.getSelectionModel().getSelectedItem().getId();
//            TransactionCRUD pcd = new TransactionCRUD();
//            pcd.deleteEntity(selectedItem);
//            System.out.println("Done!!");
//            //tableevent.getItems().clear();
//            //showEvent();
//        }
//        }
    @FXML
    private void deleteTransaction() {
        Transaction selectedTransaction = table.getSelectionModel().getSelectedItem();
        if (selectedTransaction == null) {
            // Show an alert if no transaction is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune transaction sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une transaction à supprimer.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette transaction ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            TransactionCRUD pcd = new TransactionCRUD();
            pcd.deleteEntity(selectedTransaction.getId());
            table.getItems().remove(selectedTransaction);
            System.out.println("Transaction supprimée avec succès !");
        }
    }
    
    @FXML
    private void goToUpdateTransaction(ActionEvent event) throws IOException {
        Transaction selectedTransaction = table.getSelectionModel().getSelectedItem();
        if (selectedTransaction == null) {
            // Show an alert if no transaction is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune transaction sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une transaction à modifier.");
            alert.showAndWait();
            return;
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Êtes-vous sûr de vouloir modifier cette transaction ?");
        

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
              FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTrans.fxml"));

        try {
            Parent root = loader.load();
            updatetb.getScene().setRoot(root);
            UpdateTransController controller = loader.getController();
            // Passer la transaction sélectionnée au contrôleur de la page de modification
            
            Transaction transaction = table.getSelectionModel().getSelectedItem();
            controller.setTransaction(transaction);
//            UpdateTransController controller = loader.getController();
//            controller.setTransaction(transaction);

        } catch (IOException ex) {
            Logger.getLogger(ConsulterTransController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
            }

    }
}
    
