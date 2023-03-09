/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;







/**
 *
 * @author Maissa
 */
public class FormToPdfConverter {


    
public void genererpdf(ResultSet rs) throws IOException, SQLException, COSVisitorException {
    try ( // Create a new PDF document
            PDDocument document = new PDDocument()) {
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(100, 700);

        float y = 700;
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.showText("votre réclamation");
        y -= 20; // ajuster la position verticale pour le prochain élément
        contentStream.newLineAtOffset(0, -20);

        contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
      //  contentStream.showText("Généré le " + new Date());
        y -= 15; // ajuster la position verticale pour le prochain élément
        contentStream.newLineAtOffset(0, -15);
        contentStream.newLine();

        while (rs.next()) {
            String id = rs.getString("id");
            String user_id = rs.getString("user_id");
            //String user_id = rs.getString("user_id");
            String message = rs.getString("message");
            String status = rs.getString("status");
            String subject = rs.getString("subject");
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setNonStrokingColor(Color.BLUE);
            contentStream.showText("ID: " + id);
            y -= 15; // ajuster la position verticale pour le prochain élément
            contentStream.newLineAtOffset(0, -15);

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.showText("user_id: " + user_id + " / message" + message +" /status: "+status+" /subject: "+ subject );
            y -= 15; // ajuster la position verticale pour le prochain élément
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();
        // Save the PDF document
        document.save("Nouveau document RTF.pdf");
    }
          System.out.println("pdf généré");
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setHeaderText("PDF généré avec succès !");
alert.setContentText("Le fichier PDF a été créé et enregistré avec succès.");
alert.showAndWait();



    }



    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


   /*  private PDFParser parser;
     private PDFTextStripper pdfStripper;
     private PDDocument pdDoc ;
     private COSDocument cosDoc ;
   
     private String Text ;
     private String filePath;
     private File file;

    public FormToPdfConverter() {
    }


   public String ToText() throws IOException{
  
       
       
   
       this.pdfStripper = null;
       this.pdDoc = null;
       this.cosDoc = null;
       
       file = new File(filePath);
         //String r;
        //parser = new PDFParser(new RandomAccessFileInputStream(file, "r")) ;// update for PDFBox V 2.0
        parser=new PDFParser(new FileInputStream(file) );

          
       parser.parse();
       cosDoc = parser.getDocument();
       pdfStripper = new PDFTextStripper();
       pdDoc = new PDDocument(cosDoc);
       pdDoc.getNumberOfPages();
       pdfStripper.setStartPage(1);
       pdfStripper.setEndPage(10);
       
       // reading text from page 1 to 10
       // if you want to get text from full pdf file use this code
       // pdfStripper.setEndPage(pdDoc.getNumberOfPages());
       
       Text = pdfStripper.getText(pdDoc);
       return Text;
   }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }




    
    
    
    public static void main(String[] args) {
    FormToPdfConverter pdfManager = new FormToPdfConverter();
    pdfManager.setFilePath("C:\\Users\\Maissa\\Documents\\NetBeansProjects/Nouveau document RTF.pdf");
    try {
        String text = pdfManager.ToText();
        System.out.println(text);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   */ 

