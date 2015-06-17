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

    public Valsspeler( int waarde,boolean lopen) {
        this.waarde = waarde;
        if(lopen){
            super.zetTimer();
        }
    }
    private Tegel[][] tegel;
    private Speler speler;
   private void breedteTerug(int aantalPlekken,Speler speler){
       if(speler.getLocatie().getWestBuur()!=null&& aantalPlekken>0){
           Tegel temp=speler.getLocatie();
           speler.setLocatie(speler.getLocatie().getWestBuur());
           speler.getLocatie().setSpeler(speler);
           aantalPlekken--;
           temp.setSpeler(null);
           breedteTerug(aantalPlekken, speler);
       }
   }
   private void hoogteTerug(int aantalPlekken,Speler speler){
       if(speler.getLocatie().getNorthBuur()!=null&& aantalPlekken>0){
           Tegel temp=speler.getLocatie();
           speler.setLocatie(speler.getLocatie().getNorthBuur());
           speler.getLocatie().setSpeler(speler);
           aantalPlekken--;
           
           temp.setSpeler(null);
           hoogteTerug(aantalPlekken, speler);
       }
   }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        if(super.getLocatie().getTegelKleur() == Color.BLACK) {
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
        breedteTerug(waarde, speler);
        hoogteTerug(waarde, speler);
        super.verwijderpersoon();
    }

    @Override
    public Object maakKopie() {
        Valsspeler kopie = new Valsspeler(waarde, false);
        if(super.getTimer()!=null){
            kopie.setTimer(super.getTimer());
        }
        return kopie;
    }


}
