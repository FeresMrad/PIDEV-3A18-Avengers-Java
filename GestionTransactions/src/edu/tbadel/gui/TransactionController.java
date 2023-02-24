package edu.tbadel.gui;

import edu.tbadel.entities.Transaction;
import edu.tbadel.services.TransactionCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author feres
 */
public class TransactionController implements Initializable {

    @FXML
    private TextField commentaire_transtf;
    @FXML
    private TextField offre_jetonstf;
    @FXML
    private TextField from_imagetf;
    @FXML
    private TextField from_item_nomtf;
    @FXML
    private Button envoyerbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void saveTransaction(ActionEvent event) throws IOException {
    String resFrom_item_nomtf = from_item_nomtf.getText();
    String resFrom_imagetf = from_imagetf.getText();
    int resOffre_jetonstf = Integer.parseInt(offre_jetonstf.getText());
    String resCommentaire_transtf = commentaire_transtf.getText();
    
    // Vérification des champs vides
    if (resFrom_item_nomtf.isEmpty() || resFrom_imagetf.isEmpty() || offre_jetonstf.getText().isEmpty() || resCommentaire_transtf.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
    }

    // Confirmation avant l'ajout
    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    confirm.setTitle("Confirmation");
    confirm.setHeaderText(null);
    confirm.setContentText("Voulez-vous vraiment ajouter cette transaction ?");

    ButtonType buttonTypeOui = new ButtonType("Oui");
    ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);

    confirm.getButtonTypes().setAll(buttonTypeOui, buttonTypeAnnuler);

    Optional<ButtonType> result = confirm.showAndWait();
    if (result.get() == buttonTypeOui){
        TransactionCRUD pcd = new TransactionCRUD();
        Transaction t = new Transaction(resFrom_item_nomtf, resFrom_imagetf, resOffre_jetonstf, resCommentaire_transtf);
        pcd.addEntity(t);
        System.out.println("Done");

        // Redirection
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterTrans.fxml"));

        try {
            Parent root = loader.load();
           /* ConsulterTransController controller = loader.getController();
            controller.setResultatCommentaire_trans(resCommentaire_transtf);
            controller.setResultatFrom_image(resFrom_imagetf);
            controller.setResultatFrom_item_nom(resFrom_item_nomtf);
            controller.setResultatOffre_jetons(resOffre_jetonstf);*/

            envoyerbtn.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        // Annulation de l'ajout
    }
}

    public TextField getCommentaire_transtf() {
        return commentaire_transtf;
    }

    public void setCommentaire_transtf(TextField commentaire_transtf) {
        this.commentaire_transtf = commentaire_transtf;
    }

    public TextField getOffre_jetonstf() {
        return offre_jetonstf;
    }

    public void setOffre_jetonstf(TextField offre_jetonstf) {
        this.offre_jetonstf = offre_jetonstf;
    }

    public TextField getFrom_imagetf() {
        return from_imagetf;
    }

    public void setFrom_imagetf(TextField from_imagetf) {
        this.from_imagetf = from_imagetf;
    }

    public TextField getFrom_item_nomtf() {
        return from_item_nomtf;
    }

    public void setFrom_item_nomtf(TextField from_item_nomtf) {
        this.from_item_nomtf = from_item_nomtf;
    }

    public Button getEnvoyerbtn() {
        return envoyerbtn;
    }

    public void setEnvoyerbtn(Button envoyerbtn) {
        this.envoyerbtn = envoyerbtn;
    }
}