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
    
    public ClientMENSA(String address, int port){
        
        try{
            // CREO IL SOCKET
            Socket client = new Socket(address, port);
            
            // CREO LO STREAM SUL SOCKET
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
               
            // SCRIVO SULLO STREAM DEL SOCKET
            out.println(service);
            
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Il Server risponde: "+in.readLine());
            client.close();
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void guiMenuPrincipale(String g_username)
    {
        System.out.println("Cosa vuoi fare?");
        System.out.println("Sei loggato come: "+g_username);
        System.out.println("1) Iscriviti");
        System.out.println("2) Visualizza il menu di un giorno");
        System.out.println("3) Prenotazioni per la mensa");
    }
    
    public static void guiIscrizione()
    {
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
        Scanner sc_address = new Scanner(System.in);
        String address = sc_address.nextLine();
        
        System.out.print("Inserisci la città di residenza: ");
        Scanner sc_residenza = new Scanner(System.in);
        String residenza = sc_residenza.nextLine();
        
        System.out.print("Inserisci la classe: ");
        Scanner sc_classe = new Scanner(System.in);
        String classe = sc_classe.nextLine();
        
        System.out.print("Inserisci la sezione: ");
        Scanner sc_sezione = new Scanner(System.in);
        String sezione = sc_sezione.nextLine();
    }
    
    public static void guiLogin()
    {
        System.out.print("Inserisci il nome utente: ");
        Scanner sc_username = new Scanner(System.in);
        String username = sc_username.nextLine();
        
        System.out.print("Inserisci la password: ");
        Scanner sc_password = new Scanner(System.in);
        String password = sc_password.nextLine();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String username="";
        
        System.out.print("Inserisci l'indirizzo IP del server: ");
        Scanner sc_address = new Scanner(System.in);
        String address = sc_address.nextLine();
        
        System.out.print("Inserisci la porta su cui il server fornisce il servizio: ");
        Scanner sc_port = new Scanner(System.in);
        String str_port = sc_port.nextLine();
        
        int port = Integer.parseInt(str_port);
        
        guiLogin();
        
        guiMenuPrincipale(username);
        
        
        new ClientMENSA(address, port);
        
    }
    
}
