/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenements;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;
import utils.MyConnection;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class ListeEvenementsController implements Initializable {

    @FXML
    private TableView<Evenements> tableevent;
    @FXML
    private TableColumn<Evenements, Integer> tid;
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
    private TextField tfnom;
    @FXML
    private TextArea tfdesc;
    @FXML
    private TextField tflieu;
    
    private AutoCompletionBinding<String> autoCompletionBinding;
  private String[] possibleSugg = {"Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(possibleSugg));

    
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupd;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnlistep;

    /**
     * Initializes the controller class.
     */

   

     
     
     
     public void showEvent(){
       tableevent.getItems().clear();
      


     EvenementsCRUD pcd= new EvenementsCRUD();
     ObservableList<Evenements> data = FXCollections.observableArrayList(pcd.entitiesList());

      tid.setCellValueFactory(new PropertyValueFactory<Evenements,Integer> ("id"));
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
       
       
     autoCompletionBinding = TextFields.bindAutoCompletion(tflieu, possibleSuggestions);

       
    }    
    @FXML
    private void saveEvent(ActionEvent event) {

  if (tfnom.getText().isEmpty() || tfdesc.getText().isEmpty() || 
        datedeb.getValue() == null || datefin.getValue() == null ||
        tflieu.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
    } else if (checkEventName(tfnom.getText())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'??v??nement existe d??j??");
        alert.showAndWait();
    } else if (datefin.getValue().isBefore(datedeb.getValue())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La date de fin doit ??tre sup??rieure ?? la date de d??but");
        alert.showAndWait();
    } 
    else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajouter un ??v??nement");
        alert.setHeaderText(null);
        alert.setContentText("??tes-vous s??r de vouloir ajouter cet ??v??nement ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            String resNom = tfnom.getText();
            String resDesc = tfdesc.getText();
            Date resDate_deb = java.sql.Date.valueOf(datedeb.getValue());
            Date resDate_fin = java.sql.Date.valueOf(datefin.getValue());
            String resLieu = tflieu.getText();

            EvenementsCRUD pcd = new EvenementsCRUD();
            Evenements t = new Evenements(resNom, resDesc, resDate_deb, resDate_fin, resLieu);
            pcd.addEntity(t);
            System.out.println("Done!!");
            tableevent.getItems().clear();
            showEvent();
        }
    }
}

private boolean checkEventName(String nom) {
    EvenementsCRUD pcd = new EvenementsCRUD();
    List<Evenements> list = pcd.entitiesList();
    for (Evenements e : list) {
        if (e.getNom().equalsIgnoreCase(nom)) {
            return true;
        }
    }
    return false;
}
    
 private int EventSelectionner() {
        int selectedItem = tableevent.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = tableevent.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }
 
 
 
    @FXML
    private void modifEvent(ActionEvent event) throws SQLException {
    // V??rifier si un ??v??nement est s??lectionn?? dans la table
    Evenements selectedEvent = tableevent.getSelectionModel().getSelectedItem();
    if (selectedEvent == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun ??v??nement s??lectionn??");
        alert.setContentText("Veuillez s??lectionner un ??v??nement ?? modifier.");
        alert.showAndWait();
        return;
    }

    // V??rifier si les champs sont vides
    if (tfnom.getText().isEmpty() || tfdesc.getText().isEmpty() || datedeb.getValue() == null 
        || datefin.getValue() == null || tflieu.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
       // V??rifier que la date de fin est sup??rieure ?? la date de d??but
    if (datefin.getValue().isBefore(datedeb.getValue())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date de fin invalide");
        alert.setContentText("La date de fin doit ??tre sup??rieure ?? la date de d??but.");
        alert.showAndWait();
        return;
    }

    // V??rifier que le nom de l'??v??nement n'existe pas d??j?? dans la base
    String newNom = tfnom.getText();
    if (!newNom.equals(selectedEvent.getNom()) && checkEventName(newNom)) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nom d'??v??nement d??j?? existant");
        alert.setContentText("Un ??v??nement portant le m??me nom existe d??j?? dans la base de donn??es.");
        alert.showAndWait();
        return;
    }


    // Afficher une alerte de confirmation avant la modification
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de modification");
    alert.setHeaderText("Vous ??tes sur le point de modifier l'??v??nement suivant :");
    alert.setContentText("Nom : " + selectedEvent.getNom() + "\n" +
                         "Description : " + selectedEvent.getDescription() + "\n" +
                         "Date de d??but : " + selectedEvent.getDate_debut() + "\n" +
                         "Date de fin : " + selectedEvent.getDate_fin() + "\n" +
                         "Lieu : " + selectedEvent.getLieu() + "\n\n" +
                         "??tes-vous s??r de vouloir modifier cet ??v??nement ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // R??cup??rer les nouvelles valeurs pour l'??v??nement modifi??
        String resNom = tfnom.getText();
        String resDesc = tfdesc.getText();
        Date resDate_deb = java.sql.Date.valueOf(datedeb.getValue());
        Date resDate_fin = java.sql.Date.valueOf(datefin.getValue());
        String resLieu = tflieu.getText();

        // Mettre ?? jour l'??v??nement s??lectionn??
        EvenementsCRUD pcd = new EvenementsCRUD();
        Evenements t = new Evenements(resNom,resDesc,resDate_deb,resDate_fin,resLieu);
        pcd.updEvenements(t, selectedEvent.getId());

        // Afficher une alerte de succ??s
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Modification r??ussie");
        successAlert.setContentText("L'??v??nement a ??t?? modifi?? avec succ??s.");
        successAlert.showAndWait();

        // Rafra??chir la table des ??v??nements
        tableevent.getItems().clear();
        showEvent();
    }  

    }
    
    
    
    

    @FXML
    private void suppEvent(ActionEvent event) {


    if(tableevent.getSelectionModel().getSelectedItems().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne s??lectionn??e");
        alert.setContentText("Veuillez s??lectionner une ligne ?? supprimer.");
        alert.showAndWait();
        return;
    }
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Supprimer l'??v??nement");
    alert.setHeaderText(null);
    alert.setContentText("??tes-vous s??r de vouloir supprimer cet ??v??nement ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        int selectedItem = tableevent.getSelectionModel().getSelectedItem().getId();
        EvenementsCRUD pcd = new EvenementsCRUD();
        ParticipantsCRUD scd= new ParticipantsCRUD();
        scd.delParticipants(selectedItem);
        pcd.delEvenements(selectedItem);
        System.out.println("Done!!");
        tableevent.getItems().clear();
        showEvent();
    }
    }

    @FXML
    private void listeP(ActionEvent event) {
    Evenements selectedEvent = tableevent.getSelectionModel().getSelectedItem();
       if (selectedEvent == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune ligne s??lectionn??e");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez s??lectionner une ligne pour afficher la liste des participants.");
        alert.showAndWait();
        return;
    }
    
    int resid = selectedEvent.getId();
    String resnom=selectedEvent.getNom();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeParticipants.fxml"));
    try {
        Parent root = loader.load();
        ListeParticipantsController dc = loader.getController();
        dc.setTfidevent(String.valueOf(resid));
        dc.setTfeventnom(resnom);
        dc.setEvent(selectedEvent.getId());
        dc.setDateE(selectedEvent.getDate_debut());
        dc.setLieue(selectedEvent.getLieu());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListeParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
