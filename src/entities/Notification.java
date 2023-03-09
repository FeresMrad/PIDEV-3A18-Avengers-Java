/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Maissa
 */
public class Notification {
   /**
     *
     */
    public  enum Type {
        MESSAGE("message"), TRANSACTION("transaction"), TOKEN("token");
        private String Type;
        private Type (String Type) {
            this.Type = Type;
        }}

         int id;
         int user_id;
         String message; 
         String Type ;

   
    

    public Notification(int id, int user_id, String message) {
        this.id = id;
        this.user_id = user_id;
        this.message = message;
    }

    public Notification(int user_id, String message, String Type) {
        this.user_id = user_id;
        this.message = message;
        this.Type = Type;
    }

    public Notification(int id, int user_id, String message, String Type) {
        this.id = id;
        this.user_id = user_id;
        this.message = message;
        this.Type = Type;
    }

    public Notification(String Type) {
        this.Type = Type;
    }
    

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getMessage() {
        return message;
    }

    public Notification() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     public String getType() {
        return Type;
    }
      

    public void setType(String Type) {
        this.Type = Type;
    }
  
   public String toString(){
    return "id: "+ id + "message: " +message+ "userid: " +user_id;
    }   
     
     
     
     
     
     
     
}
