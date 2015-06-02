/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import javax.swing.JLabel;

/**
 *
 * @author Raymond
 */
public class Vriend extends Persoon{
    
    private Tegel[][] tegel;
    private Spel spel = new Spel();
    private Tegel spelerTegel;
    
    
    public Vriend(Tegel[][] tegel) {
        this.tegel = tegel;
    }
    public Tegel getLokatie() {

        return locatie;
        
    }

    public void setLokatie(Tegel Lokatie) {
        this.locatie = Lokatie;
    }
    public void spelBeeindigen(Tegel sp) {
        JLabel ammo = new JLabel("0");
        this.spelerTegel = sp;
        Speler speler = new Speler(ammo);
        Doolhof doolhof = new Doolhof(speler, ammo);
        
        spel.removeDoolhof();
        spel.generateGame(speler, doolhof);
        
        
    }
}
