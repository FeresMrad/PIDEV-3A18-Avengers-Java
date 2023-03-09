/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nouhe
 */
public class GraphesController implements Initializable {

    @FXML
    private AnchorPane panePieChart;
    @FXML
    private AnchorPane paneBarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            grapheOne();
            grapheTwo();
        } catch (SQLException | IOException e) {
            System.err.println("Erreur lors de la génération des graphes: " + e.getMessage());
        }

    }
    public void grapheOne() throws SQLException, IOException {
    String requete = "SELECT c.categorie, COUNT(*) AS nombre_produits FROM items i JOIN categories c ON i.id_categorie = c.idC GROUP BY c.categorie";
    Statement st = MyConnection.getInstance().getCnx().createStatement();
    ResultSet rs = st.executeQuery(requete);

    DefaultPieDataset dataset = new DefaultPieDataset();
    while (rs.next()) {
        dataset.setValue(rs.getString("categorie"), rs.getInt("nombre_produits"));
    }

    JFreeChart chart = ChartFactory.createPieChart("La répartiton des catégories", dataset, true, true, false);

    PiePlot plot = (PiePlot) chart.getPlot();

    // Afficher uniquement les pourcentages
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}%"));

    SwingNode swingNode = new SwingNode();
    swingNode.setContent(new ChartPanel(chart));

    panePieChart.getChildren().add(swingNode);
    AnchorPane.setTopAnchor(swingNode, 0.0);
    AnchorPane.setBottomAnchor(swingNode, 0.0);
    AnchorPane.setLeftAnchor(swingNode, 0.0);
    AnchorPane.setRightAnchor(swingNode, 0.0);
}


    public void grapheTwo() throws SQLException, IOException {
        String requete = "SELECT c.categorie, COUNT(*) AS nombre_produits FROM items i JOIN categories c ON i.id_categorie = c.idC GROUP BY c.categorie";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (rs.next()) {
            dataset.setValue(rs.getInt("nombre_produits"), "Nombre de produits", rs.getString("categorie"));
        }

        JFreeChart chart = ChartFactory.createBarChart("Nombre de produits par catégorie", "Catégorie", "Nombre de produits", dataset, PlotOrientation.HORIZONTAL, true, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(new ChartPanel(chart));

        paneBarChart.getChildren().add(swingNode);
        AnchorPane.setTopAnchor(swingNode, 0.0);
        AnchorPane.setBottomAnchor(swingNode, 0.0);
        AnchorPane.setLeftAnchor(swingNode, 0.0);
        AnchorPane.setRightAnchor(swingNode, 0.0);
    }

}

