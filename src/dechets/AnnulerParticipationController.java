///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package dechets;
//
//import entities.Evenements;
//import entities.Participants;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextField;
//import services.EvenementsCRUD;
//import services.ParticipantsCRUD;
//
///**
// * FXML Controller class
// *
// * @author BACEM
// */
//public class AnnulerParticipationController implements Initializable {
//
//    @FXML
//    private ComboBox<Evenements> comboevent;
//    @FXML
//    private Button btnannuler;
//    @FXML
//    private TextField tidp;
//    @FXML
//    private Button btngenerer;
//    
//     private ObservableList<Evenements> data = FXCollections.observableArrayList();
//    private List<Participants> participantsList = new ArrayList<>();
//    private ParticipantsCRUD pcd = new ParticipantsCRUD();
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        comboevent.setItems(data);
//        participantsList = pcd.entitiesListP();
//    }    
//      @FXML
//    private void handleGenerateButtonAction() {
//        int idp = Integer.parseInt(tidp.getText());
//        
//        EvenementsCRUD ecd = new EvenementsCRUD();
//        List<Evenements> evenementsList = new ArrayList<>();
//        for (Participants p : participantsList) {
//            if (p.getUser_id() == idp) {
////                Evenements e = ecd.findById(p.getEvenement_id());
//                evenementsList.add(e);
//            }
//        }
//        data.addAll(evenementsList);
//    }
//
//    @FXML
//    private void handleCancelButtonAction() {
//        Evenements e = comboevent.getSelectionModel().getSelectedItem();
//        int idp = Integer.parseInt(tidp.getText());
//        int ide = e.getId();
//        for (Participants p : participantsList) {
//            if (p.getUser_id() == idp && p.getEvenement_id()== ide) {
////                pcd.delParticipants(p.getId());
//                data.remove(e);
//            }
//        }
//    }
//}
