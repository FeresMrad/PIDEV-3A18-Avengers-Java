package services;

import entities.user;
import tools.connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Random;

public class UserService implements IService<user>{
    Connection cnx = connexion.getInstance().getCnx();
    @Override
    public void ajouter(user userr) {
        String query = "INSERT INTO `user` (`firstname`, `lastname`, `email`, `password`, `telephone`, `adresse`) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,userr.getFirstname());
            ste.setString(2,userr.getLastname());
            ste.setString(3,userr.getEmail());
            ste.setString(4, userr.getPassword());
            ste.setString(5, userr.getTelephone());
            ste.setString(6,userr.getAdresse());
            
            ste.executeUpdate();
            System.out.println("User Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<user> afficher() {
        ObservableList<user> listUser = FXCollections.observableArrayList();
        String query = "SELECT * FROM USER";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
            user userr = new user();
                userr.setId(rs.getInt("id"));
                userr.setEmail(rs.getString("Email"));
                userr.setFirstname(rs.getString("firstname"));
                userr.setLastname(rs.getString("lastname"));
                userr.setAdresse(rs.getString("adresse"));
                userr.setPassword(rs.getString("password"));
                userr.setTelephone(rs.getString("telephone"));

                listUser.add(userr);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listUser;
    }

    @Override
    public void modifier(user userr) {
        String query = "UPDATE USER SET firstname = '" + userr.getFirstname() + "', lastname = '" +
                userr.getLastname() + "', adresse = '" + userr.getAdresse() + "', telephone = '" +
                userr.getTelephone() + "', email= '" + userr.getEmail()
                + "' WHERE id = " + userr.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("User updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param id
     */
  public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
