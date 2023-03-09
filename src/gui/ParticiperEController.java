/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenements;
import entities.Participants;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;
import static gui.ProfilMembreController.idcli;

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
    @FXML
    private Button consult;
    
    int k=idcli;
    
   

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
         
//    @FXML
//    private void participier(ActionEvent event) {
//       
//     
//       int x = EventSelectionner();
//       
//       
//         Evenements selectedEvent = tableevent.getSelectionModel().getSelectedItem();
//        
//    if (x == -1) {
//        Alert alert2 = new Alert(Alert.AlertType.WARNING);
//        alert2.setTitle("Aucun événement sélectionné");
//        alert2.setHeaderText(null);
//        alert2.setContentText("Veuillez sélectionner un événement pour participer.");
//        alert2.showAndWait();
//        return;
//    }
//    
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//    alert.setTitle("Confirmation de participation");
//    alert.setHeaderText("Vous voulez participer à l'événement suivant ");
//    alert.setContentText("Nom : " + selectedEvent.getNom() + "\n" +
//                         "Description : " + selectedEvent.getDescription() + "\n" +
//                         "Date de début : " + selectedEvent.getDate_debut() + "\n" +
//                         "Date de fin : " + selectedEvent.getDate_fin() + "\n" +
//                         "Lieu : " + selectedEvent.getLieu() + "\n\n" +
//                         "Êtes-vous sûr de vouloir participier à cet événement ?");
//    
//       Optional<ButtonType> result = alert.showAndWait();
//    if (result.isPresent() && result.get() == ButtonType.OK) {
//
// 
//         ParticipantsCRUD pcd = new ParticipantsCRUD();
//        Participants t = new Participants(x,idcli); // user_id
//        pcd.addEntityP(t);
//        // Afficher une alerte de succès
//        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
//        successAlert.setTitle("Participation réussie");
//        successAlert.setContentText("Félicitations vous êtes maintenant un participant à l'événement :  "+selectedEvent.getNom());
//        successAlert.showAndWait();
//        
////        tableevent.getItems().clear();
////           showEvent();
//    }
//    
//}

 

    @FXML
    private void consulter(ActionEvent event) throws IOException {

        Evenements selectedEvent = tableevent.getSelectionModel().getSelectedItem();
       if (selectedEvent == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne pour afficher la liste des participants.");
        alert.showAndWait();
        return;
    }
       
       
        int resid = selectedEvent.getId();
    String resnom=selectedEvent.getNom();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
    try {
        Parent root = loader.load();
        EventController dc = loader.getController();
        dc.setLblnom(resnom);
        dc.setLdesc(selectedEvent.getDescription());
        dc.setLlieu(selectedEvent.getLieu());
        dc.setLdatedeb(selectedEvent.getDate_debut().toString());
        dc.setLdatefin(selectedEvent.getDate_fin().toString());
        dc.setEvent(selectedEvent.getId());


        
 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListeParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}