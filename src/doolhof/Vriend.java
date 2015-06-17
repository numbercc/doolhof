/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Raymond
 */
public class Vriend extends Persoon{
    private Spel spel;
    public Vriend(Spel spel) {
        this.spel=spel;
    }
    
    
  

    public void spelBeeindigen() {
        
    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        if(super.getLocatie().getTegelKleur() == Color.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.GREEN);
        }
            g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
            g.setColor(Color.BLACK);
    }

    @Override
    public void wordGeraakt(Speler speler) {
        super.verwijderpersoon();
        spel.removeComp();
        spel.maakLevel();
    }

    @Override
    public Object maakKopie( ) {

        Vriend kopie = new Vriend(spel);
        return kopie;
    }
}
