package it.marzoli.baresi.monitor.view;

import java.awt.*;
import javax.swing.*;

/**
 * stampo il background, ovvero la pista di sfondo
 */
public class Background extends JPanel {
    @Override
    public void paint(Graphics g) {
        // imposto il colore a verde e disegno un rettangolo grande
        // come la finestra che corrisponde alla pista
        g.setColor(Color.green);
        g.fillRect(0, 0, 1000, 645);

        g.setColor(Color.white);
        // disegno le 6 righe orizzontali bianche che separano le corsie
        // 6 è il numero di quadrati
        for(int i=0; i<6; i++)
            g.fillRect(0, 100*i, 1000, 10);
        
        // stampo le 3 righe verticali bianche che corrispondono al traguardo
        for(int i=0; i<3; i++)
            g.fillRect(960 + i*10, 0, 5, 645);
    }
}
