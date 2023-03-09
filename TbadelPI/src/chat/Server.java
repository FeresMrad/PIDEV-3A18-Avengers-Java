/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Feriel
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5678);
        Socket socket = serverSocket.accept();
        Scanner fromConsole =new Scanner(System.in);
        Scanner fromClient = new Scanner(socket.getInputStream());
        PrintWriter fromServer = new PrintWriter(socket.getOutputStream());
        String inputFromServer, inputFromConsole;
        while (true) {
            inputFromServer = fromClient.nextLine();
            System.out.println("Client" + inputFromServer);
             if (inputFromServer.equals("exit*")) {
                    break;
                }
            System.out.println("Server: ");
            inputFromConsole=fromConsole.nextLine();
            
            fromServer.println(inputFromConsole);
            fromServer.flush();
            
            if (inputFromConsole.equals("exit*")) {
                    break;
                }
        }
        socket.close();
    }
}
