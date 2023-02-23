/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BACEM
 */
public class MyConnection {
        private String url="jdbc:mysql://localhost:3306/b";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyConnection instance;
    public Connection getCnx() {
        return cnx;
    }

    public static MyConnection getInstance() {
        if (instance==null){
            instance=new MyConnection();
        }
        return instance;
    }

    
    public MyConnection() {
        try {
            
        cnx = DriverManager.getConnection(url,login,pwd);
            System.out.println("Connection établie!");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());        
        }
    
    }
    
}
