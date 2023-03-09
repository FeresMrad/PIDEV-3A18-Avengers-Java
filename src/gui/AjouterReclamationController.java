/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import entities.Reclamation;
import java.awt.Color;
import static java.awt.PageAttributes.MediaType.A4;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import services.Rcrud;
import org.controlsfx.control.Notifications;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
//import org.apache.pdfbox.io.RandomAccessFileInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.print.DocFlavor.BYTE_ARRAY.PDF;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFTextStripper;
import utils.MyConnection;
//Mail
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;


/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextArea tfdesc;
    @FXML
    private TextField tfsujet;
    @FXML
    private TextField tfstatus;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnpdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //private void savePanier(ActionEvent event) {

    public TextField getTfnom() {
        return tfnom;
    }

    public TextArea getTfdesc() {
        return tfdesc;
    }

    public TextField getTfsujet() {
        return tfsujet;
    }

    public TextField getTfstatus() {
        return tfstatus;
    }

    public Button getBtnadd() {
        return btnadd;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    public void setTfdesc(TextArea tfdesc) {
        this.tfdesc = tfdesc;
    }

    public void setTfsujet(TextField tfsujet) {
        this.tfsujet = tfsujet;
    }

    public void setTfstatus(TextField tfstatus) {
        this.tfstatus = tfstatus;
    }

    public void setBtnadd(Button btnadd) {
        this.btnadd = btnadd;
    }
        
        


  

    @FXML
    private void ajouterReclamation(ActionEvent event) {     
    if (tfnom.getText().isEmpty() || tfsujet.getText().isEmpty() || tfdesc.getText().isEmpty()) {
        Notifications notificationBuilder = Notifications.create()
                .title("ERREUR")
                .text("Veuillez remplir les champs")
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);

        notificationBuilder.show();
    } else {
        Rcrud rec = new Rcrud();

        int nom = Integer.parseInt(tfnom.getText());
        String desc = tfdesc.getText();
        String suj = tfsujet.getText();
        String sub = tfstatus.getText();

        Reclamation r = new Reclamation(nom, sub, desc, sub);
        Rcrud pc = new Rcrud();

        pc.ajouterReclamation(r);

      /* try {
            sendEmail("benhammedmaissa3@gmail.com", "Nouvelle réclamation ajoutée", "Votre réclamation est bien prise en compte.");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.show();
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/
    }
    
    
   //  FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
   // FXMLLoader loader= new FXMLLoader(getClass().getResource("Details"));
//        try {
//            Parent root = loader.load(); // preparer les acteurs de la 2éme scene
//            ReclamationController controller = loader.getController();
//            controller.setTfnom(tfnom);
//    controller.setTfdescrip(tfdesc);
//    controller.setTfsubject(tfsujet);
//    controller.setTfstatus(tfstatus);
//
//            tfnom.getScene().setRoot(root);// scene loula naawadha b scene thenya eli heya l details
//            
//        } catch (IOException ex ) {
//            
//            System.out.println(ex.getMessage());
//        }
        
  
    
    }


            
            
            
            


public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.ssl.trust", "smtp.outlook.com");
    props.put("mail.smtp.host", "smtp.outlook.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

    // Set the authentication information
    final String username = "benhammedmaissa@outlook.com";
    final String password = "Meyssouna21";

    // Create a new session with the authentication information
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
//
//    // Create a new message
    Message message = new MimeMessage(session);

    // Set the recipient, subject, and body of the message
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
    message.setSubject(subject);
    message.setText(body);

//    // Send the message
    Transport.send(message);}
}  
//redirection
//     FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
//           try {
//            Parent root = loader.load(); // preparer les acteurs de la 2éme scene
//            ReclamationController dc = loader.getController();
//           dc.setTfnom(tfnom);
//          dc.setTfdescrip(tfdesc);
//          dc.setTfsubject(tfsujet);
//          dc.setTfstatus(tfstatus);
//           
//            tfnom.getScene().setRoot(root);// scene loula naawadha b scene thenya eli heya l details
//            
//        } catch (IOException ex ) {
//            
//            System.out.println(ex.getMessage());

        
        
        
//if (tfnom.getText().isEmpty() || tfsujet.getText().isEmpty() || tfdesc.getText().isEmpty()) {
//    Notifications notificationBuilder = Notifications.create()
//            .title("ERREUR")
//            .text("Veuillez remplir les champs")
//            .hideAfter(Duration.seconds(5))
//            .position(Pos.TOP_RIGHT);
//
//    notificationBuilder.show();
//} else {
 
  /*  Rcrud rec = new Rcrud();
    
    //SAUVEGARDE BASE DE DONNEES
  //   String resNom = tfnom.getText();
    int nom =  Integer.parseInt(tfnom.getText());
          String desc = tfdesc.getText();
          String suj = tfsujet.getText();
          String sub = tfstatus.getText();
        System.out.println(nom);
        Reclamation r = new Reclamation(nom, sub, desc, sub);
       // Reclamation r = new Reclamation(tfnom, sub, desc, sub);
       Rcrud pc = new Rcrud();
       
    pc.ajouterReclamation(r);
        
      
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      //  sendMail();
    
   
                alert.setTitle("Success");
              }
   */
            
                
                
                
                

    

    
    
  
    
        
        

 
 



         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         

   





    
    
    
    
    
    

