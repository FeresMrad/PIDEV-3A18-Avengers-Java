/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import entities.Evenements;
import entities.Participants;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;
import utils.MyConnection;

/**
 *
 * @author BACEM
 */
public class MainClass {
        public static void main(String[] args) {
          //  DateTimeFormatter f = DateTimeFormatter.ofPattern("1998-03-26");
          //  Date d = new Date(System.currentTimeMillis());
            
        MyConnection mc=new MyConnection();
//         Evenements v1= new Evenements(6,"Romdhan", "ahla romdhan",new Date(),new Date(),"Tunis");
//            EvenementsCRUD pcd= new EvenementsCRUD();
//        //Add
//        // pcd.addEntity(v1);
//       
//       //Delete
//       // pcd.delEvenements(1);
//        
//       //UPDATE
//       v1.setNom("aloo");
//       v1.setLieu("hey");
//       pcd.updEvenements(v1);
//        
//        //Affichage
//        System.out.println(pcd.entitiesList());





          ParticipantsCRUD pcp=new ParticipantsCRUD();
//            Participants p1 = new Participants(2);
            
            //Ajout 
//            Participants p2 = new Participants(3, 5, 1);
//            pcp.addEntityP(p2);
            
            //Suppression
//            pcp.delParticipants(2);
            
            //update
            
            
            //affichage
            System.out.println(pcp.entitiesListP());
        
    }
}


