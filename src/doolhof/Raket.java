/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private int damage=100;

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

    private void veranderLocatie(Tegel buur) {
        locatie.setRaket(null);
        locatie.teken();
        locatie = buur;
        locatie.setRaket(this);
        locatie.teken();
    }

    private void volgendeLocatie() {
        if (richting == richting.down) {
            if (locatie.getSouth() == null) {
                veranderLocatie(locatie.getSouthBuur());

            } else {

                locatie.getSouth().wordGeraakt(damage);
                locatie.setRaket(null);
                if (locatie.getSouth().getLeven() == 0) {
                    locatie.setSouth(null);
                    locatie.getSouthBuur().setNorth(null);
                    locatie.teken();
                }
                timer.stop();
            }
        } else if (richting == richting.left) {
            if (locatie.getWest() == null) {
                veranderLocatie(locatie.getWestBuur());


            } else {
                locatie.getWest().wordGeraakt(damage);
                locatie.setRaket(null);
                if (locatie.getWest().getLeven() == 0) {
                    locatie.setWest(null);
                    locatie.getWestBuur().setEast(null);
                    locatie.teken();
                }
                timer.stop();
            }
        } else if (richting == richting.right) {
            if (locatie.getEast() == null) {
                veranderLocatie(locatie.getEastBuur());


            } else {
                locatie.getEast().wordGeraakt(damage);
                locatie.setRaket(null);
                if (locatie.getEast().getLeven() == 0) {
                    locatie.setEast(null);
                    locatie.getEastBuur().setWest(null);
                    locatie.teken();
                }
                timer.stop();
            }
        } else if (richting == richting.up) {
            if (locatie.getNorth() == null) {
                veranderLocatie(locatie.getNorthBuur());


            } else {
                locatie.getNorth().wordGeraakt(damage);
                locatie.setRaket(null);
                if (locatie.getNorth().getLeven() == 0) {
                    locatie.setNorth(null);
                    locatie.getNorthBuur().setSouth(null);
                    locatie.teken();
                }
                timer.stop();
            }

        }
    }

    public void teken(int kamerGrote, int x, int y, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.ORANGE);
        g2.setStroke(new BasicStroke(2));
        double voortgang = (((double) getTegelVoortgang()) / 100) * (double) kamerGrote;
        if (getRichting() == Richting.left) {
            g2.drawLine(x+ kamerGrote - (int) voortgang, y + kamerGrote / 2, x+ kamerGrote - (int) voortgang - 2, y + kamerGrote / 2);
        } else if (getRichting() == Richting.right) {
            g2.drawLine(x + (int) voortgang, y + kamerGrote / 2, x + (int) voortgang + 2, y + kamerGrote / 2);
        } else if (getRichting() == Richting.up) {
            g2.drawLine(x + kamerGrote / 2, y + kamerGrote- (int) voortgang, x + kamerGrote / 2, y + kamerGrote- (int) voortgang - 2);

        } else if (getRichting() == Richting.down) {
            g2.drawLine(x + kamerGrote / 2, y + (int) voortgang, x + kamerGrote / 2, y + 2 + (int) voortgang);
        }
        g2.setStroke(new BasicStroke(1));
        g.setColor(Color.BLACK);
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
