/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Client;
import entities.User;
import services.ServiceUser;
import utils.MyConnection;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
//wnTJiFj_FMfxZKFO42kIhjdBhJXBCovUrHWtHDrA
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class SingUpMembreController implements Initializable {

    Connection cnx = MyConnection.getInstance().getCnx();
    public static final String ACCOUNT_SID = "ACed2a825e3adf2594fa0eba165be11076";
    public static final String AUTH_TOKEN = "4731aad07eb92cd1a725ef71ed987cea";
//    public static final String ACCOUNT_SID = "AC20022854457aec468ffdc56b98e25a37";
//    public static final String AUTH_TOKEN = "4972dad758476be1f11c27cfe9a09352";
    @FXML
    private Button add_membre_btn;
    @FXML
    private TextField prenom_membre;
    @FXML
    private TextField nom_membre;
    @FXML
    private TextField tel_membre;
    @FXML
    private TextField email_membre;
    @FXML
    private TextField src;
    @FXML
    private PasswordField mdp_membre;
    @FXML
    private PasswordField mdp_membre1;
    @FXML
    private TextField generatedString;
    @FXML
    private TextField text;
    String C;
    @FXML
    private Button switch_signin_membre_btn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML

    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();

        String generateString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        C = generateString;

        generatedString.setText("" + generateString + "");

    }

    @FXML
    private void action_add_membre(ActionEvent event) throws IOException, SQLException {

        ServiceUser sp = new ServiceUser();

        if (prenom_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your firstname ?");
            alert.showAndWait();
            return;
        }
        if (nom_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your lastname ?");
            alert.showAndWait();
            return;
        }

        String phoneNumber = tel_membre.getText();
        if (!phoneNumber.matches("(90|91|92|93|94|95|96|97|98|99|40|41|42|44|50|51|52|53|54|55|58|29|28|27|26|25|24|23|22|21|20)\\d{6}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Invalid phone number ");
            alert.showAndWait();
            return;
        }
        if (verifemail(email_membre.getText()) == false) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez verifier votre mail ");
            alert.showAndWait();
            return;
        }

        if (mdp_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your password ?");
            alert.showAndWait();
            return;
        }

        String password = mdp_membre.getText();

// Vérifier la longueur minimale
        if (password.length() < 8) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Le mot de passe doit avoir au moins 8 caractères");
            alert.showAndWait();
            return;
        }

// Vérifier la présence d'un caractère spécial
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Le mot de passe doit contenir au moins un caractère spécial");
            alert.showAndWait();
            return;
        }

// Vérifier la présence d'une lettre majuscule
        if (!password.matches(".*[A-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Le mot de passe doit contenir au moins une lettre majuscule");
            alert.showAndWait();
            return;
        }

// Vérifier la présence d'une lettre minuscule
        if (!password.matches(".*[a-z].*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Le mot de passe doit contenir au moins une lettre minuscule");
            alert.showAndWait();
            return;
        }
        if ((mdp_membre.getText().equals(mdp_membre1.getText())) == false) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Les mots de passe ne sont pas identiques ");
            alert.showAndWait();
            return;
        }
        if ((text.getText().equals(C) == false)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Les deux chaines ne sont pas identiques ");
            alert.showAndWait();
            return;
        }
        User m = new Client(prenom_membre.getText(), nom_membre.getText(), Integer.parseInt(tel_membre.getText()), email_membre.getText(), mdp_membre.getText(), src.getText());

        if (sp.existeMail(m) == 0) {
            sp.ajouter(m);
            //CodeQr(event);

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+216" + m.getTel_user()),
                    new PhoneNumber("+14159352345"),
                    "Salut Mr/Mme," + m.getNom_user() + " " + m.getPrenom_user()
                    + "Tbadel vous informe que votre inscription est reussi avec succes ").create();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("User Added Successfully");
            alert.setContentText(" Felecitations");

            alert.showAndWait();
            root = FXMLLoader.load(getClass().getResource("SignInMembre.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("compte existe deja ");
            alert.showAndWait();
        }

    }

    private void CodeQr(ActionEvent event) {
        String Id_stat = nom_membre.getText();
        String LoginCode = email_membre.getText();
        ByteArrayOutputStream out = QRCode.from(LoginCode)
                .to(ImageType.PNG).stream();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(new File(Path_name + "/" + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println(Path_name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void action_switch_signin_membre(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignInMembre.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private String importImg(ActionEvent event) {
        String id = email_membre.getText();
        Path to = null;
        String m = null;
        String path = "src/imagesUser/";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path + "\\" + id + ".png");
                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to, options);
                    System.out.println("added");
//                saveSystem(selectedFile, )
                    System.out.println(to);

                } catch (IOException ex) {
                    System.out.println();
                }
            }
            src.setText(to.toString());

        }
        return to.toString();
    }

    private boolean verifemail(String email) {
        String e = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern em = Pattern.compile(e, Pattern.CASE_INSENSITIVE);
        Matcher matcher = em.matcher(email);
        return matcher.find();
    }

    public void affichpdp() {
        String Id_stat = email_membre.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/imagesUser/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        img.setImage(im);
    }

}
