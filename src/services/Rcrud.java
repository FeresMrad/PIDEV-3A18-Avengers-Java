/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import interfaces.EntityCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Maissa
 */
public class Rcrud implements EntityCRUD<Reclamation> {
   @Override
    public List<Reclamation> entitiesList() {
        List<Reclamation> entitiesList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamations";
            Statement st = MyConnection.getInstance().getCnx()   .createStatement();
                 
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setUser_id(rs.getInt("user_id"));
                r.setSubject(rs.getString("subject"));
                r.setMessage(rs.getString("message"));
                 r.setStatus(rs.getString("status"));
                //r.setDate( new java.sql.Date(r.getCreated_at().getTime()));
                entitiesList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return entitiesList;
        
    }
    @Override
    public void ajouterReclamation(Reclamation t) {
    try {
        String requete = "INSERT INTO reclamations (user_id, subject, message, status) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setInt(1, t.getUser_id());
        ps.setString(2, t.getSubject());
        ps.setString(3, t.getMessage());
        ps.setString(4, t.getStatus());
       //ps.setString(5, t.getCreated_at());
        ps.executeUpdate();
        System.out.println("Reclamation ajoutée");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }}
        
   
      
    @Override
    public void suppReclamation(int id) {
     
        try {
            String requete = "DELETE FROM reclamations where id=?";
         PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);  pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Réclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }            
    }
    @Override
    public void addEntity(Reclamation t) {
           
try {
            String requete="INSERT INTO reclamations (user_id,subject,message,status,created_at) "
                + "VALUES(?,?,?,?,?)";
        
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
              st.setInt(1, t.getUser_id());
             st.setString(2, t.getSubject());
             st.setString(3, t.getMessage());
             st.setString(4, t.getStatus());
            // st.setDate(5,  new java.sql.Date(t.getCreated_at().getTime()));
           
             st.executeUpdate();
             System.out.println("Réclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
     
    
    
    }

    @Override
    public void updReclamation(Reclamation t, int x) {
         try {
              
            String requete = "UPDATE reclamations SET subject=?,message=?,status=? WHERE id="+x;
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
             
          //  pst.setInt(1, t.getUser_id());
            pst.setString(1, t.getSubject());
            pst.setString(2, t.getMessage());
            pst.setString(3, t.getStatus());
           // pst.setDate(5, new java.sql.Date(t.getCreated_at().getTime()));
           // pst.setDate(5, new java.sql.Date(0, 0, 0));
            pst.executeUpdate();
            System.out.println("Reclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  }

  
//    public Reclamation getById(int id) throws SQLException{
//    
//   
//              
//            String requete = "UPDATE reclamations SET subject=?,message=?,status=? WHERE id="+x;
//            PreparedStatement pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//             
//          //  pst.setInt(1, t.getUser_id());
//            pst.setString(1, t.getSubject());
//            pst.setString(2, t.getMessage());
//            pst.setString(3, t.getStatus());
//           // pst.setDate(5, new java.sql.Date(t.getCreated_at().getTime()));
//           // pst.setDate(5, new java.sql.Date(0, 0, 0));
//            pst.executeUpdate();
//            System.out.println("Reclamation modifiée");
//       
//    return pst ; 
//    }

    @Override
    public Reclamation FindReclamationById(int id) {

             Reclamation r = new Reclamation();
        try {
            String requete = "select * from reclamations WHERE id= "+id;
 PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
       ResultSet rs=pst.executeQuery();
       
            while (rs.next()) {
           
                r.setId(rs.getInt("id"));
                r.setUser_id(rs.getInt("user_id"));
                r.setSubject(rs.getString("subject"));
                r.setMessage(rs.getString("message"));
                r.setStatus(rs.getString("status"));
                
            
                 }

        } catch (SQLException ex) {
            Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
        }
       return r;

    }
    
// 
//   @Override
//    public int getNumberOfReclamation(int id){
//    int b = 0;
//    try {
//          String requete="select count(*) from reclamations where id="+id;
//          Statement st= MyConnection.getInstance().getCnx()
//                  .createStatement();
//          ResultSet rs = st.executeQuery(requete);
//           while(rs.next()){
//          b=rs.getInt(1);
//           }
//           return b;
//          
//      } catch (SQLException ex) {
//          Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
//      }
//    System.out.println(b);
//    return b; }
     
    /**
     *
     * @param id
     */
    public void UserBanner(int id){
      try {
          String requete="UPDATE users SET etat=2 where id_utilisateur="+id;
          Statement st= MyConnection.getInstance().getCnx()
                  .createStatement();
          st.executeUpdate(requete);
      } catch (SQLException ex) {
          Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
          
      }
          

}

    @Override
    public int getNumberOfReclamation(int id) {

int b = 0;
    try {
          String requete="select count(*) from reclamations where id="+id;
          Statement st= MyConnection.getInstance().getCnx()
                  .createStatement();
          ResultSet rs = st.executeQuery(requete);
           while(rs.next()){
          b=rs.getInt(1);
           }
           return b;
          
      } catch (SQLException ex) {
          Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
      }
    System.out.println(b);
    return b; }


    }

    
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

        

    
    
    
    




    
    
    
            
    

 
    


