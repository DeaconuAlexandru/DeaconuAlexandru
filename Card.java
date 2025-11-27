/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysticspade.core;

/**
 *
 * @author alex
 */


public class Card implements Comparable<Card> {
    private int suit;
    private int rank;
    @SuppressWarnings("FieldMayBeFinal")
    private boolean isEmpty;
    
    static String[] suits = new String[] { "diamond", "heart", "club", "spade" };//Initializam tipurile de carti potrivite pentru joc.
    static String[] ranks = new String[] { "a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k" };//Initializam valorile cartilor de joc
    
    public Card() {//Constructor public care initializeaza tipul si rankul cartilor de joc cu valoare nula.
        rank = 0;
        isEmpty = true;
    }
    
    public Card(int suit, int rank) {//Rescriem constructorul public care primeste ca parametri tipul si rankul cartilor de joc.
        this.suit = suit;
        this.rank = rank;
        isEmpty = false;
    }

    @Override
    public int compareTo(Card o) {//Se compara rankul cartii de joc.
        if (this.getRank() == o.getRank()) return 0;
        return (this.getRank() > o.getRank()) ? 1 : -1;
    }
    
    public int getSuit() {
        return suit;
    }
    
    public int getRank() {
        return rank;
    }
    
    public boolean IsEmpty() {
        return isEmpty;
    }
    
    public static int getSuitsSize() {
        return suits.length;
    }
    
    public static int getRanksSize() {
        return ranks.length;
    }
    
    public String getCardName() {
        return String.format("GameData/sprites/%s%s.png", ranks[rank], suits[suit]);
    }
    
    public void setSuit(int suit) {
        this.suit = suit;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}
