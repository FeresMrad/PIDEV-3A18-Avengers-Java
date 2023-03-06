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
public class RegisterViewController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button registerButton;
    @FXML
    private TextField phone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void register(ActionEvent event) {
        String prenom = firstName.getText();
        String nom = lastName.getText();
        String mail = email.getText();
        String tel = phone.getText();
        String mdp = password.getText();
        String rmdp = confirmPassword.getText();
       ServiceUser us;
        us = new ServiceUser() ;
         if(us.verifierEmailBd( mail)==false){
             if (mdp.equals(rmdp)) {
                 User x = new User( nom, prenom, 0, mail, mdp, mdp) ;
                 us.ajouter(x);
                 JOptionPane.showMessageDialog(null, "Votre compte est crée avec succés");
             }
             else {
                 JOptionPane.showMessageDialog(null, "verifer votre mdp");
                  }
         }
             else {
                     JOptionPane.showMessageDialog(null, "vous aver un compte ");
                     }
             
             }
                 
         
    

    @FXML
private void showLoginStage(MouseEvent event) {
    // Get the current Scene and Stage
    Scene currentScene = ((Node) event.getSource()).getScene();
    Stage currentStage = (Stage) currentScene.getWindow();

    // Load the LoginView FXML file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
    Parent loginView;
    try {
        loginView = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    // Set the LoginView as the new root node of the current Scene
    currentScene.setRoot(loginView);

    // Adjust the size of the Stage to fit the new Scene
    currentStage.sizeToScene();
}
    
}
