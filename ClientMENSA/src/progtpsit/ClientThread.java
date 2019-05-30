/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provoaoa;

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
            boolean result=false;
            PreparedStatement sel;
            String risposta=null;
            studente st= new studente();
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            //switch 1-4 1(inseriento) 2(login) 3(query1) 4(query2)
            //1 tutti i campi studente, booleano
            //2 username e password, booleano
            //3 giorno, menu e piatti
            //4 username giorno,  booleano
            String sw= in.readLine();
            switch(Integer.parseInt(sw))
            {
                case 1: sel = connection.prepareStatement("SELECT idstudente FROM utenti");
                        resultSet= sel.executeQuery();
                        int id=0;
                        while(resultSet.next())
                        {
                            id=Integer.parseInt(resultSet.getString("idStudente"));
                        }
                        st.idstudente=id+1;
                        st.nome=in.readLine();
                        st.cognome=in.readLine();
                        st.classe=in.readLine();
                        st.telefono=in.readLine();
                        st.citta=in.readLine();
                        st.indirizzo=in.readLine();
                        st.username=in.readLine();
                        st.password=in.readLine();
                        try
                        {
                        String query = "INSERT INTO utenti (idStudente,nome,cognome,classe,telefono,citta,indirizzo,username,password)VALUES"
                        + "('"+st.idstudente+"','"+st.nome+"','"+st.cognome+"','"+st.classe+"','"+st.telefono+"','"+st.citta+"','"+st.indirizzo+"','"+st.username+"','"+st.password+"')";
                        sel.executeUpdate();
                        result=true;
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                            result=false;
                            
                        }
                        finally
                        {
                            out.println(result);
                        }
                        break;
                        
                case 2: sel = connection.prepareStatement("SELECT username,password FROM utenti");
                        resultSet= sel.executeQuery();
                        
                        st.username=in.readLine();
                        st.password=in.readLine();
                        
                        if(st.username.equals(resultSet.getString("username"))&&st.password.equals(resultSet.getString("password")))
                            out.println(true);
                        else out.println(false);
                        break;
                case 3:
                case 4:
                default:
            }
            // CHIUDO I BUFFER E IL SOCKET
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
