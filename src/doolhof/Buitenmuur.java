/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public class Buitenmuur extends Muur{

    public Buitenmuur(Tegel currentRoom) {
        super(currentRoom);
    }

    @Override
    public void wordGeraakt(int damage) {
        super.setLeven(super.getLeven()-0);
    }

    @Override
    public Muur maakKopie(Muur muur) {
        return new Buitenmuur(null);
    }
    
}
