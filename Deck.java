/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysticspade.core;
import java.util.*;

/**
 *
 * @author alex
 */
public class Deck 
{
    Card[] cards;
    int[] index;
    static Random rand;
    
    public Deck(Random gen) 
    {
        cards = new Card[52];
        index = new int[52];
        rand = gen;
        
        boolean[] busy = new boolean[52];
        for(int i = 0; i < busy.length; i++) busy[i] = false;
        
        for(int i = 0; i < Card.getSuitsSize(); i++) 
        {
            for(int j = 0; j < Card.getRanksSize(); j++)
                cards[j * Card.getSuitsSize() + i] = new Card(i, j);
                //Initializeaza noi carti de joc cu valorile corespunzatoare.
        }
        
        int count = 52;
        int k = 0;
        
        while(count > 0) {
            int id = rand.nextInt(0, 52);
            
            if(!busy[id]) 
            {
                index[k++] = id;
                busy[id] = true;
                count--;
                
                //Cat timp lista de carti exista atunci cartile vor fi generate aleatoriu si amestecate.
            }
        }
    }
    
    public void Shuffle() { // Amesteca carti de joc;
        boolean[] busy = new boolean[52];
        for(int i = 0; i < busy.length; i++) busy[i] = false;

        int count = 52;
        int k = 0;
        
        while(count > 0) {
            int id = rand.nextInt(0, 52);
            
            if(!busy[id]) {
                index[k++] = id;
                busy[id] = true;
                count--;
            }
        }
    }
    
    public Card[] getDeckCards() { 
        Card[] all = new Card[52];
        
        for(int k = 0; k < 52; k++)
            all[k] = cards[index[k]];
        
        return all;// Va returna lista de carti generata automat. 
    }
}
