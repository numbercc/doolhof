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
        super.setBullet(Integer.parseInt(ammo.getText()));
        this.ammo=ammo;
        
    }

    @Override
    public void schieten() {
        if (super.getBullet() > 0) {
            super.minderKogels();
            ammo.setText(""+super.getBullet());
        }
    }


}
