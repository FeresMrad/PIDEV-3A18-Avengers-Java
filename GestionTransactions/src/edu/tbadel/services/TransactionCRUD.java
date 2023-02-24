/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tbadel.services;

import edu.tbadel.entities.Transaction;
import edu.tbadel.interfaces.EntityCRUD;
import edu.tbadel.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author feres
 */
public class TransactionCRUD implements EntityCRUD<Transaction> {

    @Override
    public void addEntity(Transaction t) {
        try {
            String query = "INSERT INTO transaction(from_item_nom, from_image, offre_jetons, commentaire_trans) VALUES (?,?,?,?)";
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);

            statement.setString(1, t.getFrom_item_nom());
            statement.setString(2, t.getFrom_image());
            statement.setInt(3, t.getOffre_jetons());
            statement.setString(4, t.getCommentaire_trans());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("transaction added successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Transaction> entitiesList() {
        ArrayList<Transaction> TransactionList = new ArrayList();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = new MyConnection().getCnx().createStatement();
            //Statement statement = new MyConnection.getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Transaction f = new Transaction();
                f.setFrom_item_nom(rs.getString("from_item_nom"));
                f.setFrom_image(rs.getString("from_image"));
                f.setOffre_jetons(rs.getInt(3));
                f.setCommentaire_trans(rs.getString("commentaire_trans"));

                TransactionList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return TransactionList;
    }
    

    @Override
     public void updateEntity( Transaction t) {
    try {
       

        // create the update query
        String query = "UPDATE transaction SET  from_item_nom=?, from_image=?, offre_jetons=?, commentaire_trans=? WHERE id=?";

        // create the prepared statement with the query
        PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);

        // set the parameters for the update query
    
        statement.setString(1, t.getFrom_item_nom());
        statement.setString(2, t.getFrom_image());
        statement.setInt(3, t.getOffre_jetons());
        statement.setString(4, t.getCommentaire_trans());
        statement.setInt(5, t.getId());

        // execute the update query
        int rowsUpdated = statement.executeUpdate();


        if (rowsUpdated == 0) {
            System.out.println("transaction object with id " + t.getId() + " does not exist in the database.");
        } else {
            System.out.println("transaction object with id " + t.getId() + " was successfully updated in the database.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating transaction object: " + e.getMessage());
    }
}
//    @Override
//    public void deleteEntity(Transaction t) {
//        try {
//            String query = "DELETE FROM transaction WHERE id=?";
//            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
//            statement.setInt(1, t.getId());
//            int rowsDeleted = statement.executeUpdate();
//            if (rowsDeleted > 0) {
//                System.out.println("Transaction with ID " + t.getId() + " deleted successfully!");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    @Override
//    public void deleteEntity(Transaction t) {
//        try {
//            int id = t.getId(); //get the id
//            String query = "DELETE FROM transaction WHERE id=?";
//            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
//            statement.setInt(1, id);
//            int rowsDeleted = statement.executeUpdate();
//            if (rowsDeleted > 0) {
//                System.out.println("Transaction with ID " + id + " deleted successfully!");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//@Override
//    public void deleteEntity(Transaction t) {
//        try {
//            String query = "DELETE FROM transaction WHERE id=?";
//            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
//            statement.setInt(1, t.getId());
//            int rowsDeleted = statement.executeUpdate();
//            if (rowsDeleted > 0) {
//                System.out.println("Transaction with ID " + t.getId() + " deleted successfully!");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            Logger.getLogger(TransactionCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


   
//    public void deleteEntity(int t) {
//
//        try {
//            String requete = "DELETE FROM evenements where id=?";
//            PreparedStatement pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//            pst.setInt(1, t);
//            int nbModif = pst.executeUpdate();
//            if (nbModif > 0) {
//                System.out.println("Evenement supprimé"); 
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
         public void deleteEntity(int id) {
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
}
