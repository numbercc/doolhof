/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
import java.awt.Graphics;
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

    public void zetSpelerTerug(Speler speler, Valsspeler vsp) {

        int oudeLocatieX = speler.getLocatie().getPositieX();
        int oudeLocatieY = speler.getLocatie().getPositieY();
        Tegel huidige = speler.getLocatie();

        int nieuweLocatieX = oudeLocatieX - waarde;
        int nieuweLocatieY = oudeLocatieY - waarde;

        if (nieuweLocatieX < 0) {
            nieuweLocatieX = 0;
        }

        if (nieuweLocatieY < 0) {
            nieuweLocatieY = 0;
        }

        speler.setLocatie(tegel[nieuweLocatieX][nieuweLocatieY]);
        speler.getLocatie().setSpeler(speler);
        speler.getLocatie().teken();
        huidige.setSpeler(null);
        if (huidige != speler.getLocatie()) {
            super.verwijderpersoon();
        }

    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        if(locatie.getTegelKleur() == Color.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.RED);
        }
               
        g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
        g.setColor(Color.BLACK);
    }

    @Override
    public void wordGeraakt(Speler speler) {
        zetSpelerTerug(speler, this);
    }

    @Override
    public Persoon maakKopie(Persoon persoon) {
        Valsspeler orginele = (Valsspeler) persoon;
        Valsspeler kopie = new Valsspeler(null, orginele.waarde);
        kopie.setLocatie(orginele.getLocatie());
        return kopie;
    }
}
