/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionBiensEtServices.gui;

import edu.gestionBiensEtServices.entites.Bien;
import edu.gestionBiensEtServices.services.BienServices;
import edu.gestionBiensEtServices.upload.Upload;
import edu.gestionBiensEtServices.utils.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nouhe
 */
public class TestBienController implements Initializable {

    @FXML
    private ComboBox idcategor;

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button vm;

    @FXML
    private TextField txtImage;

    @FXML
    private TextField idlib;
    @FXML
    private ComboBox idet;
    @FXML
    private TextArea iddesc;
    @FXML
    private Button image;
    @FXML
    private TableView<Bien> tablebiens;
    @FXML
    private TableColumn<Bien, Integer> idid;
    @FXML
    private TableColumn<Bien, String> idlibelle;
    @FXML
    private TableColumn<Bien, String> idetat;
    @FXML
    private TableColumn<Bien, String> idcatt;
   
    @FXML
    private TableColumn<Bien, String> iddescription;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp
    @FXML
    private TextField idt;
    BienServices ser = new BienServices();
    private FileInputStream fis;
    @FXML
    private Button idaccueil;
    /**
     * Initializes the controller class.
     */
    public void showBien() {
      
        BienServices s = new BienServices();
        ObservableList<Bien> data = FXCollections.observableArrayList(s.afficher());
        idlibelle.setCellValueFactory(new PropertyValueFactory<>("nom"));
        iddescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        idcatt.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tablebiens.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBien();
    }

    @FXML
    private void importerimage() throws IOException {

        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        pic = new Upload().upload(file, "\\images");
        txtImage.setText("File" + file);
        

    }

    @FXML
    private void remplircategor() {
        ObservableList<String> catgs = FXCollections.observableArrayList();

        try {
            Statement stmt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT categorie FROM categories");
            while (rs.next()) {
                String categoryName = rs.getString("categorie");
                catgs.add(categoryName);
            }
            idcategor.setItems(catgs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private int SelectionnerBien() {
        int selectedItem = tablebiens.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = tablebiens.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void modifierbien() throws FileNotFoundException, SQLException {
        int x = SelectionnerBien();
        String sql = "SELECT id, name, description, image, categorie FROM items, categories WHERE items.id_categorie = categories.idC";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int a = rs.getInt("id");
            String b = rs.getString("name");
            String c = rs.getString("description");
            String f = rs.getString("image");
            String h = rs.getString("categorie");
            if (x == a) {
                idlib.setText(b);
                iddesc.setText(c);
                txtImage.setText(f);
                idcategor.setValue(h);
            }
        }
    }

    @FXML
    private void supprimerbien(ActionEvent event) throws SQLException {
        int x = SelectionnerBien();
        if (tablebiens.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune ligne sélectionnée");
            alert.setContentText("Veuillez sélectionner le bien à supprimer.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer bien");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce bien ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ser.supprimer(x);
            tablebiens.getItems().clear();
            showBien();
        }
    }

    @FXML
    private void vmm() throws SQLException, FileNotFoundException {
        int x = SelectionnerBien();
        Bien b = new Bien();
        String categorie = idcategor.getSelectionModel().getSelectedItem().toString();
        int categorieId = 0;
        try {
            Statement stmt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idC FROM categories WHERE categorie = '" + categorie + "'");
            if (rs.next()) {
                categorieId = rs.getInt("idC");
            } else {
                System.out.println("La catégorie sélectionnée n'a pas été trouvée");
                return;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
        Bien selectedBien = tablebiens.getSelectionModel().getSelectedItem();
        if (selectedBien == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun bien sélectionné");
            alert.setContentText("Veuillez sélectionner un bien à modifier.");
            alert.showAndWait();
            return;
        }
        if (idlib.getText().isEmpty() || iddesc.getText().isEmpty() || idcategor.getSelectionModel().getSelectedItem().toString().isEmpty() || txtImage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Vous êtes sur le point de réaliser une modification");
        alert.setContentText("Êtes-vous sûr de vouloir modifier ce bien ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String Nom = this.idlib.getText();
            String Description = this.iddesc.getText();
            BienServices pcd = new BienServices();
            Bien bien = new Bien(Nom, Description, pic, categorieId);
            pcd.updateB(bien, pic, categorieId, x);

            System.out.println("Done");
            tablebiens.getItems().clear();
            showBien();
            tablebiens.getItems().clear();
            showBien();
        }
    }

    @FXML
    private void ajouterbien() throws SQLException, FileNotFoundException {
        //fis = new FileInputStream(file);
        //ObservableList<String> catgs = FXCollections.observableArrayList();
        if (idlib.getText().isEmpty() || iddesc.getText().isEmpty() || idcategor.getSelectionModel().getSelectedItem().toString().isEmpty() || txtImage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter un bien");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir ajouter ce bien ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String libelle = idlib.getText();
                String description = iddesc.getText();
                String categorie = idcategor.getSelectionModel().getSelectedItem().toString();
                int categorieId = 0;
                try {
                    Statement stmt = MyConnection.getInstance().getCnx().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT idC FROM categories WHERE categorie = '" + categorie + "'");

                    if (rs.next()) {
                        categorieId = rs.getInt("idC");
                    } else {
                        System.out.println("La catégorie sélectionnée n'a pas été trouvée");
                        return;
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
                BienServices pcd = new BienServices();
                Bien b = new Bien(libelle, description, pic, categorieId);
                pcd.ajouterBien(b, pic, categorie, categorieId);
                System.out.println("Done");
                tablebiens.getItems().clear();
                showBien();
            }
        }
    }
    @FXML
    void acceuil(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

}
    

