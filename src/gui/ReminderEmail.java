/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author BACEM
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

public class ReminderEmail {

   

    public static void sendReminder(String recipient, String subject, String body) throws MessagingException {

        // Set the properties of the mail server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Set the authentication information
        final String username = "pidev2050@outlook.fr";
        final String password = "aze123..";
        
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
        Transport.send(message);
    }

//    public static void main(String[] args) {
//        try {
//            sendReminder("mohamedferes.mrad@esprit.tn", 
//                          "Reminder:Evenement prévu", 
//                          "Dear Participant,\n\nThis is a friendly reminder that you have an Event in 3 days  at 10AM.\n\nBest regards");
//            System.out.println("Reminder email sent successfully.");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
    public void sendReminderToParticipants(int eventId) throws MessagingException, SQLException {
    // Set the properties of the mail server
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.outlook.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Set the authentication information
//    final String username = "pidev2050@outlook.fr";
//    final String password = "aze123..";

    final String username = "pidev2023@outlook.fr";
    final String password = "azerty123";

    // Create a new session with the authentication information
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    // Get the email addresses of all participants of the event
  
  String query = "SELECT email FROM users WHERE id IN (SELECT user_id FROM participants WHERE evenement_id = ?)";
    PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
    statement.setInt(1, eventId);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
        String recipient = resultSet.getString("email");
        String subject = "Reminder: Evenement prévu:";
        String body = "Dear Participant,\n\nThis is a friendly reminder that you have an Event in 3 days at 10AM.\n\nBest regards";

        // Create a new message
        Message message = new MimeMessage(session);

        // Set the recipient, subject, and body of the message
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        // Send the message
        Transport.send(message);
    }

    System.out.println("Reminder emails sent successfully to all participants of the event.");
}
  public static void main(String[] args) {
    try {
        ReminderEmail reminderEmail = new ReminderEmail();
        reminderEmail.sendReminderToParticipants(4);
    } catch (MessagingException | SQLException e) {
        e.printStackTrace();
    }
}
}