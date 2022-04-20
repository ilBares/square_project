package it.marzoli.baresi.monitor.view;

import java.awt.*;
import javax.swing.*;

import it.marzoli.baresi.monitor.model.SquareThread;

/**
 * Classe che stampa e gestisce la pista
 * "extends JFrame" serve per renderlo un componente grafico
 */
public class Race extends JFrame {
    // salva le posizioni finali di arrivo dei quadrati
    private int position;
    private Square[] squares;
    private SquareThread[] squareThreads;
    private Background track;
    private Graphics offScreen;
    private Image virtualBuffer;

    /**
     * metodo costruttore
     */
    public Race() {
        // la stringa passata corrisponde al titolo della finestra
        super("Horse Race");

        // imposto la dimensione della finestra
        setSize(1000, 645);

        // imposto la x e la y dove verrà stampata la finestra
        setLocation(10, 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        track = new Background();
        squares = new Square[6];
        squareThreads = new SquareThread[6];
        position = 1;
    }

    /**
     * Metodo che crea i 6 quadrati
     * 1) creo i quadrati a livello grafico
     * 2) creo i thread che si occuperanno di muoverli
     * 
     */
    public void startRace() {
        // posizione iniziale di ogni quadrato
        int startPosition = 15;
        
        // creo un vettore di colori, 1 per ogni quadrato
        Color[] colors = new Color[6];
        colors[0] = Color.BLUE;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.CYAN;
        colors[4] = Color.ORANGE;
        colors[5] = Color.PINK;

        for (int i = 0; i < 6; i++) {
            // creo i quadrati "grafici", passandogli il numero,
            // la posizione iniziale e il colore
            squares[i] = new Square(i+1, startPosition, colors[i]);

            // creo i threads che si occuperanno di gestire i quadrati
            // creaiamo 1 squareThreads per ogni quadrato, e gli passiamo
            // il quadrato che deve gestire ("sqaures[i]")
            squareThreads[i] = new SquareThread(squares[i], this);

            // ogni volta incremento la posizione iniziale verticale di 100px
            // (se non lo facessi tutti i quadrati sarebbero stampati nello stesso punto)
            startPosition = startPosition + 100;
        }

        // aggiungo al pannello uno sfondo (track)
        this.add(track);
        // rendo visibile il pannello
        setVisible(true);
    }

    /**
     * metodo che restituisce la posizione di arrivo
     */
    public synchronized int getPosition() {
        return position++;
    }

    /**
     * metodo che controlla se sono arrivati tutti al traguardo
     */
    public synchronized void checkArrivals() {
        boolean arrived = true;
        for (int i = 0; i < 6; i++) {
            // controllo se i quadrati sono arrivati a destinazione attraverso
            // la loro posizione di arrivo (se è 0 non sono ancora arrivati)
            if (squareThreads[i].getPosition() == 0) {
                arrived = false;
            }
        }
        // se sono arrivati tutti stampo la classifica
        if (arrived) {
            showRanking();
        }
    }

    /**
     * metodo che stampa la classifica finale
     */
    private void showRanking() {
        // le JLabel corrispondono alle scritte delle posizioni finali
        JLabel[] arrived;
        arrived = new JLabel[6];

        // JFrame mi serve per stampare una finestra con la classifica
        JFrame ranking = new JFrame("Ranking");

        // imposto la dimensione della finestra
        ranking.setSize(500, 500);

        // imposto le coordinate x e y della finestra (in pixel)
        ranking.setLocation(280, 130);

        // imposto il colore dello sfondo a BLUE
        ranking.setBackground(Color.BLUE);
        ranking.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // imposto il layout
        // serve a stampare le posizioni di arrivo una sotto l'altra
        // 6 rappresenta il numero di righe
        // 1 rappresenta il numero di colonne
        ranking.getContentPane().setLayout(new GridLayout(6, 1));

        // stampo la classifica
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (squareThreads[j].getPosition() == i) {
                    arrived[j] = new JLabel(i + " - CORSIA: " + (j+1));

                    // imposto il font della classifica
                    // "ITALIC" significa "corsivo", 20 è la dimensione del font
                    arrived[j].setFont(new Font("Times New Roman", Font.ITALIC, 20));

                    // il foreground corrisponde al colore della scritta
                    arrived[j].setForeground(squares[j].getColor());

                    // aggiungo la scritta sulla finestra (la stampo)
                    ranking.getContentPane().add(arrived[j]);
                }
            }
        }

        // imposto la finestra come visibile (di default è invisibile)
        ranking.setVisible(true);
    }

    /**
     * metodo necessario aggiornare la grafica
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * metodo che stampa i vari quadrati
     * può essere adattato per stampare immagini
     */
    @Override
    public void paint(Graphics g) {
        if (squares != null) {
            Graphics2D screen = (Graphics2D)g;
            virtualBuffer = createImage(1000, 645);
            offScreen = virtualBuffer.getGraphics();
            track.paint(offScreen);
            for (int i = 0; i < 6; i++) {
                squares[i].paint(offScreen);
            }

            screen.drawImage(virtualBuffer, 0, 30, this);
            offScreen.dispose();
        }
    }


}
