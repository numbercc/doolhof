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
    private Tegel locatie;
    public Valsspeler(Tegel[][] tegel) {
        this.tegel = tegel;
    }
    
        private Tegel locatieSpeler;
        private Tegel[][] tegel;
        private Speler speler;
    
        public Tegel getLokatie() {
            
        return locatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.locatie = Lokatie;
    }
    
    public void zetSpelerTerug(Speler speler) {
        
        int oudeLocatieX = speler.getLocatie().getX();
        int oudeLocatieY = speler.getLocatie().getY();
        Tegel huidige=speler.getLocatie();
        Random r = new Random();
        int randomX = r.nextInt(5);
        int randomY = r.nextInt(5);
        
        //int nieuweLocatieX = oudeLocatieX - 5;
       // int nieuweLocatieY = oudeLocatieY - 5;
        speler.setLokatie(tegel[0][0]);
        huidige.setSpeler(null);
        
    }
    
}
