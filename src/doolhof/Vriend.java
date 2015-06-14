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
    
    
  

    public void spelBeeindigen() {
        
    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        if(locatie.getTegelKleur() == Color.BLACK) {
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
        Spel spel=new Spel();
        spel.removeDoolhof();
        spel.maakLevel();
    }

    @Override
    public Persoon maakKopie(Persoon persoon) {
        Vriend orginele = (Vriend) persoon;
        Vriend kopie = new Vriend();
        kopie.setLocatie(orginele.getLocatie());
        return kopie;
    }
}
