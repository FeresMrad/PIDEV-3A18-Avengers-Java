/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Bien;
import entities.User;
import interfaces.BienInterface;
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
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import utils.MyConnection;
public class BienServices implements BienInterface<Bien> {

   
 
    @Override
public void ajouterBien(Bien b, String pic, String categorie, int categorieId, int userId) {
    try {
        String requete = "INSERT INTO items (name, description, image, id_categorie, user_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
        stmt.setString(1, b.getNom());
        stmt.setString(2, b.getDescription());
        stmt.setString(3, pic);
        stmt.setInt(4, categorieId);
        stmt.setInt(5, userId);

        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout de bien");
            alert.setHeaderText(null);
            alert.setContentText("Le bien a été ajouté avec succès !");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du bien : " + ex.getMessage());
        ex.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de l'ajout du bien. Veuillez réessayer plus tard.");
        alert.showAndWait();
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
                File file = new File("C:\\Users\\Feriel\\Documents\\NetBeansProjects\\TbadelTrans\\src\\images\\" + imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(150);
                imageView.setFitWidth(200);
                Bien bien = new Bien(id, name, description, imageView, categorie);
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
    
  /*  @Override
public void supprimer(int id, int userId) throws SQLException {
    String req = "delete from items where id=? and user_id=?";
    try {
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
        st.setInt(1, id);
        st.setInt(2, userId);
        st.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("erreur");
    }
}*/

    


    @Override
    public void updateB(Bien b, String pic, int categorieId, int x) {
        try {
            //String requete = "update items set name=?, description=?, image=?, id_categorie=? where id=" + x;
            String requete = "update items set name=?, description=?, image=?, id_categorie=? where id=" + x;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, b.getNom());
            pst.setString(2, b.getDescription());
            pst.setString(3, pic);
            pst.setString(3,b.getImageBien());
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
