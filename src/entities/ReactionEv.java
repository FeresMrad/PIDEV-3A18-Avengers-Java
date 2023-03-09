package entities;


public class ReactionEv {
    public int id;
    public int interaction;
    public int dislike;
    public String commentaire;
    public int evenement_id;

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
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

    public ReactionEv(int id) {
        this.id = id;
    }

    public ReactionEv(int id, int interaction, String commentaire, int evenement_id) {
        this.id = id;
        this.interaction = interaction;
        this.commentaire = commentaire;
        this.evenement_id = evenement_id;
    }

    public ReactionEv(int id, int interaction, int dislike, String commentaire, int evenement_id) {
        this.id = id;
        this.interaction = interaction;
        this.dislike = dislike;
        this.commentaire = commentaire;
        this.evenement_id = evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }


    public int getInteraction() {
        return interaction;
    }

    public void setInteraction(int interaction) {
        this.interaction = interaction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

       @Override
    public String toString() {
        return "ReactionEv{" + "id=" + id + ", evenement_id=" + evenement_id + ", interaction=" + interaction + ", dislike=" + dislike + ", commentaire=" + commentaire + '}';
    }

    
    
}
