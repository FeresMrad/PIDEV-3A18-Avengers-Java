/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Evenements;
import entities.Participants;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Worker;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;
import utils.MyConnection;
import static gui.ProfilMembreController.idcli;


/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class EventController implements Initializable {

    @FXML
    private Label lblnom;
    @FXML
    private TextArea ldesc;
    @FXML
    private Label llieu;
    @FXML
    private Label ldatedeb;
    @FXML
    private Label ldatefin;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnparticiper;
    @FXML
    private Label lblike;
    @FXML
    private TextArea tfcomment;
    @FXML
    private Button btncomment;
    @FXML
    private JFXButton like;
    @FXML
    private JFXButton dislik;
    
    private int event;
    private Label labellike2;
    @FXML
    private Label lbdislike;
    @FXML
    private ScrollPane scrollcom;
    private AnchorPane anchorcom;
    @FXML
    private VBox vboxcom;
    @FXML
    private WebView map;

    public Label getLabellike2() {
        return labellike2;
    }

    public void setLabellike2(String message) {
        this.labellike2.getText();
    }

    public TextArea getTfcomment() {
        return tfcomment;
    }

    public void setTfcomment(String message) {
        this.tfcomment.getText();
    }

    public Label getLblike() {
        return lblike;
    }

    public void setLblike(String message) {
        this.lblike.getText();
    }

    
    
    
    
    public Label getLblnom() {
        return lblnom;
    }

    public TextArea getLdesc() {
        return ldesc;
    }

    public void setLdesc(String message) {
        this.ldesc.setText(message);
    }

    public Label getLlieu() {
        return llieu;
    }

    public void setLlieu(String message) {
        this.llieu.setText(message);
    }

    public Label getLdatedeb() {
        return ldatedeb;
    }

    public void setLdatedeb(String message) {
        this.ldatedeb.setText(message);
    }

    public Label getLdatefin() {
        return ldatefin;
    }

    public void setLdatefin(String message) {
        this.ldatefin.setText(message);
    }

    public void setLblnom(String message) {
        this.lblnom.setText(message);
    }

    public Label getLbdislike() {
        return lbdislike;
    }

    public void setLbdislike(Label lbdislike) {
        this.lbdislike = lbdislike;
    }
    
        
    
        
        
          public int getEvent() {
      
        return event;
    }

    public void setEvent(int event) {
    
        this.event = event; 
    }

    /**
     * Initializes the controller class.
     */
   
    
   private void displayComments() {

VBox vboxcom = new VBox();
vboxcom.setSpacing(0); // Add some spacing between comments
List<String> comments = showComments(getEvent()); 
for (String comment : comments) {
    TextArea commentArea = new TextArea(comment);
    commentArea.setEditable(false);
    commentArea.setWrapText(true);
    commentArea.setPrefWidth(600);
    commentArea.setPrefHeight(Region.USE_COMPUTED_SIZE);
    vboxcom.getChildren().add(commentArea);
}
scrollcom.setContent(vboxcom);

}
 
  
   public void initialize(URL url, ResourceBundle rb) { 
    Platform.runLater(() -> {
              map.getEngine().load("https://www.google.com/maps");
        map.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue == Worker.State.SUCCEEDED) {
        String location = llieu.getText();
        String jsCode = String.format("document.getElementById('searchboxinput').value='%s';"
                + "document.getElementById('searchbox-searchbutton').click();", location);
        map.getEngine().executeScript(jsCode);
    }
});
    try {
        String requete = "SELECT likee FROM evenements WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setInt(1, getEvent());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int likee = rs.getInt("likee");
            
          
           
            lblike.setText(Integer.toString(likee));
            
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    try {
        String requete = "SELECT dislikee FROM evenements WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setInt(1, getEvent());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int dislikee = rs.getInt("dislikee");
            
          
           
            lbdislike.setText(Integer.toString(dislikee));
            
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    

        displayComments();
    });
   
}


