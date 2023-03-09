    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
     */
    package gui;

import entities.Bien;
    import entities.Transaction;
    import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
    import java.io.IOException;
import java.io.OutputStream;
    import java.net.URL;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
    import java.util.List;
    import java.util.Optional;
    import java.util.Properties;
    import java.util.ResourceBundle;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
import javafx.scene.Group;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.ButtonBar;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
    import javafx.scene.control.TextArea;
    import javafx.scene.control.TextField;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.stage.Stage;
    import javax.mail.Message;
    import javax.mail.MessagingException;
    import javax.mail.PasswordAuthentication;
    import javax.mail.Session;
    import javax.mail.Transport;
    import javax.mail.internet.AddressException;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeMessage;
    import okhttp3.MediaType;
    import okhttp3.OkHttpClient;
    import okhttp3.Request;
    import okhttp3.RequestBody;
    import okhttp3.Response;
    import services.TransactionCRUD;
    import utils.MyConnection;

    /**
     * FXML Controller class
     *
     * @author feres
     */
    public class StartTransactionController implements Initializable {

        @FXML
        private ComboBox<String> from_user_itemst;
        @FXML
        private ImageView from_user_imagest;
        @FXML
        private TextField to_user_itemst;
        @FXML
        private TextField jetons_propst;
        @FXML
        private TextField jetons_demst;
        @FXML
        private ImageView to_user_imagest;
        @FXML
        private Button envoyerst;
        @FXML
        private TextArea commentairest;
        @FXML
        private Button captchabtn;
        @FXML 
        private ImageView captchaImageView;
        @FXML
        private TextField captchatext;  
        
//        private TableView<Bien> table_from_imagest;
//        @FXML
//        private TableColumn<Bien, ImageView> column_from_imagest;

        private OkHttpClient client;
        private Bien bien;

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

        private String captchaSolution;
        private int test;

        public int getTest() {
            return test;
        }
    //int x;
        public void setTest(int test) {
            this.test = test;
        TransactionCRUD pcd = new TransactionCRUD();
        String toUserItem = pcd.getNameByItemId(test);
        to_user_itemst.setText(toUserItem);
            //x=getTest();
        showtr();
        }

        /**
         * Initializes the controller class.
         */
        int y;
        public void showtr(){
           y = getTest();
            System.out.println("mostfa" + y);
        }
      @Override
        public void initialize(URL url, ResourceBundle rb) {
            // initialize OkHttpClient client
        client = new OkHttpClient();
        // load captcha image
        loadCaptcha();
    //    TransactionCRUD pcd = new TransactionCRUD();
    //    String toUserItem = pcd.getUserItemById(1);
    //    to_user_itemst.setText(toUserItem);
        TransactionCRUD pcd = new TransactionCRUD();
        //String toUserItem = pcd.getUserItemById(1);
        List<String> FromUserItem = pcd.getUserItemsById(1);
        ObservableList<String> items = FXCollections.observableArrayList(FromUserItem);
        //ObservableList<String> items = (ObservableList<String>) pcd.getUserItemsById(1);
        from_user_itemst.setItems(items);
        if (!items.isEmpty()){
        from_user_itemst.setValue(items.get(0));}
       String toUserItem = pcd.getNameByItemId(getTest());
        to_user_itemst.setText(toUserItem);
        
       // from_user_imagest.setImage(image);
        
        
        
        
        }  
     

//@Override
//public void initialize(URL url, ResourceBundle rb) {
//    int x=getTest();
//    try {
//        Image image = getImageByIdDB(x);
//        ImageView from_user_imagest = new ImageView();
//        // set the dimensions of the ImageView
//        from_user_imagest.setFitHeight(100);
//        from_user_imagest.setFitWidth(100);
//        // set the image retrieved from the database to the ImageView
//        from_user_imagest.setImage(image);
//        
//        // create a new Scene containing the ImageView
//        Scene scene = new Scene(new Group(from_user_imagest));
//        
//        // create a new Stage to display the Scene
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException | SQLException ex) {
//        ex.printStackTrace();
//    }
//}


public Image getImageByIdDB(int id) throws IOException, SQLException {
    String imageString = "";
    try {
        String requete = "SELECT image FROM items WHERE id = " + id;
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        if (rs.next()) {
            imageString = rs.getString("image");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    // decode the base64 string to a byte array
    byte[] imageData = Base64.getDecoder().decode(imageString);

    // save the byte array to a temporary file
    File tempFile = File.createTempFile("image", ".tmp");
    try (OutputStream os = new FileOutputStream(tempFile)) {
        os.write(imageData);
    }

    // load the image from the temporary file
    Image image = new Image(tempFile.toURI().toString());

    // delete the temporary file
    tempFile.delete();

    return image;
}



  
        
        

        @FXML
        private void loadCaptcha() {
            // create request to retrieve captcha image
            Request request = new Request.Builder()
                    .url("https://image-captcha-solver.p.rapidapi.com/generateImage")
                    .get()
                    .addHeader("X-RapidAPI-Key", "ce2e7d9603msh56d0a5daca1476bp168843jsnd04ec9527f91")
                    .addHeader("X-RapidAPI-Host", "image-captcha-solver.p.rapidapi.com")
                    .build();
            try {
                // execute request and retrieve response body as byte array
                Response response = client.newCall(request).execute();
                byte[] imageBytes = response.body().bytes();
                // create JavaFX Image object from byte array and set to ImageView
                Image captchaImage = new Image(new ByteArrayInputStream(imageBytes));
                captchaImageView.setImage(captchaImage);
                // get captcha solution from response headers and store for later validation
                captchaSolution = /*response.header("X-Captcha-Solution")*/ "m7y77w";
                //System.out.println("Captcha solution: " + captchaSolution);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @FXML
    private void saveTransaction(ActionEvent event) throws MessagingException, SQLException {
                if (!captchaSolution.equals(captchatext.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Captcha incorrect");
                alert.setHeaderText(null);
                alert.setContentText("Le captcha est incorrect !");
                alert.showAndWait();
                return;
            }
        int resJetons_propst = Integer.parseInt(jetons_propst.getText());
        int resJetons_demst = Integer.parseInt(jetons_demst.getText());
        String resCommentairest = commentairest.getText();
        String resFrom_user_itemst = from_user_itemst.getValue().toString();

                // Retrieve the id of the selected item in the from_user_itemst ComboBox  

        // Vérification des champs vides
        if (jetons_propst.getText().isEmpty() || jetons_demst.getText().isEmpty() || resCommentairest.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        // Vérification du nombre de jetons
        TransactionCRUD ppcd = new TransactionCRUD();
        int countJetons = ppcd.getCountJetonsFrom(1);
        if (resJetons_propst > countJetons) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas suffisamment de jetons !");
            alert.showAndWait();
            return;
        }

        // Confirmation avant l'ajout
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation");
        confirm.setHeaderText(null);
        confirm.setContentText("Voulez-vous vraiment envoyer cette transaction ?");

        ButtonType buttonTypeOui = new ButtonType("Oui");
        ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirm.getButtonTypes().setAll(buttonTypeOui, buttonTypeAnnuler);

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == buttonTypeOui) {
            TransactionCRUD pcd = new TransactionCRUD();

//            int newCountJetons = countJetons - resJetons_propst;
//            pcd.updCountJetonsProp(1, newCountJetons);
//            System.out.println("Jetons mis à jour");

            int resFrom_user_item_id = pcd.getItemIdByName(from_user_itemst.getValue());
            String resTo_user_item =pcd.getNameByItemId(getTest());
            int resTo_user_id =pcd.getUserIdByItemId(getTest());
            System.out.println("From user item id: " + resFrom_user_item_id);
            Transaction t = new Transaction(resJetons_propst, resJetons_demst, resCommentairest, 
                    resFrom_user_itemst,resFrom_user_item_id,1, getTest(), resTo_user_item, resTo_user_id);
            pcd.addEntity(t);
            System.out.println("Transaction envoyée");

//                // Set the properties of the mail server
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.outlook.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//    
//        // Set the authentication information
//    //    final String username = "pidev2050@outlook.fr";
//    //    final String password = "aze123..";
//    
//        final String username = "tbadeltrans@outlook.com";
//        final String password = "azerty123";
//    
//            // Create a new session with the authentication information
//            Session session = Session.getInstance(props,
//                    new javax.mail.Authenticator() {
//                        @Override
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(username, password);
//                        }
//                    });
//    
//            // Get the email addresses of all participants of the event
//    
//          String query = "SELECT nom_user,email_user,prenom_user FROM user WHERE id_user IN (SELECT from_user_id FROM transactions WHERE from_user_id = ?)";
//            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
//            statement.setInt(1, 1);
//            ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()) {
//                try {
//                    String recipient = resultSet.getString("email_user");
//                    String nomR = resultSet.getString("nom_user");
//                    String prenomR = resultSet.getString("prenom_user");
//                    String subject = "Notification Transaction:";
//                    String body = "Cher " + nomR + " " + prenomR + ",\n\n Nous sommes ravis de vous informer que votre réservation a été envoyée avec succès. Vous pouvez maintenant accéder à "
//                            + "votre espace de consultation de transactions pour suivre l'état de votre transaction.\n\nNous vous invitons également à parcourir notre sélection de biens "
//                            + "proposés par d'autres utilisateurs et à proposer les vôtres. Vous pourrez ainsi faire de nouvelles connaissances à travers notre application de troc.\n\nNous "
//                            + "vous remercions d'utiliser Tbadel et espérons que vous passerez un excellent moment en échangeant avec notre communauté d'utilisateurs|\n\nCordialement,\n" +
//                                "L'équipe Tbadel.";    
//                    // Create a new message
//                    Message message = new MimeMessage(session);
//    
//                    // Set the recipient, subject, and body of the message
//                    message.setFrom(new InternetAddress(username));
//                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//                    message.setSubject(subject);
//                    message.setText(body);
//    
//                    // Send the message
//                    Transport.send(message);
//                } catch (AddressException ex) {
//                    Logger.getLogger(StartTransactionController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//    
//            System.out.println(" email sent successfully.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTransaction.fxml"));
            try {
                Parent root = loader.load();
                ListeTransactionController dc = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else {
             //Annulation de l'ajout
        }
        //    @FXML
    //    private void saveTransaction(ActionEvent event) {
    //        int resJetons_propst = Integer.parseInt(jetons_propst.getText());
    //        int resJetons_demst = Integer.parseInt(jetons_demst.getText());
    //        String resCommentairest = commentairest.getText();
    //        // Vérification des champs vides
    //        if (jetons_propst.getText().isEmpty() || jetons_demst.getText().isEmpty() || resCommentairest.isEmpty()) {
    //            Alert alert = new Alert(Alert.AlertType.WARNING);
    //            alert.setTitle("Champs vides");
    //            alert.setHeaderText(null);
    //            alert.setContentText("Veuillez remplir tous les champs !");
    //            alert.showAndWait();
    //            return;
    //        }
    //
    //        // Confirmation avant l'ajout
    //        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    //        confirm.setTitle("Confirmation");
    //        confirm.setHeaderText(null);
    //        confirm.setContentText("Voulez-vous vraiment envoyer cette transaction ?");
    //
    //        ButtonType buttonTypeOui = new ButtonType("Oui");
    //        ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
    //
    //        confirm.getButtonTypes().setAll(buttonTypeOui, buttonTypeAnnuler);
    //
    //        Optional<ButtonType> result = confirm.showAndWait();
    //        if (result.get() == buttonTypeOui){
    //            TransactionCRUD pcd = new TransactionCRUD();
    //            Transaction t = new Transaction(resJetons_propst, resJetons_demst, resCommentairest);
    //            pcd.addEntity(t);
    //            System.out.println("Done");
    //    } else {
    //            // Annulation de l'ajout
    //        }
    //    }
    }



    //@FXML
    //void startCaptcha(ActionEvent event) throws IOException {
    //    // Obtenez la valeur du champ captchatext et utilisez-la pour construire la chaîne JSON pour la demande
    //    String captchaValue = captchatext.getText();
    //String requestBodyString = "{'url': '" + captchaValue + "'}";
    //    // Créez un objet RequestBody avec la chaîne JSON
    //    MediaType mediaType = MediaType.parse("application/json");
    //    RequestBody requestBody = RequestBody.create(mediaType, requestBodyString);
    //
    //    // Créez un objet OkHttpClient et un objet Request pour la demande
    //    OkHttpClient client = new OkHttpClient();
    //    Request request = new Request.Builder()
    //            .url("https://image-captcha-solver.p.rapidapi.com/recognizeUrl%22")
    //            .post(requestBody)
    //            .addHeader("content-type", "application/json")
    //            .addHeader("X-RapidAPI-Key", "72a1860d84msh15024c99fb7e206p11200bjsncc83812f32eb")
    //            .addHeader("X-RapidAPI-Host", "image-captcha-solver.p.rapidapi.com")
    //            .build();
    //
    //    // Exécutez la demande avec l'objet OkHttpClient et obtenez la réponse
    //    Response response = client.newCall(request).execute();
    //
    //    if (response.isSuccessful()) {
    //        String responseBodyString = response.body().string();
    //        // Utilisez la réponse pour remplir les champs de la transaction
    //        
    //    } else {
    //        System.out.println("Error: " + response.code() + " " + response.message());
    //    }
    //
    //    // Appelez la classe CaptchaSolver pour résoudre le captcha
    ////    CaptchaSolver captchaSolver = new CaptchaSolver();
    ////    captchaSolver.solveCaptcha(captchaValue);
    //}
    //@FXML
    //private void handleCaptchaButton(ActionEvent event) {
    //    OkHttpClient client = new OkHttpClient();
    //
    //    MediaType mediaType = MediaType.parse("application/json");
    //    String value = "{\r\n    \"url\": \"https://i.ibb.co/PWYYSm1/d1.jpg\"\r\n}";
    //    RequestBody body = RequestBody.create(mediaType, value);
    //    Request request = new Request.Builder()
    //        .url("https://image-captcha-solver.p.rapidapi.com/recognizeUrl")
    //        .post(body)
    //        .addHeader("content-type", "application/json")
    //        .addHeader("X-RapidAPI-Key", "ce2e7d9603msh56d0a5daca1476bp168843jsnd04ec9527f91")
    //        .addHeader("X-RapidAPI-Host", "image-captcha-solver.p.rapidapi.com")
    //        .build();
    //
    //    try {
    //        Response response = client.newCall(request).execute();
    //        System.out.println(response.body().string());
    //    } catch (IOException e) {
    //        e.printStackTrace();
        }
