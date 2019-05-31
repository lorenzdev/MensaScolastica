/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package progtpsit;

import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author gazzadi.davide
 */
public class Server {
    static public ArrayList<Socket> vSocket=new ArrayList<Socket>(); 
    static final String DB_URL = "jdbc:mysql://localhost/mensa";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    static int port=3333;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            
            Class.forName(DB_DRV);
           connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
           statement=connection.createStatement();
            // CREO IL SOCKET SUL SERVER
            ServerSocket socket = new ServerSocket(port);
            
            
            while(true){
                
                Socket client = socket.accept();
                vSocket.add(client);
                // CREO IL THREAD CHE GESTISCE LA RICHIESTA DEL CLIENT
                ClientThread newConnect = new ClientThread(client,connection);
                newConnect.start();
                
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
