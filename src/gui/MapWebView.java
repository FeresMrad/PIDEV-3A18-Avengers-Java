/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import javafx.concurrent.Worker;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MapWebView extends Application {
    
    private static final String API_KEY = "AIzaSyBrlxgEchQyNe0_JsXpf3W1SBGKU0i4fEc";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MapV.fxml"));
        AnchorPane root = loader.load();
        MapVController controller = loader.getController();
        WebView webView = controller.getWebv();
        WebEngine engine = webView.getEngine();
        engine.load(getClass().getResource("maps.html").toExternalForm() + "?key=" + API_KEY);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}




//  AIzaSyBrlxgEchQyNe0_JsXpf3W1SBGKU0i4fEc