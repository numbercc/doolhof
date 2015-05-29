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
public class Bazooka extends Wapen {
private JLabel ammo;

    public Bazooka(JLabel ammo) {
        this.ammo=ammo;
        
    }

    @Override
    public void schieten() {
        if (super.getBullet() > 0) {
            super.minderKogels();
            ammo.setText(""+super.getBullet());
            Raket raket=new Raket(super.getSpeler().getLocatie());
            raket.setRichting(super.getSpeler().getRichting());
            raket.setTegelVoortgang(60);
            super.getSpeler().getLocatie().setRaket(raket);
        }
    }

    @Override
    public void updateScore() {
        ammo.setText(""+super.getBullet());
    }


}
