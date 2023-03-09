/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Bien;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nouhe
 */
public class FAvorisController implements Initializable {

    @FXML
    private TableView<Bien> tablefavoris;
    @FXML
    private TableColumn<Bien, String> idl;
    @FXML
    private TableColumn<Bien,String> idc;
    @FXML
    private TableColumn<Bien,Image> idi;
    @FXML
    private TableColumn<Bien, String> idd;
    @FXML
    private Button idconsultation;
    @FXML
    private TableColumn<Bien,Void> idsupprimer;
    /**
     * Initializes the controller class.
     */
      public ObservableList<Bien> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int userId = 2;

        // Récupérer les données des biens favoris de l'utilisateur connecté
        data = FXCollections.observableArrayList();
      
            try {
    String requete = "SELECT items.id, items.name, items.description, items.image, categories.categorie \n" +
                     "FROM items \n" +
                     "JOIN categories ON items.id_categorie = categories.idC \n" +
                     "JOIN favoris ON items.id = favoris.id_item \n" +
                     "WHERE favoris.id_utilisateur = ?";
    PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
    st.setInt(1, userId);
    ResultSet rs = st.executeQuery(); // Utilisez executeQuery() ici
    while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String imagePath = rs.getString("image");
        String categorie = rs.getString("categorie");
        File file = new File("C:\\Users\\Nouhe\\Documents\\NetBeansProjects\\GestionBiensEtServices\\src\\images\\" + imagePath);
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        Bien bien = new Bien(id, name, description, imageView, categorie);
        data.add(bien);
    }
} catch (SQLException ex) {
    System.err.println("Une erreur s'est produite lors de la récupération des biens favoris : " + ex.getMessage());
}

        // Afficher les données dans la TableView
       
        idl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idd.setCellValueFactory(new PropertyValueFactory<>("description"));
        idi.setCellValueFactory(new PropertyValueFactory<>("image"));
        idc.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        tablefavoris.setItems(data);
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
     @FXML
    void consultation(ActionEvent event)throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("TestBien.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
    }    
    
