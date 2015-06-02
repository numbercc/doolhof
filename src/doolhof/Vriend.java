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
    
    private Tegel[][] tegel;
    
  

    public void spelBeeindigen() {
        
    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        g.setColor(Color.GREEN);
            g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
    }

    @Override
    public void wordGeraakt(Speler speler) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
