/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

/**
 *
 * @author Chie-cheung
 */
public class Speler {

    private Tegel locatie;
    int score = 0;
    private Wapen wapen;
    private Richting richting;

    public Speler(JLabel score) {
        richting = Richting.left;
    }

    public Tegel getLocatie() {
        return locatie;
    }


    public Wapen getWapen() {
        return wapen;
    }

    public void setWapen(Wapen wapen) {
        this.wapen = wapen;
    }

    public void setLocatie(Tegel Locatie) {
        this.locatie = Locatie;
    }

    public void move(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_W) {
            if (locatie.getNorth() == null) {
                moveUp();
            }
            setRichting(Richting.up);
        }
        else if (ke.getKeyCode() == KeyEvent.VK_A) {
            if (locatie.getWest() == null) {
                moveLeft();
            }
            setRichting(Richting.left);
        }
        else if (ke.getKeyCode() == KeyEvent.VK_S) {
            if (locatie.getSouth() == null) {
                moveDown();
            }
            setRichting(Richting.down);
        }
        else if (ke.getKeyCode() == KeyEvent.VK_D) {
            if (locatie.getEast() == null) {
                moveRight();
            }
            setRichting(Richting.right);
        } //Hieronder Valsspeler collision
    }

    public void teken(int kamerGrote, int x, int y, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.blue);
        g.fillOval(x - 2 + kamerGrote / 8, y - 2 + kamerGrote / 8, kamerGrote, kamerGrote);
        g.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        if (getRichting() == Richting.left) {
            g.drawLine(x + kamerGrote / 8, y + kamerGrote / 2, x + kamerGrote / 2, y + kamerGrote / 2);
        } else if (getRichting() == Richting.right) {
            g.drawLine(x + kamerGrote / 2, y + kamerGrote / 2, x - 2 + kamerGrote, y + kamerGrote / 2);
        } else if (getRichting() == Richting.up) {
            g.drawLine(x + kamerGrote / 2, y + kamerGrote / 2, x + kamerGrote / 2, y + 2);
        } else if (getRichting() == Richting.down) {
            g.drawLine(x + kamerGrote / 2, y + kamerGrote / 2, x + kamerGrote / 2, y + kamerGrote);
        }
        g2.setStroke(new BasicStroke(1));
        g.setColor(Color.BLACK);
    }

    public void schieten(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_SPACE) {
            if (getWapen() != null) {
                getWapen().schieten();
            }
        }
    }

    private void pickUpWaepon() {
        if (wapen == null) {
            setWapen(locatie.getWapen());
            wapen.setBullet(1);
        } else {
            wapen.setBullet(wapen.getBullet() + 1);
        }
        locatie.setWapen(null);
        wapen.setSpeler(this);
    }

    private void moveUp() {
        int som;

        if (locatie.getNorthBuur().getPersoon() == null) {
            locatie.setSpeler(null);
            locatie.getNorthBuur().setSpeler(this);
            locatie = locatie.getNorthBuur();
            score = score + 1;
            if (locatie.getWapen() != null) {
                pickUpWaepon();
            } else if (locatie.getPersoon() != null && locatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else{
            locatie.getNorthBuur().getPersoon().wordGeraakt(this);
        }
    }

    private void moveLeft() {
        int som;

        if (locatie.getWestBuur().getPersoon() == null) {
            locatie.setSpeler(null);
            locatie.getWestBuur().setSpeler(this);
            locatie = locatie.getWestBuur();
            score = score + 1;
            if (locatie.getWapen() != null) {
                pickUpWaepon();
            } else if (locatie.getPersoon() != null && locatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else{
            locatie.getWestBuur().getPersoon().wordGeraakt(this);
        }
    }

    private void moveDown() {
        int som;

        if (locatie.getSouthBuur().getPersoon() == null) {
            locatie.setSpeler(null);
            locatie.getSouthBuur().setSpeler(this);
            locatie = locatie.getSouthBuur();
            score = score + 1;
            if (locatie.getWapen() != null) {
                pickUpWaepon();
            } else if (locatie.getPersoon() != null && locatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else{
            locatie.getSouthBuur().getPersoon().wordGeraakt(this);
        }
    }

    private void moveRight() {
        int som;

        if (locatie.getEastBuur().getPersoon() == null) {
            locatie.setSpeler(null);
            locatie.getEastBuur().setSpeler(this);
            locatie = locatie.getEastBuur();
            score = score + 1;
            if (locatie.getWapen() != null) {
                pickUpWaepon();
            } else if (locatie.getPersoon() != null && locatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else{
            locatie.getEastBuur().getPersoon().wordGeraakt(this);
        }

    }


    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }
}
