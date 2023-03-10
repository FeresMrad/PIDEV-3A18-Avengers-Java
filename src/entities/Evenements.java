/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 * @author BACEM
 */
public class Evenements {
    private int id;
    private String nom;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private String lieu;
    
    private int likee;
    private int dislikee;
    private String comment;

 
  
 

    public int getLikee() {
        return likee;
    }

    public void setLikee(int likee) {
        this.likee = likee;
    }

    public int getDislikee() {
        return dislikee;
    }

    public void setDislikee(int dislikee) {
        this.dislikee = dislikee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Evenements() {
    }
    
    
    
    
   public Evenements(int likee, int dislikee, String comment) {
        this.likee = likee;
        this.dislikee = dislikee;
        this.comment = comment;
    }

   
      public Evenements(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

   
    public Evenements(String comment) {
        this.comment = comment;
    }
    
    
    
    public Evenements(String nom, String description, Date date_debut, Date date_fin, String lieu) {
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu = lieu;
    }

    
    
    
    public Evenements(int id, String nom, String description, Date date_debut, Date date_fin, String lieu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu = lieu;
    }

    
    
    
    
    public Evenements(int id, String nom, String description, Date date_debut, Date date_fin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    
    
    public Evenements(int id, String nom, String description, String lieu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Evenements(int id, int likee) {
        this.id = id;
        this.likee = likee;
    }

    public Evenements(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", lieu=" + lieu + '}';
    }
    
    
}
