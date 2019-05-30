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
    
    public static String address;
    public static int port;
    
    public ClientMENSA(String address, int port){
        
        try{
            
            String username="";
            // CREO IL SOCKET
            Socket client = new Socket(address, port);
            
            /* CREO LO STREAM SUL SOCKET
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
               
            // SCRIVO SULLO STREAM DEL SOCKET
            out.println(service);
            
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Il Server risponde: "+in.readLine());
            */
            
            guiMenuPrincipale(username,client);
            
            client.close();
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void guiMenuPrincipale(String g_username, Socket g_client)
    {
        System.out.println("Cosa vuoi fare?");
        if (g_username!="")
            System.out.println("Sei loggato come: "+g_username);
        System.out.println("1) Iscriviti");
        System.out.println("2) Login");
        System.out.println("3) Visualizza il menu di un giorno");
        System.out.println("4) Prenotazioni per la mensa");
        
        Scanner sc_input = new Scanner(System.in);
        String str_input = sc_input.nextLine();
        int input = Integer.parseInt(str_input);
        
        switch(input)
        {
            case 1: guiIscrizione(g_client,input);
                break;
            case 2: guiLogin(g_client,input);
                break;
            case 3:System.out.println("Menu");
                break;
            case 4:System.out.println("Prenotazione");
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
        
        System.out.print("Inserisci la data di nascita: ");
        Scanner sc_dataNascita = new Scanner(System.in);
        String dataNascita = sc_dataNascita.nextLine();
        
        System.out.print("Inserisci il tuo indirizzo: ");
        Scanner sc_indirizzo = new Scanner(System.in);
        String indirizzo = sc_indirizzo.nextLine();
        
        System.out.print("Inserisci la città di residenza: ");
        Scanner sc_residenza = new Scanner(System.in);
        String residenza = sc_residenza.nextLine();
        
        System.out.print("Inserisci la classe: ");
        Scanner sc_classe = new Scanner(System.in);
        String classe = sc_classe.nextLine();
        
        System.out.print("Inserisci la sezione: ");
        Scanner sc_sezione = new Scanner(System.in);
        String sezione = sc_sezione.nextLine();
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sig_client.getOutputStream())), true); //creazione stream
        out.println(service);//invio il tipo di servizio richiesto
        out.println(username);//scrivo sullo stream l'username
        out.println(password);//scrivo sullo stream la password
        out.println(nome);
        out.println(cognome);
        out.println(telefono);
        out.println(dataNascita);
        out.println(indirizzo);
        out.println(residenza);
        out.println(classe);
        out.println(sezione);
        BufferedReader in = new BufferedReader(new InputStreamReader(sig_client.getInputStream()));//ricevo risposta dal server
        }
        catch(Exception ex)
            {ex.printStackTrace();}
    }
    
    public static boolean guiLogin(Socket log_client, int service)
    {
            try{
            System.out.print("Inserisci il nome utente: ");
            Scanner sc_username = new Scanner(System.in);
            String username = sc_username.nextLine();

            System.out.print("Inserisci la password: ");
            Scanner sc_password = new Scanner(System.in);
            String password = sc_password.nextLine();
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(log_client.getOutputStream())), true); //creazione stream
            out.println(username);//scrivo sullo stream l'username
            out.println(password);//scrivo sullo stream la password
            BufferedReader in = new BufferedReader(new InputStreamReader(log_client.getInputStream()));//ricevo risposta dal server
            String str_logged = in.readLine();
            
            boolean logged = Boolean.parseBoolean(str_logged);
            
            return logged;
            
            }catch(Exception ex)
                {ex.printStackTrace();
                return false;}
        
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
