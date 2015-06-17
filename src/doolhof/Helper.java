/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 *
 * @author Montoo
 */
public class Helper extends Persoon {

    private Vriend eind;

    public Helper( Vriend vriendLocatie, boolean lopen) {
        eind = vriendLocatie;
        
        if(lopen){
            super.zetTimer();
        }
    }

    public Vriend getEind() {
        return eind;
    }

    public void setEind(Vriend eind) {
        this.eind = eind;
    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        if(super.getLocatie().getTegelKleur() == Color.BLACK) {
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.ORANGE);
        }
        g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
        g.setColor(Color.BLACK);
    }

    @Override
    public void wordGeraakt(Speler speler) {
        ArrayList<Tegel> kortsteRoute = super.kortsteRoute(getLocatie(),eind.getLocatie());
        if(kortsteRoute!=null){
            routeKleuren(kortsteRoute);
        }
        super.verwijderpersoon();
    }

    
    public void routeKleuren(ArrayList<Tegel> route) {
        for (Tegel tegel : route) {
            tegel.setTegelKleur(Color.yellow);
        }
    }

    @Override
    public Object maakKopie() {
        Helper kopie = new Helper(null,false );
        return kopie;
    }

}
