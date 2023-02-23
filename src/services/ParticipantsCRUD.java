/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Participants;
import interfaces.CRUDP;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
/**
 *
 * @author BACEM
 */
public class ParticipantsCRUD implements CRUDP<Participants> {

    @Override
    public void addEntityP(Participants t) {
        try {
            String requete="INSERT INTO participants (id,evenement_id,user_id) "
                + "VALUES(?,?,?)";
        
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
             st.setInt(1, t.getId());
             st.setInt(2, t.getEvenement_id());
             st.setInt(3, t.getUser_id());
  
             int nbModif=st.executeUpdate();
            
            if (nbModif > 0) {
                System.out.println("Félicitations!!");
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Participants> entitiesListP() {
             ArrayList<Participants> myList = new ArrayList();
             
        try {
            String requete = "SELECT * FROM participants";
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while (rs.next()){
                Participants p = new Participants();
                p.setId(rs.getInt(1));
                p.setEvenements_id(rs.getInt("evenement_id"));
                p.setUser_id(rs.getInt("user_id"));
   
       
              myList.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return myList;
    }
    
//       @Override
//    public List<Participants> entitiesListP(int x) {
//             ArrayList<Participants> myList = new ArrayList();
//             
//        try {
//            String requete = "SELECT * FROM participants WHERE evenement_id="+x;
//            Statement st=MyConnection.getInstance().getCnx().createStatement();
//            ResultSet rs= st.executeQuery(requete);
//            
//            while (rs.next()){
//                Participants p = new Participants();
//                p.setId(rs.getInt(1));
//                p.setEvenements_id(rs.getInt("evenement_id"));
//                p.setUser_id(rs.getInt("user_id"));
//   
//       
//              myList.add(p);
//            }
//            
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    return myList;
//    }
        public List<Participants> entitiesListP(int x) {
        List<Participants> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM participants WHERE evenement_id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, x);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Participants p = new Participants();
                p.setId(rs.getInt(1));
                p.setEvenements_id(rs.getInt("evenement_id"));
                p.setUser_id(rs.getInt("user_id"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }


    
    
    @Override
    public void delParticipants(int t) {
            try {
            String requete = "DELETE FROM participants where evenement_id=?";
//            String requete = "DELETE FROM participants where evenement_id=? AND user_id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t);
            int nbModif = pst.executeUpdate();
            if (nbModif <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Participation supprimée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }




    
}
