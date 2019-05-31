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
public class piatti {
    private String nome;
    private int numerocalorie;
    private String tipologia; 
    
    public piatti()
    {
        numerocalorie = 0;
        nome = null;
        tipologia = null;       
    }

    public void viewStudente()
    {
        System.out.println("----STUDENTE----");
        System.out.println("|"+nome+"|");
        System.out.println("|"+numerocalorie+"|");
        System.out.println("|"+tipologia+"|");
        System.out.println("----------------");
    }
}
