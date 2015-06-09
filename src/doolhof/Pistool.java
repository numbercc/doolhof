/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Graphics;

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
        if(super.getBullet()>0){
        richting=super.getSpeler().getRichting();
        pistoolSchieten(super.getSpeler().getLocatie());
        super.minderKogels();
        }
    }

    public void pistoolSchieten(Tegel locatie) {
        if(richting==Richting.up){
            if(locatie.getPersoon()==null&&locatie.getNorth()==null){
                pistoolSchieten(locatie.getNorthBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
        else if(richting==Richting.left&&locatie.getWest()==null){
            if(locatie.getPersoon()==null){
                pistoolSchieten(locatie.getWestBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
        else if(richting==Richting.right&&locatie.getEast()==null){
            if(locatie.getPersoon()==null){
                pistoolSchieten(locatie.getEastBuur());
            }
            else{
                locatie.setPersoon(null);
            }
        }
        else if(richting==Richting.down){
            if(locatie.getPersoon()==null&&locatie.getSouth()==null){
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


    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
    }

    @Override
    public void wordOpgepakt(Speler speler) {
        speler.getPistool().setBullet(speler.getPistool().getBullet()+1);
        if(speler.getWapen()==null){
            speler.setWapen(speler.getPistool());
        }
    }

}
