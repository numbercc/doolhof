/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.util.Random;

/**
 *
 * @author Montoo
 */
public class Valsspeler extends Persoon {

    private int waarde;

    public Valsspeler(Tegel[][] tegel, int waarde) {
        this.tegel = tegel;
        this.waarde = waarde;
    }
    private Tegel[][] tegel;
    private Speler speler;

    public Tegel getLokatie() {

        return locatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.locatie = Lokatie;
    }

    public void zetSpelerTerug(Speler speler, Valsspeler vsp) {

        int oudeLocatieX = speler.getLocatie().getX();
        int oudeLocatieY = speler.getLocatie().getY();
        Tegel huidige = speler.getLocatie();


        int nieuweLocatieX = oudeLocatieX - waarde;
        int nieuweLocatieY = oudeLocatieY - waarde;

        if (nieuweLocatieX < 0) {
            nieuweLocatieX = 0;
        }

        if (nieuweLocatieY < 0) {
            nieuweLocatieY = 0;
        }

        speler.setLokatie(tegel[nieuweLocatieX][nieuweLocatieY]);
        huidige.setSpeler(null);
        
        if (huidige != speler.getLocatie()) {
            verwijderValsspeler(vsp);
        }
            
        

    }

    public void verwijderValsspeler(Valsspeler vsp) {
        vsp.locatie.setPersoon(null);
        vsp.getLokatie().setPersoon(null);

    }
}
