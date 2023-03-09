/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Jetons;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author feres
 */
public class JetonsCRUD {
    
//    public List<Jetons> entitiesList() {
//    List<Jetons> listJet = new ArrayList<>();
//    try {
//        String query = "SELECT * FROM jetons";
//        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            Jetons t = new Jetons();
//            t.setUser_id(rs.getInt("user_id"));
//            t.setCount(rs.getInt("count"));
//            listJet.add(t);
//        }
//    } catch (SQLException ex) {
//        System.out.println("Error getting listJet: " + ex.getMessage());
//    }
//    return listJet;
//}
    public List<Jetons> entitiesList() {
    List<Jetons> listJet = new ArrayList<>();
    try {
        String query = "SELECT jetons.user_id, users.username, jetons.count FROM jetons "
                + "INNER JOIN users ON jetons.user_id = users.id";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Jetons t = new Jetons();
            t.setUser_id(rs.getInt("user_id"));
            t.setUsername(rs.getString("username"));
            t.setCount(rs.getInt("count"));
            listJet.add(t);
        }
    } catch (SQLException ex) {
        System.out.println("Error getting listJet: " + ex.getMessage());
    }
    return listJet;
}
    public void updateCount(int user_id, int amount, boolean isAddition) {
    String operation = isAddition ? "+" : "-";
    String query = "UPDATE jetons SET count = count " + operation + " ? WHERE user_id = ?";
    try {
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setInt(1, amount);
        st.setInt(2, user_id);
        st.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error updating count: " + ex.getMessage());
    }
}
    
    public void addCount(int user_id, int value) {
    try {
        String query = "UPDATE jetons SET count = count + ? WHERE user_id = ?";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setInt(1, value);
        st.setInt(2, user_id);
        st.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error adding count: " + ex.getMessage());
    }
}

public void substractCount(int user_id, int value) {
    try {
        String query = "UPDATE jetons SET count = count - ? WHERE user_id = ?";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setInt(1, value);
        st.setInt(2, user_id);
        st.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error subtracting count: " + ex.getMessage());
    }
}



}
