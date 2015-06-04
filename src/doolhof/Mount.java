/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Montoo
 */
public class Mount extends Upgrade {

    private double waarde;
    private int stapWaarde;

    public Mount(int w, int s) {
        this.waarde = w;
        this.stapWaarde = s;
    }

    public double getWaarde() {
        return waarde;
    }

    public int getStapWaarde() {
        return stapWaarde;
    }

    public void vermoeideMount(Speler speler) {

        if (stapWaarde > 0) {
            stapWaarde--;
        } else {
            speler.setMount(null);
        }
    }

    @Override
    public void wordOpgepakt(Speler speler) {
        if (speler.getMount() == null) {
            speler.setMount(this);
        } else {
            double stappen = speler.getStappen();
            stappen = stappen + (1 / this.waarde);
            speler.setStappen(stappen);
            vermoeideMount(speler);
        }
    }

    public void teken(int kamerGrote, int x, int y, Graphics g) {
        g.setColor(Color.magenta);
        g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
        g.setColor(Color.BLACK);
    }

}
