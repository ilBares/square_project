package it.marzoli.baresi.monitor.model;

import it.marzoli.baresi.monitor.view.*;

/**
 * Classe che rappresenta il "modello" di un quadrato
 * contiene:
 * la dimensione della pista ("DIM_RACE")
 * la rappresentazione grafica del quadrato ("square")
 * la pista ("race")
 * 
 */
public class SquareThread implements Runnable{
    // 960 indica la dimensione della pista in pixel
    public static final int DIM_RACE = 960;
    // contiere l'immagine del quadrato
    private Square square;
    // rappresenta la pista dove gareggiano i quadrati
    private Race race;
    // velocità di movimento (di quanti pixel mi sposto ogni volta)
    private int speed;
    private Thread t;
    // serve per scegliere quando cambiare in modo random la velocità
    private int speedCounter;
    // posizione finale del quadrato
    private int position;

    public SquareThread(Square _square, Race _race) {
        square = _square;
        race = _race;

        speed = 2;
        speedCounter = 0;
        position = 0;
        
        t = new Thread(this);

        // t.start() invoca il metodo "run()"
        t.start();
    }

    @Override
    public void run() {
        // continua fino a quando il quadrato è raggiunge il traguardo
        while((square.getXCoo() + Square.SIZE) < DIM_RACE) {
            // ogni 25 cicli imposto una velocità random
            if (speedCounter%25 == 0)
                speed = (int)(speed*0.5 + (Math.random()*3 + 1)*0.5);
            
            square.setXCoo(square.getXCoo() + speed);
            speedCounter++;
            try {
                Thread.sleep(25);
            } catch (Exception e) { }
            race.repaint();
        }

        // final position
        position = race.getPosition();
        race.checkArrivals();
    }

    public int getPosition() {
        return position;
    }

    
}
