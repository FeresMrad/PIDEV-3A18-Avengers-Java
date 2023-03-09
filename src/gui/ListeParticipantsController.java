
package gui;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import entities.Evenements;
import entities.Participants;
import static gui.PDFBoxExample.genererpdf;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.EvenementsCRUD;
import services.ParticipantsCRUD;
import utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author BACEM
 */
public class ListeParticipantsController implements Initializable {
    @FXML
    private TableView<Participants> tableparticipants;
    @FXML
    private TableColumn<Participants, Integer> tid;
    @FXML
    private TableColumn<Participants, Integer> tuserid;
       @FXML
    private TableColumn<Participants, String> tnom;
          @FXML
    private TableColumn<Participants, String> tprenom;
             @FXML
    private TableColumn<Participants, String> tmail;
    @FXML
    private TextField tfidevent;
    @FXML
    private TextField tfeventnom;
    
    private int event;
    @FXML
    private Button btnretour;
    
    private Date dateE;

    public Date getDateE() {
        return dateE;
    }

    public void setDateE(Date dateE) {
        this.dateE = dateE;
    }

    private String lieue;

    public String getLieue() {
        return lieue;
    }

    public void setLieue(String lieue) {
        this.lieue = lieue;
    }
    


  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      


    }    

    public TableColumn<Participants, Integer> getTid() {
        return tid;
    }

    public void setTid(TableColumn<Participants, Integer> tid) {
        this.tid = tid;
    }

    public TableColumn<Participants, Integer> getTuserid() {
        return tuserid;
    }
    public TableColumn<Participants, String> getTnom() {
        return tnom;
    }

    public void setTnom(TableColumn<Participants, String> tnom) {
        this.tnom = tnom;
    }

    public TableColumn<Participants, String> getTprenom() {
        return tprenom;
    }

    public void setTprenom(TableColumn<Participants, String> tprenom) {
        this.tprenom = tprenom;
    }

    public TableColumn<Participants, String> getTmail() {
        return tmail;
    }

    public void setTmail(TableColumn<Participants, String> tmail) {
        this.tmail = tmail;
    }
    public void setTuserid(TableColumn<Participants, Integer> tuserid) {
        this.tuserid = tuserid;
    }
    

    public TextField getTfidevent() {
        return tfidevent;
    }

    public TextField getTfeventnom() {
        return tfeventnom;
    }

    public void setTfeventnom(String message) {
        this.tfeventnom.setText(message);
    }

    public void setTfidevent(String message) {
        this.tfidevent.setText(message);
        
    }
      public int getEvent() {
      
        return event;
    }

    public void setEvent(int event) {
    
        this.event = event;
        
         showParticipants();
    }
    public void showParticipants() {
    try {
        tableparticipants.getItems().clear();
        int evenementId = getEvent();
        ParticipantsCRUD pcd = new ParticipantsCRUD();
     
      
        ObservableList<Participants> data = FXCollections.observableArrayList(pcd.entitiesListP(evenementId));
        
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tuserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableparticipants.setItems(data);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    
    

    @FXML
    private void retourE(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEvenements.fxml"));
    try {
        Parent root = loader.load();
        ListeEvenementsController dc = loader.getController();
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListeEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    
    
     private int EventSelectionner() {
        int selectedItem = tableparticipants.getSelectionModel().getSelectedItem().getEvenement_id();
        int selectedIndex = tableparticipants.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }
     
     
     
     private static float sum(float[] arr, int endIndex) {
    float sum = 0;
    for (int i = 0; i <= endIndex; i++) {
        sum += arr[i];
    }
    return sum;
}

     
     
 public void genererpdf(ResultSet rs) throws IOException, SQLException {
        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(100, 700);

        float y = 700;
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.showText("Liste des Participants de l'evenement: "+tfeventnom.getText());
        y -= 20; // ajuster la position verticale pour le prochain élément
        contentStream.newLineAtOffset(0, -20);

        contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
        contentStream.showText("Généré le " + new Date());
        y -= 15; // ajuster la position verticale pour le prochain élément
        contentStream.newLineAtOffset(0, -15);
        contentStream.newLine();

        while (rs.next()) {
//            String id = rs.getString("id");
            String evenement_id = rs.getString("evenement_id");
//            String user_id = rs.getString("user_id");
                String nom = rs.getString("nom_user");
        String prenom = rs.getString("prenom_user");
        String email = rs.getString("email_user");


//            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//            contentStream.setNonStrokingColor(Color.BLUE);
//            contentStream.showText("ID: " + id);
            y -= 15; // ajuster la position verticale pour le prochain élément
            contentStream.newLineAtOffset(0, -15);

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.showText("Nom: "+nom+" "+ prenom +"   Email: "+email);
            y -= 15; // ajuster la position verticale pour le prochain élément
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();
        // Save the PDF document
   document.save("ListeParticipants.pdf");
       document.close();
          System.out.println("pdf généré");
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setHeaderText("PDF généré avec succès !");
alert.setContentText("Le fichier PDF a été créé et enregistré avec succès.");
alert.showAndWait();



    }
 
     
     private int getNumRows(ResultSet rs) throws SQLException {
    int numRows = 0;
    if (rs.last()) {
        numRows = rs.getRow();
        rs.beforeFirst();
    }
    return numRows;
}

   
    @FXML
    private void genererpdf(ActionEvent event) throws IOException {
        try {
//            PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement("SELECT p.id,p.evenement_id, p.user_id, u.nom, u.prenom, u.email FROM participants p JOIN users u ON p.user_id = u.id WHERE p.evenement_id = ?");
             PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement("SELECT p.evenement_id, u.nom_user, u.prenom_user, u.email_user FROM participants p JOIN user u ON p.user_id = u.id_user WHERE p.evenement_id = ?");

            pstmt.setInt(1, getEvent());
            ResultSet rs = pstmt.executeQuery();
            
            genererpdf(rs);
            
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListeParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
   private void sendReminderToParticipants(ActionEvent event) throws SQLException, MessagingException {

    // Set the properties of the mail server
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.outlook.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Set the authentication information
//    final String username = "pidev2050@outlook.fr";
//    final String password = "aze123..";

    final String username = "tbadel2023@outlook.fr";
    final String password = "azerty123";

    // Create a new session with the authentication information
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

   
  
  String query = "SELECT email_user,nom_user FROM user WHERE id_user IN (SELECT user_id FROM participants WHERE evenement_id = ?)";
    PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
    statement.setInt(1, getEvent());
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
        try {
            String recipient = resultSet.getString("email_user");
            String subject = "Reminder: Evenement prévu:"+tfeventnom.getText();
            String body = "Dear "+resultSet.getString("nom_user")+",\n\nThis is a friendly reminder that you have an Event :"+tfeventnom.getText()+".\n\n We hope to see you there on "+getDateE()+" in "+getLieue()+"\n\nBest regards";
            
            // Create a new message
            Message message = new MimeMessage(session);
            
            // Set the recipient, subject, and body of the message
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            
            // Send the message
            Transport.send(message);
        } catch (AddressException ex) {
            Logger.getLogger(ListeParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    System.out.println("Reminder emails sent successfully to all participants of the event.");
}
}
