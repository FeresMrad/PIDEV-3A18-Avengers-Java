/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

/**
 *
 * @author hazem
 */
public class Client extends User {

    public Client(int id_user, String nom_user, String prenom_user, int tel_user, String email_user, String mdp_user) {
        super(id_user, nom_user, prenom_user, tel_user, email_user, mdp_user);
    }

    public Client(String nom_user, String prenom_user, int tel_user, String email_user, String mdp_user) {
        super(nom_user, prenom_user, tel_user, email_user, mdp_user);
    }

    public Client() {
    }

    public Client(String email_user, String mdp_user) {
        super(email_user, mdp_user);
    }

    public Client(int id_user, String nom_user, String prenom_user, int tel_user, String email_user, String mdp_user, String pdp) {
        super(id_user, nom_user, prenom_user, tel_user, email_user, mdp_user, pdp);
    }

    public Client(String nom_user, String prenom_user, int tel_user, String email_user, String mdp_user, String pdp) {
        super(nom_user, prenom_user, tel_user, email_user, mdp_user, pdp);
    }
    
    
}
