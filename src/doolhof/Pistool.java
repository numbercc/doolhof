/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import javax.swing.JLabel;

/**
 *
 * @author Chie-cheung
 */
public class Pistool extends Wapen {

    private JLabel ammo;
    private Richting richting;
    private Tegel locatie;

    public Pistool(JLabel ammo) {
        this.ammo = ammo;

    }

    @Override
    public void updateScore() {
        ammo.setText("" + super.getBullet());
    }

    @Override
    public void schieten() {
        pistoolSchieten(this.locatie);
    }

    public void pistoolSchieten(Tegel locatie) {
        if(richting==Richting.up){
          //  if(locatie.get()==null){
                
            //}
        }

    }

//    public Richting getRichting() {
//        return richting;
//    }
//
//    public void setRichting(Richting richting) {
//        this.richting = richting;
//    }
//
//    public Tegel getLocatie() {
//        return locatie;
//    }
//
//    public void setLocatie(Tegel locatie) {
//        this.locatie = locatie;
//    }

}
