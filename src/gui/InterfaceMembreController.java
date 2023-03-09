/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.User;
import static gui.ProfilMembreController.idcli;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.MyConnection;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class InterfaceMembreController implements Initializable {

    @FXML
    private AnchorPane rootPane3;
    @FXML
    private Pane paneId;
    @FXML
    private Button profilbtn;
    @FXML
    private Button logoffbtn1;
    @FXML
    private Label wlcm;
 
    private static final Session session = Session.getInstance();
     Connection mc ; 
     PreparedStatement ste2,ste;
     
  User user = Session.getUser();
  
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
//     wlcm.setText("Welcome " + user.getNom_user() + " " + user.getPrenom_user() + "!");
 Select() ;
    }   

    @FXML
    private void ActionProfilbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilMembre.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    @FXML
    private void ActionLogOffbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("SignInMembre.fxml"));
        rootPane3.getChildren().setAll(pane);
    }
    public void Select(){
        
         mc = MyConnection.getInstance().getCnx();
        String sql="SELECT * FROM user where id_user = "+idcli+" " ;

        try{
        ste2 = mc.prepareStatement(sql);
         
        ResultSet rs=ste2.executeQuery();
           while(rs.next()){             
              
                      
                       wlcm.setText("Welcome  " +rs.getString(3)+" !");
                       
                       
           }
        }catch (SQLException ex) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    
    }
    @FXML
    void p(ActionEvent event)throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("TestBien.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
    @FXML
    void redservices(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("AffichageService.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
    
 @FXML
    void ajouterreclam(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
@FXML
    void consulteven(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("ParticiperE.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
   @FXML
    void reds(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("Graphes.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    

}