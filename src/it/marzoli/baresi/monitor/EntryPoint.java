package it.marzoli.baresi.monitor;

import it.marzoli.baresi.monitor.view.Race;

/**
 * Entry Point
 * "punto di partenza" del gioco
 */
public class EntryPoint {
    public static void main(String[] args) {
        // creo una nuova pista
        Race race = new Race();
        // avvio la gara
        race.startRace();
    }
}
