/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Notification;
import entities.Reclamation;
import interfaces.NoCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author Maissa
 */
public class Ncrud implements NoCRUD<Notification>{

     @Override
    public List<Notification> NotificationList() {
     
        List<Notification> NotificationList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM notifications";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Notification n = new Notification();
                n.setId(rs.getInt("id"));
                n.setUser_id(rs.getInt("user_id"));
                n.setMessage(rs.getString("message"));
                NotificationList.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return NotificationList;

    }

    @Override
    public void ajouternotification(Notification n) {

        
        try {
            String requete = "INSERT INTO notifications (user_id,message,type)"
                    + "VALUES ('"+n.getUser_id()+"','"
                    +n.getMessage()+"','"+n.getType()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("notification ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void suppNotification( int id) {
          
      
        try {
            String requete = "DELETE FROM notifications where id=?";
         PreparedStatement pst = MyConnection.getInstance().getCnx()  .prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Notification supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }            
    }

    @Override
    public void upnotification(Notification n, String type) {
         try {
              
            String sql = "UPDATE notifications SET type = 'transaction' WHERE type = 'token'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(sql);
             
            
           
                pst.executeUpdate();
            
         
            System.out.println("Reclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  }

   
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
  
    
    
        
        
    

  
          
     
    

  
    
    
   
