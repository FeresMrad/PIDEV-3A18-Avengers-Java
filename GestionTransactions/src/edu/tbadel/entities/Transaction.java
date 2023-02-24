/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tbadel.entities;

/**
 *
 * @author feres
 */
public class Transaction {
    private int id;
    private int item_id;
    private int from_user_id;
    private String from_item_nom;
    private String from_image;
    private int to_user_id;
    private String to_image;
    private int offre_jetons;
    private String commentaire_trans;
    
    public Transaction (){
        
    }
    public Transaction(int id, int item_id, int  from_user_id, String from_item_nom, String from_image, int to_user_id, String to_image, int offre_jetons, String commentaire_trans ) {
        this.id = id;
        this.item_id = item_id;
        this.from_user_id = from_user_id;
        this.from_item_nom = from_item_nom;
        this.from_image = from_image;
        this.to_user_id = to_user_id;
        this.to_image = to_image;
        this.offre_jetons = offre_jetons;
        this.commentaire_trans = commentaire_trans;
    }   
    public Transaction(String from_item_nom, String from_image, int offre_jetons, String commentaire_trans ) {
      
       
        this.from_item_nom = from_item_nom;
        this.from_image = from_image;
        this.offre_jetons = offre_jetons;
        this.commentaire_trans = commentaire_trans;
    } 

    public int getId() {
        return id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public String getFrom_item_nom() {
        return from_item_nom;
    }

    public String getFrom_image() {
        return from_image;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public String getTo_image() {
        return to_image;
    }

    public int getOffre_jetons() {
        return offre_jetons;
    }

    public String getCommentaire_trans() {
        return commentaire_trans;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public void setFrom_item_nom(String from_item_nom) {
        this.from_item_nom = from_item_nom;
    }

    public void setFrom_image(String from_image) {
        this.from_image = from_image;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public void setTo_image(String to_image) {
        this.to_image = to_image;
    }

    public void setOffre_jetons(int offre_jetons) {
        this.offre_jetons = offre_jetons;
    }

    public void setCommentaire_trans(String commentaire_trans) {
        this.commentaire_trans = commentaire_trans;
    }
    @Override
    public String toString() {
        return "Transaction{"
                + "id=" + id
                + ", item_id='" + item_id 
                + ", from_user_id=" + from_user_id
                + ", from_item_nom=" + from_item_nom
                + ", from_image=" + from_image
                + ", to_user_id=" + to_user_id
                + ", to_image=" + to_image
                + ", offre_jetons=" + offre_jetons
                + ", commentaire_trans=" + commentaire_trans
                + '}';
    }

    public String getCommentaire_transtb() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
