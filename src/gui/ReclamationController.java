/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
















import entities.Reclamation;
import java.awt.Color;

import static java.awt.PageAttributes.MediaType.A4;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Cell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static javax.print.DocFlavor.BYTE_ARRAY.PDF;
import static javax.print.DocFlavor.INPUT_STREAM.PDF;
import static javax.print.DocFlavor.URL.PDF;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.Rcrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class ReclamationController implements Initializable {
    
    
      @FXML
    private TextField tfnom;
   
    @FXML
    private TextArea tfdescrip;
  
    @FXML
    private Button btnsave;
    @FXML
    private TableView<Reclamation> tablerec;
    @FXML
    private TableColumn<Reclamation, Integer> idrec;
    @FXML
    private TableColumn<Reclamation, Integer> idcl;
    @FXML
    private TableColumn<Reclamation, String> idsub;
    @FXML
    private TableColumn<Reclamation, String> idtxt;
    @FXML
    private TableColumn<Reclamation, String> idstat;
    @FXML
    private TextField tfstatus;
    @FXML
    private TextField tfsubject;
    @FXML
    private Button btndel;
    @FXML
    private Button btnadd;
    @FXML
    private Button retour;
   
   
    
    public void showRecl(){
    tablerec.getItems().clear();
    
    Rcrud pcd= new Rcrud();
     ObservableList<Reclamation> data = FXCollections.observableArrayList(pcd.entitiesList());

      idrec.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer> ("id"));
      idcl.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer> ("user_id"));
      idsub.setCellValueFactory(new PropertyValueFactory<Reclamation,String> ("subject"));
     idtxt.setCellValueFactory(new PropertyValueFactory<Reclamation,String> ("message"));
      idstat.setCellValueFactory(new PropertyValueFactory<Reclamation,String> ("status"));
     
     
    tablerec.setItems(data);
    
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showRecl();
    }   

    public TextField getTfnom() {
        return tfnom;
    }

    public TextArea getTfdescrip() {
        return tfdescrip;
    }

    public Button getBtnsave() {
        return btnsave;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    public void setTfdescrip(TextArea tfdescrip) {
        this.tfdescrip = tfdescrip;
    }

    public void setBtnsave(Button btnsave) {
        this.btnsave = btnsave;
    }

    public TextField getTfstatus() {
        return tfstatus;
    }

    public TextField getTfsubject() {
        return tfsubject;
    }

    public void setTfstatus(TextField tfstatus) {
        this.tfstatus = tfstatus;
    }

    public void setTfsubject(TextField tfsubject) {
        this.tfsubject = tfsubject;
    }

    public Button getBtndel() {
        return btndel;
    }

    public void setBtndel(Button btndel) {
        this.btndel = btndel;
    } 
    
    private void saveReclam(ActionEvent event) {
      int resUserid = Integer.parseInt(tfnom.getText());
       // String resNom = tfnom.getText();
            String resDesc = tfdescrip.getText();
     String resStatus= tfstatus.getText();
     String setTfsubject = tfsubject.getText();
   
          Rcrud pcd = new Rcrud();
          Reclamation r = new Reclamation(resUserid, resDesc);
        
//          pcd.ajouterReclamation(r,t);
     
        System.out.println("Done!!"); 
    
    
    }
         
    private int ReclamSelectionner() {
    int selectedItem = -1; // Valeur par défaut si aucun élément sélectionné
    int selectedIndex = tablerec.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) { // Vérifier si un élément est sélectionné
        selectedItem = tablerec.getSelectionModel().getSelectedItem().getId();
        System.out.println(selectedItem);
    }
    return selectedItem;
}
    private void modifNotif(ActionEvent event) throws SQLException {
      
             int x = ReclamSelectionner();

               int resNom = Integer.parseInt(tfnom.getText());
     String resDesc = tfdescrip.getText();
    String resStatus= tfstatus.getText();
     String resSubject = tfsubject.getText();
     
     Rcrud pcd = new Rcrud();
      Reclamation r = new Reclamation(resNom, resSubject, resStatus, resStatus);
     
        pcd.updReclamation(r, x);
        System.out.println("Done!!");
        
        tablerec.getItems().clear();
           showRecl();
    }
    
    
    
        @FXML
    private void suppRec(ActionEvent event) {
          int x = ReclamSelectionner();
          
          Rcrud pcd=new Rcrud();
          pcd.suppReclamation(x);
       
  tablerec.getItems().clear();
            showRecl();}
    
     @FXML
     private void addreclam(ActionEvent event){
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
           try {
            Parent root = loader.load(); // preparer les acteurs de la 2éme scene
            AjouterReclamationController dc = loader.getController();
           dc.setTfnom(tfnom);
          dc.setTfdesc(tfdescrip);
          dc.setTfsujet(tfsubject);
          dc.setTfstatus(tfstatus);
           
            tfnom.getScene().setRoot(root);// scene loula naawadha b scene thenya eli heya l details
            
        } catch (IOException ex ) {
            
            System.out.println(ex.getMessage());
        }}

  
  @FXML
    void RET(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("InterfaceMembre.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }







}
    
    

    
    
    
    
   
    
    
    
    
    
    
