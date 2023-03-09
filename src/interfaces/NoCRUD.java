/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Notification;
import java.util.List;

/**
 *
 * @author Maissa
 */
public interface NoCRUD <N>{
    
    public List<N>NotificationList();
     public void ajouternotification(Notification n );
     public void suppNotification( int id);
     public void  upnotification(N n, String type );
}
