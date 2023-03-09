/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Feriel
 */
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


public class Notification extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Feedback envoyé avec succès avec un commentaire: ")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(15))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(event -> System.out.println("Clicked on notification"));

        notificationBuilder.darkStyle();
       

        notificationBuilder.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