public List<String> showComments(int eventId) {
    List<String> comments = new ArrayList<>();
    try {
          String requete  = "SELECT r.commentaire, u.nom_user,prenom_user FROM reactionev r JOIN user u ON r.user_id = u.id_user WHERE r.evenement_id =?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
     
       
        ps.setInt(1, eventId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String comment = rs.getString("commentaire");
            String userName = rs.getString("nom_user");
            String userNamepre = rs.getString("prenom_user");
            String commentString = userName +" "+userNamepre+ ": " + comment;
            comments.add(commentString);
        }
    } catch (SQLException e) {
        System.out.println("Error executing SQL query: " + e.getMessage());
    }
    return comments;
}

    
    
  

    @FXML
    private void retour(ActionEvent event) {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("ParticiperE.fxml"));
    try {
        Parent root = loader.load();
        ParticiperEController dc = loader.getController();
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListeEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
  private void participier(ActionEvent event) {
    Evenements e = new Evenements(getEvent());
    
    ParticipantsCRUD pcd = new ParticipantsCRUD();
    List<Participants> participantsList = pcd.getAllParticipantsByEventId(e.getId());
    boolean isAlreadyParticipant = false;
    for (Participants p : participantsList) {
        if (p.getUser_id() == 577) { // Replace 577 with the actual ID of the user
            isAlreadyParticipant = true;
            break;
        }
    }
    
    if (isAlreadyParticipant) {
        // Show an alert indicating that the user is already a participant
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Déjà participant");
        alert.setHeaderText("Vous êtes déjà un participant à cet événement");
        alert.setContentText("Vous ne pouvez pas participer deux fois au même événement");
        alert.showAndWait();
    } else {
        // Show a confirmation dialog before adding the user as a participant
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de participation");
        alert.setHeaderText("Vous voulez participer à cet événement ");
        alert.setContentText("Êtes-vous sûr de vouloir participer à cet événement ?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Participants t = new Participants(e.getId(), 577); // Replace 577 with the actual ID of the user
            pcd.addEntityP(t);
            
            // Show a success alert after adding the user as a participant
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Participation réussie");
            successAlert.setContentText("Félicitations vous êtes maintenant un participant à cet Evenement");
            successAlert.showAndWait();
        }
    }
}


    @FXML
    private void commenter(ActionEvent event) {
       
            
          //  //metier bad word
        String filteredText = BadWord.filter(tfcomment.getText());
        if (!filteredText.equals(tfcomment.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire contient des mots interdits./n" + filteredText);
            alert.showAndWait();
            return;
        }
          EvenementsCRUD pcd = new EvenementsCRUD();
            Evenements t = new Evenements(getEvent(),filteredText);
            
            pcd.addComment(t,getEvent());
            displayComments();
            
            
    }
    

@FXML
private void liker(ActionEvent event) {
    EvenementsCRUD ec = new EvenementsCRUD();
    try {
        String requete = "SELECT likee,dislikee FROM evenements WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setInt(1, getEvent());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int likee = rs.getInt("likee");
            int dislikee = rs.getInt("dislikee");
            int newLike = likee + 1;
          
            ec.addLike(getEvent(), newLike);
            lblike.setText(Integer.toString(newLike));
            lbdislike.setText(Integer.toString(dislikee));
            like.setDisable(true);
            dislik.setDisable(true);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



    @FXML
    private void disliker(ActionEvent event) {
            EvenementsCRUD ec = new EvenementsCRUD();
    try {
        String requete = "SELECT likee,dislikee FROM evenements WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setInt(1, getEvent());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int dislikee = rs.getInt("dislikee");
            int likee = rs.getInt("likee");
            int newdisLike = dislikee + 1;
          
            ec.adddisLike(getEvent(), newdisLike);
            lbdislike.setText(Integer.toString(newdisLike));
            lblike.setText(Integer.toString(likee));
            dislik.setDisable(true);
             like.setDisable(true);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    private void showshow(ActionEvent event) {
             List<String> comments = showComments(getEvent()); // Replace with your own method to load comments
    int commentY = 0;
    for (String comment : comments) {
        TextArea commentArea = new TextArea(comment);
        commentArea.setEditable(false);
        commentArea.setWrapText(true);
        commentArea.setPrefWidth(600);
        commentArea.setPrefHeight(Region.USE_COMPUTED_SIZE);
        commentArea.setLayoutX(0);
        commentArea.setLayoutY(commentY);
        anchorcom.getChildren().add(commentArea);
        commentY += commentArea.getPrefHeight() + 10; // Add some spacing between comments
    }

    // Set the AnchorPane as the content of the ScrollPane
    scrollcom.setContent(anchorcom);
    }
    
}
