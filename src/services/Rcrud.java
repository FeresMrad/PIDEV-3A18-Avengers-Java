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
import java.sql.Timestamp;
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
               // r.setUser_id(rs.getInt("user_id"));
                r.setSubject(rs.getString("subject"));
                r.setMessage(rs.getString("message"));
                 r.setStatus(rs.getString("status"));
                 r.setDate(rs.getDate("created_at"));                  
                entitiesList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return entitiesList;
        
    }
    //badalt
   @Override
       public void ajouterReclamation(Reclamation t,String email) {
    try {
        Rcrud rec=new Rcrud();
        String requete = "INSERT INTO reclamations (user_id, subject, message, status, email) VALUES (?, ?, ?, ?,?)";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
//      ps.setInt(1, t.getUser_id());
  int user_id = rec.getuseridF(email);
       ps.setInt(1, user_id);
      
    
     // ps.setInt(2, t.getUser_id());
              //  ps.setString(1, t.getNom());
        ps.setString(2, t.getSubject());
        ps.setString(3, t.getMessage());
        ps.setString(4, t.getStatus());
         ps.setString(5, t.getEmail_user());
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
            String requete="INSERT INTO reclamations (user_id,subject,message,status,email_user) "
                + "VALUES(?,?,?,?,?)";
        
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, t.getUser_id());
              
             
             st.setString(2, t.getSubject());
             st.setString(3, t.getMessage());
             st.setString(4, t.getStatus());
             st.setString(5, t.getEmail_user());
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

//    @Override
//    public Reclamation FindReclamationByUserd(int user_id) {
//
//             Reclamation r = new Reclamation();
//        try {
//            String requete = "select * from reclamations WHERE user_id= "+user_id;
// PreparedStatement pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//       ResultSet rs=pst.executeQuery();
//       
//            while (rs.next()) {
//           
//                r.setId(rs.getInt("id"));
//                //r.setUser_id(rs.getInt("user_id"));
//                r.setSubject(rs.getString("subject"));
//                r.setMessage(rs.getString("message"));
//                r.setStatus(rs.getString("status"));
//                
//            
//                 }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       return r;
//
//    }
    
    public List<Reclamation>  recherche(int user_id  ) {
       List<Reclamation> reclamationList = new ArrayList<>();
         try {
             String requete = "select * from reclamations where user_id='"+user_id+"'";
              PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
             
             ResultSet rs=pst.executeQuery();
              while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
              //  r.setUser_id(rs.getInt("user_id"));
                r.setSubject(rs.getString("subject"));
                r.setMessage(rs.getString("message"));
               
                reclamationList.add(r);
            }
         } catch (SQLException ex) {
             Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
         }
               return reclamationList;
  }
      public List<Reclamation> searchReclamationByUserId(String userId) {
    List<Reclamation> reclamationList = new ArrayList<>();
    
    try {
        // Connectez-vous à votre base de données ici en utilisant JDBC ou un ORM
     
        
        // Préparez votre instruction SQL pour sélectionner les réclamations en fonction de "userid"
        String sql = "SELECT * FROM reclamation WHERE userid = ?";
       PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(sql);
        pst.setString(1, userId);
        
        // Exécutez votre requête et récupérez les résultats dans un ResultSet
        ResultSet resultSet = pst.executeQuery();
        
        // Itérez sur les résultats et créez des objets Reclamation pour chaque ligne
        while(resultSet.next()) {
            Reclamation r = new Reclamation();
        
            r.setId(resultSet.getInt("id"));
          //  r.setUser_id(resultSet.getInt("userid"));
            r.setSubject(resultSet.getString("subject"));
            r.setMessage(resultSet.getString("message"));
            r.setDate(resultSet.getDate("created_at"));
            reclamationList.add(r);
             System.out.println(reclamationList);
        }
        
        // Fermez les ressources JDBC
        resultSet.close();
        pst.close();
     
    } catch(SQLException ex) {
        ex.printStackTrace();
    }
    
    return reclamationList;
        
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
          String requete="UPDATE users SET etat=2 where user_id="+id;
          Statement st= MyConnection.getInstance().getCnx()
                  .createStatement();
          st.executeUpdate(requete);
      } catch (SQLException ex) {
          Logger.getLogger(Rcrud.class.getName()).log(Level.SEVERE, null, ex);
          
      }
          

}
    


    
    
public int getuseridF(String email){
    int val = 0;//as user_id
    try {
        String query = "SELECT u.id_user as user_id FROM user u  JOIN reclamations r ON u.email_user = r.email WHERE r.email  = ? ";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            val = rs.getInt("user_id"); //Modification pour récupérer l'alias user_id
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return val ;
}


    
//       public int getuseridF(String email ){
//       int val = -1;
//        try {
//            
//            String query = " SELECT u.id_user as user_id FROM user u  JOIN reclamations r ON u.email_user = r.email WHERE r.email  = ? ";
//            PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
//            st.setString(1, email );
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//              val =   rs.getInt("id_user");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return val;
//            
//        }
    
    
//    public int getUserid(String email_user) {
//    int k=0;
//        try {
//            System.out.println(email_user);
//        String query = "SELECT u.id as user_id FROM users u  JOIN reclamations r ON u.email  = r.email WHERE r.email  = ?";
//        PreparedStatement pstmt = new MyConnection().getCnx().prepareStatement(query);
//        pstmt.setString(1, email_user);
//       
//        ResultSet rs = pstmt.executeQuery();
//        
//        if (rs.next()) {
//           // System.out.println("mlmlml");
//           k= rs.getInt("user_id");
//           
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//        System.out.println(k);
//   return k;
//}

    
    
    
    
    
    
    @Override
    public int getNumberOfReclamation(int user_id) {

int b = 0;
    try {
          String requete="select count(*) from reclamations where user_id="+user_id;
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
    
    /**
     *
     * @param email
     * @return
     */
//    public int getUserid(String email) {
//  int k=0;
//    try {
//        
//        String query = "SELECT u.id_user as user_id FROM user u  JOIN reclamations r ON u.email_user = r.email WHERE r.email  = ?";
//        PreparedStatement pstmt = new MyConnection().getCnx().prepareStatement(query);
//        pstmt.setString(1, email);
//       
//        ResultSet rs = pstmt.executeQuery();
//        
//        if (rs.next()) {
//           k = rs.getInt("id_user");
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//        
//   return k ;
//}

    }