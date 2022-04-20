package it.marzoli.baresi.monitor.view;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Classe contenente la rappresentazione grafica del quadrato
 */
public class Square extends JPanel {
    // dimensione del quadrato da "stampare"
    public static int SIZE = 80;
    // x coordinate
    private int xCoo;
    // y coordinate
    private int yCoo;
    // colore del quadrato
    private Color color;

    public Square(int _x, int _y, Color _color) {
        xCoo = _x;
        yCoo = _y;
        color = _color;
    }

    public void setXCoo(int _x) {
        xCoo = _x;
    }

    public int getXCoo() {
        return xCoo;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        // sto stampando un quadrato
        // xCoo e yCoo indicano la posizione sullo schermo (in pixel)
        // il primo e il secondo SIZE indicano larghezza e altezza del quadrato
        // "fillRect" mi crea un rettangono del colore settato prima
        g.fillRect(xCoo, yCoo, SIZE, SIZE);
    }
}
