/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.MyConnection;
import entities.Chat;
import interfaces.EntityCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Feriel
 */
public class ChatCRUD  implements EntityCRUD<Chat>{

  

    @Override
    public List<Chat> getAll() {
     List<Chat> conv = new ArrayList<>();
        try {
            String query = "SELECT * FROM chats";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Chat message = new Chat();

                message.setId(rs.getInt("id"));
                message.setSender(rs.getString("sender"));
                message.setReceiver(rs.getString("receiver"));
                message.setMessage(rs.getString("message"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                conv.add(message);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conv;    }
    public static boolean estChaineValide(String chaine) {
    // Vérifier si la chaîne est vide ou nulle
   
    // Vérifier si la chaîne ne contient que des lettres
    if (!chaine.matches("[a-zA-Z ]+") || chaine.trim().isEmpty()) {
        return false;
    }
    
    // La chaîne est valide si elle passe toutes les vérifications
    return true;
}
  public boolean isStringLength(String str) {
    return str.length() < 100;
}

    @Override
    public void add(Chat entity) {
try {
            String query = "INSERT INTO chats (sender, receiver, message, timestamp) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
entity.setMessage(entity.getMessage().trim());
           
         if (estChaineValide(entity.getMessage())&&isStringLength(entity.getMessage())) {
            statement.setString(1, entity.getSender());
            statement.setString(2, entity.getReceiver());
            statement.setString(3, entity.getMessage());
            statement.setTimestamp(4, new Timestamp(entity.getTimestamp().getTime()));
         }
         else{System.out.println("error");}
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New message added successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void update(Chat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
   try {
            String query = "DELETE FROM chats WHERE id=?";
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Message deleted successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public Chat getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
