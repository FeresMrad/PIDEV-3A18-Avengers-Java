/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.MyConnection;
import interfaces.EntityCRUD;
import entities.Feedback;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Feriel
 */
public class FeedbackCRUD implements EntityCRUD<Feedback> {

    @Override
    public Feedback getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Feedback> getAll() {
        List<Feedback> feedbackList = new ArrayList<>();

        try {
            String query = "SELECT * FROM feedback";
            Statement stmt = new MyConnection().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int transactionId = rs.getInt("transaction_id");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                Feedback feedback = new Feedback(transactionId, rating, comment, date);
                feedback.setId(id);

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

        


@Override
public void add(Feedback f) {
   

  
    
    try {
      
        String query ="INSERT INTO feedback (transaction_id, rating, comment, date) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
           if (f.getRating() < 1 || f.getRating() > 10) {
        System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
        return;
    }

    if (f.getComment() == null || f.getComment().trim().isEmpty()) {
        System.out.println("Invalid comment. Please provide a non-empty string.");
        return;
    }
        statement.setInt(1, f.getTransactionId());
        statement.setInt(2, f.getRating());
        statement.setString(3, f.getComment());
        statement.setDate(4, new java.sql.Date(f.getDate().getTime()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Feedback added successfully!");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public void update(Feedback f) {
        try {
            String query = "UPDATE feedback SET transaction_id=?, rating=?, comment=?, date=? WHERE id=?";

            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, f.getTransactionId());
            statement.setInt(2, f.getRating());
            statement.setString(3, f.getComment());
            statement.setDate(4, new java.sql.Date(f.getDate().getTime()));
            statement.setInt(5, f.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Feedback object with id " + f.getId() + " does not exist in the database.");
            } else {
                System.out.println("Feedback object with id " + f.getId() + " was successfully updated in the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating feedback object: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM feedback WHERE id=?";
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Feedback deleted successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public static boolean estChaineValide(String chaine) {
    // V??rifier si la cha??ne est vide ou nulle
   
    // V??rifier si la cha??ne ne contient que des lettres
    if (chaine.matches("[a-zA-Z ]+") || chaine.trim().isEmpty()) {
        return false;
    }
    
    // La cha??ne est valide si elle passe toutes les v??rifications
    return true;
}
  

}
