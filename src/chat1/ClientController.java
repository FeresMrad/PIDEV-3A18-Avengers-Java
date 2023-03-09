/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * FXML Controller class
 *
 * @author Feriel
 */
public class ClientController implements Initializable {

    @FXML
    private Button sendButton;
    @FXML
    private TextField messageField;
    @FXML
    private ScrollPane sp_main;
    @FXML
    private VBox vbox_messages;
    private Client client;
    private Connection connection;
    private DataInputStream inputStream; // Define inputStream as an instance variable
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            // TODO
            client = new Client(new Socket("localhost", 1234));
            // Connect to database
            String url1 = "jdbc:mysql://localhost:3306/tbadel_db";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url1, user, password);
            
            // Set the value of inputStream
           /* inputStream = new DataInputStream(client.getSocket().getInputStream());*/
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
        client.reciveMessageFromServer(vbox_messages);
        
        sendButton.setOnAction((event) -> {
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
                    client.sendMessageToServer(messageToSend);
                    messageField.clear();
                    // Close the statement
                    statement.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
    }
 /*   public void reciveMessageFromServer(VBox vBox) {
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    // Receive the chat message from the server
                    String messageFromServer = inputStream.readUTF();

                    // Convert the message to speech
                    OkHttpClient client = new OkHttpClient();
                    MediaType mediaType = MediaType.parse("application/json");
                    String value = "{\r\n    \"text\": \"" + messageFromServer + "\"\r\n}";
                    RequestBody body = RequestBody.create(mediaType, value);
                    Request request = new Request.Builder()
                            .url("https://large-text-to-speech.p.rapidapi.com/tts")
                            .post(body)
                            .addHeader("content-type", "application/json")
                            .addHeader("X-RapidAPI-Key", "72a1860d84msh15024c99fb7e206p11200bjsncc83812f32eb")
                            .addHeader("X-RapidAPI-Host", "large-text-to-speech.p.rapidapi.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    byte[] soundBytes = response.body().bytes();

                    // Play the speech sound
                    InputStream soundStream = new ByteArrayInputStream(soundBytes);
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundStream);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    // Add the chat message to the UI
                    addlabel(messageFromServer, vBox);
                }
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }
    });
    thread.start();
}*/


    public static void addlabel(String messageFromServer, VBox vBox) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,235);"
                + " -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hbox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hbox);
            }
        });

    }
    //api emoji
    
    
    

}
