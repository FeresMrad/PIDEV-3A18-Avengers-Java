/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFBoxExample {
//    public static void main(String[] args) throws SQLException, IOException {
//        // Connect to the database
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b", "root", "");
////        Statement stmt = conn.createStatement();
////        ResultSet rs = stmt.executeQuery("SELECT * FROM participants");
//// ID de l'événement sélectionné
//int selectedEventId = 1;
//
//// Requête SQL paramétrée pour sélectionner les participants de l'événement sélectionné
//PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM participants WHERE evenement_id = ?");
//pstmt.setInt(1, selectedEventId);
//ResultSet rs = pstmt.executeQuery();
//
//
//        // Create a new PDF document
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//        document.addPage(page);
//        PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);
//
//
//contentStream.beginText();
//contentStream.setFont(PDType1Font.HELVETICA, 12);
//contentStream.newLineAtOffset(100, 700);
//
//float y = 700;
//contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
//contentStream.showText("Liste des Participants");
//y -= 20; // ajuster la position verticale pour le prochain élément
//contentStream.newLineAtOffset(0, -20);
//
//
//
//contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
//contentStream.showText("Généré le " + new Date());
//y -= 15; // ajuster la position verticale pour le prochain élément
//contentStream.newLineAtOffset(0, -15);
//contentStream.newLine();
//
//
//
//while (rs.next()) {
//    String id = rs.getString("id");
//    String evenement_id = rs.getString("evenement_id");
//    String user_id = rs.getString("user_id");
//
//    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//    contentStream.setNonStrokingColor(Color.BLUE);
//    contentStream.showText("ID: " + id);
//    y -= 15; // ajuster la position verticale pour le prochain élément
//    contentStream.newLineAtOffset(0, -15);
//
//    contentStream.setFont(PDType1Font.HELVETICA, 12);
//    contentStream.setNonStrokingColor(Color.BLACK);
//    contentStream.showText("Evenement_id: " + evenement_id + " / user_id" + user_id);
//    y -= 15; // ajuster la position verticale pour le prochain élément
//    contentStream.newLineAtOffset(0, -15);
//    contentStream.newLine();
//}
//
//contentStream.endText();
//contentStream.close();
//        // Save the PDF document
//        document.save("ListeP.pdf");
//        document.close();
//    }
    
    
    
     public static void main(String[] args) throws SQLException, IOException {
        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b", "root", "");
        // ID de l'événement sélectionné
        int selectedEventId = 1;
        // Requête SQL paramétrée pour sélectionner les participants de l'événement sélectionné
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM participants WHERE evenement_id = ?");
        pstmt.setInt(1, selectedEventId);
        ResultSet rs = pstmt.executeQuery();

        genererpdf(rs);

        rs.close();
        pstmt.close();
        conn.close();
    }

    public static void genererpdf(ResultSet rs) throws IOException, SQLException {
        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(100, 700);

        float y = 700;
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.showText("Liste des Participants");
        y -= 20; // ajuster la position verticale pour le prochain élément
        contentStream.newLineAtOffset(0, -20);

        contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
        contentStream.showText("Généré le " + new Date());
        y -= 15; // ajuster la position verticale pour le prochain élément
        contentStream.newLineAtOffset(0, -15);
        contentStream.newLine();

        while (rs.next()) {
            String id = rs.getString("id");
            String evenement_id = rs.getString("evenement_id");
            String user_id = rs.getString("user_id");

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setNonStrokingColor(Color.BLUE);
            contentStream.showText("ID: " + id);
            y -= 15; // ajuster la position verticale pour le prochain élément
            contentStream.newLineAtOffset(0, -15);

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.showText("Evenement_id: " + evenement_id + " / user_id" + user_id);
            y -= 15; // ajuster la position verticale pour le prochain élément
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();
        // Save the PDF document
   document.save("ListeP.pdf");
       document.close();
     
    }
}