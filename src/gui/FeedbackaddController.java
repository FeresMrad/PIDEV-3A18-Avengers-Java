/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.api.GoogleAPIException;
//import com.google.api.gax.rpc.ApiException;
import entities.Feedback;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.FeedbackCRUD;
import org.controlsfx.control.Notifications;
import javafx.geometry.Pos;
import javafx.util.Duration;
import com.google.api.translate.Language;
//import com.google.cloud.translate.Translate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import javafx.scene.control.ComboBox;
//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;
//import com.google.cloud.translate.v3.LocationName;
//import com.google.cloud.translate.v3.TranslateTextRequest;
//import com.google.cloud.translate.v3.TranslateTextResponse;
//import com.google.cloud.translate.v3.TranslationServiceClient;
import gui.Translator;

/**
 * FXML Controller class
 *
 * @author Feriel
 */

public class FeedbackaddController implements Initializable {

    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button btn;
    @FXML
    private Rating ratingStars;
    @FXML
    private Label msg;
    @FXML
    private Label username;
   // private int transactionId;
    private String api="f346669e7560d0c49c5533beed0caeb408884353";

    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Button translateButton;
    //String comment = commentTextArea.getText();
    // String rating = msg.getText();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ratingStars.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                msg.setText(newValue + " Stars");
            }
        });
        
        FeedbackCRUD pcd = new FeedbackCRUD();
         String name = pcd.getName(1);
        username.setText(name);
    
       /* //language
        ObservableList<String> languageList = FXCollections.observableArrayList("en","fr");
        languageComboBox.setItems(languageList);
        //Feedback f= new Feedback();
        /*FeedbackCRUD fcdd = new FeedbackCRUD();
         String name = fcdd.getName(); // replace 1 with the transaction ID of the current user
        username.setText(name);*/
        // Get the username of the user associated with the transaction
       // String username = fcd.getName(transactionId);*/
    }


    
    //Ajouter feedback
    @FXML
    private void handleAddButtonAction(ActionEvent event) {

        boolean feedbackAddedSuccessfully = false;
        //  //metier bad word
        String filteredText = BadwordMetier.filter(commentTextArea.getText());
        if (!filteredText.equals(commentTextArea.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire contient des mots interdits./n" + filteredText);
            alert.showAndWait();
            return;
        }
 
        //controle de saisie
        if (msg.getText().isEmpty() || commentTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
            return;
        }
        String rating = msg.getText();

        FeedbackCRUD fcd = new FeedbackCRUD();
        Feedback f = new Feedback(1, rating, filteredText, new java.sql.Date(new Date().getTime()));

        //Alert de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous ajouter ce feedback?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            fcd.add(f);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Feedback ajouté avec succès.");
            successAlert.showAndWait();
            feedbackAddedSuccessfully = true;
            
            // call showNotification() function if feedback is successfully added
            if (feedbackAddedSuccessfully) {
                showNotification();
            }

        }
        //redirection
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        try {
            Parent root = loader.load();
            btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FeedbackaddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // function to show notification

    public TextArea getCommentTextArea() {
        return commentTextArea;
    }

    public void setCommentTextArea(TextArea commentTextArea) {
        this.commentTextArea = commentTextArea;
    }

    private void showNotification() {

        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Feedback envoyé avec succès avec un commentaire: " + commentTextArea.getText() + " est rating: " + msg.getText())
                .graphic(null)
                .hideAfter(Duration.seconds(20))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(event -> System.out.println("Clicked on notification"));

        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

/*public void translateComment(String targetLang) {
    String text = commentTextArea.getText();

    try {
      Translator.translate("en", targetLang, text);

      // Show the translated text in the commentTextArea
      commentTextArea.setText(Translator.());

      // Show an alert with the original and translated comments
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Translated Comment");
      alert.setHeaderText("Original Comment: " + text);
      alert.setContentText("Translated Comment: " + Translator.getTranslatedText());
      alert.showAndWait();

    } catch (Exception e) {
      e.printStackTrace();
      // Show an error alert
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Translation Error");
      alert.setContentText("An error occurred while translating the comment.");
      alert.showAndWait();
    }
  }*/
   @FXML
    void translateComment(ActionEvent event) {
          String sourceLanguage = "en";
        String targetLanguage = "fr";
        String text = commentTextArea.getText();
        String apiKey = "72a1860d84msh15024c99fb7e206p11200bjsncc83812f32eb";

        RequestBody body = new FormBody.Builder()
                .add("source_language", sourceLanguage)
                .add("target_language", targetLanguage)
                .add("text", text)
                .build();

        Request request = new Request.Builder()
                .url("https://text-translator2.p.rapidapi.com/translate")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Translation Result");
            alert.setContentText(responseBody);
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
           
        }

}}
