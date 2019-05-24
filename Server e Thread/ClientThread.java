/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasockettcp;

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
 * @author lorenzovitale
 */
public class ClientThread extends Thread{
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    static Connection connection =null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    
    // COSTRUTTORE
    public ClientThread(Socket client, Connection conn){
        this.client = client;
        this.connection = conn;
    }
    
    
    public void run(){
       
        
        try{
            
            statement=connection.createStatement();
            
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            //switch 1-4 1(inseriento) 2(login) 3(query1) 4(query2)
            //1 tutti i campi studente, booleano
            //2 username e password, booleano
            //3 giorno, menu e piatti
            //4 username giorno,  booleano
        
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            out.println();
            // CHIUDO I BUFFER E IL SOCKET
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
