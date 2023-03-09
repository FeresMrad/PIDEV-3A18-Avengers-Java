
package entities;

/**
 *
 * @author BACEM
 */
public class Participants {
    private int id;
    private int evenement_id;
    private int user_id;
    private String nom;
    private String prenom;
    private String email;
    
    public Participants() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Participants(int id) {
        this.id = id;
    }

    public Participants(int id, int evenement_id, int user_id, String nom, String prenom, String email) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.user_id = user_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }



    public Participants(int id, int evenement_id, int user_id) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.user_id = user_id;
    }
    public Participants( int evenement_id, int user_id) {
      
        this.evenement_id = evenement_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenements_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Participants{" + "id=" + id + ", evenement_id=" + evenement_id + ", user_id=" + user_id + '}';
    }
    
    
    
    
    
    
    
}


