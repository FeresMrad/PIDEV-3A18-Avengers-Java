/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenements;
import entities.Participants;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;

/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class ParticiperEController implements Initializable {

    @FXML
    private Button btnparticipier;
    @FXML
    private TableView<Evenements> tableevent;
//    @FXML
//    private TableColumn<Evenements, Integer> tid;
    @FXML
    private TableColumn<Evenements, String> tnom;
    @FXML
    private TableColumn<Evenements, String> tdesc;
    @FXML
    private TableColumn<Evenements, Date> tdatedeb;
    @FXML
    private TableColumn<Evenements, Date> tdatefin;
    @FXML
    private TableColumn<Evenements, String> tlieu;
    
   

    /**
     * Initializes the controller class.
     */

      public void showEvent(){
       tableevent.getItems().clear();


     EvenementsCRUD pcd= new EvenementsCRUD();
     ObservableList<Evenements> data = FXCollections.observableArrayList(pcd.entitiesList());

//      tid.setCellValueFactory(new PropertyValueFactory<Evenements,Integer> ("id"));
      tnom.setCellValueFactory(new PropertyValueFactory<Evenements,String> ("nom"));
      tdesc.setCellValueFactory(new PropertyValueFactory<Evenements,String> ("description"));
      tdatedeb.setCellValueFactory(new PropertyValueFactory<Evenements,Date> ("date_debut"));
     tdatefin.setCellValueFactory(new PropertyValueFactory<Evenements,Date> ("date_fin"));
     tlieu.setCellValueFactory(new PropertyValueFactory<Evenements,String> ("lieu"));
     
     
    tableevent.setItems(data);
   
 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEvent();
    }    
     private int EventSelectionner() {
    int selectedItem = -1;
    int selectedIndex = tableevent.getSelectionModel().getSelectedIndex();
    if (selectedIndex != -1) {
        selectedItem = tableevent.getSelectionModel().getSelectedItem().getId();
    }
    return selectedItem;
    }
         
    @FXML
    private void participier(ActionEvent event) {
       
     
       int x = EventSelectionner();
       
       
         Evenements selectedEvent = tableevent.getSelectionModel().getSelectedItem();
        
    if (x == -1) {
        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Aucun événement sélectionné");
        alert2.setHeaderText(null);
        alert2.setContentText("Veuillez sélectionner un événement pour participer.");
        alert2.showAndWait();
        return;
    }
    
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de participation");
    alert.setHeaderText("Vous voulez participer à l'événement suivant ");
    alert.setContentText("Nom : " + selectedEvent.getNom() + "\n" +
                         "Description : " + selectedEvent.getDescription() + "\n" +
                         "Date de début : " + selectedEvent.getDate_debut() + "\n" +
                         "Date de fin : " + selectedEvent.getDate_fin() + "\n" +
                         "Lieu : " + selectedEvent.getLieu() + "\n\n" +
                         "Êtes-vous sûr de vouloir participier à cet événement ?");
    
       Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {

 
         ParticipantsCRUD pcd = new ParticipantsCRUD();
        Participants t = new Participants(x,2);
        pcd.addEntityP(t);
        // Afficher une alerte de succès
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Participation réussie");
        successAlert.setContentText("Félicitations vous êtes maintenant un participant à l'événement :  "+selectedEvent.getNom());
        successAlert.showAndWait();
        
//        tableevent.getItems().clear();
//           showEvent();
    }
    
}
}