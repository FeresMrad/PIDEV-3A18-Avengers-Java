/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1;

import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Feriel
 */
public class SampleController implements Initializable {

    @FXML
    private Button sendButton;
    @FXML
    private TextField messageField;
    @FXML
    private ScrollPane sp_main;
    @FXML
    private VBox vbox_messages;
    private Server server;
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            server = new Server(new ServerSocket(1234));
             // Connect to database
        String url1 = "jdbc:mysql://localhost:3306/tbadel_db";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url1, user, password);
       } catch (IOException | SQLException ex) {
        ex.printStackTrace();
        System.out.println("error");
    }
        vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });
        server.reciveMessageFromClient(vbox_messages);
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 String messageToSend = messageField.getText();
            if (!messageToSend.isEmpty()) {
                try {
                    // Prepare the SQL statement to insert the chat message
                    String sql = "INSERT INTO chat1 (contenu, date_message) VALUES ( ?, NOW())";
                    PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setString(1, messageToSend);
                    

                    // Execute the SQL statement to insert the chat message
                    statement.executeUpdate();

                
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 5, 10));
                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);
                     textFlow.setStyle("-fx-color: rgb(239,242,255); -fx-background-color: rgb(15,125,242); -fx-background-radius: 20px;");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.945, 0.996));
                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);
                    server.sendMessageToClient(messageToSend);
                    messageField.clear();

                
                    // Close the statement
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }}
            }
        });
    }

 public static void addlabel(String messageFromClient, VBox vBox) {
    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_LEFT);
    Text text = new Text(messageFromClient);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle("-fx-background-color: rgb(233,233,235);"
                + " -fx-background-radius: 20px;");
    textFlow.setPadding(new Insets(5, 10, 5, 10));
    hbox.getChildren().add(textFlow);
    Platform.runLater(() -> vBox.getChildren().add(hbox));
}

}
