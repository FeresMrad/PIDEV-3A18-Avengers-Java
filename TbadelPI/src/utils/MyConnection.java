/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

/**
 *
 * @author Feriel
 */
public class MyConnection {

    
//ajouter le nom reel de base
    private String url = "jdbc:mysql://localhost:3306/tbadel";
    private String login = "root";
    private String pwd = "";
    private Connection cnx;
    private static MyConnection instance;

    public MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
}
