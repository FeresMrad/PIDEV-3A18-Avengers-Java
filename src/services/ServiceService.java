/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Service;
import interfaces.ServiceInterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utils.MyConnection;

/**
 *
 * @author Nouhe
 */
public class ServiceService implements ServiceInterface<Service> {

    @Override
    public void ajouterService(Service s,int userId) {
         try {
        String requete = "insert into services (nom,description,id_utilisateur) VALUES (?, ?, ?)";
        PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
        stmt.setString(1, s.getIntitule());
        stmt.setString(2, s.getDescription());
        stmt.setInt(3, userId);

        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Service ajouté !");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du service : " + ex.getMessage());
        ex.printStackTrace();
    }
        
    }

    @Override
    public List<Service> etitiesList() {
        ArrayList<Service> myList = new ArrayList();
        try {
            String requete = "select * from services";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getInt(1));
                s.setIntitule(rs.getString("nom"));
                s.setDescription(rs.getString("description"));
                myList.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void supprimerService(int s) {

        try {
            String requete = "DELETE FROM services where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, s);
            int nbModif = pst.executeUpdate();
            if (nbModif > 0) {
                System.out.println("Service supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateService(Service s, int x) {
        try {
            String requete = "UPDATE services SET nom=? , description=? WHERE id=?" + x;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, s.getIntitule());
            pst.setString(2, s.getDescription());
            pst.setInt(3, s.getId());
            int nbModif = pst.executeUpdate();
            if (nbModif > 0) {
                System.out.println("Service modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
