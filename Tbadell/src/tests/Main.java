/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.User;
import services.ServiceUser;
import utils.MyConnection;

/**
 *
 * @author hazem
 */
public class Main {
public static void main (String[]args){
    MyConnection mc = new MyConnection();
    User x = new User("bacem", "soltana ", 99819017, "hazemh585@gmail.com", "azerty","yyy");
    ServiceUser su =new ServiceUser();
  su.ajouter(x);
 // su.supprimer(571);
//su.modifierPassword("hazemh585@gmail.com", "XX");

  
//    System.out.println(su.getAll());
//    System.out.println(su.findById(555));
System.out.println( su.testEmail("sasaazjjgmail.com"));



}
}
