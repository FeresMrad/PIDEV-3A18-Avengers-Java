/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Maissa
 */
public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage)  {
          try{
        //
        
      Parent root = FXMLLoader.load(getClass().getResource("Reclamation.fxml")); 
         // Parent root = FXMLLoader.load(getClass().getResource("UpRec.fxml"));
   //    Parent root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
         // Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml")); 
            // Parent root = FXMLLoader.load(getClass().getResource("Notification.fxml"));
        
       Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Reclamation ");
        
        primaryStage.show();
    } 
        catch (IOException ex){
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    
         
    }
    








}
    

