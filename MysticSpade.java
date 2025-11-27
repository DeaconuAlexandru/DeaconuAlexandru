/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mysticspade;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mysticspade.core.*;
/**
 *
 * @author alex
 */
public class MysticSpade extends javax.swing.JFrame {
    private Timer clock, playClock;//Initializam o variabila Timer privata pentru a o 
    //folosi in declansarea temporala a selectiilor cartilor de joc.
    
    private JLabel[] playerCards;
    private JLabel[] enemyCards;
    private JLabel[] table;
    private boolean lock = false;
    
    /**
     * Creates new form MysticSpade
     */
    
    public MysticSpade() 
    {
        // Se vor incheia toate procesele aplicatiei pentru 
        // a putea fi reinitiate si reapelate ;
        
        initComponents();
        
        ImageIcon img = new ImageIcon("GameData/images/mysticSpade.png");
        // Se va reinitializa pictograma imaginii;
        this.setIconImage(img.getImage()); // Imaginea din Icon ,
        // Se seteaza pictograma la imaginea obtinuta;  
        
        // La reinitializarea pictogramei imaginii, aceasta va fi resetata default;
        
        setLocationRelativeTo(null);
        winLabel.setVisible(false);
        
        // Apelurile functiilor 
        // "InitGame()" si "Simulate()" se vor incheia dupa finalizarea jocului.
        InitGame();
        Simulate();
    }
    
    private void InitGame() {//Se va initializa inceperea jocului.
        playerCards = new JLabel[] { card1, card2, card3, card4 };//Se va initializeaza cartile de joc ale jucatorului;
        enemyCards = new JLabel[] { card5, card6, card7, card8 };//Se va initializeaza cartile de joc ale adversarului;
        table = new JLabel[] { card9, card10 };//Se aloca intr-o lista doua carti care vor reprezenta cartile finale selectate de catre jucator si inamic.
        
        GameSimulator.InitGame();//Simuleaza jocul dintre doi playeri.
        Card[] hands = GameSimulator.getHandCards();//Se vor selecta automat cartile de joc de catre jucator si inamic.
        Card[] pool = GameSimulator.getPoolCards();//Se vor selecta automat cartile de joc.
        
        for(int i = 0; i < 4; i++) {
            playerCards[i].setIcon(getCardImage(hands[i]));//Se vor genera aleatoriu cartile jucatorului.
            enemyCards[i].setIcon(getCardImage(hands[i + 4]));//Se vor genera aleatoriu cartile inamicului.
        }
        
        for(int i = 0; i < 2; i++)
            table[i].setIcon(getCardImage(pool[i])); 
    }
    
    private void Simulate() {
        ActionListener listener = (ActionEvent event) -> {
            int status = GameSimulator.GetAction();
            
            if(status != 0) 
            {
                playerCards = new JLabel[] { card1, card2, card3, card4 };//Cartile jucatorului.
                enemyCards = new JLabel[] { card5, card6, card7, card8 };//Cartile inamicului.
                table = new JLabel[] { card9, card10 };

                Card[] hands = GameSimulator.getHandCards();//Lista cartilor care au fost selectate.
                Card[] pool = GameSimulator.getPoolCards();//Lista de carti obtinuta in comun.

                for(int i = 0; i < 4; i++) {
                    playerCards[i].setIcon(getCardImage(hands[i]));// Obtine imaginea cartilor setate ca elemente ale listei jucatorului;
                    enemyCards[i].setIcon(getCardImage(hands[i + 4])); // Obtine imaginea cartilor setate ca elemente ale listei adversarului;
                }

                for(int i = 0; i < 2; i++)
                    table[i].setIcon(getCardImage(pool[i])); // Obtine imaginea cartilor setate ca elemente ale listei;

                playerBonus.setText(GameSimulator.getPlayerScore() + "");//Scorul obtinut de jucator
                enemyBonus.setText(GameSimulator.getPlayerScore() + "");//Scorul obtinut de inamic.
            }
            else {
                simulate.setEnabled(true);
                play.setEnabled(true);
            }
        };
        
        clock = new Timer(1000, listener);//Durata producerii animatiei este de 1 s. 
        clock.setRepeats(true);//Se va repeta timpul animatiei pana la incheierea jocului.
    }
    
