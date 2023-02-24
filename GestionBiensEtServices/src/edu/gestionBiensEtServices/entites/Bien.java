/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionBiensEtServices.entites;

import javafx.scene.image.ImageView;

/**
 *
 * @author Nouhe
 */
public class Bien {

    private int id;
    private String nom;
    private String description;
    private String imageBien;
    private ImageView image;
    private String categorie;
    private int id_categ;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getId_categ() {
        return id_categ;
    }

    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }

    public Bien() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bien(String nom, String description, int id_categ, String imageBien) {
        this.nom = nom;
        this.description = description;

        this.id_categ = id_categ;
        this.imageBien = imageBien;
    }

    public Bien(String nom, String description, String imageBien, int id_categ) {
        this.nom = nom;
        this.description = description;
        this.imageBien = imageBien;
        this.id_categ = id_categ;
    }

    public Bien(String categorie) {
        this.categorie = categorie;
    }

    public String getImageBien() {
        return imageBien;
    }

    public void setImageBien(String imageBien) {
        this.imageBien = imageBien;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Bien(int id, String nom, String description, ImageView image, String categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Bien{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", imageBien=" + imageBien + ", image=" + image + ", categorie=" + categorie + ", id_categ=" + id_categ + '}';
    }
   
}
