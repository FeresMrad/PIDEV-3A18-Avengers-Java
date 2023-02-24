/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionBiensEtServices.gui;

import edu.gestionBiensEtServices.entites.Service;
import edu.gestionBiensEtServices.services.ServiceService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nouhe
 */
public class AffichageServiceController implements Initializable {

    @FXML
    private TextField idtext;
    @FXML
    private TextArea iddescription;
    @FXML
    private TableView<Service> tableservices;
    @FXML
    private TableColumn<Service,String> idintitule;
    @FXML
    private TableColumn<Service,String> iddesc;
    @FXML
    private Button idajout;
    @FXML
    private Button idmodif;
    @FXML
    private Button idsupp;
    @FXML
    private Button idaccueil;

    /**
     * Initializes the controller class.
     */
    public void showService() {
        tableservices.getItems().clear();
        ServiceService s = new ServiceService();
        ObservableList<Service> data = FXCollections.observableArrayList(s.etitiesList());
        idintitule.setCellValueFactory(new PropertyValueFactory<Service, String>("intitule"));
        iddesc.setCellValueFactory(new PropertyValueFactory<Service, String>("description"));
        tableservices.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showService();
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        if (idtext.getText().isEmpty() || iddescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter un service");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir ajouter ce service ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String resNom = idtext.getText();
                String resDesc = iddescription.getText();
                ServiceService ser = new ServiceService();
                Service s = new Service(resNom, resDesc);
                ser.ajouterService(s);
                System.out.println("OK!");
                tableservices.getItems().clear();
                showService();
            }
        }
    }

    private int ServiceSelectionner() {
        int selectedItem = tableservices.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = tableservices.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Service selectedService = tableservices.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun service est sélectionné");
            alert.setContentText("Veuillez sélectionner un service à modifier.");
            alert.showAndWait();
            return;
        }
        if (idtext.getText().isEmpty() || iddescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Vous êtes sur le point de modifier le service suivant :");
        alert.setContentText("Nom : " + selectedService.getIntitule() + "\n"
                + "Description : " + selectedService.getDescription() + "\n"
                + "Êtes-vous sûr de vouloir modifier ce service ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String resNom = idtext.getText();
            String resDesc = iddescription.getText();
            ServiceService ser = new ServiceService();
            Service s = new Service(resNom, resDesc);
            ser.updateService(s, selectedService.getId());
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Modification réussie");
            successAlert.setContentText("Service modifié avec succès.");
            successAlert.showAndWait();
            tableservices.getItems().clear();
            showService();
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (tableservices.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune ligne sélectionnée");
            alert.setContentText("Veuillez sélectionner le service à supprimer.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer le service");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce service ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int selectedItem = tableservices.getSelectionModel().getSelectedItem().getId();
            ServiceService ser = new ServiceService();
            ser.supprimerService(selectedItem);
            System.out.println("OK!");
            tableservices.getItems().clear();
            showService();
        }
    }
    
    @FXML
    void accueil(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
}
