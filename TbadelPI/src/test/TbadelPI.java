/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Chat;
import entities.Feedback;
import java.util.Date;
import services.ChatCRUD;
import services.FeedbackCRUD;


/**
 *
 * @author Feriel
 */
public class TbadelPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       FeedbackCRUD fcd = new FeedbackCRUD();
        // Test Add methode 
          Feedback f1 = new Feedback(1, 4, "Great transaction",new java.sql.Date(new Date().getTime()));
          
         // fcd.add(f1);
        //update
       // Feedback f1 = new Feedback(5,1, 4, "Great transaction !",new java.sql.Date(new Date().getTime()));
       // fcd.update(f1);
        //delete
        //fcd.delete(5);
        //read
        System.out.println(fcd.getAll());
         System.out.println("***************Chat**********************");
        Chat c=new Chat();
        //add
        Chat c1= new Chat(1, "bacem", "hazem", "hi",new Date());
        Chat c2= new Chat( "hazem", "bacem", "hello",new Date());
        ChatCRUD ch=new ChatCRUD();
        ch.delete(4);
        
         
    }
    
}
