/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import services.ServiceUser;
import com.esprit.utils.DataSource;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

 
/**
 * FXML Controller class
 *
 * @author hazem
 */
public class ProfilMembreController implements Initializable {
    public static int idcli = 0;
    SignInMembreController s = new SignInMembreController();   
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
   
    @FXML
    private TextField mail;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tel;
    @FXML
    private Button upd;
    @FXML
    private TextField id;
    @FXML
    private ImageView img;
    Connection mc ; 
    
    PreparedStatement ste2,ste;
    @FXML
    private TextField scr;
    @FXML
    private Button acceuilbtn;
    @FXML
    private AnchorPane rootPane2;
    @FXML
    private ImageView qr;
    @FXML
    private Button qrcode;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Select();
    }   

    @FXML
    private void upd(ActionEvent event) {
        int  id_u  = Integer.parseInt(id.getText());
       ServiceUser sp = new ServiceUser();
       int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Modifier  cet utilisateur" ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
         User a = new User(id_u,nom.getText(), prenom.getText(), Integer.parseInt(tel.getText()) ,  mail.getText() , scr.getText() );
         sp.modifier(a);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("L'utilisateur a été modifié avec succés !");
                        alert.setContentText("Félécitation");

            alert.showAndWait();
        } else if (result == JOptionPane.NO_OPTION) {
           
        } 
    }
     public void affichImage() {
        String Id_stat = nom.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        qr.setImage(im);
        // affichImage();
            
    }

     @FXML
    private String importImg(ActionEvent event) {
         String id =mail.getText();
          Path to = null;
         String  m = null;
         String path = "src/imagesUser/";
         JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg","jpeg","PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );
            
            if(chooser.getSelectedFile() != null){
                
               try {
                   Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path+"\\"+id+".png");
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
             scr.setText(to.toString());
        
    }
        affichpdp();
      return to.toString();      

    }
        public void affichpdp() {
        String Id_stat = mail.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/imagesUser/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        img.setImage(im);
    }
    public void Select(){
        
         mc = DataSource.getInstance().getCnx();
        String sql="SELECT * FROM user where id_user = "+idcli+" " ;

        try{
        ste2 = mc.prepareStatement(sql);
         
        ResultSet rs=ste2.executeQuery();
           while(rs.next()){             
              
                       id.setText(""+rs.getInt(1));
                       nom.setText(rs.getString(3));
                       prenom.setText(rs.getString(2));
                       tel.setText(rs.getString(4));
                       mail.setText(rs.getString(5));
                       scr.setText(rs.getString("pdp"));
                       String path2= rs.getString("pdp");
      

       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
        img.setImage(im);
                       
           }
        }catch (SQLException ex) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
} 

    @FXML
    private void ActionAcceuilbtn(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/InterfaceMembre.fxml"));
        rootPane2.getChildren().setAll(pane);
    }
 private void CodeQr(ActionEvent event) {
        String Id_stat = nom.getText();
        String LoginCode = mail.getText() ;
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
    private void qrAction(ActionEvent event) {
        CodeQr(event);  
        affichImage();
    }
         
    }
    
