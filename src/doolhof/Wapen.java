/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Graphics;

/**
 *
 * @author Chie-cheung
 */
public abstract class Wapen {
    private int bullet=0;
    private Speler speler;
    public abstract void  schieten();
    public abstract void teken(int kamerGrote, int x, int y, Graphics g);
    public void minderKogels(){
        bullet=bullet-1;
    }
    public int getBullet() {
        return bullet;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
    
}
