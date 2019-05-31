/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package progtpsit;
/**
 *
 * @author gazzadi.davide
 */
public class Studente {
    public int idstudente;
    public String nome;
    public String cognome;
    public String classe;
    public String telefono;
    public String indirizzo;
    public String citta;
    public String username;
    public String password;
    
    public Studente()
    {
        idstudente = 0;
        nome = null;
        cognome = null;
        classe = null;
        telefono = null;
        indirizzo = null;
        citta = null;
        username = null;
        password = null;       
    }

    public void viewStudente()
    {
        System.out.println("----STUDENTE----");
        System.out.println("|"+idstudente+"|");
        System.out.println("|"+nome+"|");
        System.out.println("|"+cognome+"|");
        System.out.println("|"+classe+"|");
        System.out.println("|"+telefono+"|");
        System.out.println("|"+indirizzo+"|");
        System.out.println("|"+citta+"|");
        System.out.println("|"+username+"|");
        System.out.println("|"+password+"|");
        System.out.println("----------------");
    }
}