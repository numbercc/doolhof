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
public class Speler implements MaakKopie{

    private Tegel locatie;
    private int score = 0;
    private double stappen = 0;
    private Wapen wapen;
    private Bazooka bazooka;
    private Pistool pistool;
    private Richting richting;
    private Mount mount;
    //private Upgrade upgrade;

    public Speler() {
        richting = Richting.left;
        bazooka = new Bazooka();
        bazooka.setSpeler(this);
        pistool = new Pistool();
        pistool.setSpeler(this);

    }

    public Tegel getLocatie() {
        return locatie;
    }

    public Wapen getWapen() {
        return wapen;
    }

    public Mount getMount() {
        return mount;
    }

    public void setWapen(Wapen wapen) {
        this.wapen = wapen;
    }

    public void setMount(Mount mount) {
        this.mount = mount;
    }

    public void setLocatie(Tegel Locatie) {
        this.locatie = Locatie;
    }

    public void move(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_W) {
            moveUp();

        } else if (ke.getKeyCode() == KeyEvent.VK_A) {

            moveLeft();

        } else if (ke.getKeyCode() == KeyEvent.VK_S) {

            moveDown();

        } else if (ke.getKeyCode() == KeyEvent.VK_D) {

            moveRight();

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
        g.setColor(Color.LIGHT_GRAY);
        locatie.setTegelKleur(Color.LIGHT_GRAY);
    }

