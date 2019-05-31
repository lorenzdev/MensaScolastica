/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtpsit;

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
            Studente st= new Studente();
            //menu m= new menu();//creare cartella menu
            //piatto p= new piatto();//creare cartella piatto
            String giorno=null;
            String menu=null;
            
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
                case 1: sel = connection.prepareStatement("SELECT idstudente FROM studente");
                        resultSet= sel.executeQuery();
                        int id=0;
                        while(resultSet.next())
                        {
                            id=Integer.parseInt(resultSet.getString("idStudente"));
                        }
                        st.idstudente=id+1;
                        st.username=in.readLine();
                        st.password=in.readLine();
                        st.nome=in.readLine();
                        st.cognome=in.readLine();
                        st.telefono=in.readLine();
                        st.citta=in.readLine();
                        st.indirizzo=in.readLine();
                        st.classe=in.readLine();
                        try
                        {
                        String query = "INSERT INTO studente (idStudente,nome,cognome,classe,telefono,citta,indirizzo,username,password)VALUES"
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
                        
                case 2: try
                        {
                        sel = connection.prepareStatement("SELECT username,password FROM studente");
                        resultSet= sel.executeQuery();
                        
                        st.username=in.readLine();
                        st.password=in.readLine();
                        
                        while(resultSet.next())
                        {
                        if(st.username.equals(resultSet.getString("username"))&&st.password.equals(resultSet.getString("password")))
                        {
                            result=true;
                            out.println(result);
                        }
                        else result=false;
                        }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                case 3: try
                        {   
                        giorno= in.readLine();
                        sel = connection.prepareStatement("SELECT menu.giorno,menu.nome,menu.etichetta,piatto.nome,piatto.numCalorie,piatto.tipologia "
                                + "FROM piatto,menu,inserito"
                                + "WHERE menu.idMenu = inserito.idMenu"
                                + "AND piatto.idPiatto = inserito.idPiatto"
                                + "AND menu.idMenu = ordinano.idMenu"
                                + "AND menu.giorno = "+ giorno
                                + "GROUP BY menu.nome");
                        resultSet= sel.executeQuery();
                        
                        menu=null;
                        risposta=giorno;
                        while(resultSet.next())
                        {
                            if(!resultSet.getString("menu.nome").equals(menu))
                            {
                                risposta=risposta+"Menu "+resultSet.getString("menu.nome")+"\n"
                                        +"Etichetta: "+resultSet.getString("menu.etichetta")+"\n"
                                        +"---------------------------\n";
                            }
                            menu= resultSet.getString("menu.nome");
                            risposta=risposta+ "Piatto: "+resultSet.getString("piatto.nome")+"\n"
                                    +"Calorie: "+resultSet.getString("piatto.numcalorie")+"\n"
                                    +"Tipologia: "+resultSet.getString("piatto.tipologia")+"\n";
                        }
                        out.println(risposta);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                case 4: try
                        {
                        String username=in.readLine();
                        giorno=in.readLine();
                        menu=in.readLine();
                        sel = connection.prepareStatement("SELECT idStudente FROM studente WHERE username = "+username);
                        resultSet=sel.executeQuery();
                        resultSet.next();
                        int idStudente= Integer.parseInt(resultSet.getString("idStudente"));
                        
                       /* 
                       //gestisco multicasting
                        sel= connection.prepareStatement("SELECT idStudente,idMenu FROM ordinano");
                            resultSet=sel.executeQuery();
                            boolean test=false;
                            while(resultSet.next())
                            {
                               int testStudente= Integer.parseInt(resultSet.getString("idStudente")); 
                               String testMenu= resultSet.getString("idMenu");
                               if(testStudente != idStudente && testMenu != menu)
                               {
                                   test=true;
                                   for(int i=0;i<Server.vSocket.size();i++)
                                   {
                                       out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Server.vSocket.get(i))), true);
                                       
                                   }
                               }
                                   
                            }
                      */   
                        sel = connection.prepareStatement("INSERT INTO ordinano(idStudente,idMenu) VALUES("+idStudente+","+menu+","+giorno+")");
                        sel.executeUpdate();
                        out.println(true);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        
                       
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
