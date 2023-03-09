/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static com.oracle.util.Checksums.update;
import entities.user;
import services.UserService;
import tools.connexion;

/**
 *
 * @author elbarhoumi
 */
public class main{
    public static void main(String[] args){
    //connexion cnx;
        //cnx = new connexion ();
        //user userr= new user("azer","azerr","qsss","dssd","erfr","eere");
       UserService u = new UserService();
      //user userr= new user(17,"hama","tahan","vvvv@dddd.yn","qwerty","123452267","elmenzah");
         
       // user userr= update userr(1,"achref","tahan","vvvv@dddd.yn","qwerty","123452267","elmenzah");
      System.out.println(u.afficher());
      //u.ajouter(userr);
      // u.supprimer(16);
      //user userr=  new user("houcine","agrebi","qsss","dssd","erfr","kefff");
      //u.modifier(userr);
    }   

    //private static user user(String houcine, String agrebi, String qsss, String dssd, String erfr, String kefff) {
        //return null;
    }

