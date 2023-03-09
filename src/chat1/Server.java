/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.VBox;

/**
 *
 * @author Feriel
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            System.out.println("error");
            ex.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessageToClient(String messageToClient) {
        try {
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void reciveMessageFromClient(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    String messageFromClient;
                    try {
                        messageFromClient = bufferedReader.readLine();
                        SampleController.addlabel(messageFromClient, vBox);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("error");
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                     }

                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

        try {
            if (bufferedReader != null) {

                bufferedReader.close();
            }
            if (bufferedWriter != null) {

                bufferedWriter.close();
            }
            if (socket != null) {

                socket.close();
            }

        } catch (IOException ex) {
            System.out.println("error");
            ex.printStackTrace();
        }
    }

}
