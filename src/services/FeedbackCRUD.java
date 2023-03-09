/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import entities.Feedback;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import interfaces.EntityCRUDF;

/**
 *
 * @author Feriel
 */
public class FeedbackCRUD implements EntityCRUDF<Feedback> {

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
                String rating = rs.getString("rating");
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

            String query = "INSERT INTO feedback (transaction_id, rating, comment, date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);

            if (f.getRating() == null || f.getComment() == null || f.getComment().trim().isEmpty()) {
                System.out.println("Invalid comment. Please provide a non-empty string.");
                return;
            }
            statement.setInt(1, f.getTransactionId());
            statement.setString(2, f.getRating());
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
            statement.setString(2, f.getRating());
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
        // Vérifier si la chaîne est vide ou nulle

        // Vérifier si la chaîne ne contient que des lettres
        if (chaine.matches("[a-zA-Z ]+") || chaine.trim().isEmpty()) {
            return false;
        }

        // La chaîne est valide si elle passe toutes les vérifications
        return true;
    }
//jointure
   public String getName(int transactionId) {
    try {
        String query = "SELECT username FROM users u JOIN transactions t ON u.id = t.to_user_id JOIN feedback f ON t.id = f.transaction_id WHERE t.id = ?";
        PreparedStatement pstmt = new MyConnection().getCnx().prepareStatement(query);
        pstmt.setInt(1, transactionId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getString("username");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
}
