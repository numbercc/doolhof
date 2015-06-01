/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Chie-cheung
 */
public class Raket {

    private Tegel locatie;
    private int vluchtLeven;
    private int tegelVoortgang;
    private Richting richting;
    private Timer timer;

    public Raket(Tegel locatie) {
        this.locatie = locatie;
        vluchtLeven = 5;
        tegelVoortgang = 0;
        timer = new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // update x and y respectively 
                vlieg();
                Spel.comp.repaint();

            }
        });
        timer.start();
    }

    public Tegel getLocatie() {
        return locatie;
    }

    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }

    public void vlieg() {
        System.out.println("vlieg");
        if (vluchtLeven > 0) {
            if (tegelVoortgang < 90) {
                tegelVoortgang = tegelVoortgang + 10;

            } else {
                tegelVoortgang = 0;
                volgendeLocatie();
            }

        } else {
            locatie.setRaket(null);
            timer.stop();
        }

    }

    private void volgendeLocatie() {
        if (richting == richting.down) {
            if (locatie.getSouth()== null) {
                locatie.setRaket(null);
                locatie = locatie.getSouthBuur();
                locatie.setRaket(this);
                vluchtLeven=vluchtLeven-1;
                
            } else {
                locatie.getSouthBuur().setNorth(null);
                locatie.setRaket(null);
                //Spel.comp.repaint();
                timer.stop();
            }
        } else if (richting == richting.left) {
            if (locatie.getWest() == null) {
                locatie.setRaket(null);
                locatie = locatie.getWestBuur();
                locatie.setRaket(this);
                vluchtLeven=vluchtLeven-1;
                
            } else {
                locatie.setWest(null);
                locatie.setRaket(null);
                //Spel.comp.repaint();
                timer.stop();
            }
        } else if (richting == richting.right) {
            if (locatie.getEast()== null) {
                locatie.setRaket(null);
                locatie = locatie.getEastBuur();
                locatie.setRaket(this);
                vluchtLeven=vluchtLeven-1;
                
            } else {
                locatie.getEastBuur().setWest(null);
                locatie.setRaket(null);
                //Spel.comp.repaint();
                timer.stop();
            }
        } else if (richting == richting.up) {
            if (locatie.getNorth() == null) {
                locatie.setRaket(null);
                locatie = locatie.getNorthBuur();
                locatie.setRaket(this);
                vluchtLeven=vluchtLeven-1;
                
            } else {
                locatie.setNorth(null);
                locatie.setRaket(null);
                //Spel.comp.repaint();
                timer.stop();
            }

        }
    }

    public void setLocatie(Tegel locatie) {
        this.locatie = locatie;
    }

    public int getVluchtLeven() {
        return vluchtLeven;
    }

    public void setVluchtLeven(int vluchtLeven) {
        this.vluchtLeven = vluchtLeven;
    }

    public int getTegelVoortgang() {
        return tegelVoortgang;
    }

    public void setTegelVoortgang(int tegelVoortgang) {
        this.tegelVoortgang = tegelVoortgang;
    }

}
