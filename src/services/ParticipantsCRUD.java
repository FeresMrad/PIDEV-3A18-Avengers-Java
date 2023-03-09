
package services;
import entities.Participants;
import interfaces.CRUDP;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
/**
 *
 * @author BACEM
 */
public class ParticipantsCRUD implements CRUDP<Participants> {

    @Override
    public void addEntityP(Participants t) {
        try {
            String requete="INSERT INTO participants (id,evenement_id,user_id) "
                + "VALUES(?,?,?)";
        
             PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
             st.setInt(1, t.getId());
             st.setInt(2, t.getEvenement_id());
             st.setInt(3, t.getUser_id());
  
             int nbModif=st.executeUpdate();
            
            if (nbModif > 0) {
                System.out.println("Félicitations!!");
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Participants> getAllParticipantsByEventId(int eventId) {
    List<Participants> participantsList = new ArrayList<>();
    try {
        String query = "SELECT * FROM participants WHERE evenement_id=?";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setInt(1, eventId);
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()) {
            Participants p = new Participants(
                resultSet.getInt("evenement_id"),
                resultSet.getInt("user_id")
            );
            participantsList.add(p);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return participantsList;
}
    @Override
    public List<Participants> entitiesListP() {
             ArrayList<Participants> myList = new ArrayList();
             
        try {
            String requete = "SELECT * FROM participants";
//           String requete=" SELECT p.id, p.user_id, u.nom, u.prenom, u.email FROM participants p JOIN users u ON p.user_id = u.id WHERE p.evenement_id = ?";

            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while (rs.next()){
                Participants p = new Participants();
                p.setId(rs.getInt(1));
                p.setEvenements_id(rs.getInt("evenement_id"));
                p.setUser_id(rs.getInt("user_id"));
   
       
              myList.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return myList;
    }
    
//       @Override
//    public List<Participants> entitiesListP(int x) {
//             ArrayList<Participants> myList = new ArrayList();
//             
//        try {
//            String requete = "SELECT * FROM participants WHERE evenement_id="+x;
//            Statement st=MyConnection.getInstance().getCnx().createStatement();
//            ResultSet rs= st.executeQuery(requete);
//            
//            while (rs.next()){
//                Participants p = new Participants();
//                p.setId(rs.getInt(1));
//                p.setEvenements_id(rs.getInt("evenement_id"));
//                p.setUser_id(rs.getInt("user_id"));
//   
//       
//              myList.add(p);
//            }
//            
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    return myList;
//    }
//    @Override
//        public List<Participants> entitiesListP(int x) {
//        List<Participants> myList = new ArrayList<>();
//        try {
//          //  String requete = "SELECT * FROM participants WHERE evenement_id=?";
//          String requete= "SELECT p.id, p.user_id, u.nom, u.prenom, u.email FROM participants p JOIN users u ON p.user_id = u.id WHERE p.evenement_id = ?";
//
//            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, x);
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//                Participants p = new Participants();
//                p.setId(rs.getInt(1));
//                p.setEvenements_id(rs.getInt("evenement_id"));
//                p.setUser_id(rs.getInt("user_id"))
//                            ;
//                myList.add(p);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return myList;
//    }
    @Override
public List<Participants> entitiesListP(int x) {
    List<Participants> myList = new ArrayList<>();
    try {
//          String requete = "SELECT * FROM participants WHERE evenement_id=?";
       String requete= "SELECT p.id, p.user_id, u.nom_user, u.prenom_user, u.email_user FROM participants p JOIN user u ON p.user_id = u.id_user WHERE p.evenement_id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, x);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Participants p = new Participants();
            p.setId(rs.getInt(1));
            p.setEvenements_id(x);
            p.setUser_id(rs.getInt("user_id"));
            p.setNom(rs.getString("nom_user"));
            p.setPrenom(rs.getString("prenom_user"));
            p.setEmail(rs.getString("email_user"));
            myList.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}
//    @Override
//public List<Participants> entitiesListP(int x) {
//    List<Participants> myList = new ArrayList<>();
//    try {
//        String requete = "SELECT * FROM participants WHERE evenement_id=?";
//        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//        pst.setInt(1, x);
//        ResultSet rs = pst.executeQuery();
//        while (rs.next()) {
//            Participants p = new Participants();
//            p.setId(rs.getInt(1));
//            p.setEvenements_id(x);
//            p.setUser_id(rs.getInt("user_id"));
//            // Appeler getUserById pour récupérer le nom, le prénom et l'e-mail de l'utilisateur
//            String[] userInfo = getUserById(rs.getInt("user_id"));
//            if (userInfo != null) {
//                p.setNom(userInfo[0]);
//                p.setPrenom(userInfo[1]);
//                p.setEmail(userInfo[2]);
//            }
//            myList.add(p);
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return myList;
//}


public String[] getUserById(int id) throws SQLException {
    String query = "SELECT nom_user, prenom_user, email_user FROM user WHERE id_user = ?";
    PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
    st.setInt(1, id);
    ResultSet resultSet = st.executeQuery();
    if (resultSet.next()) {
        String[] user = new String[3];
        user[0] = resultSet.getString("nom_user");
        user[1] = resultSet.getString("prenom_user");
        user[2] = resultSet.getString("email_user");
        return user;
    }
    return null;
}
    
    
    @Override
    public void delParticipants(int t) {
            try {
            String requete = "DELETE FROM participants where evenement_id=?";
//            String requete = "DELETE FROM participants where evenement_id=? AND user_id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t);
            int nbModif = pst.executeUpdate();
            if (nbModif <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Participation supprimée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }




    
}
