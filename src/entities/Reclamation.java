/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.sql.Date;
import org.bouncycastle.asn1.tsp.TimeStampReq;


/**
 *
 * @author Maissa
 */ 

public class Reclamation {

long timestamp = System.currentTimeMillis(); // get the current timestamp
Date date = new Date(timestamp); // initialize a Date object with the timestamp
//System.out.println(date); // prints the date and time in the default format
   int id;
     int user_id;
    String subject;
    String message;
       String status;  
       String email_user;
   // Date created_at;
   
 public Reclamation() {
    }

    

    /**
     *
     * @param user_id
     * @param subject
     * @param message
     * @param status
     * @param email_user
     */

   
  
    public Reclamation(int user_id, String subject, String message, String status, String email_user) {
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.email_user = email_user;
    }

    public Reclamation(int user_id, String subject, String message, String status) {
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
        this.status = status;
    }
 
    public Reclamation(int user_id, String message) {
        this.email_user = email_user;
        this.message = message;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }
    /**
     *
     */
//   
//    public Reclamation(String user_id, String subject, String message, String status,Date date) {
//       
//        this.subject = subject;
//        this.message = message;
//        this.status = status;
//         this.email_user = email_user;
//        this.date=date;
////this.created_at = created_at;
//    }
//    public Reclamation(String user_id,String subject, String message, String status) {
//        this.nom = nom;
//        this.subject = subject;
//        this.message = message;
//        this.status = status;
//    }

   

//    public Reclamation(int id, String user_id, String subject, String message, String status) {
//        this.id = id;
//        this.nom = nom;
//        this.subject = subject;
//        this.message = message;
//        this.status = status;
//    }

//    public Reclamation(String nom, String subject, String message, String status, int user_id) {
//        this.nom = nom;
//        this.subject = subject;
//        this.message = message;
//        this.status = status;
//        this.user_id = user_id;
//    }
    
  
    
    public int getId() {
        return id;
    }

  

 

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

//    public Date getCreated_at() {
//        return created_at;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public void setId(int id) {
        this.id = id;
    }

  

    public int getUser_id() {
        return user_id;
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

//    public void setCreated_at(Date created_at) {
//        this.created_at = created_at;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    public String toString(){
    return "id: "+ id + " message:  " +message+ " userid:  " +user_id+ " Subject : "+subject+" created_at "+date+" Status de la réclamation: "+status;
    }

//    public void setDate(Date date) {
//        java.util.Date utilDate = new java.util.Date();
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//
//
//
//           DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//           Date d = new Date(System.currentTimeMillis());
//    }
     }