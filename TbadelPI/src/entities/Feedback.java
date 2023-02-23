/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Feriel
 */
public class Feedback {
      private int id;
    private int transactionId;
    private int rating;
    private String comment;
    private Date date;

    public Feedback(int transactionId, int rating, String comment, Date date) {
        this.transactionId = transactionId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Feedback() {
    }

    public Feedback(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Feedback(int id, int transactionId, int rating, String comment, Date date) {
        this.id = id;
        this.transactionId = transactionId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Feedback(int transactionId, int rating, String comment) {
        this.transactionId = transactionId;
        this.rating = rating;
        this.comment = comment;
    }
    

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", transactionId=" + transactionId + ", rating=" + rating + ", comment=" + comment + ", date=" + date + '}';
    }
    
}
