/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Raymond
 */
public class Vriend extends Persoon{
    
    private Tegel[][] tegel;
    
    
    public Vriend(Tegel[][] tegel) {
        this.tegel = tegel;
    }
    public Tegel getLokatie() {

        return locatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.locatie = Lokatie;
    }
    public void spelBeeindigen() {
        
    }
}
