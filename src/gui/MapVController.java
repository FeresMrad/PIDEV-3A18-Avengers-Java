/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class MapVController implements Initializable {

    @FXML
    private WebView webv;

    public WebView getWebv() {
        return webv;
    }

    public void setWebv(WebView webv) {
        this.webv = webv;
    }

    private static final String API_KEY = "AIzaSyBrlxgEchQyNe0_JsXpf3W1SBGKU0i4fEc";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine engine = webv.getEngine();
        engine.load(getClass().getResource("maps.html").toExternalForm() + "?key=" + API_KEY);

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                String initJS = "function initMap() {\n" +
                        "  var myLatLng = {lat: 37.7749, lng: -122.4194};\n" +
                        "  var map = new google.maps.Map(document.getElementById('map'), {\n" +
                        "    zoom: 12,\n" +
                        "    center: myLatLng\n" +
                        "  });\n" +
                        "  var marker = new google.maps.Marker({\n" +
                        "    position: myLatLng,\n" +
                        "    map: map,\n" +
                        "    title: 'San Francisco'\n" +
                        "  });\n" +
                        "}";
                engine.executeScript(initJS);
                String showMapJS = "document.getElementById('map').style.display = 'block';";
                engine.executeScript(showMapJS);
            }
        });
    }
}

    

