/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Reclamation;
import java.util.List;

/**
 *
 * @author Maissa
 */
public interface EntityCRUD <T> {
  // public void addEntity(T t);
   

//     public void supprimerReclamation(T t);+
//     public void updateReclamation(T t);
//     public List<T> displayReclamations();
//    
    //public void ajouterReclaamtion(T t );
    public void ajouterReclamation(T t,String e);
    public List<T>entitiesList();
    public void suppReclamation(int id);
     public void updReclamation(T t,int x);
   
     public void addEntity(Reclamation t);
   //  public Reclamation getById(int id);
    //  public Reclamation FindReclamationById(int user_id); 
     public int  getNumberOfReclamation(int id);

}