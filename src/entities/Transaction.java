/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author feres
 */
public class Transaction {
    private int id;
    private int from_user_id;
    private int to_user_id;
    private int from_user_item_id;
    private int to_user_item_id;
    private String from_user_item;
    private String to_user_item;
    private String from_user_image;
    private String to_user_image;
    private int jetons_prop;
    private int jetons_dem;
    private String commentaire;
    private String etat;
    
    public Transaction () {
    }
    
    public Transaction (int jetons_prop, int jetons_dem, String commentaire) {
        this.jetons_prop = jetons_prop;
        this.jetons_dem = jetons_dem;
        this.commentaire = commentaire;
    }
    
    public Transaction (int jetons_prop, int jetons_dem, String commentaire, String from_user_item) {
        this.jetons_prop = jetons_prop;
        this.jetons_dem = jetons_dem;
        this.commentaire = commentaire;
        this.from_user_item = from_user_item;
    }
    
    public Transaction (int jetons_prop, int jetons_dem, String commentaire, String from_user_item, int from_user_item_id) {
        this.jetons_prop = jetons_prop;
        this.jetons_dem = jetons_dem;
        this.commentaire = commentaire;
        this.from_user_item = from_user_item;
        this.from_user_item_id = from_user_item_id;
    }
    
    public Transaction (int jetons_prop, int jetons_dem, String commentaire, String from_user_item, int from_user_item_id, int from_user_id) {
        this.jetons_prop = jetons_prop;
        this.jetons_dem = jetons_dem;
        this.commentaire = commentaire;
        this.from_user_item = from_user_item;
        this.from_user_item_id = from_user_item_id;
        this.from_user_id = from_user_id;
    }

    public int getId() {
        return id;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public int getFrom_user_item_id() {
        return from_user_item_id;
    }

    public int getTo_user_item_id() {
        return to_user_item_id;
    }

    public String getFrom_user_item() {
        return from_user_item;
    }

    public String getTo_user_item() {
        return to_user_item;
    }

    public String getFrom_user_image() {
        return from_user_image;
    }

    public String getTo_user_image() {
        return to_user_image;
    }

    public int getJetons_prop() {
        return jetons_prop;
    }

    public int getJetons_dem() {
        return jetons_dem;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public void setFrom_user_item_id(int from_user_item_id) {
        this.from_user_item_id = from_user_item_id;
    }

    public void setTo_user_item_id(int to_user_item_id) {
        this.to_user_item_id = to_user_item_id;
    }

    public void setFrom_user_item(String from_user_item) {
        this.from_user_item = from_user_item;
    }

    public void setTo_user_item(String to_user_item) {
        this.to_user_item = to_user_item;
    }

    public void setFrom_user_image(String from_user_image) {
        this.from_user_image = from_user_image;
    }

    public void setTo_user_image(String to_user_image) {
        this.to_user_image = to_user_image;
    }

    public void setJetons_prop(int jetons_prop) {
        this.jetons_prop = jetons_prop;
    }

    public void setJetons_dem(int jetons_dem) {
        this.jetons_dem = jetons_dem;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public class Item {
    private int id;
    private String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
}

