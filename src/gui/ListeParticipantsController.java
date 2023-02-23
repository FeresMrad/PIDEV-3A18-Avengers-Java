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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;

/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class ListeParticipantsController implements Initializable {
    @FXML
    private TableView<Participants> tableparticipants;
    @FXML
    private TableColumn<Participants, Integer> tid;
    @FXML
    private TableColumn<Participants, Integer> tuserid;
    @FXML
    private TextField tfidevent;
    @FXML
    private TextField tfeventnom;
    
    private int event;
    @FXML
    private Button btnretour;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
      
    }    

    public TableColumn<Participants, Integer> getTid() {
        return tid;
    }

    public void setTid(TableColumn<Participants, Integer> tid) {
        this.tid = tid;
    }

    public TableColumn<Participants, Integer> getTuserid() {
        return tuserid;
    }

    public void setTuserid(TableColumn<Participants, Integer> tuserid) {
        this.tuserid = tuserid;
    }

    public TextField getTfidevent() {
        return tfidevent;
    }

    public TextField getTfeventnom() {
        return tfeventnom;
    }

    public void setTfeventnom(String message) {
        this.tfeventnom.setText(message);
    }

    public void setTfidevent(String message) {
        this.tfidevent.setText(message);
        
    }
      public int getEvent() {
      
        return event;
    }

    public void setEvent(int event) {
    
        this.event = event;
        showParticipants();
    }
    

public void showParticipants(){
    tableparticipants.getItems().clear();
     
     
    int evenementId = getEvent();
    ParticipantsCRUD pcd = new ParticipantsCRUD();
    ObservableList<Participants> data = FXCollections.observableArrayList(pcd.entitiesListP(evenementId));
     
    tid.setCellValueFactory(new PropertyValueFactory<>("id"));
    tuserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));

    tableparticipants.setItems(data);
}

    @FXML
    private void retourE(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEvenements.fxml"));
    try {
        Parent root = loader.load();
        ListeEvenementsController dc = loader.getController();
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListeEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
