///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Gui;
//
//import entities.Reclamation;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.cell.PropertyValueFactory;
//import services.Rcrud;
//
//
///**
// * FXML Controller class
// *
// * @author Maissa
// */
//public class DelRecController implements Initializable {
//    
//    
//    
//    
//    private TableView<Reclamation> tablerec;
//    @FXML
//    private TableColumn<Reclamation, Integer> tfid;
//    
//    @FXML
//     private TableColumn<Reclamation, Integer> tfuser;
//  
//    @FXML
//    private TableColumn<Reclamation, String> tfsubject;
//    @FXML
//    private TableColumn<Reclamation, String> tfmessage;
//    
//    @FXML
//    private TableColumn<Reclamation, String> tfetat;
//    
//   
//    
//    @FXML
//    private TextArea iddescription;
//    @FXML
//    private Button btndel;
//    @FXML
//    private Button btnup;
//    @FXML
//    private Button btnsave;
//   
//public void showreclam(){
//    tablerec.getItems().clear();
//
//     Rcrud pcd= new Rcrud();
//     ObservableList<Reclamation> data = FXCollections.observableArrayList(pcd.entitiesList());
//      tfid.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer> ("id"));
//      tfuser.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer> ("user_id"));
//      tfmessage.setCellValueFactory(new PropertyValueFactory<Reclamation,String> ("message"));
//      tfsubject.setCellValueFactory(new PropertyValueFactory<Reclamation,String> ("subject"));
//      tfetat.setCellValueFactory(new PropertyValueFactory<Reclamation,String> ("status"));
//      
//     
//    tablerec.setItems(data);
// }
//        
//        /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        showreclam();
//     }    
//    
////    @FXML
////    private void savereclam(ActionEvent event) {
////    
////      int resUserid = Integer.parseInt(tfuser.getText());
////       // String resNom = tfnom.getText();
////     String resDesc = iddescription.getText();
////     
////   
////          Rcrud pcd = new Rcrud();
////          Reclamation r = new Reclamation(resUserid, resDesc);
////        
////        
////      pcd.ajouterReclamation(r);
////     
////        System.out.println("Done!!");
////    
////    
////    //redirection
////      FXMLLoader loader = new FXMLLoader(getClass().getResource("UpRecController.fxml"));
////        
////        try {
////            Parent root = loader.load();
////            UpRecController dc = loader.getController();
////            
////           
////      
////                dc.setResnom(tfuser);
////               dc.setResdiscrip(tfmessage);
////
////          iddescription.getScene().setRoot(root);
////        } catch (IOException ex) {
////            System.out.println(ex.getMessage());  
////        }
//    }
//    
//    
//    
//    
//// private int ReclamSelectionner() {
////        int selectedItem = tablerec.getSelectionModel().getSelectedItem().getId();
////        int selectedIndex = tablerec.getSelectionModel().getSelectedIndex();
////        System.out.println(selectedItem);
////        return selectedItem;
////    }
////    
////        @FXML
////    private void suppReclamation(ActionEvent event) {
////  Reclamation u =tablerec.getSelectionModel().getSelectedItem();
////           if(u== null)
////    {//msg alertt
////         Alert alert = new Alert(Alert.AlertType.WARNING);
////        alert.setTitle("Supprimer réclamation");
////alert.setHeaderText(null);
////alert.setContentText("Vous devez selectionnez une réclamation!");
////
////alert.showAndWait();
////        return;
////    }
////    Rcrud UC = new Rcrud();
////    UC.suppReclamation(0);
////     //tablerec.setItems(UC.showreclam());
////     }
//      
//    
////       @FXML
////     
////    private void saveReclam(ActionEvent event) {
////           int resid = Integer.parseInt(tfid.getText());
////           int resuserid = Integer.parseInt(tfuser.getText());
////            String resmessage = tfmessage.getText();
////            String ressubject = tfsubject.getText();    
////     
////    Rcrud pcd = new Rcrud();
////        
////        Reclamation r = new Reclamation(resuserid, ressubject, resmessage, ressubject);
////        pcd.addEntity(r);
////        System.out.println("Done!!");
////        tablerec.getItems().clear();
////           //showReclam();
////   
////       } 
//
//
//
//
//  
//
//     
//     
//     
//     
//     
//     
//     
//     
//     
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//
