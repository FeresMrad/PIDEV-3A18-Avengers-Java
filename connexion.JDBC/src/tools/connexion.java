/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elbarhoumi
 */
public class connexion {
    public String url="jdbc:mysql://localhost:3306/testdatabase";
    public String user="root";
    public String pwd ="";
    private Connection cnx;
    private static connexion mc;

    public connexion(){
        try{
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connected Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static connexion getInstance(){
        if(mc == null)
            mc = new connexion();
        return mc;
    }
    public Connection getCnx(){
        return cnx;
    }

}

