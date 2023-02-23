/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Feriel
 */
public class ChatClientController implements Initializable {

   private Socket socket;
private Scanner reader;
private PrintWriter writer;

@FXML
private Button jButtonSend;

@FXML
private TextArea jTextAreaMessage;

@FXML
private TextArea jTextAreaChat;

@Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO
    try {
        // Connexion au serveur
        socket = new Socket("localhost", 4789);
        // Initialisation du scanner pour lire les messages du serveur
        reader = new Scanner(socket.getInputStream());
        // Initialisation du PrintWriter pour envoyer des messages au serveur
        writer = new PrintWriter(socket.getOutputStream());
        // Création d'un thread pour écouter les messages du serveur
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String message = reader.nextLine();
                    // Ajout du message dans la zone de chat
                    jTextAreaChat.appendText("Server: " + message + "\n");
                }
            }
        });
        myThread.start();
    } catch (IOException ex) {
        Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

@FXML
private void jButtonSendActionPerformed(ActionEvent event) {
    // Récupération du message
    String message = jTextAreaMessage.getText();
    // Envoi du message au serveur
    writer.println(message);
    writer.flush();
    // Ajout du message dans la zone de chat
    jTextAreaChat.appendText("Client: " + message + "\n");
    // Effacement du champ de saisie
    jTextAreaMessage.setText("");
}

}
