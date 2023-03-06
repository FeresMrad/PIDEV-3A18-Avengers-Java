/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class LoginViewController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private TextField loginEmail;
    @FXML
    private PasswordField loginPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
  private void showRegisterStage(MouseEvent event) {
    // Get the current Scene and Stage
    Scene currentScene = ((Node) event.getSource()).getScene();
    Stage currentStage = (Stage) currentScene.getWindow();

    // Load the RegisterView FXML file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterView.fxml"));
    Parent registerView;
    try {
        registerView = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    // Set the RegisterView as the new root node of the current Scene
    currentScene.setRoot(registerView);

    // Adjust the size of the Stage to fit the new Scene
    currentStage.sizeToScene();
}

    @FXML
private void LoginMember(ActionEvent event) throws IOException {
    String email = loginEmail.getText();
    String password = loginPassword.getText();

    // Create a new stage for the main application window
    Stage primaryStage = new Stage();

    // Attempt to login the user and redirect to the appropriate view
    ServiceUser userService = new ServiceUser();
    User user = userService.login(email, password);
    if (user != null) {
        try {
            FXMLLoader loader = new FXMLLoader();
            if (user.getRole().equals("admin")) {
                loader.setLocation(getClass().getResource("AdminView.fxml"));
                Parent adminViewParent = loader.load();
                Scene adminViewScene = new Scene(adminViewParent);
                AdminViewController adminViewController = loader.getController();
                adminViewController.setUser(user);
                primaryStage.setScene(adminViewScene);
                primaryStage.show();
            } else {
                loader.setLocation(getClass().getResource("ClientView.fxml"));
                Parent clientViewParent = loader.load();
                Scene clientViewScene = new Scene(clientViewParent);
                ClientViewController clientViewController = loader.getController();
                clientViewController.setUser(user);
                primaryStage.setScene(clientViewScene);
                primaryStage.show();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    } else {
        // Show an error message if the login failed
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Invalid email or password.");
        alert.showAndWait();
    }
}

    
}
