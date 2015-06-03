/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Montoo
 */
public abstract class Upgrade {
    
    private Tegel locatie;
    
    public abstract void wordOpgepakt(Speler speler);
    
    public Tegel getLocatie() {
        return locatie;
    }
    
    public void setLocatie(Tegel locatie) {
        this.locatie = locatie;
    }
}
