/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package progtpsit;

import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author coppelli.stefano
 */
public class ClientMENSA {

    /**
     * @param args the command line arguments
     */
    
    private static String service;
    public static boolean logged=false;
    public static String address;
    public static int port;
    
    String global_username = "";
    
    public ClientMENSA(String address, int port){ //FUNZIONE CLIENT
        
        try{
            
            
            // CREO IL SOCKET
            Socket client = new Socket(address, port); //CREAZIONE SOCKET
            
            /* CREO LO STREAM SUL SOCKET
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
               
            // SCRIVO SULLO STREAM DEL SOCKET
            out.println(service);
            
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Il Server risponde: "+in.readLine());
            */
            
            guiMenuPrincipale(client);
            
            client.close();
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void guiMenuPrincipale(Socket g_client)
    {
        while(true)
        {
        System.out.println("Cosa vuoi fare?");
        if (global_username!="")
            System.out.println("Sei loggato come: "+global_username);
        System.out.println("1) Iscriviti");
        System.out.println("2) Login");
        System.out.println("3) Visualizza il menu di un giorno");
        System.out.println("4) Prenotazioni per la mensa");
        System.out.println("5) Servizio di multicasting");
        
        Scanner sc_input = new Scanner(System.in);
        String str_input = sc_input.nextLine();
        int input = Integer.parseInt(str_input);
        
        switch(input)
        {
            case 1: guiIscrizione(g_client,input);
                break;
            case 2: guiLogin(g_client,input);
                break;
            case 3: guiVisualizzaMenu(g_client);
                break;
            case 4: guiPrenotati(g_client);
                break;
            case 5: multicasting(g_client);
                break;
        }
        try{
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(g_client.getOutputStream())), true); //creazione stream
        out.println(input);//scrivo sullo stream il servizio richiesto
        BufferedReader in = new BufferedReader(new InputStreamReader(g_client.getInputStream()));//ricevo risposta dal server
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        }
    }
    
    public static void guiIscrizione(Socket sig_client,int service)
    {
        try{
        System.out.print("Inserisci il nome utente: ");
        Scanner sc_username = new Scanner(System.in);
        String username = sc_username.nextLine();
        
        System.out.print("Inserisci la password: ");
        Scanner sc_password = new Scanner(System.in);
        String password = sc_password.nextLine();
        
        System.out.print("Inserisci il nome: ");
        Scanner sc_nome = new Scanner(System.in);
        String nome = sc_nome.nextLine();
        
        System.out.print("Inserisci il cognome: ");
        Scanner sc_cognome = new Scanner(System.in);
        String cognome = sc_cognome.nextLine();
        
        System.out.print("Inserisci il numero di telefono: ");
        Scanner sc_telefono = new Scanner(System.in);
        String telefono = sc_telefono.nextLine();
        
        System.out.print("Inserisci il tuo indirizzo: ");
        Scanner sc_indirizzo = new Scanner(System.in);
        String indirizzo = sc_indirizzo.nextLine();
        
        System.out.print("Inserisci la città di residenza: ");
        Scanner sc_residenza = new Scanner(System.in);
        String residenza = sc_residenza.nextLine();
        
        System.out.print("Inserisci la classe: ");
        Scanner sc_classe = new Scanner(System.in);
        String classe = sc_classe.nextLine();
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sig_client.getOutputStream())), true); //creazione stream
        out.println(service);//invio il tipo di servizio richiesto
        out.println(username);//scrivo sullo stream l'username
        out.println(password);//scrivo sullo stream la password
        out.println(nome);
        out.println(cognome);
        out.println(telefono);
        out.println(indirizzo);
        out.println(residenza);
        out.println(classe);
        BufferedReader in = new BufferedReader(new InputStreamReader(sig_client.getInputStream()));//ricevo risposta dal server
        String flagIscrizione = in.readLine();
        boolean bool_flagIscrizione = Boolean.parseBoolean(flagIscrizione);
        }
        catch(Exception ex)
            {ex.printStackTrace();}
    }
    
    public boolean guiLogin(Socket log_client, int service)
    {
        
            try{
            System.out.print("Inserisci il nome utente: ");
            Scanner sc_username = new Scanner(System.in);
            String username = sc_username.nextLine();

            System.out.print("Inserisci la password: ");
            Scanner sc_password = new Scanner(System.in);
            String password = sc_password.nextLine();
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(log_client.getOutputStream())), true); //creazione stream
            out.println(service);
            out.println(username);//scrivo sullo stream l'username
            out.println(password);//scrivo sullo stream la password
            BufferedReader in = new BufferedReader(new InputStreamReader(log_client.getInputStream()));//ricevo risposta dal server
            String str_logged = in.readLine();
            
            logged = Boolean.parseBoolean(str_logged);
            if(logged=true)
            {
                username=global_username;
                System.out.println("Sei loggato!!!!!!!!!!");
            }
            return logged;
            
            }catch(Exception ex)
                {ex.printStackTrace();
                logged=false;
                return logged;}
        
    }
    
    public static void guiVisualizzaMenu(Socket v_client)
    {
        System.out.println("Inserisci il giorno di cui vuoi vedere il menu: ");
        Scanner sc_reqMenu = new Scanner(System.in);
        String reqMenu = sc_reqMenu.nextLine();
        try{
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(v_client.getOutputStream())), true); //creazione stream
            out.println(service);
            out.println(reqMenu);//scrivo sullo stream il menu richiesto
            BufferedReader in = new BufferedReader(new InputStreamReader(v_client.getInputStream()));//ricevo risposta dal server
            String outMenu = in.readLine();
            System.out.println(outMenu);
        }catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Impossibile visualizzare il menu.");
        }
    }
    
    public static void guiPrenotati(Socket p_client)
    {
        if(logged)
            {
        try{
            
            System.out.println("Inserisci il giorno per cui vuoi prenotarti alla mensa: ");
            Scanner sc_reqBooking = new Scanner(System.in);
            String reqBooking = sc_reqBooking.nextLine();
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(p_client.getOutputStream())), true); //creazione stream
            out.println(service);
            out.println(reqBooking);//scrivo sullo stream il giorno
            BufferedReader in = new BufferedReader(new InputStreamReader(p_client.getInputStream()));//ricevo risposta dal server
            String outMenu = in.readLine();
            
        }catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Prenotazione fallita.");
        }
            }
        else{
            System.out.println("Non sei loggato!!!");
        }
    }
    
    public static void multicasting(Socket p_client)
    {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(p_client.getInputStream()));
            while(true)
            {
            String outMulticast = in.readLine();
            System.out.println(outMulticast);
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("Inserisci l'indirizzo IP del server: ");
        Scanner sc_address = new Scanner(System.in); 
        address = sc_address.nextLine();

        System.out.print("Inserisci la porta su cui il server fornisce il servizio: ");
        Scanner sc_port = new Scanner(System.in);
        String str_port = sc_port.nextLine();

        port = Integer.parseInt(str_port);

        new ClientMENSA(address,port);

        
        
    }
    
}
