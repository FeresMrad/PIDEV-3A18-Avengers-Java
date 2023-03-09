/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import entities.User;
import services.ServiceUser;
/**
 *
 * @author hazem
 */
public class Session {

    private static final ServiceUser fs = new ServiceUser();

    private static Session instance = null;
    private static User user = null;

    private Session(User userConnected) {
        super();
        Session.user = userConnected;

    }

    private Session(User userConnected, Boolean b) {
        super();
        Session.user = userConnected;
    }

    public final static Session getInstance(User userConnected) {

        if (Session.instance == null) {
            Session.instance = new Session(userConnected);
        }
        return Session.instance;
    }

    public final static Session getFirstInstance(User userConnected) {

        if (Session.instance == null) {

            Session.instance = new Session(userConnected, false);

        }
        return Session.instance;
    }

    public static ServiceUser getFs() {
        return fs;
    }

    public static Session getInstance() {
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }

}