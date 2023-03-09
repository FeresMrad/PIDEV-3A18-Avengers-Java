/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.sql.Date;

/**
 *
 * @author Maissa
 */ 
public class Reclamation {

   int id;
    int user_id;
    String subject;
    String message;
    Date created_at;
   
    String status;
    
   public java.sql.Date getDate(java.lang.String columnName)  {
        Date Date = created_at;
    
     return  created_at ;

     }

    public Reclamation(int user_id, String message) {
        this.user_id = user_id;
        this.message = message;
    }
 
 public Reclamation() {
    }
    public Reclamation(int user_id, String subject, String message, String status,Date created_at) {
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.created_at = created_at;
    }

       public Reclamation(int user_id, String subject, String message, String status) {
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
        this.status = status;
    }
    
   

    public Reclamation(int id, int user_id, String subject, String message, String status,Date created_at) {
        this.id = id;
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
         this.status = status;
        this.created_at = created_at;
}
    
  
    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

   


   
   
   
   
   
   
   
   
    public String toString(){
    return "id: "+ id + " message:  " +message+ " userid:  " +user_id+ " Subject : "+subject+" created_at "+created_at+" Status de la réclamation: "+status;
    }
     }
    

    

   
  
    
        
    

    


    
    

