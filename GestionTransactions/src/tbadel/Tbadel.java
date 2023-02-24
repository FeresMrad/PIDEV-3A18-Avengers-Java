/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tbadel;

import edu.tbadel.entities.Transaction;
import edu.tbadel.services.TransactionCRUD;

/**
 *
 * @author feres
 */
public class Tbadel {

    public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
    
    Transaction t1= new Transaction("Voiture","image",5,"commentairehehe");
    TransactionCRUD pcd = new TransactionCRUD();
    
    //Add
    //pcd.addEntity(t1);
    
    //delete
    //pcd.deleteEntity(1);
    
    //update
    t1.setFrom_item_nom("porte");
    t1.setFrom_image("image2");
    t1.setOffre_jetons(2);
    t1.setCommentaire_trans("cc cv");
    t1.setId(3);
    pcd.updateEntity(t1);
    
    //Affichage
    System.out.println(pcd.entitiesList());
}  
}
