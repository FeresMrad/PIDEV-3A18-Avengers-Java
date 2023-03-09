/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import entities.Jetons;
import entities.Transaction;
import services.JetonsCRUD;
import services.TransactionCRUD;

/**
 *
 * @author feres
 */
public class MainClass {
    
        public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
    
    //Transaction t1= new Transaction(5,5,"cv2");
    //TransactionCRUD pcd = new TransactionCRUD();
    
    Jetons j1 = new Jetons();
    JetonsCRUD jcd = new JetonsCRUD();
    //Add
    //pcd.addEntity(t1);
    
    //Delete
    //pcd.delEntity(4);
    
    //Update 
    /*t1.setJetons_prop(3);
    t1.setJetons_dem(2);
    t1.setCommentaire("miseaj");
    t1.setId(3);
    pcd.updEntity(t1*/
    
    //Afichage
    System.out.println(jcd.entitiesList());
    }
        
    
} 
