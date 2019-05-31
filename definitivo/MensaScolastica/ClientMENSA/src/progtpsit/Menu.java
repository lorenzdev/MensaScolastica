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
public class Menu {
    private String nome;
    private String etichetta;
    private String giorno; 
    
    public Menu()
    {
        nome = null;
        etichetta = null;
        giorno = null;       
    }

    public void viewStudente()
    {
        System.out.println("----STUDENTE----");
        System.out.println("|"+nome+"|");
        System.out.println("|"+etichetta+"|");
        System.out.println("|"+giorno+"|");
        System.out.println("----------------");
    }
}