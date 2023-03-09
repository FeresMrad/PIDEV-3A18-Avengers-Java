/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat2;

import chat.ChatServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
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
public class ChatServerController implements Initializable {

    private Socket socket;
    private Scanner reader;
    private PrintWriter writer;
    private ServerSocket serverSocket = null;

    @FXML
    private Button jButtonSend;
    @FXML
    private TextArea jTextAreaMessage;
    @FXML
    private TextArea jTextAreaChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            // TODO add your handling code here:
            serverSocket = new ServerSocket(4789);
            socket = serverSocket.accept();
            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream());
            Thread myThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String message = reader.nextLine();
                        jTextAreaChat.appendText("Client:" + message + "\n");
                    }
                }
            });
           myThread.start();
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
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
        jTextAreaChat.appendText("Server: " + message + "\n");
        // Effacement du champ de saisie
        jTextAreaMessage.setText("");
    }

}