    public void schieten(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_SPACE) {
            if (getWapen() != null) {
                getWapen().schieten();
            }
        }
    }

    public void switchWaepon(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_E) {
            if (getWapen() != bazooka) {
                setWapen(bazooka);
            } else {
                setWapen(pistool);
            }
        }
    }
    //Aanpassingen 3-6-2015 17:32   wapen --> upgrade

    private void PickUpUpgrade() {
        locatie.getUpgrade().wordOpgepakt(this);
        locatie.setUpgrade(null);
    }

    public void moveUp() {
        int som;

        if (getRichting() == Richting.up && locatie.getNorth() == null) {
            if (locatie.getNorthBuur().getPersoon() == null) {
                locatie.setSpeler(null);
                locatie.getNorthBuur().setSpeler(this);
                locatie = locatie.getNorthBuur();
                if (getMount() != null) {
                    getMount().wordOpgepakt(this);
                } else {
                    score = score + 1;
                    stappen = stappen + 1;
                }

                if (locatie.getUpgrade() != null) {
                    PickUpUpgrade();
                }
            } else {
                locatie.getNorthBuur().getPersoon().wordGeraakt(this);
            }
        }
        setRichting(Richting.up);
        showVision();

    }

    public void moveLeft() {
        int som;

        if (getRichting() == Richting.left && locatie.getWest() == null) {
            if (locatie.getWestBuur().getPersoon() == null) {
                locatie.setSpeler(null);
                locatie.getWestBuur().setSpeler(this);
                locatie = locatie.getWestBuur();
                if (getMount() != null) {
                    getMount().wordOpgepakt(this);
                } else {
                    score = score + 1;
                    stappen++;
                }
                if (locatie.getUpgrade() != null) {
                    PickUpUpgrade();
                }
            } else {
                locatie.getWestBuur().getPersoon().wordGeraakt(this);
            }
        }
        setRichting(Richting.left);
        showVision();
    }

    public void moveDown() {
        int som;

        if (getRichting() == Richting.down && locatie.getSouth() == null) {
            if (locatie.getSouthBuur().getPersoon() == null) {
                locatie.setSpeler(null);
                locatie.getSouthBuur().setSpeler(this);
                locatie = locatie.getSouthBuur();
                if (getMount() != null) {
                    getMount().wordOpgepakt(this);

                } else {
                    score = score + 1;
                    stappen++;
                }
                if (locatie.getUpgrade() != null) {
                    PickUpUpgrade();
                }
            } else {
                locatie.getSouthBuur().getPersoon().wordGeraakt(this);
            }
        }
        setRichting(Richting.down);
        showVision();
    }

    public void moveRight() {
        int som;

        if (getRichting() == Richting.right && locatie.getEast() == null) {
            if (locatie.getEastBuur().getPersoon() == null) {

                locatie.setSpeler(null);
                locatie.getEastBuur().setSpeler(this);
                locatie = locatie.getEastBuur();
                if (getMount() != null) {
                    getMount().wordOpgepakt(this);

                } else {
                    score = score + 1;
                    stappen++;
                }
                if (locatie.getUpgrade() != null) {
                    PickUpUpgrade();
                }
            } else {
                locatie.getEastBuur().getPersoon().wordGeraakt(this);
            }
        }
        setRichting(Richting.right);
        showVision();
    }

    public void showVision() {

        Tegel secondTile = null;
        Tegel thirdTile = null;


        if (locatie.getEast() == null) {
            secondTile = locatie.getEastBuur();
            thirdTile = secondTile.getEastBuur();
            if (locatie.getEastBuur().getTegelKleur() != Color.YELLOW) {
                locatie.getEastBuur().setTegelKleur(Color.LIGHT_GRAY);
            }
            if (getRichting() == Richting.right && secondTile.getEast() == null) {
                if (secondTile.getEastBuur().getTegelKleur() != Color.YELLOW) {
                    secondTile.getEastBuur().setTegelKleur(Color.LIGHT_GRAY);
                }
                if (thirdTile.getEast() == null && thirdTile.getEastBuur().getTegelKleur() != Color.YELLOW) {
                    thirdTile.getEastBuur().setTegelKleur(Color.LIGHT_GRAY);
                }
            }
        }

        if (locatie.getWest() == null) {
            secondTile = locatie.getWestBuur();
            thirdTile = secondTile.getWestBuur();
            if (locatie.getWestBuur().getTegelKleur() != Color.YELLOW) {
                locatie.getWestBuur().setTegelKleur(Color.LIGHT_GRAY);
            }
            if (getRichting() == Richting.left && secondTile.getWest() == null) {
                if (secondTile.getWestBuur().getTegelKleur() != Color.YELLOW) {
                    secondTile.getWestBuur().setTegelKleur(Color.LIGHT_GRAY);
                }
                if (thirdTile.getWest() == null && thirdTile.getWestBuur().getTegelKleur() != Color.YELLOW) {
                    thirdTile.getWestBuur().setTegelKleur(Color.LIGHT_GRAY);

                }
            }
        }

        if (locatie.getSouth() == null) {
            secondTile = locatie.getSouthBuur();
            thirdTile = secondTile.getSouthBuur();
            if (locatie.getSouthBuur().getTegelKleur() != Color.YELLOW) {
                locatie.getSouthBuur().setTegelKleur(Color.LIGHT_GRAY);
            }
            if (getRichting() == Richting.down && secondTile.getSouth() == null) {
                if (secondTile.getSouthBuur().getTegelKleur() != Color.YELLOW) {
                    if (secondTile.getSouthBuur().getTegelKleur() != Color.YELLOW) {
                        secondTile.getSouthBuur().setTegelKleur(Color.LIGHT_GRAY);
                    }
                    if (thirdTile.getSouth() == null && thirdTile.getSouthBuur().getTegelKleur() != Color.YELLOW) {
                        thirdTile.getSouthBuur().setTegelKleur(Color.LIGHT_GRAY);
                    }
                }
            }
            if (locatie.getNorth() == null) {
                secondTile = locatie.getNorthBuur();
                thirdTile = secondTile.getNorthBuur();
                if (locatie.getNorthBuur().getTegelKleur() != Color.YELLOW) {
                    locatie.getNorthBuur().setTegelKleur(Color.LIGHT_GRAY);
                }
                if (getRichting() == Richting.up && secondTile.getNorth() == null) {
                    if (secondTile.getNorthBuur().getTegelKleur() != Color.YELLOW) {

                        secondTile.getNorthBuur().setTegelKleur(Color.LIGHT_GRAY);
                    }
                    if (thirdTile.getNorth() == null && thirdTile.getNorthBuur().getTegelKleur() != Color.YELLOW) {
                        thirdTile.getNorthBuur().setTegelKleur(Color.LIGHT_GRAY);
                    }
                }

            }

        }
    }
    

    public double getStappen() {
        return stappen;
    }

    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }

    public Bazooka getBazooka() {
        return bazooka;
    }

    public void setBazooka(Bazooka bazooka) {
        this.bazooka = bazooka;
    }

    public Pistool getPistool() {
        return pistool;
    }

    public void setPistool(Pistool pistool) {
        this.pistool = pistool;
    }

    public void setStappen(double s) {
        this.stappen = s;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Object maakKopie() {
        Speler kopie = new Speler();

        kopie.setBazooka((Bazooka) getBazooka().maakKopie());
        kopie.setPistool((Pistool) getPistool().maakKopie());
        if (getMount() != null) {
            kopie.setMount((Mount) getMount().maakKopie());
        }
        kopie.setRichting(getRichting());
        kopie.setStappen(getStappen());
        kopie.setScore(getScore());
        if (getWapen() instanceof Bazooka) {
            kopie.setWapen(kopie.getBazooka());
        } else if (getWapen() instanceof Pistool) {
            kopie.setWapen(kopie.getPistool());
        }
        return kopie;

    }
}
