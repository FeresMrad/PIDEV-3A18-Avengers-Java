/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import entities.Bien;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import services.BienServices;
import upload.Upload;
import utils.MyConnection;

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
    private TableColumn<Bien, ImageView> imageg;
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
    private File file;
    String pic;
    @FXML
    private TableColumn<Bien, String> iddescription;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private ImageView imagebien;
    @FXML
    private TextField idt;
    BienServices ser = new BienServices();
    private FileInputStream fis;
    @FXML
    private Button idaccueil;

    @FXML
    private TableColumn<Bien, Image> qrCodeColumn;
    @FXML
    private Button idf;
    @FXML
    private ComboBox idfiltre;
    @FXML
    private Button idr;
    @FXML
    private Button idff;
    @FXML
    private Button idgraphes;
    private List<Bien> favorisList;
    public ObservableList<Bien> data = FXCollections.observableArrayList();
    @FXML
    private ListView<Bien> recommendedItemsListView;

    /**
     * Initializes the controller class.
     */
    public void showBien() {
    
        BienServices s = new BienServices();
        ObservableList<Bien> data = FXCollections.observableArrayList(s.afficher());
        idlibelle.setCellValueFactory(new PropertyValueFactory<>("nom"));

        imageg.setCellValueFactory(new PropertyValueFactory<>("image"));

        tablebiens.setItems(data);
        tablebiens.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tablebiens.getSelectionModel().isEmpty()) {
                // Récupérer l'objet Bien correspondant à la ligne sélectionnée
                Bien bienSelectionne = tablebiens.getSelectionModel().getSelectedItem();

                try {
                    // Charger la nouvelle interface FXML
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BienDetail.fxml"));
                    Parent root = loader.load();

                    // Passer l'objet Bien au contrôleur de la nouvelle interface
                    BienDetailController controller = loader.getController();
                    controller.setBien(bienSelectionne);

                    // Afficher la nouvelle interface dans une nouvelle fenêtre
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

public List<Bien> getFavorites(int userId) {
    List<Bien> favorites = new ArrayList<Bien>();
    try {
        String query = "SELECT i.id, i.name, i.description, i.image, i.id_categorie " +
                       "FROM items i JOIN favoris f ON i.id = f.id_item " +
                       "WHERE f.id_utilisateur = ?";
        PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(query);
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int itemId = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String image = rs.getString("image");
            int categoryId = rs.getInt("id_categorie");
            Bien item = new Bien(itemId, name, description, image, categoryId);
            favorites.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return favorites;
}
public Map<Integer, Integer> countCategoryOccurrences(List<Bien> items) {
    Map<Integer, Integer> categoryCounts = new HashMap<Integer, Integer>();
    for (Bien item : items) {
        int categoryId = item.getId_categ();
        if (categoryCounts.containsKey(categoryId)) {
            int count = categoryCounts.get(categoryId);
            categoryCounts.put(categoryId, count + 1);
        } else {
            categoryCounts.put(categoryId, 1);
        }
    }
    return categoryCounts;
}
public int findMostFrequentCategory(Map<Integer, Integer> categoryCounts) {
    int mostFrequentCategory = -1;
    int maxCount = 0;
    for (Map.Entry<Integer, Integer> entry : categoryCounts.entrySet()) {
        int categoryId = entry.getKey();
        int count = entry.getValue();
        if (count > maxCount) {
            mostFrequentCategory = categoryId;
            maxCount = count;
        }
    }
    return mostFrequentCategory;
}
public List<Bien> getRecommendedItems(int userId) {
    List<Bien> recommendedItems = new ArrayList<Bien>();
    try {
        List<Bien> favorites = getFavorites(userId);
        Map<Integer, Integer> categoryCounts = countCategoryOccurrences(favorites);
        int mostFrequentCategoryId = findMostFrequentCategory(categoryCounts);

        String query = "SELECT id, name, description, image, id_categorie " +
                "FROM items " +
                "WHERE id_categorie = ? " +
                "AND id NOT IN (SELECT id_item FROM favoris WHERE id_utilisateur = ?) ";

        PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(query);
        pstmt.setInt(1, mostFrequentCategoryId);
        pstmt.setInt(2, userId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int itemId = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String image = rs.getString("image");
            int itemCategoryId = rs.getInt("id_categorie");
            Bien item = new Bien(itemId, name, description, image, itemCategoryId);
            recommendedItems.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return recommendedItems;
}







private ListChangeListener<Bien> favorisListener;


  @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBien();
        Button addToFavoritesButton = new Button("Ajouter aux favoris");
        addToFavoritesButton.setOnAction(event -> {
            // Récupérer l'ID de l'élément sélectionné par l'utilisateur
            int itemId = tablebiens.getSelectionModel().getSelectedItem().getId();

            // Utiliser un ID statique pour l'utilisateur en attendant l'implémentation du système d'authentification
            int userId = 2;

            // Insérer l'ID de l'élément et l'ID de l'utilisateur dans la table "favoris" de votre base de données
            try {
                String requete = "INSERT INTO favoris (id_item, id_utilisateur) VALUES (?, ?)";
                PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
                stmt.setInt(1, itemId);
                stmt.setInt(2, userId);
                stmt.executeUpdate();
                System.out.println("L'élément a été ajouté aux favoris.");
            } catch (SQLException ex) {
                System.err.println("Une erreur s'est produite lors de l'ajout de l'élément aux favoris : " + ex.getMessage());
            }
        });

  


// Configurer la colonne pour afficher les images
   
 
        TableColumn<Bien, Void> favorisCol = new TableColumn<>("Favoris");
        Callback<TableColumn<Bien, Void>, TableCell<Bien, Void>> cellFactory = new Callback<TableColumn<Bien, Void>, TableCell<Bien, Void>>() {
            @Override
            public TableCell<Bien, Void> call(final TableColumn<Bien, Void> param) {
                final TableCell<Bien, Void> cell = new TableCell<Bien, Void>() {

                    private final Button favorisButton = new Button("Ajouter aux favoris");

                    {
                        favorisButton.setOnAction((ActionEvent event) -> {
                            // Récupérer le Bien associé à la ligne sélectionnée
                            Bien bien = getTableView().getItems().get(getIndex());

                            // Récupérer l'ID du Bien associé à la ligne sélectionnée
                            int itemId = bien.getId();

                            // Utiliser un ID statique pour l'utilisateur en attendant l'implémentation du système d'authentification
                            int userId = 2;

                            // Insérer l'ID de l'élément et l'ID de l'utilisateur dans la table "favoris" de votre base de données
                            try {
                                String requete = "INSERT INTO favoris (id_item, id_utilisateur) VALUES (?, ?)";
                                PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
                                stmt.setInt(1, itemId);
                                stmt.setInt(2, userId);
                                stmt.executeUpdate();
                                System.out.println("L'élément a été ajouté aux favoris.");
                            } catch (SQLException ex) {
                                System.err.println("Une erreur s'est produite lors de l'ajout de l'élément aux favoris : " + ex.getMessage());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(favorisButton);
                        }
                    }
                };
                return cell;
            }
        };
        favorisCol.setCellFactory(cellFactory);
        tablebiens.getColumns().add(favorisCol);

        BienServices s = new BienServices();
        ObservableList<Bien> data = FXCollections.observableArrayList(s.afficher());
        tablebiens.setItems(data);
        favorisList = new ArrayList<Bien>();

      int loggedInUserId = 2;
      List<Bien> favorisList = getFavorites(loggedInUserId);
      Map<Integer, Integer> categoryCounts = countCategoryOccurrences(favorisList);
      int mostFrequentCategory = findMostFrequentCategory(categoryCounts);
//List<Bien> recommendedItems = getRecommendedItems(mostFrequentCategory, loggedInUserId);
      List<Bien> recommendedItems = getRecommendedItems(loggedInUserId);


// Retirer les biens déjà présents dans la liste des favoris de l'utilisateur connecté
      recommendedItems.removeIf(favorisList::contains);

// Ajouter les biens recommandés à la liste d'affichage
      recommendedItemsListView.getItems().addAll(recommendedItems);

// Créer un écouteur sur la liste de favoris de l'utilisateur
      recommendedItemsListView.setCellFactory(new Callback<ListView<Bien>, ListCell<Bien>>() {
          @Override
          public ListCell<Bien> call(ListView<Bien> param) {
              return new ListCell<Bien>() {
                  private Label nameLabel = new Label();

            @Override
            protected void updateItem(Bien item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    nameLabel.setText(item.getNom());
                    setGraphic(nameLabel);
                    setOnMouseClicked(event -> {
                        // Récupérer l'index de la ligne dans la TableView contenant le bien sélectionné
                        int index = tablebiens.getItems().indexOf(item);
                        // Sélectionner la ligne correspondante dans la TableView
                        tablebiens.getSelectionModel().select(index);
                        tablebiens.scrollTo(index);
                    });
                }
            }
        };
    }
});
recommendedItemsListView.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) {
        String selectedItemDescription = recommendedItemsListView.getSelectionModel().getSelectedItem().getDescription();
        for (Bien item : tablebiens.getItems()) {
            if (item.getDescription().equals(selectedItemDescription)) {
                int index = tablebiens.getItems().indexOf(item);
                tablebiens.getSelectionModel().select(index);
                Platform.runLater(() -> {
                    tablebiens.scrollTo(index);
                    tablebiens.getSelectionModel().select(index);
                });
                break;
            }
        }
    }
});
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
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imagebien.setImage(image);
        } else {
            System.out.println("No file selected.");
        }

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

    @FXML
    private void remplir() {
        ObservableList<String> catgs = FXCollections.observableArrayList();

        try {
            Statement stmt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT categorie FROM categories");
            while (rs.next()) {
                String categoryName = rs.getString("categorie");
                catgs.add(categoryName);
            }
            idfiltre.setItems(catgs);
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
            String image = this.txtImage.getText();
            File file = new File(image);
            Image img = new Image(file.toURI().toString());
            imagebien.setImage(img);
            BienServices pcd = new BienServices();
            Bien bien = new Bien(Nom, Description, pic, categorieId);
            pcd.updateB(bien, image, categorieId, x);
            System.out.println("Done");
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

    @FXML
    private void filtrer() {
        String categorie = (String) idfiltre.getSelectionModel().getSelectedItem();
        if (categorie != null) {

            Predicate<Bien> filterPredicate = bien -> bien.getCategorie().equals(categorie);

            tablebiens.setItems(tablebiens.getItems().filtered(filterPredicate));

        }

    }

    @FXML
    private void reinitialiserFiltre(MouseEvent event) {
        idfiltre.getSelectionModel().clearSelection();

        // Afficher toutes les données dans la TableView
        showBien();

    }

    @FXML
    void favoris(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("FAvoris.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    void graphes(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("Graphes.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

}
