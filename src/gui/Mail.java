/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * 
 */
import entities.Reclamation;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mail {

    public Mail() {
    }

    public static void sendEmail(String recipient, String subject, String body) throws MessagingException {

        // Set the properties of the mail server
        Properties props = new Properties();
       props.put("mail.smtp.ssl.trust", "smtp.outlook.com");
      props.put("mail.smtp.host", "smtp.outlook.com");
        props.put("mail.smtp.port", "587");//587
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

        // Create a new message
        Message message = new MimeMessage(session);

        // Set the recipient, subject, and body of the message
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        // Send the message
        
Transport.send(message, "fatenbouzaffara@gmail.com", "Meyssouna21");    }

    public static void main(String[] args) {
        try {
            sendEmail("bacem.bensoltana@esprit.tn", "Réclamation ", "Bonjour cher clien,\n Je vous informe que votre réclamation est bien prise en considération. \n Je vous remerci pour votre confiance");
                         
            System.out.println("Email to customer sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}