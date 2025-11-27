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
public class Game 
{
    static int current;
    static Random rand;
    static Deck deck;

    static ArrayList<Card> pools;
    static ArrayList<Card> playerHand;
    static ArrayList<Card> enemyHand;
    static int order; //

    static int playerScore;//Scorul jucatorului
    static int enemyScore;//Scorul adversarului
    static int isWinning;//Este castigator.

    public static void InitGame() 
    {
        rand = new Random(System.currentTimeMillis());
        deck = new Deck(rand);

        pools = new ArrayList<>();
        playerHand = new ArrayList<>();

        enemyHand = new ArrayList<>();
        current = 0;

        for (int i = 0; i < 4; i++) {
            playerHand.add(deck.getDeckCards()[i]);
            enemyHand.add(deck.getDeckCards()[i + 4]);
        }

        for (int j = 0; j < 2; j++)
            pools.add(new Card());

        order = rand.nextInt() & 1;
        playerScore = 0;
        enemyScore = 0;
        
        isWinning = 0;
        current = 8; //Numatrul total disponibil de carti initial folosite de catre jucatori.
    }

    public static boolean GameInput(int index) {
        /* Nu e rândul tău, așa că așteaptă o mână, sau jocul se termină! */
        if (order % 2 != 0) return false;
        if (current >= deck.getDeckCards().length) return false;

        /* Se va sterge tot ce se adauga in comun */
        if (!pools.isEmpty()) pools.clear();

        /* Sterge cartea selectata din mana. */
        Card myCard = playerHand.get(index);
        playerHand.remove(myCard);

        pools.add(deck.getDeckCards()[current++]);
        pools.add(myCard);
        
        if(pools.get(0).compareTo(myCard) >= 0) //Daca scorul jucatorului >= 0,atunci
        {
            isWinning = 1; //jucatorul este castigator
            playerScore++;
        }
        else isWinning = -1;

        order++;
        playerHand.add(deck.getDeckCards()[current++]); 
        // Obtine si adauga carti noi la fiecare jucator.
        
        return true;
    }
    
    public static boolean GameEnd()
    {
        Card[] cards = deck.getDeckCards();//Obtine pachetul de carti.
        if(current >= cards.length) return true; // Jucatorul a pierdut;
        
        /* Se va sterge tot ce se adauga in comun */
        if (!pools.isEmpty()) pools.clear();
        int index = rand.nextInt() & 0x3;
        
        if(order % 2 != 0) 
        {
            /* Sterge cartea selectata din mana */
            Card enemyCard = enemyHand.get(index);
            enemyHand.remove(enemyCard);

            pools.add(deck.getDeckCards()[current++]);
            pools.add(enemyCard);

            if(pools.get(0).compareTo(enemyCard) >= 0)
            {
                isWinning = 1;//Inamicul a castigat runda
                enemyScore++; 
                // Scorul creste daca inamicul castiga runda;
            }
            else {
                isWinning = -1; 
                //Niciun jucator nu a castigat runda.
            }
            order++;
            enemyHand.add(deck.getDeckCards()[current++]);
        }
        
        return false; // Jocatorul nu a pierdut;
    }
    
    public static int HasWon() 
    {
        return isWinning;
    }

    public static Card[] getPoolCards() {
        Card[] array = new Card[pools.size()];

        for (int i = 0; i < pools.size(); i++)
            array[i] = pools.get(i);

        return array;
    }

    public static Card[] getHandCards() {
        Card[] array = new Card[8];

        for (int i = 0; i < 4; i++) {
            array[i] = playerHand.get(i);
            array[i + 4] = enemyHand.get(i);
        }

        return array;
    }
    
    

    public static int getScore() {
        if(playerScore > enemyScore) return playerScore;
        if(playerScore < enemyScore) return -enemyScore;
        return 0;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static int getEnemyScore() {
        return enemyScore;
    }
}
