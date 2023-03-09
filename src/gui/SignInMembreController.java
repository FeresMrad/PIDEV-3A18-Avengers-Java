/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class SignInMembreController implements Initializable {

    @FXML
    private Button login_membre_btn;
    @FXML
    private PasswordField mdp_membre;
    @FXML
    private TextField email_membre;
    @FXML
    private Button switch_signup_membre_btn;
    
    
    Connection cn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private  void action_login_membre(ActionEvent event ) {
//        String sql = "Select * from user where email_user = ? and mdp_user =? and role='Client'  ";
//        cn = MyConnection.getInstance().getCnx();
//        try {
//            String newMD = "";
//          String mdp = Hash();
//            pst = cn.prepareStatement(sql);
//            pst.setString(1, email_membre.getText());
//            pst.setString(2, mdp +  mdp_membre.getText());
//            
//
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                ProfilMembreController.idcli = rs.getInt(1);
//                System.out.println(ProfilMembreController.idcli);
//                JOptionPane.showMessageDialog(null, "E-mail and Password are Correct");
//                root = FXMLLoader.load(getClass().getResource("../views/InterfaceMembre.fxml"));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//               
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalide E-mail Or Password");
//                
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
     String sql = "Select * from user where email_user = ? and mdp_user =?   ";
        cn = MyConnection.getInstance().getCnx();
        try {
            String newMD = "";
          String mdp = Hash();
            pst = cn.prepareStatement(sql);
            pst.setString(1, email_membre.getText());
            pst.setString(2, mdp +  mdp_membre.getText());
            

            rs = pst.executeQuery();
            if (rs.next()) {
                ProfilMembreController.idcli = rs.getInt(1);
                int idSessions = ProfilMembreController.idcli = rs.getInt(1);
//                System.out.println(ProfilMembreController.idcli);
System.out.println(idSessions);
                JOptionPane.showMessageDialog(null, "E-mail and Password is Correct");
                root = FXMLLoader.load(getClass().getResource("../gui/InterfaceMembre.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
               
            } else {
                JOptionPane.showMessageDialog(null, "Invalide E-mail Or Password");
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String Hash() throws Exception {

        String mdp_user = "";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp_user.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    @FXML
    private void switch_signup_membre(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SingUpMembre.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void passReset(MouseEvent event) {
        
    
    }

    @FXML
    private void action_forgot_pass(ActionEvent event) {
        Stage Stage1 ;
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("RestPass.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
        Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
        Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
    });
});
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
         
    }


}