    private ImageIcon getCardImage(Card card) {
        String filePath = card.IsEmpty() ? "GameData/sprites/back.png" : card.getCardName();
        // Daca numele imaginii nu exista in fisierul proiectului atunci va fi preluata path-ul imaginii;
        // Daca exista path-ul imaginii exista in fisierul proiectului atunci numele cartii obtinute
        // va fi stocat intr-o variabila de tip String.
        
        ImageIcon img = new ImageIcon(filePath); // Se va desena pictograma imaginii selectate
        // din path-ul fisierului corespunzator proiectului;
        Image bm = img.getImage().getScaledInstance( 60, 68,  java.awt.Image.SCALE_AREA_AVERAGING );
        // Se vor stabili dimensiunile medii ale imaginii adaugate;
        // Apoi imaginea finala va fi redimensionata si 
        return new ImageIcon(bm); // inserata in interfata grafica a jocului.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        card3 = new javax.swing.JLabel();
        card1 = new javax.swing.JLabel();
        card2 = new javax.swing.JLabel();
        card4 = new javax.swing.JLabel();
        card5 = new javax.swing.JLabel();
        card6 = new javax.swing.JLabel();
        card7 = new javax.swing.JLabel();
        card8 = new javax.swing.JLabel();
        card9 = new javax.swing.JLabel();
        card10 = new javax.swing.JLabel();
        simulate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        playerBonus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        enemyBonus = new javax.swing.JLabel();
        play = new javax.swing.JButton();
        winLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mystic Spade");

        panel.setBackground(new java.awt.Color(150, 200, 224));

        card3.setToolTipText("Choose this card");
        card3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card3MouseClicked(evt);
            }
        });

        card1.setToolTipText("Choose this card");
        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });

        card2.setToolTipText("Choose this card");
        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });

        card4.setToolTipText("Choose this card");
        card4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card4MouseClicked(evt);
            }
        });

        simulate.setText("Simulate...");
        simulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulateActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 161, 109)));

        jLabel1.setText("Player 1");

        playerBonus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        playerBonus.setForeground(new java.awt.Color(2, 117, 216));
        playerBonus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerBonus.setText("0");

        jLabel2.setText("Player 2");

        enemyBonus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        enemyBonus.setForeground(new java.awt.Color(217, 83, 79));
        enemyBonus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyBonus.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(playerBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(enemyBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(playerBonus))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(enemyBonus)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        play.setText("Play!");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        winLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        winLabel.setForeground(new java.awt.Color(148, 127, 109));
        winLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winLabel.setText("Prize has been winned!");
        winLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(winLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(card9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(card10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(play, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(simulate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(winLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(card9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(simulate)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(play)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulateActionPerformed
        if(winLabel.isVisible()) 
        {  
            // Daca "winLabel" este visibil la nivelul ferestrei grafice,atunci 
            // initial va deveni invisibila.
            winLabel.setVisible(false);
            
            if(playClock.isRunning()) { /* Daca se declanseaza animatia
                atunci se va opri animatia pentru fiecare runda a jocului */
                playClock.stop();
            }
        }
        
        clock.start(); // Declansarea timpului animatiei in timpul jocului;
        simulate.setEnabled(false); // Se dezactiveaza resimularea jocului in momentul in care
        // jocul a inceput deja;
        play.setEnabled(false); // Se dezactiveaza reinceperea jocului;

        /* Dacă simularea a fost terminată, după ce faceți clic din nou pe buton, va reporni. */
        if(clock.isRunning() && GameSimulator.GetAction() == 0)
        {
            // Daca animatia se declanseaza si daca 
            // simularea jocului a fost incheiata atunci 
            
            GameSimulator.NewGame(); // se va resimula jocul de carti
            clock.restart(); // si se va declansa animatia jocului. 
        }
    }//GEN-LAST:event_simulateActionPerformed

    private void GameStart() {
        playerCards = new JLabel[] { card1, card2, card3, card4 };
        enemyCards = new JLabel[] { card5, card6, card7, card8 };
        table = new JLabel[] { card9, card10 };
        
        playerBonus.setText("0");
        enemyBonus.setText("0");
        
        Game.InitGame();
        Card[] hands = Game.getHandCards();
        Card[] pool = Game.getPoolCards();
        
        for(int i = 0; i < 4; i++) {
            playerCards[i].setIcon(getCardImage(hands[i]));
            enemyCards[i].setIcon(getCardImage(new Card()));
        }
        
        for(int i = 0; i < 2; i++)
            table[i].setIcon(getCardImage(pool[i]));
    }
    
    private void GameUpdate() {
        ActionListener listener = (ActionEvent event) -> { // Se va deschide fir de excutie;
            if(lock) // Daca jocul se actualizeaza atunci
            {
                // Jocul anterior se va incheia;
                // si se va verifica daca jucatorul a pierdut sau a castigat;
                boolean gameOver = Game.GameEnd();
                
                GamePaint();
                // Se va stabili scorul final al jucatorilor, 
                // si, in functie de scorul obtinut, va fi castigator jucatorul cu
                // scorul cel mai mare.
                
                if(!gameOver) 
                {
                   // Daca jocatorul nu a pierdut, atunci se 
                    
                    try {
                        Thread.sleep(500);
                        // va intra in pauza de 500 de milisecunde;
                    } 
                    catch (InterruptedException ex) 
                    {
                        Logger.getLogger(MysticSpade.class.getName()).log(Level.SEVERE, null, ex);
                        // Intreruperea executiei;
                    }
                }
            }
        };
        
        playClock = new Timer(1000, listener); // Timpul animatiei - 1 secunda;
        playClock.setRepeats(true); // Se repeta animatia jocului pana la incheiere;
    }
    
    private void GamePaint() // Se va stabili scorul final al jucatorilor
    {
        // si, in functie de scorul obtinut, va fi castigator jucatorul cu
        // scorul cel mai mare.
        // In functia "GamePaint()" este stabilit designul jocului de carti;
        
        playerCards = new JLabel[] { card1, card2, card3, card4 };
        enemyCards = new JLabel[] { card5, card6, card7, card8 };
        table = new JLabel[] { card9, card10 };
        int gameStatus = Game.HasWon();  
        
        if(gameStatus != 0) 
        {  
            winLabel.setVisible(true);
            
            if(gameStatus < 0) 
            {
                //Inamicul a castigat;
                winLabel.setText("Prize was lost!");
                winLabel.setForeground(Color.decode("#D9534F"));
            } 
            else {
                // Inamicul a pierdut;
                winLabel.setText("Prize has been winned!");
                winLabel.setForeground(Color.decode("#947F6D"));
            }
        }
        else { 
            winLabel.setVisible(false);
            // Remiza;
        }
        
        Card[] hands = Game.getHandCards(); // Lista de carti selectate;
        Card[] pool = Game.getPoolCards(); // Lista de carti generate aleatoriu;
        
        playerBonus.setText(Game.getPlayerScore() + "");
        enemyBonus.setText(Game.getEnemyScore() + "");
        
        for(int i = 0; i < 4; i++) {
            playerCards[i].setIcon(getCardImage(hands[i]));
            enemyCards[i].setIcon(getCardImage(new Card()));
        }
        
        if(pool.length > 0) {
            for(int i = 0; i < 2; i++)
                table[i].setIcon(getCardImage(pool[i]));
        }
    }
    
    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        GameStart();
        GameUpdate();
        
        if(clock.isRunning()) { /* Daca aplicatia ruleaza
            atunci se va opri animatia pentru fiecare runda a jocului */
            clock.stop();
        }
    }//GEN-LAST:event_playActionPerformed

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        if(!Game.GameEnd()) // Daca jocul nu s-a terminat
        {
            winLabel.setVisible(false);
            Game.GameInput(0); /* Jocul continua daca  
            nu s-a obtinut scoruri > 0 sau daca unul dintre jucatori
            nu a selectat o carte de joc dintre propriile carti de joc 
            existente care pot fi selectate;
            */
            GamePaint();

            if(!playClock.isRunning()) { /* Daca aplicatia ruleaza
                atunci va porni animatia pentru fiecare runda a jocului */
                playClock.start();
            }
            lock = true; // 
        } 
        else {
            /* Afișează fereastra de mesaj al scorului */
            int score = Game.getScore();
            String msg = "You have reached a draw!";
            
            if(score > 0) msg = "You won the game!";
            else if(score < 0) msg = "You lost this game!";
            
            JOptionPane.showMessageDialog(this, msg, "Mystic Spade", JOptionPane.INFORMATION_MESSAGE);
            /* Dupa ce jocul s-a finalizat se va afisa o fereastra Panel 
            cu titlul "Mystic Spade" iar "JOptionPane.INFORMATION_MESSAGE" este folosit 
            pentru a afisa un mesaj informativ cu referire la situatia finala a jocului.
            */
        }
    }//GEN-LAST:event_card1MouseClicked

    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        if(!Game.GameEnd()) 
        {
            winLabel.setVisible(false);
            Game.GameInput(1);
            
            /* Jocul continua daca  
            nu s-a obtinut scoruri > 0 sau daca unul dintre jucatori
            nu a selectat o carte de joc dintre propriile carti de joc 
            existente care pot fi selectate; */
            
            GamePaint();

            if(!playClock.isRunning()) { /* Daca aplicatia ruleaza
                atunci va porni animatia pentru fiecare runda a jocului */
                playClock.start();
            }
            lock = true;
        }
        else {
            /* Afișează fereastra de mesaj al scorului. */
            int score = Game.getScore();
            String msg = "You have reached a draw!";
            if(score > 0) msg = "You won the game!";
            else if(score < 0) msg = "You lost this game!";
            
            JOptionPane.showMessageDialog(this, msg, "Mystic Spade", JOptionPane.INFORMATION_MESSAGE);
            /* Dupa ce jocul s-a finalizat se va afisa o fereastra Panel 
            cu titlul "Mystic Spade" iar "JOptionPane.INFORMATION_MESSAGE" este folosit 
            pentru a afisa un mesaj informativ cu referire la situatia finala a jocului.
            */
        }
    }//GEN-LAST:event_card2MouseClicked

    private void card3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card3MouseClicked
        if(!Game.GameEnd()) 
        {
            winLabel.setVisible(false);
            Game.GameInput(2);
            GamePaint();
            
            /* Jocul continua daca  
            nu s-a obtinut scoruri > 0 sau daca unul dintre jucatori
            nu a selectat o carte de joc dintre propriile carti de joc 
            existente care pot fi selectate; */
            
            if(!playClock.isRunning()) { /* Daca aplicatia ruleaza
                atunci va porni animatia pentru fiecare runda a jocului */
                playClock.start();
            }

            lock = true;
        }
        else {
            /* Afișează fereastra de mesaj al scorului. */
            int score = Game.getScore();
            String msg = "You have reached a draw!";
            if(score > 0) msg = "You won the game!";
            else if(score < 0) msg = "You lost this game!";
            
            JOptionPane.showMessageDialog(this, msg, "Mystic Spade", JOptionPane.INFORMATION_MESSAGE);
            /* Dupa ce jocul s-a finalizat se va afisa o fereastra Panel 
            cu titlul "Mystic Spade" iar "JOptionPane.INFORMATION_MESSAGE" este folosit 
            pentru a afisa un mesaj informativ cu referire la situatia finala a jocului.
            */
        }
    }//GEN-LAST:event_card3MouseClicked

    private void card4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card4MouseClicked
        if(!Game.GameEnd()) {
            winLabel.setVisible(false);
            Game.GameInput(3);
            GamePaint();
            
             /* Jocul continua daca  
            nu s-a obtinut scoruri > 0 sau daca unul dintre jucatori
            nu a selectat o carte de joc dintre propriile carti de joc 
            existente care pot fi selectate; */
            
            if(!playClock.isRunning()) { /* Daca aplicatia ruleaza
                atunci va porni animatia pentru fiecare runda a jocului */
                playClock.start();
            }

            lock = true;
        }
        else {
            /* Afișează fereastra de mesaj al scorului. */
            int score = Game.getScore();
            String msg = "You have reached a draw!";
            if(score > 0) msg = "You won the game!";
            else if(score < 0) msg = "You lost this game!";
            
            JOptionPane.showMessageDialog(this, msg, "Mystic Spade", JOptionPane.INFORMATION_MESSAGE);
            /* Dupa ce jocul s-a finalizat se va afisa o fereastra Panel 
            cu titlul "Mystic Spade" iar "JOptionPane.INFORMATION_MESSAGE" este folosit 
            pentru a afisa un mesaj informativ cu referire la situatia finala a jocului.
            */
        }
    }//GEN-LAST:event_card4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MysticSpade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MysticSpade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MysticSpade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MysticSpade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MysticSpade().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel card1;
    private javax.swing.JLabel card10;
    private javax.swing.JLabel card2;
    private javax.swing.JLabel card3;
    private javax.swing.JLabel card4;
    private javax.swing.JLabel card5;
    private javax.swing.JLabel card6;
    private javax.swing.JLabel card7;
    private javax.swing.JLabel card8;
    private javax.swing.JLabel card9;
    private javax.swing.JLabel enemyBonus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panel;
    private javax.swing.JButton play;
    private javax.swing.JLabel playerBonus;
    private javax.swing.JButton simulate;
    private javax.swing.JLabel winLabel;
    // End of variables declaration//GEN-END:variables
}
