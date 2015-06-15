/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Graphics;

/**
 *
 * @author Montoo
 */
public abstract class Upgrade {
    
    protected Tegel locatie;
    
    public abstract void wordOpgepakt(Speler speler);
    public abstract void teken(int kamerGrote, int x, int y, Graphics g);
    public abstract Upgrade maakKopie(Upgrade upgrade);
    public Tegel getLocatie() {
        return locatie;
    }
    
    public void setLocatie(Tegel locatie) {
        this.locatie = locatie;
    }
}
