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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.FeedbackCRUD;

/**
 * FXML Controller class
 *
 * @author Feriel
 */
public class DetailsController implements Initializable {

    @FXML
    private TableView<Feedback> feedbackTable;
    @FXML
    private TableColumn<Feedback, Integer> idColumn;
    @FXML
    private TableColumn<Feedback, Integer> transactionIdColumn;
    @FXML
    private TableColumn<Feedback, String> ratingColumn;
    @FXML
    private TableColumn<Feedback, String> commentColumn;
    @FXML
    private TableColumn<Feedback, Date> dateColumn;

    //instantiation
    FeedbackCRUD ser = new FeedbackCRUD();

//initialize
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //appel de fct show
        showFeedback();

    }

    //Liste Feedback
    public void showFeedback() {
        FeedbackCRUD pcd = new FeedbackCRUD();
        ObservableList<Feedback> data = FXCollections.observableArrayList(pcd.getAll());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transaction_id"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        //delete
        TableColumn<Feedback, Void> deleteColumn = new TableColumn<>("Delete");
        Callback<TableColumn<Feedback, Void>, TableCell<Feedback, Void>> deleteCellFactory = new Callback<TableColumn<Feedback, Void>, TableCell<Feedback, Void>>() {
            @Override
            public TableCell<Feedback, Void> call(final TableColumn<Feedback, Void> param) {
                final TableCell<Feedback, Void> cell = new TableCell<Feedback, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {   //deleteButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
                        deleteButton.setOnAction((ActionEvent event) -> {

                            if (feedbackTable.getSelectionModel().getSelectedItems().isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Aucune ligne sélectionnée");
                                alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
                                alert.showAndWait();
                                return;
                            }
                            Feedback feedback = getTableView().getItems().get(getIndex());

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirm Delete");
                            alert.setHeaderText(null);
                            alert.setContentText("Are you sure you want to delete this feedback?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                pcd.delete(feedback.getId());
                                data.remove(feedback);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        };
        //update
        TableColumn<Feedback, Void> updateColumn = new TableColumn<>("Update");
        Callback<TableColumn<Feedback, Void>, TableCell<Feedback, Void>> updateCellFactory = new Callback<TableColumn<Feedback, Void>, TableCell<Feedback, Void>>() {
            @Override
            public TableCell<Feedback, Void> call(final TableColumn<Feedback, Void> param) {
                final TableCell<Feedback, Void> cell = new TableCell<Feedback, Void>() {
                    private final Button updateButton = new Button("Update");

                    {
                        updateButton.setOnAction((ActionEvent event) -> {
                            goToUpdateFeedback(event);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(updateButton);
                        }
                    }
                };
                return cell;
            }
        };

        deleteColumn.setCellFactory(deleteCellFactory);
        feedbackTable.getColumns().add(deleteColumn);
        updateColumn.setCellFactory(updateCellFactory);
        feedbackTable.getColumns().add(updateColumn);
        //reafficher la liste
        feedbackTable.setItems(data);

    }

    //fonction de redirection  UpdateFeedback 
    @FXML
    private void goToUpdateFeedback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateFeedback.fxml"));
            Parent root = loader.load();
            UpdateFeedbackController controller = loader.getController();

            // Passer le feedback sélectionné au contrôleur de la page de modification
            Feedback feedback = feedbackTable.getSelectionModel().getSelectedItem();
            controller.setFeedback(feedback);
            //controle

            if (feedback != null) {
                controller.setFeedback(feedback);
                // Afficher l'interface de modification
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                // Afficher un message d'erreur si aucun feedback n'est sélectionné
                Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner un feedback à modifier.");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
