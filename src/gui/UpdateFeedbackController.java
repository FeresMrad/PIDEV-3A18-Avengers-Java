/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Feedback;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

/**
 * FXML Controller class
 *
 * @author Feriel
 */
public class UpdateFeedbackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Feedback feedback;
    @FXML
    private TextField ratingTextField;
    @FXML
    private TextArea commentTextArea;
 @FXML
    private Rating ratingStars;
    @FXML
    private Label msg;
    @FXML

    private void updateFeedback(ActionEvent event) {
        String rating = msg.getText();
        String comment = commentTextArea.getText();

        // Show confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update this feedback?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            FeedbackCRUD feedbackCRUD = new FeedbackCRUD();
            feedback.setRating(rating);
            feedback.setComment(comment);
            feedbackCRUD.update(feedback);

            // Show message that feedback has been updated
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Feedback Updated");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Feedback has been successfully updated!");
            successAlert.showAndWait();
            // Close the window after successful update
            if (successAlert.getResult() == ButtonType.OK) {
                mod.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));

            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackaddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
} 

    public Rating getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(Rating ratingStars) {
        this.ratingStars = ratingStars;
    }

    public Label getMsg() {
        return msg;
    }

    public void setMsg(Label msg) {
        this.msg = msg;
    }







    public TextField getRatingTextField() {
        return ratingTextField;
    }

    public void setRatingTextField(TextField ratingTextField) {
        this.ratingTextField = ratingTextField;
    }

    public TextArea getCommentTextArea() {
        return commentTextArea;
    }

    public void setCommentTextArea(TextArea commentTextArea) {
        this.commentTextArea = commentTextArea;
    }

    public Button getMod() {
        return mod;
    }

    public void setMod(Button mod) {
        this.mod = mod;
    }
    @FXML
    private Button mod;

    // Declare a public method to set the feedback
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
        msg.setText(feedback.getRating());
        commentTextArea.setText(feedback.getComment());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ratingStars.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                msg.setText(newValue + " Stars");
            }
        });
    }

}
