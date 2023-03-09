/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyConnection;
import entities.Evenements;
import static gui.ProfilMembreController.idcli;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import interfaces.EventCRUDB;


/**
 *
 * @author BACEM
 */
public class EvenementsCRUD implements EventCRUDB<Evenements>{

//    @Override
//    public void addEntity2(Evenements t) {
//        try {
//            String requete = "INSERT INTO evenements (id,nom,description,date_debut,date_fin,lieu)" + "VALUES ('"+t.getNom()+"','"+t.getDescription()+"','"+t.getDate_debut()+"','"+t.getDate_fin()+"','"+t.getLieu()+"')";
//            
//            Statement st=MyConnection.getInstance().getCnx().createStatement();
//            
//            st.executeUpdate(requete);
//            System.out.println("Evenement ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//
//    }
    
    
           @Override
           public void addEntity(Evenements t) {
        try {
            String requete="INSERT INTO evenements (id,nom,description,date_debut,date_fin,lieu) "
                + "VALUES(?,?,?,?,?,?)";
        
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
             st.setInt(1, t.getId());
             st.setString(2, t.getNom());
             st.setString(3, t.getDescription());
             st.setDate(4, new java.sql.Date(t.getDate_debut().getTime() ));
             st.setDate(5,  new java.sql.Date(t.getDate_fin().getTime()));
             st.setString(6, t.getLieu());
             st.executeUpdate();
             System.out.println("Evenement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
//           public void addComment(Evenements t,int x) {
//         try {
//        // Insert the new comment
////        String insertQuery = "INSERT INTO evenements (comment) VALUES (?)";
////        PreparedStatement insertStatement = MyConnection.getInstance().getCnx().prepareStatement(insertQuery);
////        insertStatement.setString(1, t.getComment());
////        insertStatement.executeUpdate();
//
//        // Update the existing row with the corresponding ID
//        String updateQuery = "UPDATE evenements SET comment = ? WHERE id = ?";
//        PreparedStatement updateStatement = MyConnection.getInstance().getCnx().prepareStatement(updateQuery);
//        
//        updateStatement.setString(1, t.getComment());
//        updateStatement.setInt(2, t.getId());
//        updateStatement.executeUpdate();
//
//        System.out.println("Commentaire ajouté");
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    }
           public void addComment(Evenements t,int x) {
    try {
//        String Query = "UPDATE evenements SET comment = CONCAT(comment,'/', ?) WHERE id = ?";
 
//        PreparedStatement updateStatement = MyConnection.getInstance().getCnx().prepareStatement(Query);
//        
//        updateStatement.setString(1, " " + t.getComment()); // Add a space before the new comment
//        updateStatement.setInt(2, t.getId());
//        updateStatement.executeUpdate();

        
             String Query = "INSERT INTO reactionev (commentaire,evenement_id,user_id) "
                + "VALUES(?,?,?) ";
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(Query);
          st.setString(1, " " + t.getComment()); // 
        st.setInt(2, t.getId());
        st.setInt(3, idcli);
        st.executeUpdate();

             
             
        System.out.println("Commentaire ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

           
           
           
           public void addLike(int evenement_id, int newLike) {
    try {
        String req = "UPDATE evenements SET likee=? WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
        ps.setInt(1, newLike);
        ps.setInt(2, evenement_id);
        ps.executeUpdate();
        System.out.println("like Added");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


                   public void adddisLike(int evenement_id, int newdisLike) {
    try {
        String req = "UPDATE evenements SET dislikee=? WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
        ps.setInt(1, newdisLike);
        ps.setInt(2, evenement_id);
        ps.executeUpdate();
        System.out.println("dislike Added");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
           
           
           
           @Override
    public List<Evenements> entitiesList() {
      ArrayList<Evenements> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM evenements";
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while (rs.next()){
                Evenements p = new Evenements();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setDate_debut(rs.getDate("date_debut"));
                p.setDate_fin(rs.getDate("date_fin"));
                p.setLieu(rs.getString("lieu"));
                myList.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return myList;
    }
    
    
    
    public Evenements findById(int id) {
    String query = "SELECT * FROM evenements WHERE id=?";
    PreparedStatement statement;
    ResultSet resultSet;
    Evenements evenement = null;

    try {
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setInt(1, id);
        resultSet = st.executeQuery();

        if (resultSet.next()) {
            evenement = new Evenements(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("description"),
                resultSet.getDate("date_debut"),
                resultSet.getDate("date_fin"),
                resultSet.getString("lieu")
            );
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération de l'événement : " + e.getMessage());
    }

    return evenement;
}
    
    
    
    
    
    
    
       @Override
    public void delEvenements(int t) {
       
        try {
            String requete = "DELETE FROM evenements where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t);
            int nbModif = pst.executeUpdate();
            if (nbModif > 0) {
                System.out.println("Evenement supprimé"); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
    
    
            @Override
    public void updEvenements(Evenements t,int x) {
       
        try {
            String requete = "UPDATE evenements SET nom=? , description=? , date_debut=? , date_fin=? ,  lieu=?  WHERE id=?"+x;
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setDate(3, new java.sql.Date(t.getDate_debut().getTime()));
            pst.setDate(4, new java.sql.Date(t.getDate_fin().getTime()));
            pst.setString(5, t.getLieu());
            pst.setInt(6, t.getId());
            
          
           
            int nbModif = pst.executeUpdate();
            if (nbModif > 0) {
            System.out.println("Evenement modifiée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
}
