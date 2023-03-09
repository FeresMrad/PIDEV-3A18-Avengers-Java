/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entities.Bien;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nouhe
 */
public interface BienInterface<B> {

    public void ajouterBien(Bien b, String pic, String categorie, int categorieId);

    public List<B> afficher();

    public void supprimer(int id) throws SQLException;

    public void updateB(Bien b, String pic, int categorieId, int x);
}
