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
    
        private Tegel locatieSpeler;
        private Tegel[][] tegel;
        private Speler speler;
    
        public Tegel getLokatie() {
            
        return locatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.locatie = Lokatie;
    }
    
    public Tegel zetSpelerTerug() {
        
        int oudeLocatieX = locatieSpeler.getSpeler().getLokatie().getX();
        int oudeLocatieY = locatieSpeler.getSpeler().getLokatie().getY();
        
        Random r = new Random();
        int randomX = r.nextInt(5);
        int randomY = r.nextInt(5);
        
        int nieuweLocatieX = oudeLocatieX - 5;
        int nieuweLocatieY = oudeLocatieY - 5;
        
        locatieSpeler = tegel[nieuweLocatieX][nieuweLocatieY];
        
        return locatieSpeler;
        
    }
    
}
