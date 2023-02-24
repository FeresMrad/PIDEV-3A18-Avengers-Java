/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionBiensEtServices.services;

import edu.gestionBiensEtServices.entites.Bien;
import edu.gestionBiensEtServices.interfaces.BienInterface;
import edu.gestionBiensEtServices.utils.MyConnection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Nouhe
 */
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
public class BienServices implements BienInterface<Bien> {

    @Override
    public void ajouterBien(Bien b, String pic, String categorie, int categorieId) {
        try {
            String requete = "INSERT INTO items (name, description,image,id_categorie) VALUES (?,?, ?, ?)";
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
            stmt.setString(1, b.getNom());
            stmt.setString(2, b.getDescription());
            stmt.setString(3, pic);
            stmt.setInt(4, b.getId_categ());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Bien ajouté !");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du bien : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public List<Bien> afficher() {
        List<Bien> list = new ArrayList<>();
        try {
            String requete = "SELECT id, name, description, image, categorie FROM items, categories WHERE items.id_categorie = categories.idC";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String imagePath = rs.getString("image");
                String categorie = rs.getString("categorie");
          
                list.add(bien);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from items where id=? ";
        try {
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
    }

    @Override
    public void updateB(Bien b, String pic, int categorieId, int x) {
        try {
            String requete = "update items set name=?, description=?, image=?, id_categorie=? where id=" + x;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, b.getNom());
            pst.setString(2, b.getDescription());
            pst.setString(3, pic);
            pst.setInt(4, categorieId);
            pst.executeUpdate();
            int nbModif = pst.executeUpdate();
            if (nbModif > 0) {
                System.out.println("Service modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
