/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author Chie-cheung
 */
public class Pistool extends Wapen {

    private Richting richting;
    private Tegel locatie;

    public Pistool() {

    }

    @Override
    public void schieten() {
        pistoolSchieten(this.locatie);
        super.minderKogels();
    }

    public void pistoolSchieten(Tegel locatie) {
        if(richting==Richting.up){
            if(locatie.getPersoon()==null){
                pistoolSchieten(locatie.getNorthBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
        else if(richting==Richting.left){
            if(locatie.getPersoon()==null){
                pistoolSchieten(locatie.getWestBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
        else if(richting==Richting.right){
            if(locatie.getPersoon()==null){
                pistoolSchieten(locatie.getEastBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
        else if(richting==Richting.down){
            if(locatie.getPersoon()==null){
                pistoolSchieten(locatie.getSouthBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
    }

    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }

    public Tegel getLocatie() {
        return locatie;
    }

    public void setLocatie(Tegel locatie) {
        this.locatie = locatie;
    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
