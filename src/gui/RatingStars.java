/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Feriel
 */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RatingStars extends HBox {
    
    private final DoubleProperty rating = new SimpleDoubleProperty();
    private final int maxStars = 5;
    
    public RatingStars() {
        setAlignment(Pos.CENTER_LEFT);
        for (int i = 0; i < maxStars; i++) {
            Rectangle star = createStar();
            star.setOnMouseClicked(event -> setRating((double) (getChildren().indexOf(star) + 1)));
            getChildren().add(star);
        }
    }
    
    private Rectangle createStar() {
        Rectangle star = new Rectangle(20, 20, Color.WHITE);
        star.setStroke(Color.BLACK);
        star.setFill(Color.TRANSPARENT);
        star.setStrokeWidth(2);
        return star;
    }
    
    public DoubleProperty ratingProperty() {
        return rating;
    }
    
    public double getRating() {
        return rating.get();
    }
    
    public void setRating(double value) {
        rating.set(value);
        updateStars();
    }
    
    private void updateStars() {
        for (int i = 0; i < maxStars; i++) {
            Rectangle star = (Rectangle) getChildren().get(i);
            if (i < rating.get()) {
                star.setFill(Color.YELLOW);
            } else {
                star.setFill(Color.TRANSPARENT);
            }
        }
    }
    
}

