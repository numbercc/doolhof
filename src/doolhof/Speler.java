/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Chie-cheung
 */
public class Speler {

    private Tegel lokatie;
    int score=0;
    private Wapen wapen;
    private Richting richting;
    private static JComponent comp;

    public Speler(JLabel score) {
        richting = Richting.left;
    }

    public Tegel getLocatie() {
        return lokatie;
    }

    public static JComponent getComp() {
        return comp;
    }

    public static void setComp(JComponent comp) {
        Speler.comp = comp;
    }

    public Wapen getWapen() {
        return wapen;
    }

    public void setWapen(Wapen wapen) {
        this.wapen = wapen;
    }

    public void setLokatie(Tegel Lokatie) {
        this.lokatie = Lokatie;
    }

    public void move(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_W) {
            if (lokatie.getNorth() == null) {
                moveUp();
            }
            setRichting(Richting.up);
        }
        if (ke.getKeyCode() == KeyEvent.VK_A) {
            if (lokatie.getWest() == null) {
                moveLeft();
            }
            setRichting(Richting.left);
        }
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            if (lokatie.getSouth() == null) {
                moveDown();
            }
            setRichting(Richting.down);
        }
        if (ke.getKeyCode() == KeyEvent.VK_D) {
            if (lokatie.getEast() == null) {
                moveRight();
            }
            setRichting(Richting.right);
        } //Hieronder Valsspeler collision
        comp.repaint();
    }

    public void schieten(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_SPACE) {
            if (getWapen() != null) {
                getWapen().schieten();
                comp.repaint();
            }
        }
    }

    private void pickUpWaepon() {
        if (wapen == null) {
            setWapen(lokatie.getWapen());
            wapen.setBullet(1);
        } else {
            wapen.setBullet(wapen.getBullet() + 1);
        }
        lokatie.setWapen(null);
        wapen.setSpeler(this);
    }

    private void moveUp() {
        int som;

        if (lokatie.getNorthBuur().getPersoon() == null) {
            lokatie.setSpeler(null);
            lokatie.getNorthBuur().setSpeler(this);
            lokatie = lokatie.getNorthBuur();
            score = score + 1;
            if (lokatie.getWapen() != null) {
                pickUpWaepon();
            } else if (lokatie.getPersoon() != null && lokatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else if (lokatie.getNorthBuur().getPersoon() instanceof Valsspeler) {
            Valsspeler vsp = (Valsspeler) lokatie.getNorthBuur().getPersoon();
            valsspelerCollision(vsp);
        }
    }

    private void moveLeft() {
        int som;

        if (lokatie.getWestBuur().getPersoon() == null) {
            lokatie.setSpeler(null);
            lokatie.getWestBuur().setSpeler(this);
            lokatie = lokatie.getWestBuur();
            score = score + 1;
            if (lokatie.getWapen() != null) {
                pickUpWaepon();
            } else if (lokatie.getPersoon() != null && lokatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else if (lokatie.getWestBuur().getPersoon() instanceof Valsspeler) {
            Valsspeler vsp = (Valsspeler) lokatie.getWestBuur().getPersoon();
            valsspelerCollision(vsp);
        }
    }

    private void moveDown() {
        int som;

        if (lokatie.getSouthBuur().getPersoon() == null) {
            lokatie.setSpeler(null);
            lokatie.getSouthBuur().setSpeler(this);
            lokatie = lokatie.getSouthBuur();
            score = score + 1;
            if (lokatie.getWapen() != null) {
                pickUpWaepon();
            } else if (lokatie.getPersoon() != null && lokatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else if (lokatie.getSouthBuur().getPersoon() instanceof Valsspeler) {
            Valsspeler vsp = (Valsspeler) lokatie.getSouthBuur().getPersoon();
            valsspelerCollision(vsp);
        }
    }

    private void moveRight() {
        int som;

        if (lokatie.getEastBuur().getPersoon() == null) {
            lokatie.setSpeler(null);
            lokatie.getEastBuur().setSpeler(this);
            lokatie = lokatie.getEastBuur();
            score = score + 1;
            if (lokatie.getWapen() != null) {
                pickUpWaepon();
            } else if (lokatie.getPersoon() != null && lokatie.getPersoon() instanceof Vriend) {
                System.out.println("Test!");
            }
        } else if (lokatie.getEastBuur().getPersoon() instanceof Valsspeler) {
            Valsspeler vsp = (Valsspeler) lokatie.getEastBuur().getPersoon();
            valsspelerCollision(vsp);
        }

    }

    private void valsspelerCollision(Valsspeler vsp) {
        Tegel temp = lokatie;
        getLocatie().setSpeler(null);
        temp.setSpeler(null);
        vsp.zetSpelerTerug(this, vsp);
        lokatie.setSpeler(this);
    }

    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }
}
