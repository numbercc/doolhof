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
public class Bazooka extends Wapen {

    public Bazooka() {
    }

    @Override
    public void schieten() {
        if (super.getBullet() > 0) {
            super.minderKogels();
            Raket raket = new Raket(super.getSpeler().getLocatie());
            raket.setRichting(super.getSpeler().getRichting());
            raket.setTegelVoortgang(0);
            super.getSpeler().getLocatie().setRaket(raket);
        }
    }

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
    }

    @Override
    public void wordOpgepakt(Speler speler) {
        speler.getBazooka().setBullet(speler.getBazooka().getBullet() + 1);
        if (speler.getWapen() == null) {
            speler.setWapen(speler.getBazooka());
        }
    }

    @Override
    public Object maakKopie() {
        Wapen kopie = new Bazooka();
        kopie.setBullet(getBullet());
        kopie.setLocatie(getLocatie());
        return kopie;
    }

}
