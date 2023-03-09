/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Notification;
import entities.Notification.Type;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import services.Ncrud;
import services.Rcrud;


/**
 * FXML Controller class
 *
 * @author Maissa
 */

public class NotificationController implements Initializable {
    private TableView<Notification> tablnotif;
    private TableColumn<Notification, Integer> id;
    
    private TextField tfnotif;
    @FXML
    private TableView<Notification> tablenotif;
    
    @FXML
    private TableColumn<Notification, Integer> tbid ;
    @FXML
    private TableColumn<Notification, Integer> tbclient;
    @FXML
    private TableColumn<Notification, String> tbnotification;
    @FXML
    private TableColumn<Notification, String > tbtype;
    private javafx.scene.control.Button btnshow;
    @FXML
    private javafx.scene.control.Button btndel;
    @FXML
    private javafx.scene.control.Button btnupdate;
    @FXML
    private javafx.scene.control.Button btnajou;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnotification;
    @FXML
    private ComboBox<Type> type;
   
    
   
   public void showNotif(){
    //tablnotif.getItems().clear();
    
    Ncrud pcd= new Ncrud();
     ObservableList<Notification> data = FXCollections.observableArrayList(pcd.NotificationList());

      tbid.setCellValueFactory(new PropertyValueFactory<Notification,Integer> ("id"));
      tbclient.setCellValueFactory(new PropertyValueFactory<Notification,Integer> ("user_id "));
      tbnotification.setCellValueFactory(new PropertyValueFactory<Notification,String> ("message"));
     tbtype.setCellValueFactory(new PropertyValueFactory<Notification,String> ("type"));
     
     
     
    tablenotif.setItems(data);
    
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          showNotif();
        // TODO 
      }         

    public TableView<Notification> getTablnotif() {
        return tablnotif;
    }

    public TableColumn<Notification, Integer> getId() {
        return id;
    }

    public TextField getTfnotif() {
        return tfnotif;
    }

    public TableView<Notification> getTablenotif() {
        return tablenotif;
    }

    public TableColumn<Notification, Integer> getTbid() {
        return tbid;
    }

    public TableColumn<Notification, Integer> getTbclient() {
        return tbclient;
    }

    public TableColumn<Notification, String> getTbnotification() {
        return tbnotification;
    }

    public TableColumn<Notification, String> getTbtype() {
        return tbtype;
    }

    public Button getBtnshow() {
        return btnshow;
    }

    public Button getBtndel() {
        return btndel;
    }

    public Button getBtnupdate() {
        return btnupdate;
    }

    public Button getBtnajou() {
        return btnajou;
    }

    public TextField getTfnom() {
        return tfnom;
    }

    public TextField getTfnotification() {
        return tfnotification;
    }

    public ComboBox<Type> getType() {
        return type;
    }

    public void setTablnotif(TableView<Notification> tablnotif) {
        this.tablnotif = tablnotif;
    }

    public void setId(TableColumn<Notification, Integer> id) {
        this.id = id;
    }

    public void setTfnotif(TextField tfnotif) {
        this.tfnotif = tfnotif;
    }

    public void setTablenotif(TableView<Notification> tablenotif) {
        this.tablenotif = tablenotif;
    }

    public void setTbid(TableColumn<Notification, Integer> tbid) {
        this.tbid = tbid;
    }

    public void setTbclient(TableColumn<Notification, Integer> tbclient) {
        this.tbclient = tbclient;
    }

    public void setTbnotification(TableColumn<Notification, String> tbnotification) {
        this.tbnotification = tbnotification;
    }

    public void setTbtype(TableColumn<Notification, String> tbtype) {
        this.tbtype = tbtype;
    }

    public void setBtnshow(Button btnshow) {
        this.btnshow = btnshow;
    }

    public void setBtndel(Button btndel) {
        this.btndel = btndel;
    }

    public void setBtnupdate(Button btnupdate) {
        this.btnupdate = btnupdate;
    }

    public void setBtnajou(Button btnajou) {
        this.btnajou = btnajou;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    public void setTfnotification(TextField tfnotification) {
        this.tfnotification = tfnotification;
    }

    public void setType(ComboBox<Type> type) {
        this.type = type;
    } 
    
    
       
      private void saveNotif(ActionEvent event) {
         int resId = Integer.parseInt(id.getText());
            int resUserid = Integer.parseInt(tbclient.getText());
      
           String resNotif = tbnotification.getText();
            String resType= tbtype.getText();
   
   
         Ncrud pcd = new Ncrud();
        Notification n = new Notification(resId, resUserid, resNotif, resType);
        pcd.NotificationList();
       
     
        System.out.println("Done!!");}
    
    
      private int NotifSelectionner() {
    int selectedItem = -1; // Valeur par défaut si aucun élément sélectionné
    int selectedIndex = tablenotif.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) { // Vérifier si un élément est sélectionné
        selectedItem = tablenotif.getSelectionModel().getSelectedItem().getId();
        System.out.println(selectedItem);
    }
    return selectedItem;
}
 
      
      @FXML
         private void ajouterNotif(ActionEvent event) {
if (tfnom.getText().isEmpty() || tfnotification.getText().isEmpty() || type.getSelectionModel().isEmpty()) {
    Notifications notificationBuilder = Notifications.create()
            .title("ERREUR")
            .text("Veuillez remplir les champs")
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT);

    notificationBuilder.show();
} else {
 
    Rcrud rec = new Rcrud();
    
    //SAUVEGARDE BASE DE DONNEES
    int nom =  Integer.parseInt(tfnom.getText());
          String notif = tfnotification.getText();
          String typeC = type.getTypeSelector();
        //  String sub = tfstatus.getText();
        System.out.println(nom);
        Notification n = new Notification(nom, notif, typeC);
                
                Ncrud pc = new Ncrud();
       
pc.ajouternotification(n);
      
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      //  sendMail();
    
   
                alert.setTitle("Success");
      
      
}}    
      
 
    @FXML
    private void suppnotif(ActionEvent event) {
          int x = NotifSelectionner();
          
          Ncrud pcd=new Ncrud();
          pcd.suppNotification(x);
       
  tablenotif.getItems().clear();
           showNotif(); }
//    

        @FXML
    private void modifNotif(ActionEvent event) throws SQLException {  
     String resType = tbtype.getText();
     Ncrud pcd = new Ncrud();
     
    Notification n = new Notification( resType);
     
        pcd.upnotification(n, "transaction");
        System.out.println("Done!!");        
//        tablerec.getItems().clear();
           showNotif();
   }
    
  
   
    
    
    
    
    
    
    
    
    
}






