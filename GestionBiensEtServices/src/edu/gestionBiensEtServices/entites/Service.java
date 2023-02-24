/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionBiensEtServices.entites;

/**
 *
 * @author Nouhe
 */
public class Service {

    private int id;
    private String intitule;
    private String description;

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", intitule=" + intitule + ", description=" + description + '}';
    }

    public Service() {
    }

    public Service(String intitule, String description) {
        this.intitule = intitule;
        this.description = description;
    }

}
