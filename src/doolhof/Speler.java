/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public class Speler {

    private Tegel lokatie;

    public Speler() {
    }

    public Tegel getLokatie() {
        return lokatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.lokatie = Lokatie;
    }

    public void move(String richting) {
        if (richting.equals("w") && lokatie.getNorth().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getNorthBuur().setSpeler(this);
            lokatie=lokatie.getNorthBuur();
        }
        if (richting.equals("a") && lokatie.getWest().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getWestBuur().setSpeler(this);
            lokatie=lokatie.getWestBuur();
        }
        if (richting.equals("s") && lokatie.getSouthBuur().getNorth().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getSouthBuur().setSpeler(this);
            lokatie=lokatie.getSouthBuur();
        }
        if (richting.equals("d") && lokatie.getEastBuur().getWest().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getEastBuur().setSpeler(this);
            lokatie=lokatie.getEastBuur();
        }
    }
}
