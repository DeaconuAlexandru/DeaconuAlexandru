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
public class GameSimulator {
    static int current;
    static Random rand;
    static Deck deck;
    
    static ArrayList<Card> pools;
    static ArrayList<Card> playerHand;
    static ArrayList<Card> enemyHand;
    static int order;
    
    static int playerScore; //Scorul playerului
    static int enemyScore; //Scorul inamicului
    
    public static void InitGame() {
        rand = new Random(System.currentTimeMillis());//Se aloca random un timp actual in milisecunde
        deck = new Deck(rand);//Genereaza aleatoriu carti amestecate potrivite pentru joc.  
        
        pools = new ArrayList<>();
        playerHand = new ArrayList<>();//Se aloca lista de carti a jucatorului.
        
        enemyHand = new ArrayList<>();//Se aloca lista de carti a inamcului.
        current = 0;
        
        for(int i = 0; i < 4; i++) {
            playerHand.add(deck.getDeckCards()[i]);//Se vor adauga si genera carti playerului.
            enemyHand.add(deck.getDeckCards()[i + 4]);//Se vor adauga si genera carti inamicului.
        }
        
        for(int j = 0; j < 2; j++)
            pools.add(new Card());
        
        order = rand.nextInt() & 0xFFFFFFF;
        playerScore = 0; enemyScore = 0;
        current = 8;
    }
    
    public static void NewGame() {//Inceperea unui joc nou.
        deck.Shuffle();//Se vor amesteca cartile de joc.
        pools = new ArrayList<>();
        
        playerHand = new ArrayList<>();
        enemyHand = new ArrayList<>();
        current = 0;
        
        for(int i = 0; i < 4; i++) {
            playerHand.add(deck.getDeckCards()[i]);//Se vor adauga cartile amestecate ale jucatorului
            enemyHand.add(deck.getDeckCards()[i + 4]);//Se vor adauga cartile amestecate ale inamicului.
        }
        
        for(int j = 0; j < 2; j++)
            pools.add(new Card());//Adauga o noua carte in lista de carti existente.
        
        order = rand.nextInt() & 0xFFFFFFF;//Se genereaza noi carti de joc.
        playerScore = 0; enemyScore = 0;
        current = 8;
    }
    
    public static int GetAction() {//Actiunea jocului
        Card[] cards = deck.getDeckCards();
        if(current >= cards.length) return 0;//Daca numarul de carti al player-ului este mai mic 
        // decat numarul de carti curent selectate atunci jocul nu se desfasoara.
        
        Card next = cards[current++];//Se va selecta urmatoarea carte.
        int index = rand.nextInt() & 0x3;
        
        /* primul jucator */
        if((order & 1) == 0) {
            /* playerul a ghicit cartea de joc.*/
            if(playerHand.get(index).compareTo(next) >= 0)
                playerScore++;
            
            pools.clear();//Se sterg cartile player-ului care au fost selectate.
            pools.add(next);//Player-ul va adauga la alegere o carte neselectata.
            
            pools.add(playerHand.get(index));//Se va adauga cartea selectata de player.
            playerHand.remove(index);//Se va sterge cartea selectata.         
            playerHand.add(cards[current++]);//Se vor adauga carti de joc.
        }
        else {
            /* inamicul a ghicit, deci da cărțile au un rang mai mare sau egal */
            if(enemyHand.get(index).compareTo(next) >= 0)
                enemyScore++;
            
            pools.clear(); //Se sterg cartile adversarului care au fost selectate.
            pools.add(next);//Adversarul va adauga la alegere o carte neselectata.
            
            pools.add(enemyHand.get(index));//Se adauga in lista cartea selectata de inamic.
            enemyHand.remove(index);//Se sterge din lista cartea selectata.
            enemyHand.add(cards[current++]);//Se vor adauga carti de joc.
        }

        order >>>= 1;
        return 1;
    }
    
    public static Card[] getPoolCards() {
        Card[] array = new Card[pools.size()];
        
        for(int i = 0; i < pools.size(); i++)
            array[i] = pools.get(i);
        
        return array;//Returneaza lista de carti obtinute.
    }
    
    public static Card[] getHandCards() {
        Card[] array = new Card[8];
        
        for(int i = 0; i < 4; i++)
        {
            array[i] = playerHand.get(i);
            array[i + 4] = enemyHand.get(i);
        }
        
        return array;//Returneaza lista de carti selectate.
    }
    
    public static int getScore() {//Scorul obtinut
        if(playerScore > enemyScore) return playerScore;
        // Daca scorul player-ului este mai mare decat cel al inamicului atunci castiga jucatorul, atunci
        // jucatorul a castigat.
        if(playerScore < enemyScore) return -enemyScore; //Altfel adversarul a castigat.
        return 0;
    }
    
    public static int getPlayerScore() { //Scorul obtinut de player
        return playerScore;
    }
    
    public static int getEnemyScore() { //Scorul obtinut de adversar.
        return enemyScore;
    }
}
