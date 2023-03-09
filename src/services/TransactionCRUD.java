/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Transaction;
import entities.Transaction.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import interfaces.EntityCRUDT;

/**
 *
 * @author feres
 */
public class TransactionCRUD implements EntityCRUDT<Transaction> {

    @Override
    public void addEntity(Transaction t) {
        try {
//            String requete="INSERT INTO transactionv2 (id,from_user_id,to_user_id,from_user_item_id,to_user_item_id,from_user_item,to_user_item,from_user_image,to_user_image,jetons_prop,jetons_dem,commentaire) "
//                + "VALUES(?,?,?,?,?,?)";
            String requete="INSERT INTO transactionv2 (jetons_prop,jetons_dem,commentaire,from_user_item,from_user_item_id,from_user_id) "
                + "VALUES(?,?,?,?,?,?)";
        
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
//             st.setInt(1, t.getId());
             st.setInt(6, t.getFrom_user_id());
//             st.setInt(3, t.getTo_user_id());
            st.setInt(5, t.getFrom_user_item_id());
//            st.setInt(5, t.getTo_user_item_id());
             st.setString(4, t.getFrom_user_item());
//             st.setInt(7, t.getTo_user_item());
//             st.setInt(8, t.getFrom_user_image());
//             st.setInt(9, t.getTo_user_image());
             st.setInt(1, t.getJetons_prop());
             st.setInt(2, t.getJetons_dem());
             st.setString(3, t.getCommentaire());
             st.executeUpdate();
             System.out.println("Transaction ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

@Override
public List<Transaction> entitiesList() {
    List<Transaction> transactions = new ArrayList<>();
    try {
        String query = "SELECT * FROM transactionv2 ";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Transaction t = new Transaction();
            t.setId(rs.getInt("id"));
            t.setFrom_user_id(rs.getInt("from_user_id"));
            t.setTo_user_id(rs.getInt("to_user_id"));
            t.setFrom_user_item_id(rs.getInt("from_user_item_id"));
            t.setTo_user_item_id(rs.getInt("to_user_item_id"));
            t.setFrom_user_item(rs.getString("from_user_item"));
            t.setTo_user_item(rs.getString("to_user_item"));
            t.setFrom_user_image(rs.getString("from_user_image"));
            t.setTo_user_image(rs.getString("to_user_image"));
            t.setJetons_prop(rs.getInt("jetons_prop"));
            t.setJetons_dem(rs.getInt("jetons_dem"));
            t.setCommentaire(rs.getString("commentaire"));
            transactions.add(t);
        }
    } catch (SQLException ex) {
        System.out.println("Error getting transactions list: " + ex.getMessage());
    }
    return transactions;
}


@Override
public void delEntity(int id) {
    try {
        // create the delete query
        String query = "DELETE FROM transactionv2 WHERE id=?";

        // create the prepared statement with the query
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);

        // set the parameter for the delete query
        st.setInt(1, id);

        // execute the delete query
        int rowsDeleted = st.executeUpdate();

        if (rowsDeleted == 0) {
            System.out.println("transaction object with id " + id + " does not exist in the database.");
        } else {
            System.out.println("transaction avec id " + id + " supprimée.");
        }
    } catch (SQLException e) {
        System.out.println("Error deleting transaction object: " + e.getMessage());
    }
}


    @Override
     public void updEntity( Transaction t) {
    try {
       

        // create the update query
        String query = "UPDATE transactionv2 SET  jetons_prop=?, jetons_dem=?, commentaire=? WHERE id=?";

        // create the prepared statement with the query
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);

        // set the parameters for the update query
    
        st.setInt(1, t.getJetons_prop());
        st.setInt(2, t.getJetons_prop());
        st.setString(3, t.getCommentaire());
        st.setInt(4, t.getId());

        // execute the update query
        int rowsUpdated = st.executeUpdate();


        if (rowsUpdated == 0) {
            System.out.println("transaction object with id " + t.getId() + " does not exist in the database.");
        } else {
            System.out.println("transaction avec id " + t.getId() + " mise à jour.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating transaction object: " + e.getMessage());
    }
}
//     public String getUserItemById(int id) throws SQLException {
//    String query = "SELECT name FROM items WHERE id = ?";
//    PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
//    st.setInt(1, id);
//    ResultSet resultSet = st.executeQuery();
//    if (resultSet.next()) {
//        return resultSet.getString("name");
//    }
//    return null;
//}
//     public String getUserItemById(int from_user_id) {
//    try {
//        String query = "SELECT name FROM items i JOIN transactionv2 t ON i.id = t.from_user_item_id WHERE t.id = ?";
//        PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
//        st.setInt(1, from_user_id);
//        ResultSet rs = st.executeQuery();
//
//        if (rs.next()) {
//            return rs.getString("name");
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return null;
     
     
     

     //retrives the items that the from_user has
     public List<String> getUserItemsById(int from_user_id) {
    try {
        String query = "SELECT name FROM items WHERE user_id = ?";
        PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
        st.setInt(1, from_user_id);
        ResultSet rs = st.executeQuery();

        List<String> items = new ArrayList<>();
        while (rs.next()) {
            items.add(rs.getString("name"));
            //items.add(rs.getInt("id"), rs.getString("name"));
        }
        return items;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
     
         public String getFromUsernameById (int from_user_id) {
             String FromUsername= null;
             try{
                String query = "SELECT username from users where id=?";
                PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
                st.setInt(1, from_user_id);
                ResultSet resultSet = st.executeQuery();
                if (resultSet.next()) {
                    FromUsername = resultSet.getString("username");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        } return FromUsername;
    }
         
             public String getToUsernameById (int to_user_id) {
             String ToUsername= null;
             try{
                String query = "SELECT username from users where id=?";
                PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
                st.setInt(1, to_user_id);
                ResultSet resultSet = st.executeQuery();
                if (resultSet.next()) {
                    ToUsername = resultSet.getString("username");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        } return ToUsername;
    }
     
    public int getItemIdByName(String name) {
        int itemId = -1;
        try {
            String query = "SELECT id FROM items WHERE name=?";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                itemId = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return itemId;
    }
    
    public int getCountJetons(int from_user_id){
        int countJetons = 0;
        try {
            
            String query = " SELECT j.count FROM jetons j JOIN users u ON j.user_id=u.id JOIN transactionv2 t ON u.id=t.from_user_id where t.from_user_id=? ";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
            st.setInt(1, from_user_id );
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                countJetons = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countJetons;
            
        }
    
    public void updCountJetons(int user_id, int newCount) {
    try {
        String query = "UPDATE jetons SET count = ? WHERE user_id = ?";
        PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
        st.setInt(1, newCount);
        st.setInt(2, user_id);
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
     
//     public List<Item> getUserItemsById(int from_user_id) {
//    try {
//        String query = "SELECT i.id, i.name FROM items i JOIN transactionv2 t ON i.id = t.from_user_item_id WHERE t.id = ?";
//        PreparedStatement st = new MyConnection().getCnx().prepareStatement(query);
//        st.setInt(1, from_user_id);
//        ResultSet rs = st.executeQuery();
//
//        List<Item> items = new ArrayList<>();
//        while (rs.next()) {
//            items.add(new Item(rs.getInt("id"), rs.getString("name")));
//        }
//        return items;
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return null;
//}

   
}
