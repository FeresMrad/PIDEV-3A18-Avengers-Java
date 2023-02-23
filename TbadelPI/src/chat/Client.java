/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Feriel
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5678);
            Scanner fromConsole = new Scanner(System.in);
            Scanner fromServer = new Scanner(socket.getInputStream());
            PrintWriter fromClient = new PrintWriter(socket.getOutputStream(), true);
            String input, output;
            while (true) {
                System.out.println("Client :");
                input = fromConsole.nextLine();

                fromClient.println(input);
                if (input.equals("exit*")) {
                    break;
                }
                System.out.println("Server: ");
                output = fromServer.nextLine();
                System.out.println(output);
                if (output.equals("exit*")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
